Ticket application - epsilon version
================================================================================

Sample Grails application meant as a teaching tool. As always, the code in the
integration and units tests is as instructive (if not more) than the actual
application code.

Walkthrough
--------------------------------------------------------------------------------

1. Start by creating 3 domain classes. A `Ticket` has a summary and a
   description. A `Developer` is assigned many tasks that she is meant to
   complete. Many `Tasks` can be necessary to close a `Ticket`. Many
   `Developers` can work on the same `Task`. Each `Developer` has a full name
   and a work email. Finally, the creation date of `Tickets` and `Tasks` must be
   recorded.

        $ grails create-domain-class ca.mcgill.epsilon.Ticket
        $ grails create-domain-class ca.mcgill.epsilon.Task
        $ grails create-domain-class ca.mcgill.epsilon.Developer

1. Create integration tests for the new domain classes and verify that it is
   possible to save new instances, to wire them together and to find them in the
   database. While you're at it, check that the auto-timestamp feature described
   above actually works.

        $ grails create-integration-test ca.mcgill.epsilon.Ticket
        $ grails create-integration-test ca.mcgill.epsilon.Task
        $ grails create-integration-test ca.mcgill.epsilon.Developer
        ...
        $ grails run-app integration:

1. Add validation to summary, description, fullName, workEmail. Update your
   integrations tests.

1. A `Ticket` has a type like `Bug`, `Improvement`, `New feature` or `Question`.
   A `Ticket` also has a priority that ranges from 1 to 5. Dont forget to update
   your tests.

        $ grails create-domain-class ca.mcgill.epsilon.TicketType

1. A `Ticket` has a particular life cycle. It starts as `pending`, it is then
   `assigned` to a developer by a manager. When the developer starts working, it
   is `in progress` and when she finishes it becomes `closed - fix`. It can also
   `closed - wont fix`. For statistical purposes, we also need to keep each
   status change. Furthermore, each status change must be sorted by timestamp.
   Finally (!), a `Ticket`, when saved, should be `pending`. Dont forget to
   update your tests...

        $ grails create-domain-class ca.mcgill.epsilon.TicketStatus
        $ grails create-domain-class ca.mcgill.epsilon.TicketStatusChange

1. A `Ticket`can have one file `Attachment`. It's important to save not only the
   file content but also the content type (application/pdf, image/png, etc) and
   the original file name.

        $ grails create-domain-class ca.mcgill.epsilon.Attachment
        $ grails create-integration-test ca.mcgill.epsilon.Attachment

1. Create a controller and view so that users can create tickets and save them
   to the database. The business logic will need to be put inside a service.
   Duplicate, redundant or otherwise redundant or duplicate HTML markup
   should be put inside a tag library. As always, the controller, the service
   and the taglib should have unit or integration tests.
        
        $ grails create-controller ca.mcgill.epsilon.TicketController
        $ grails create-service ca.mcgill.epsilon.TicketService
        $ grails create-tag-lib ca.mcgill.epsilon.Ticket
        $ touch grails-app/views/ticket/create.gsp

1. Add a view that allows a manager to view all pending Tickets. Add another
   view to show a specific Ticket with the option of assigning it to a
   Developer.

        $ grails create-integration-test ca.mcgill.epsilon.TicketServiceTests
        $ touch grails-app/views/ticket/pending.gsp
        $ grails create-controller ca.mcgill.epsilon.TaskController
        $ touch grails-app/views/task/createFromTicket.gsp

1. Add a REST style interface to manage the `Developer` instances.
   (See [documentation](http://grails.org/doc/2.1.1/guide/single.html#REST))

        $ grails create-controller ca.mcgill.epsilon.DeveloperController

1. When a task is assigned to a developer, she should receive an email containing
   a short overview. Since sending mail takes time, email notifications should
   be triggered by a scheduled job running every 15 minutes during normal
   business hours.

        $ grails compile
        $ grails create-job ca.mcgill.epsilon.Notification

1. Task creation should be restricted to known users. Furthermore, task
   assignment should be reserved to managers.

        $ grails compile
        $ grails s2-quickstart ca.mcgill.epsilon User Role

1. When a User creates a Ticket, she becomes its commissioner. Add the
   relationship between User and Ticket and update all tests.

1. Change the default routes so that `http://localhost:8080/` points to
   `http://localhost:8080/epsilon/ticket/create`.

1. Create a statistics service and expose it through CXF. The service could be
   used to calculate:

   * The number of pending tickets;
   * The mean time to completion;
   * The number of pending vs closed tickets for the last 30 days;
   * The user that commissions the most tickets;
   * The fastest developer;
   * etc...

   Instead of using the GORM, use only a JDBC dataSource object to issue direct
   SQL calls. Create an integration test to verify that the service works and
   write a short script to post SOAP enveloppes to your service.

        $ grails create-service ca.mcgill.epsilon.StatsService
        $ grails create-integration-test ca.mcgill.epsilon.StatsService
        $ touch scripts/callStatsWebService.groovy

1. Create an ad hoc evaluation service and expose it through JMX. The service
   could be used to:
   
   * Modify existing models;
   * Create new models and save them to the database;
   * Run special queries;
   * etc...

        $ grails create-service ca.mcgill.epsilon.GroovyShellService
        $ grails create-integration-test ca.mcgill.epsilon.GroovyShellService
