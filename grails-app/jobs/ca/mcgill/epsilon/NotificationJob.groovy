package ca.mcgill.epsilon

class NotificationJob {
  static triggers = {
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
