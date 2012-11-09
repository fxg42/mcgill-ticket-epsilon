package ca.mcgill.epsilon

import grails.test.mixin.*
import org.junit.*

@TestFor(TicketTagLib)
class TicketTagLibTests {

  @Test void should_render_a_single_radio_button () {
    def taglib = new TicketTagLib()
    def renderedText = taglib.priorityRadioButton(1, 1)
    assert renderedText.contains('value="1"')
    assert renderedText.contains('checked')
  }

  @Test void should_render_the_priority_picker () {
    def renderedText = applyTemplate('<t:priorityPicker value="1"/>')
    assert renderedText.contains('value="1"')
    assert renderedText.contains('value="2"')
    assert renderedText.contains('value="3"')
    assert renderedText.contains('value="4"')
    assert renderedText.contains('value="5"')
  }
}
