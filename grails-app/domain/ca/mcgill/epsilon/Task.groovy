package ca.mcgill.epsilon

class Task {

  // defines a many-to-many relationship between Developer and Task.
  static hasMany = [ responsibles:Developer ]
  static belongsTo = Developer

  // defines a unidirectional many-to-one relationship between Ticket and Task.
  Ticket originalTicket

  // automatically initialize when first saved to the database.
  Date dateCreated

  static constraints = {
  }
}
