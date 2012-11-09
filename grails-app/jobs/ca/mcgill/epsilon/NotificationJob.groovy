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
      mailService.sendMail {
        to task.responsibles*.workEmail
        from "ticket-epsilon@mcgill.ca"
        subject "You've been assigned a ticket"
        body task.originalTicket.summary
      }
      log.info "Sent notification to ${task.responsibles*.fullName.join(' and ')}."
      task.notificationSent = true
      taskService.save(task)
    }
  }
}
