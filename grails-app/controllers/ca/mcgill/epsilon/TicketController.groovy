package ca.mcgill.epsilon

class TicketController {

  def ticketService

  def create () {
    [ ticket:new Ticket() ]
  }

  def save () {
    def ticket = bindData(new Ticket(), params, [include:['summary', 'description', 'priority', 'type']])
    if (params.attachment?.originalFilename) {
      ticket.attachment = new Attachment(params.attachment.properties)
    }
    try {
      def savedTicket = ticketService.save(ticket)
      render('save successful')
    } catch (e) {
      render( view:'create', model: [ ticket:ticket ] )
    }
  }
}
