package ca.mcgill.epsilon

import grails.plugins.springsecurity.*

class TaskController {

  def ticketService
  def taskService

  @Secured(['ROLE_MANAGER'])
  def createFromTicket (Long id) {
    def foundTicket = ticketService.getPending(id)
    if (! foundTicket) {
      flash.message = 'Ticket not found or is not pending.'
      redirect(controller: 'ticket', action: 'pending')
      return
    }
    
    [ task: new Task(originalTicket:foundTicket) ]
  }

  @Secured(['ROLE_MANAGER'])
  def save () {
    def task = bindData(new Task(), params, [include:['originalTicket', 'responsibles']])
    try {
      taskService.assignAndSave(task)
      flash.message = "Ticket was successfully assigned."
      redirect(controller: 'ticket', action: 'pending')
    } catch (e) {
      log.error("Error: ${e.message}", e)
      flash.message = 'Problem while trying to assign task'
      render(view: 'createFromTicket', model: [ task: task ])
    }
  }
}
