import ca.mcgill.epsilon.*

class BootStrap {

  def init = { servletContext ->
    def saveOptions = [ flush:true, failOnError:true ]

    new TicketType(key:'BUG', description:'Bug').save(saveOptions)
    new TicketType(key:'IMPROVEMENT', description:'Improvement').save(saveOptions)
    new TicketType(key:'NEW_FEATURE', description:'New feature').save(saveOptions)
    new TicketType(key:'QUESTION', description:'Question').save(saveOptions)
    new TicketType(key:'EPIC', description:'Epic').save(saveOptions)

    new TicketStatus(key:'PENDING', description:'Waiting to be assigned to a developer.').save(saveOptions)
    new TicketStatus(key:'ASSIGNED', description:'assigned to a developer.').save(saveOptions)
    new TicketStatus(key:'IN_PROGRESS', description:'currently being worked on.').save(saveOptions)
    new TicketStatus(key:'CLOSED_FIXED', description:'Fixed.').save(saveOptions)
    new TicketStatus(key:'CLOSED_WONT_FIX', description:'Will not be fixed.').save(saveOptions)

    new Developer(fullName:'Alice', workEmail:'fxg@code3.ca').save(saveOptions)
    new Developer(fullName:'Bob', workEmail:'fxg@code3.ca').save(saveOptions)
    new Developer(fullName:'Carol', workEmail:'carol@mcgill.ca').save(saveOptions)
    new Developer(fullName:'Dave', workEmail:'dave@mcgill.ca').save(saveOptions)

  }

  def destroy = {
  }
}
