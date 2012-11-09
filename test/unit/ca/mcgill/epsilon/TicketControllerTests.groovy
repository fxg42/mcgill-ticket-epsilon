package ca.mcgill.epsilon

import grails.test.mixin.*
import org.junit.*

@TestFor(TicketController)
@Mock([Ticket, TicketType, TicketService, Attachment])
class TicketControllerTests {

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

    assert response.text == 'save successful'
  }

  @Test void save_should_render_proper_text_when_failed () {
    controller.save()
    assert view == '/ticket/create'
    assert model.ticket != null
  }
}
