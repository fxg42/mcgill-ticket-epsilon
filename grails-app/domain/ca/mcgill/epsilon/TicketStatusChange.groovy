package ca.mcgill.epsilon

class TicketStatusChange {

  static belongsTo = Ticket

  TicketStatus status
  Date dateCreated

  static constraints = {
  }
}
