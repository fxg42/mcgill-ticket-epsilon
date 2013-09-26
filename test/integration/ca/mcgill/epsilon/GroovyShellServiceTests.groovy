package ca.mcgill.epsilon

import static org.junit.Assert.*
import org.junit.*

class GroovyShellServiceTests {

    def service
    
    @Before void setUp() {
         service = new GroovyShellService()
    }

    @Test void should_create_tickets_in_shell_script() {
        def id = service.evaluate("""
            package ca.mcgill.epsilon

            def bug = TicketType.findByKey('BUG')
            def user = User.findByUsername('user')
            def assigned = TicketStatus.findByKey('ASSIGNED')

            def ticket = new Ticket(summary:'a summary', description:'a description', type:bug, priority:3, commissioner:user).save(flush:true)

            return ticket.id
        """)
        
        def pending = Ticket.findAll("from Ticket as ticket where ticket.progress.size = 1 order by ticket.dateCreated")
        
        assert pending.size() == 1    
        assert pending.first().id == id
    }
}
