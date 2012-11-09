package ca.mcgill.epsilon

import grails.test.mixin.*
import org.junit.*

@TestFor(TicketService)
@Mock([Ticket, TicketType])
class TicketServiceTests {

  def ticket

  @Before void setup () {
    def bug = new TicketType(key:'BUG', description:'Bug').save()
    ticket = new Ticket(summary:'a summary', description:'a description', type:bug, priority:3)
  }
  
  @Test void save_should_return_saved_ticket () {
    def savedTicket = service.save(ticket)
    assert savedTicket.id
  }

  @Test(expected = grails.validation.ValidationException)
  void save_should_throw_exception_when_invalid () {
    ticket.summary = ''
    service.save(ticket)
  }
}
