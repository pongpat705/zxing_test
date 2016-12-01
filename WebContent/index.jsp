<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html>
<html>
<head>

    <!-- CSS (load bootstrap) -->
    <link rel="stylesheet" href="resource/vendor/bootstrap/dist/css/bootstrap.min.css">
    <style>
        .navbar { border-radius:0; }
    </style>

    <!-- JS (load angular, ui-router, and our custom js file) -->
    <script src="resource/vendor/angular/angular.js"></script>
    <script src="resource/vendor/angular-ui-router/release/angular-ui-router.min.js"></script>
    <script src="resource/script/route.config.js"></script>
    <script src="resource/vendor/oclazyload/dist/ocLazyLoad.js"></script>
    <script src="resource/vendor/angular-bootstrap/ui-bootstrap-tpls.js"></script>
    <script src="resource/vendor/angular-sanitize/angular-sanitize.js"></script>
    <script src='resource/vendor/textAngular/dist/textAngular-rangy.min.js'></script>
    <script src='resource/vendor/textAngular/dist/textAngular-sanitize.min.js'></script>
    <script src="resource/vendor/textAngular/dist/textAngularSetup.js"></script>
    <script src="resource/vendor/textAngular/dist/textAngular.js"></script> 
    <script src="resource/vendor/satellizer/satellizer.min.js"></script>
    <script src="resource/vendor/angular-file-upload/dist/angular-file-upload.js"></script>
    <script src="resource/vendor/ng-file-upload/dist/ng-file-upload.js"></script>
    <script src="resource/vendor/valdr-1.1.6/src/valdr.js"></script>        
</head>

<!-- apply our angular app to our site -->
<body ng-app="routerApp">

<!-- NAVIGATION -->
<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" ui-sref="#">AngularUI Router</a>
    </div>
    <ul class="nav navbar-nav">
        <li><a ui-sref="home">Home</a></li>
        <li><a ui-sref="about">About</a></li>
    </ul>
</nav>

<script>
	var _config = {
		contextPath : "${pageContext.request.contextPath}"
	};
</script>
    
<!-- MAIN CONTENT -->
<div class="container">

    <!-- THIS IS WHERE WE WILL INJECT OUR CONTENT ============================== -->
    <div ui-view></div>

</div>

</body>
</html>