package ca.mcgill.epsilon

import org.junit.*

class StatsServiceTests {

  def service
  def dataSource
  def bug, user, assigned

  @Before void setup () {
    service = new StatsService(dataSource:dataSource)
    bug = TicketType.findByKey('BUG')
    user = User.findByUsername('user')
    assigned = TicketStatus.findByKey('ASSIGNED')
  }

  @Test void should_only_return_count_of_still_pending_tickets () {
    def ticketIds = []
    5.times {
      def ticket = new Ticket(summary:'a summary', description:'a description', type:bug, priority:3, commissioner:user).save(flush:true)
      ticketIds << ticket.id
    }

    assert service.findNumberOfPendingTickets() == 5

    Ticket.get(ticketIds[0]).addToProgress(status:assigned).save(flush:true)
    Ticket.get(ticketIds[1]).addToProgress(status:assigned).save(flush:true)

    assert service.findNumberOfPendingTickets() == 3
  }
}
