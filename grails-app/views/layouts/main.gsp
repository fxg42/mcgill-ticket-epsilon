<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
    <div class="navbar navbar-inverse navbar-static-top">
      <div class="navbar-inner">
        <ul class="nav">
          <li>
            <a class="brand" href="#">Alpha</a>
          </li>
          <li>
            <a href="#">section 1</a>
          </li>
          <li>
            <a href="#">section 2</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="container" style="margin: 10px auto">
      <div class="row">
        <div class="span3">
          <ul class="nav nav-list well">
            <li class="nav-header">Tickets</li>
            <li><g:link controller="ticket" action="create">Create new</g:link></li>
          </ul>
        </div>
        <div class="span9">
          <g:layoutBody/>
        </div>
      </div>
    </div>
		<div class="footer" role="contentinfo"></div>
    <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
    <script src="${resource(dir: 'bootstrap/js', file:'bootstrap.min.js')}"></script>
		<r:layoutResources />
	</body>
</html>
