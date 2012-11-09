package ca.mcgill.epsilon

import static org.junit.Assert.*
import org.junit.*

class TaskServiceTests {

  def service, bug, dave, ticket

  @Before void setup () {
    service = new TaskService()
    bug = TicketType.findByKey('BUG')
    dave = Developer.findByFullName('Dave')
    ticket = new Ticket(summary:'a summary', description:'a description', type:bug, priority:3).save()
  }

  @Test void should_add_progress_item_to_original_ticket () {
    def task = new Task(originalTicket:ticket)
    task.addToResponsibles(dave)
    service.assignAndSave(task)

    def found = Task.get(task.id)

    assert found == task
    assert found.originalTicket.progress.size() == 2
  }
}
