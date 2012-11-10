package ca.mcgill.epsilon

class TaskService {

  def assignAndSave (task) {
    task.originalTicket.addToProgress(status:TicketStatus.findByKey('ASSIGNED'))
    task.originalTicket.save()
    save(task)
  }

  def save (task) {
    task.save()
  }

  def findAllAssignedButNotNotified () {
    def tasks = Task.findAll("from Task as task where task.notificationSent = false")
    tasks?.findAll{
      def progress = it.originalTicket.progress
      def keys = progress.status.key
      progress.size() == 2 && 'PENDING' in keys && 'ASSIGNED' in keys
    }
  }
}
