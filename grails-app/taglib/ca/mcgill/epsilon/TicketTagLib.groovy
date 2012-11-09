package ca.mcgill.epsilon

class TicketTagLib {

  static namespace = 't'

  def priorityPicker = { attrs, content ->
    (1..5).each { level ->
      out << priorityRadioButton(level, attrs.value)
    }
  }

  private priorityRadioButton (level, test) {
    """
    <label class="radio inline">
      <input type="radio" name="priority" value="${level}" ${level == test ? 'checked' : ''}/>
      ${level}
    </label>
    """
  }
}
