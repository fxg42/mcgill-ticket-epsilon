package ca.mcgill.epsilon

import grails.test.mixin.*
import org.junit.*

@TestFor(TicketController)
@Mock([Ticket, TicketType, TicketService, Attachment, User])
class TicketControllerTests {

  @Before void setup () {
    def user = new User(username:'user', password:'s3cret', enabled:true, accountExpired:false, accountLocked:false, passwordExpired:false).save()
    controller.springSecurityService = [currentUser: user]
  }

  @Test void create_should_return_empty_ticket () {
    def model = controller.create()
    assert model.ticket != null
  }

  @Test void save_should_render_proper_text_when_successful () {
    def bug = new TicketType(key:'BUG', description:'Bug').save()

    request.method = "POST"
    params.summary = 'a summary'
    params.description = 'a description'
    params.priority = 1
    params.type = bug

    controller.save()

    assert response.redirectUrl == '/ticket/pending'
  }

  @Test void save_should_render_proper_text_when_failed () {
    controller.save()
    assert view == '/ticket/create'
    assert model.ticket != null
  }
}
