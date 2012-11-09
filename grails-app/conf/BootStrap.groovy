import ca.mcgill.epsilon.*

class BootStrap {

  def init = { servletContext ->
    new TicketStatus(key:'PENDING', description:'Waiting to be assigned to a developer.').save(flush:true, failOnError:true)
    new TicketStatus(key:'ASSIGNED', description:'assigned to a developer.').save(flush:true, failOnError:true)
    new TicketStatus(key:'IN_PROGRESS', description:'currently being worked on.').save(flush:true, failOnError:true)
    new TicketStatus(key:'CLOSED_FIXED', description:'Fixed.').save(flush:true, failOnError:true)
    new TicketStatus(key:'CLOSED_WONT_FIX', description:'Will not be fixed.').save(flush:true, failOnError:true)
  }

  def destroy = {
  }
}
