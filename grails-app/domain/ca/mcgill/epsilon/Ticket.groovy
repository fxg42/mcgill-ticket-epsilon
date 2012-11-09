package ca.mcgill.epsilon

class Ticket {

  String summary
  String description

  // automatically initialize when first saved to the database
  Date dateCreated

  static constraints = {
  }
}
