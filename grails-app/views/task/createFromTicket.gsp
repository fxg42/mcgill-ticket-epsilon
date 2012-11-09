<%@ page import="ca.mcgill.epsilon.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ticket.label', default: 'Task')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
    <div>
      <g:if test="${flash.message}">
        <div class="alert alert-info" role="status">${flash.message}</div>
      </g:if>
      <g:form action="save" class="form-horizontal">
        <g:hiddenField name="originalTicket.id" value="${task.originalTicket.id}"/>
        <fieldset>
          <legend>Assign task to developers</legend>
          <g:hasErrors bean="${task}">
            <div class="alert alert-error">
              <ul style="list-style:none; margin: 0">
                <g:eachError bean="${task}" var="error">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
              </ul>
            </div>
          </g:hasErrors>
          <h4>${task.originalTicket.summary}</h4>
          <div class="control-group">
            <label for="responsibles" class="control-label">Available developers</label>
            <div class="controls">
              <g:select
                name="responsibles"
                from="${Developer.list()}"
                value="${task?.responsibles*.id}"
                optionKey="id"
                optionValue="${{it.fullName.capitalize()}}"
                multiple="true" />
            </div>
          </div>
          <div class="control-group">
            <div class="controls">
              <g:submitButton name="create" class="btn btn-success" value="${message(code: 'task.button.create.label', default: 'Assign')}" />
            </div>
          </div>
        </fieldset>
      </g:form>
    </div>
	</body>
</html>


