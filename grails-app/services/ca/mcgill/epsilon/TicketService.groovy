package ca.mcgill.epsilon

class TicketService {

  def save (ticket) {
    ticket.save(failOnError:true)
  }
}
