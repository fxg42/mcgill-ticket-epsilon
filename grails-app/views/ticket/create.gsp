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
      <g:uploadForm action="save" class="form-horizontal">
        <fieldset>
          <legend>Create a new ticket</legend>
          <g:hasErrors bean="${ticket}">
            <div class="alert alert-error">
              <ul style="list-style:none; margin: 0">
                <g:eachError bean="${ticket}" var="error">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
              </ul>
            </div>
          </g:hasErrors>
          <div class="control-group">
            <label class="control-label" for="summary">Summary</label>
            <div class="controls">
              <g:textField name="summary" value="${ticket.summary}" placeholder="Enter a summary" class="input-xxlarge"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="summary">Type</label>
            <div class="controls">
              <g:select name="type.id" from="${TicketType.list()}" value="${ticket.type?.id}" optionKey="id" optionValue="description"/>
            </div>
          </div>
          <div class="control-group">
            <label for="priority" class="control-label">Priority level</label>
            <div class="controls">
              <t:priorityPicker value="${ticket.priority}"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="body">Description</label>
            <div class="controls">
              <g:textArea name="description" value="${ticket.description}" placeholder="What seems to be the problem?", class="input-xxlarge" rows="7"/>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="attachment">Attachment</label>
            <div class="controls">
              <input type="file" name="attachment" class="input-xxlarge">
            </div>
          </div>
          <div class="control-group">
            <div class="controls">
              <g:submitButton name="create" class="btn btn-success" value="${message(code: 'ticket.button.create.label', default: 'Create')}" />
            </div>
          </div>
        </fieldset>
      </g:uploadForm>
    </div>
	</body>
</html>

