package ca.mcgill.epsilon

import org.junit.*

class TicketTests {

  def saveOptions = [ flush:true, failOnError:true ]
  def bug, user

  @Before void setup () {
    bug = TicketType.findByKey('BUG')
    user = User.findByUsername('user')
  }

  @Test void should_be_able_to_save () {
    def aSummary = 'test summary'
    def aDescription = 'test description'
    def ticket = new Ticket(summary:aSummary, description:aDescription, type:bug, priority:3, commissioner:user)

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
    def ticket = new Ticket(summary:'', description:'', type:bug, priority:6, commissioner:user)
    def wasSaved = ticket.save(flush:true)

    assert ! wasSaved
    assert ticket.errors.errorCount == 3
  }

  @Test void should_add_a_status_change_before_save () {
    def ticket = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user)
    def wasSaved = ticket.save(flush:true)

    assert wasSaved

    assert ticket.progress.size() == 1
    assert 'PENDING' in ticket.progress*.status*.key
  }

  @Test void should_sort_status_changes () {
    def assigned = TicketStatus.findByKey('ASSIGNED')
    def inProgress = TicketStatus.findByKey('IN_PROGRESS')

    def ticket = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user)
    ticket.save(flush:true)

    ticket.addToProgress(status:assigned).save(flush:true)
    ticket.addToProgress(status:inProgress).save(flush:true)

    def found = Ticket.get(ticket.id)

    def progress = found.progress
    assert progress.size() == 3
    assert 'PENDING' == progress.first().status.key
    assert 'IN_PROGRESS' == progress.last().status.key
  }
}
