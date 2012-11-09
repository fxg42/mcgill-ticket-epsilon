package ca.mcgill.epsilon

class TicketStatusChange implements Comparable {

  static belongsTo = Ticket

  TicketStatus status
  Date dateCreated

  int compareTo (other) {
    other.dateCreated <=> this.dateCreated
  }

  static constraints = {
  }
}
