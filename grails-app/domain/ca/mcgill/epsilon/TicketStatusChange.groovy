package ca.mcgill.epsilon

class TicketStatusChange implements Comparable {

  static belongsTo = Ticket

  TicketStatus status

  // Timestamp by hand instead of relying on Grails' autostamping feature.
  Date dateCreated = new Date()

  int compareTo (other) {
    this.dateCreated <=> other.dateCreated
  }

  static constraints = {
  }
}
