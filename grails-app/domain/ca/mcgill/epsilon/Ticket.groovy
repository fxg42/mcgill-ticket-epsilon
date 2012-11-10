package ca.mcgill.epsilon

class Ticket {

  static hasMany = [ progress:TicketStatusChange ]
  static hasOne = [ attachment:Attachment ]

  User commissioner
  String summary
  String description
  TicketType type
  Integer priority

  // automatically initialize when first saved to the database
  Date dateCreated

  def beforeInsert () {
    addToProgress(status:TicketStatus.findByKey('PENDING'))
  }

  static constraints = {
    summary blank:false
    description blank:false
    priority range:1..5
    attachment unique:true, nullable:true
  }
}
