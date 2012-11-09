package ca.mcgill.epsilon

class Attachment {

  static belongsTo = [ ticket:Ticket ]

  byte[] bytes
  String contentType
  String originalFilename

  static constraints = {
    bytes maxSize:(1024 * 1024 * 2) // limit file size to 2MB.
  }
}
