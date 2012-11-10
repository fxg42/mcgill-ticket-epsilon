package ca.mcgill.epsilon

import static org.junit.Assert.*
import org.junit.*

class TaskServiceTests {

  def service, bug, dave, user, ticket

  @Before void setup () {
    service = new TaskService()
    bug = TicketType.findByKey('BUG')
    dave = Developer.findByFullName('Dave')
    user = User.findByUsername('user')
    ticket = new Ticket(summary:'a summary', description:'a description', type:bug, priority:3, commissioner:user).save()
  }

  @Test void should_add_progress_item_to_original_ticket () {
    def task = new Task(originalTicket:ticket)
    task.addToResponsibles(dave)
    service.assignAndSave(task)

    def found = Task.get(task.id)

    assert found == task
    assert found.originalTicket.progress.size() == 2
  }

  @Test void find_all_to_notify_should_not_return_newly_created () {
    def task = new Task(originalTicket:ticket)
    task.save()

    def found = service.findAllAssignedButNotNotified()
    assert ! found
  }

  @Test void find_all_to_notify_should_not_return_if_notified () {
    def task = new Task(originalTicket:ticket)
    service.assignAndSave(task)
    task.notificationSent = true
    task.save()

    def found = service.findAllAssignedButNotNotified()
    assert ! found
  }

  @Test void find_all_to_notify_should_return_if_assigned_and_not_notified () {
    def task = new Task(originalTicket:ticket)
    service.assignAndSave(task)

    def found = service.findAllAssignedButNotNotified()
    assert found.first() == task
  }
}
