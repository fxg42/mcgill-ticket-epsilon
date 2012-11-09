package ca.mcgill.epsilon

import static org.junit.Assert.*
import org.junit.*

class TaskControllerTests {

  def saveOptions = [ flush:true, failOnError:true ]
  def controller, ticket

  @Before void setup () {
    controller = new TaskController()
    def bug = new TicketType(key:'BUG', description:'Bug').save(flush:true)
    ticket = new Ticket(summary:'test summary', description:'test description', type:bug, priority:3).save(saveOptions)
  }

  // Must be an integration test because TicketService uses HQL...
  @Test void create_should_fail_is_no_ticket () {
    assert ! controller.createFromTicket(1000)
    assert controller.createFromTicket(ticket.id)
  }
}
