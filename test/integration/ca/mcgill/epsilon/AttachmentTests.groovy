package ca.mcgill.epsilon

import org.junit.*

class AttachmentTests {

  def saveOptions = [ flush:true, failOnError:true ]
  def bug, ticket

  @Before void setup () {
    bug = new TicketType(key:'BUG', description:'A bug is a bug').save(saveOptions)
    ticket = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3)
  }

  @Test void should_save_attachments () {
    def file = new File("./test/integration/ca/mcgill/epsilon/testfile.pdf")
    def attachment = new Attachment(bytes:file.bytes, contentType:'application/pdf', originalFilename:'testfile.pdf')
    ticket.attachment = attachment
    def wasSaved = ticket.save(saveOptions)

    assert wasSaved
  }
}
