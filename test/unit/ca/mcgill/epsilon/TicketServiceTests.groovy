package ca.mcgill.epsilon

import grails.test.mixin.*
import org.junit.*

@TestFor(TicketService)
@Mock([Ticket, TicketType, User])
class TicketServiceTests {

  def ticket

  @Before void setup () {
    def bug = new TicketType(key:'BUG', description:'Bug').save()
    def user = new User(username:'user', password:'s3cret', enabled:true, accountExpired:false, accountLocked:false, passwordExpired:false).save()
    ticket = new Ticket(summary:'a summary', description:'a description', type:bug, priority:3, commissioner:user)
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
