@Grab('com.github.groovy-wslite:groovy-wslite:0.7.1')
import wslite.soap.*

def client = new SOAPClient("http://localhost:8080/services/stats")

def response = client.send {
  body {
    findNumberOfPendingTickets('xmlns':'http://epsilon.mcgill.ca/')
  }
}

def nbPending = response.findNumberOfPendingTicketsResponse.toInteger()

switch (nbPending) {
case 0:
  println "There are no pending tickets."
  break
case 1:
  println "There is one pending ticket."
  break
default:
  println "There are ${nbPending} pending tickets."
}
