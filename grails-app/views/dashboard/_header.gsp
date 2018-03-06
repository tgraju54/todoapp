<!DOCTYPE html>
<html lang="en">
<head>
    <title>Todo||Dashboard</title>
    <!--boostrap header -->
    <!--cdn installations-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script data-require="angular.js@1.4.0-beta.6" data-semver="1.4.0-beta.6" src="https://code.angularjs.org/1.4.0-beta.6/angular.js"></script>
    <asset:stylesheet src="loader.css"/>
    <script
            src="https://code.jquery.com/jquery-2.0.1.min.js"
            integrity="sha256-JD9u5RNjfbbYl/AbiYYvVPKcLNlKNe2urUMuGzNEIck="
            crossorigin="anonymous"></script>
    <asset:javascript src="library/sweetalert.js"/>
    <asset:javascript src="/controllers/settings.js"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <g:external dir="stylesheets" file="dash.css" />
    <asset:javascript src="library/angui.js" />
    <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'todo.ico')}" type="image/x-icon" />
</head>
<script type="text/javascript">
    $(window).load(function() {
        $("#loader").fadeOut("slow");
    })
</script>
<!--navbar for dashboard-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--side bar for todo -->
            <a class="navbar-brand" href="#">Todo Dashboard</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li ><%= link(action:'index',controller:'dashboard') { 'Dashboard' } %></li>
                <li ><%= link(action:'settings',controller:'dashboard') { 'Settings' } %></li>
                <li ><%= link(action:'index',controller:'logout') { 'Logout' } %></li>
            </ul>
        </div>
    </div>
</nav>
<!--navbar ends -->