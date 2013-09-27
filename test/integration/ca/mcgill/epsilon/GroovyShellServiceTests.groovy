package ca.mcgill.epsilon

import static org.junit.Assert.*
import org.junit.*

class GroovyShellServiceTests {

    def service, ticketService
    
    @Before void setUp() {
         service = new GroovyShellService()
         ticketService = new TicketService()
    }

    @Test void should_create_tickets_in_shell_script() {
        def pending = ticketService.findAllPending()
        assert pending.size() == 0
        
        def id = service.evaluate("""
            package ca.mcgill.epsilon

            def bug = TicketType.findByKey('BUG')
            def user = User.findByUsername('user')

            def ticket = new Ticket(summary:'a summary', description:'a description', type:bug, priority:3, commissioner:user).save(flush:true)

            return ticket.id
        """)
        
        pending = ticketService.findAllPending()
        assert pending.size() == 1
        assert pending.first().id == id
        
        Ticket.get(id).delete()
    }
}
