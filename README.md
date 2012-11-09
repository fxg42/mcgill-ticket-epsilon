Ticket application - epsilon version
================================================================================

Sample Grails application meant as a teaching tool.

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

