package ca.mcgill.epsilon

class TicketStatus {
  String key
  String description

  static constraints = {
    key blank:false
    description blank:false
  }
}
