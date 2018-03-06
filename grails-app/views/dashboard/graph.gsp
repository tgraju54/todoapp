<!--add required headers for dashboard -->
<g:render template="header"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li ><%= link(action:'index',controller:'dashboard') { '<span class="glyphicon glyphicon-list"></span> Todays Tasks' } %></li>
                <li ><%= link(action:'altask',controller:'dashboard') { '<span class="glyphicon glyphicon-calendar"></span> Upcoming Tasks' } %> </li>
                <li class="active"><%= link(action:'graph',controller:'dashboard') { '<span class="glyphicon glyphicon-stats"></span> Graph' } %></li>
                <li><%= link(action:'allabel',controller:'dashboard') { '<span class="glyphicon glyphicon-tag"></span> Labels' } %> </li>
                <li ><%= link(action:'settings',controller:'dashboard') { '<span class="glyphicon glyphicon-cog"></span> Settings' } %> </li>
            </ul>
        </div>
    </div>
</div>
<!--google graph scripts -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header"><span class="glyphicon glyphicon-stats"></span> Productivity Graph</h1>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <asset:javascript src="/controllers/graph.js"/>
    <body ng-app="todoApp">
    <div ng-controller="graphCtrl">
        <div id="line_top_x"></div>
    </div>
</div>
</body>
</div>