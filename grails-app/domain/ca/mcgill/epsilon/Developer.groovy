package ca.mcgill.epsilon

class Developer {

  // defines a many-to-many relationship between Developer and Task
  static hasMany = [ tasks:Task ]

  String fullName
  String workEmail

  static constraints = {
  }
}
