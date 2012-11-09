package ca.mcgill.epsilon

import org.junit.*

class TicketTests {

  def saveOptions = [ flush:true, failOnError:true ]
  def bug

  @Before void setup () {
    bug = new TicketType(key:'BUG', description:'A bug is a bug').save(saveOptions)
  }

  @Test void should_be_able_to_save () {
    def aSummary = 'test summary'
    def aDescription = 'test description'
    def ticket = new Ticket(summary:aSummary, description:aDescription, type:bug, priority:3)

    def wasSaved = ticket.save(saveOptions)

    assert wasSaved
    assert ticket.id != null
    assert ticket.version != null
    assert ticket.dateCreated != null

    def found = Ticket.findBySummary(aSummary)

    assert found
    assert found.summary == aSummary
    assert found.description == aDescription
    assert found.id == ticket.id
  }

  @Test void should_validate_properties () {
    def ticket = new Ticket(summary:'', description:'', type:bug, priority:6)
    def wasSaved = ticket.save(flush:true)

    assert ! wasSaved
    assert ticket.errors.errorCount == 3
  }
}
