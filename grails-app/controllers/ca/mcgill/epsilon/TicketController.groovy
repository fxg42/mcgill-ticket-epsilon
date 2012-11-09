package ca.mcgill.epsilon

import grails.plugins.springsecurity.*

class TicketController {

  def ticketService

  @Secured(['ROLE_USER', 'ROLE_MANAGER'])
  def pending () {
    [ tickets:ticketService.findAllPending() ]
  }

  @Secured(['ROLE_USER', 'ROLE_MANAGER'])
  def create () {
    [ ticket:new Ticket() ]
  }

  @Secured(['ROLE_USER', 'ROLE_MANAGER'])
  def save () {
    def ticket = bindData(new Ticket(), params, [include:['summary', 'description', 'priority', 'type']])
    if (params.attachment?.originalFilename) {
      ticket.attachment = new Attachment(params.attachment.properties)
    }
    try {
      ticketService.save(ticket)
      redirect(action: 'pending')
    } catch (e) {
      log.error("Error: ${e.message}", e)
      render(view: 'create', model: [ ticket:ticket ])
    }
  }
}
