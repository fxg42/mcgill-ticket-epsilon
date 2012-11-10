package ca.mcgill.epsilon

class NotificationJob {
  static triggers = {
    // fire from Monday to Friday, every 15 minutes, starting at 08:00 and firing for the last time at 17:45.
    cron cronExpression: "0 0/15 8-17 ? * MON-FRI"
  }

  def taskService
  def mailService

  def execute () {
    log.info "Verifying for notifications to send."
    taskService.findAllAssignedButNotNotified().each { task ->

      /** Deactivate mail service. */

      // mailService.sendMail {
      //   to task.responsibles.workEmail.toArray()
      //   from "NO_REPLY@tickets.epsilon.mcgill.ca"
      //   subject "You've been assigned a ticket"
      //   body task.originalTicket.summary
      // }

      log.info "Sent notification to ${task.responsibles*.fullName.join(' and ')}."
      task.notificationSent = true
      taskService.save(task)
    }
  }
}
