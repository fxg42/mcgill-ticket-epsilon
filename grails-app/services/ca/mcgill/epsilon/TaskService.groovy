package ca.mcgill.epsilon

class TaskService {

  def assignAndSave (task) {
    task.originalTicket.addToProgress(status:TicketStatus.findByKey('ASSIGNED'))
    task.save()
  }
}
