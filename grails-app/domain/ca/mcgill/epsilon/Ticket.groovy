package ca.mcgill.epsilon

class Ticket {

  String summary
  String description
  TicketType type
  Integer priority

  // automatically initialize when first saved to the database
  Date dateCreated

  static constraints = {
    summary blank:false
    description blank:false
    priority range:1..5
  }
}
