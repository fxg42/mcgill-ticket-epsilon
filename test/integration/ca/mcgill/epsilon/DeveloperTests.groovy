package ca.mcgill.epsilon

import org.junit.*

class DeveloperTests {

  def saveOptions = [ flush:true, failOnError:true ]
  def ticket, bug, task1, task2

  @Before void setup () {
    bug = new TicketType(key:'BUG', description:'A bug is a bug').save(saveOptions)
    ticket = new Ticket(summary:'test summary', description:'test description', type:bug, priority:3).save(saveOptions)
    task1 = new Task(originalTicket:ticket).save(saveOptions)
    task2 = new Task(originalTicket:ticket).save(saveOptions)
  }

  @Test void should_be_able_to_add_many_tasks () {
    def dave = new Developer(fullName:'Dave', workEmail:'dave@mcgill.ca').save(saveOptions)
    dave.addToTasks(task1)
    dave.addToTasks(task2)

    def wasSaved = dave.save(saveOptions)

    assert wasSaved

    // For more HQL examples see:
    // http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html/queryhql.html#queryhql-examples
    def found = Developer.find("from Developer as developer where developer.fullName = :search", [search:'Dave'])

    assert found
    assert found.fullName == 'Dave' // Dave? Dave's not here, man!
    assert found.tasks.size() == 2
    assert task1 in found.tasks
    assert task2 in found.tasks
  }

  @Test void should_validate_properties () {
    def dave = new Developer(fullName:'   \t  \n', workEmail:'dave@mcgill.ca')
    def wasSaved = dave.save(flush:true)
    assert ! wasSaved
    assert dave.errors.errorCount == 1

    dave = new Developer(fullName:'Dave', workEmail:'')
    wasSaved = dave.save(flush:true)
    assert ! wasSaved
    assert dave.errors.errorCount == 1

    dave = new Developer(fullName:'Dave', workEmail:'not an email @ddress.com')
    wasSaved = dave.save(flush:true)
    assert ! wasSaved
    assert dave.errors.errorCount == 1

    dave = new Developer(fullName:'Dave', workEmail:'dave@mcgill.ca')
    wasSaved = dave.save(flush:true)
    assert wasSaved
    assert dave.errors.errorCount == 0
  }
}
