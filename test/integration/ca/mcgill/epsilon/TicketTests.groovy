package ca.mcgill.epsilon

import org.junit.*

class TicketTests {

  @Test void should_be_able_to_save () {
    def aSummary = 'test summary'
    def aDescription = 'test description'
    def ticket = new Ticket(summary:aSummary, description:aDescription)

    def wasSaved = ticket.save(flush:true, failOnError:true)

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
}
