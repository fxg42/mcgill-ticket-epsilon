package ca.mcgill.epsilon

class TicketController {

  def ticketService

  def pending () {
    [ tickets:ticketService.findAllPending() ]
  }

  def create () {
    [ ticket:new Ticket() ]
  }

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
