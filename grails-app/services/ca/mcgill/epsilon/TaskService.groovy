package ca.mcgill.epsilon

class TaskService {

  def assignAndSave (task) {
    task.originalTicket.addToProgress(status:TicketStatus.findByKey('ASSIGNED')).save()
    save(task)
  }

  def save (task) {
    task.save()
  }

  def findAllAssignedButNotNotified () {
    def tasks = Task.findAll("from Task as task where task.notificationSent = false")
    tasks?.findAll{ it.originalTicket.progress.status.key.last() == 'ASSIGNED' }
  }
}
