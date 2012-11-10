package ca.mcgill.epsilon

import static org.junit.Assert.*
import org.junit.*

class TicketServiceTests {

  def saveOptions = [ flush:true, failOnError:true ]
  def bug, assigned, service, user

  @Before void setup () {
    bug = TicketType.findByKey('BUG').save(saveOptions)
    user = User.findByUsername('user')
    assigned = TicketStatus.findByKey('ASSIGNED')
    service = new TicketService()
  }

  // This needs to be an integration test because the method findAllPending uses
  // HQL. HQL does not work in unit tests.
  @Test void find_pending_should_only_return_tickets_with_only_one_status_change () {
    def pendingTicket = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user)
    pendingTicket.save(saveOptions)

    def assignedTicket = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user)
    assignedTicket.save(flush:true)
    assignedTicket.addToProgress(status:assigned).save(flush:true)

    assert pendingTicket.progress.size() == 1
    assert assignedTicket.progress.size() == 2

    def found = service.findAllPending()

    assert found.size() == 1    
    assert found.first() == pendingTicket
  }

  @Test void find_pending_results_should_be_sorted_by_timestamp () {
    def pendingTicket1 = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user).save(saveOptions)
    def pendingTicket2 = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user).save(saveOptions)
    def pendingTicket3 = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user).save(saveOptions)

    def found = service.findAllPending()

    assert found.size() == 3
    assert found.first() == pendingTicket1
    assert found.last() == pendingTicket3
  }

  @Test void get_pending_should_only_return_if_only_one_status_change () {
    def pendingTicket = new Ticket(summary:'test summary', description:'a description', type:bug, priority:3, commissioner:user).save(flush:true)
   
    def found = service.getPending(pendingTicket.id)
    
    assert found == pendingTicket  

    found.addToProgress(status:assigned).save(flush:true)

    found = service.getPending(pendingTicket.id)

    assert ! found
  }
}
