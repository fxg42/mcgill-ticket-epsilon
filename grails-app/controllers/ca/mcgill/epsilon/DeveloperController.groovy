package ca.mcgill.epsilon

import grails.converters.XML

class DeveloperController {

  def show (Long id) {
    if (id && Developer.exists(id)) {
      render Developer.get(id) as XML
    } else {
      render Developer.list() as XML
    }
  }

  def save () {
    def developer = bindData(new Developer(), params, [include:['fullName', 'workEmail']])
    if (developer.save()) {
      render developer as XML
    } else {
      render developer.errors
    }
  }
}
