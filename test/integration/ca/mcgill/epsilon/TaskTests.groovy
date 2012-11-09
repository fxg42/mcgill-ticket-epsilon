package ca.mcgill.epsilon

import org.junit.*

class TaskTests {

  def saveOptions = [ flush:true, failOnError:true ]
  def ticket, bug, dave

  // This method is ran before each `@Test` method.
  @Before void setup () {
    bug = new TicketType(key:'BUG', description:'A bug is a bug').save(saveOptions)
    ticket = new Ticket(summary:'test summary', description:'test description', type:bug, priority:3).save(saveOptions)
    dave = new Developer(fullName:'Dave', workEmail:'dave@mcgill.ca').save(saveOptions)
  }

  @Test void should_be_able_to_save () {
    def task = new Task(originalTicket:ticket)

    def wasSaved = task.save(saveOptions)

    assert wasSaved
    assert task.id != null
    assert task.dateCreated != null

    def found = Task.findByOriginalTicket(ticket)

    assert found
    assert found.id == task.id
    assert found.originalTicket.id == ticket.id
  }

  @Test void should_be_able_to_get_assigned_developers () {
    def task = new Task(originalTicket:ticket)
    task.addToResponsibles(dave)
    task.save(saveOptions)

    // For more HQL examples see:
    // http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html/queryhql.html#queryhql-examples
    def found = Task.find("from Task as task where :search member of task.responsibles", [search: dave])

    assert found
    assert found.originalTicket == ticket
    assert dave in found.responsibles
  }
}
