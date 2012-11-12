package ca.mcgill.epsilon

import groovy.sql.*

class StatsService {

  static expose = ['cxf']

  def dataSource // inject JDBC dataSource in service to issue direct SQL calls.

  Integer findNumberOfPendingTickets () {
    // query the join table between Ticket and TicketStatusChange for rows 
    def sql
    try {
      sql = new Sql(dataSource.connection)
      def row = sql.firstRow("""
        select count(*) as nb_pending
        from (
          select ticket_progress_id
          from ticket_ticket_status_change
          group by ticket_progress_id
          having count(*) = 1
        )
      """)
      row.nb_pending
    } finally {
      sql?.close()
    }
  }
}
