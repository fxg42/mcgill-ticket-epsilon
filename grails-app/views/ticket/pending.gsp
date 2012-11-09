<%@ page import="ca.mcgill.epsilon.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ticket.label', default: 'Ticket')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
    <div>
      <g:if test="${flash.message}">
        <div class="alert alert-info" role="status">${flash.message}</div>
      </g:if>
      <legend>Unassigned tickets</legend>
      <g:if test="${tickets}">
      <table class="table table-striped table-bordered">
        <thead>
          <tr>
            <th width="10%">#</th>
            <th width="20%">Type</th>
            <th>Summary</th>
            <th width="20%">Available actions</th>
          </tr>
        </thead>
        <tbody>
          <g:each var="ticket" in="${tickets}">
          <tr>
            <td>${ticket.id}</td>
            <td>${ticket.type.description}</td>
            <td>${ticket.summary}</td>
            <td>
              <div class="btn-group">
                <g:link controller="ticket" action="show" id="${ticket.id}" class="btn btn-mini">show</g:link>
                <g:link controller="task" action="createFromTicket" id="${ticket.id}" class="btn btn-mini btn-success">assign</g:link>
              </div>
            </td>
          </tr>
          </g:each>
        </tbody>
      </table>
      </g:if>
      <g:else>
      <h3>There aren't any unassigned tickets</h3>
      </g:else>
    </div>
	</body>
</html>


