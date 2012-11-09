package ca.mcgill.epsilon

class TicketService {

  def save (ticket) {
    ticket.save(failOnError:true)
  }

  def findAllPending () {
    Ticket.findAll("from Ticket as ticket where ticket.progress.size = 1 order by ticket.dateCreated")
  }

  def getPending (id) {
    Ticket.find("from Ticket as ticket where ticket.id = :id and ticket.progress.size = 1", [id:id])
  }
}
