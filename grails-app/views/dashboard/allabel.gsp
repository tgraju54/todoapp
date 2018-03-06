<html>
<!--include the header -->
<g:render template="header"/>
<asset:javascript src="/controllers/allabel.js"/>
<body ng-app="todoApp">
<!--side bar -->
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li ><%= link(action:'index',controller:'dashboard') { '<span class="glyphicon glyphicon-list"></span> Todays Tasks' } %></li>
                <li ><%= link(action:'altask',controller:'dashboard') { '<span class="glyphicon glyphicon-calendar"></span> Upcoming Tasks' } %> </li>
                <li><%= link(action:'graph',controller:'dashboard') { '<span class="glyphicon glyphicon-stats"></span> Graph' } %></li>
                <li class="active"><%= link(action:'allabel',controller:'dashboard') { '<span class="glyphicon glyphicon-tag"></span> Labels' } %> </li>
                <li ><%= link(action:'settings',controller:'dashboard') { '<span class="glyphicon glyphicon-cog"></span> Settings' } %> </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" ng-controller="displaylabelCtrl" >
            <h1 class="page-header">All Labels</h1>
            <!--add task for todo-->
            <h2 class="sub-header">
                <form ng-submit="addlabel()"  method="POST" class="form-inline">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-tags"></i></span>
                        <input id="lname"  type="text" class="form-control" name="lname" placeholder="Enter Label Name " ng-model="label.lname" minlength="3">
                    </div>
                    &nbsp; <input type="submit" class=" btn btn-success">

                </form>

                <!--table for tasks -->
            </h2>
            <div class="table-responsive" ng-controller="displaylabelCtrl" >
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <form>
                        <tr  ng-repeat="p in ldetail">
                            <td><input type="radio" name="lid" value={{p.id}}  ng-model="$parent.lid.id" required></td>
                            <td>{{p.lname}}</td>
                        </tr>
                    </tbody>

                </table>
                <div id="loader" ng-show="showLoader">
                    <div class="blockG" id="rotateG_01"></div>
                    <div class="blockG" id="rotateG_02"></div>
                    <div class="blockG" id="rotateG_03"></div>
                    <div class="blockG" id="rotateG_04"></div>
                    <div class="blockG" id="rotateG_05"></div>
                    <div class="blockG" id="rotateG_06"></div>
                    <div class="blockG" id="rotateG_07"></div>
                    <div class="blockG" id="rotateG_08"></div>
                </div>
                <input type="button" value="Delete"  class="btn btn-danger" ng-click="ldelete()"/>
            </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>