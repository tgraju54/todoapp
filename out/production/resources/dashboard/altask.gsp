<html>
<g:render template="header"/>
<!--header for dashboard -->
<asset:javascript src="/controllers/atask.js"/>
<body ng-app="todoApp" ng-controller="ataskCtrl">
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li ><%= link(action:'index',controller:'dashboard') { '<span class="glyphicon glyphicon-list"></span> Todays Tasks' } %></li>
                <li class="active"><%= link(action:'altask',controller:'dashboard') { '<span class="glyphicon glyphicon-calendar"></span> Upcoming Tasks' } %> </li>
                <li><%= link(action:'graph',controller:'dashboard') { '<span class="glyphicon glyphicon-stats"></span> Graph' } %></li>
                <li ><%= link(action:'allabel',controller:'dashboard') { '<span class="glyphicon glyphicon-tag"></span> Labels' } %> </li>
                <li ><%= link(action:'settings',controller:'dashboard') { '<span class="glyphicon glyphicon-cog"></span> Settings' } %> </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">All Tasks</h1>
            <!--add task dashboard -->
            <h2 class="sub-header">
                <form class="form-inline">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-pushpin"></i></span>
                        <input id="tval" type="text" class="form-control" name="Task" placeholder="Enter Your Task" ng-model="task.name">
                    </div>
                    &nbsp;<input type="button" value="Add" id="ib" class="btn btn-success"  data-toggle="modal" data-target="#taskModal" >
                    <div class="input-group pull-right">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                        <input  type="text" class="form-control"  ng-change="pageset(0)" placeholder="Search"  ng-model="search">
                    </div>
                </form>
            </h2>
            <div class="table-responsive"  >
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>
                            <div ng-if="sortType!='name'">Task<a ng-click="sorting(true,1)"><span class="glyphicon glyphicon-minus"></span> </a></div>
                            <div ng-if="sortReverse==false&&sortType=='name'">Task <a ng-click="sorting(true,1)"><span class="glyphicon glyphicon-chevron-down"></span> </a></div>
                            <div ng-if="sortReverse==true&&sortType=='name'">Task<a ng-click="sorting(false,1)"> <span class="glyphicon glyphicon-chevron-up"></span> </a></div>

                        </th>
                        <th>
                            <div ng-if="sortType!='label'">Label <a ng-click="sorting(true,2)"><span class="glyphicon glyphicon-minus"></span> </a></div>
                            <div ng-if="sortReverse==false&&sortType=='label'">Label <a ng-click="sorting(true,2)"><span class="glyphicon glyphicon-chevron-down"></span> </a></div>
                            <div ng-if="sortReverse==true&&sortType=='label'">Label<a ng-click="sorting(false,2)"> <span class="glyphicon glyphicon-chevron-up"></span> </a></div>

                        </th>
                        <th>
                            <div ng-if="sortType!='date'">Date <a ng-click="sorting(true,4)"><span class="glyphicon glyphicon-minus"></span> </a></div>
                            <div ng-if="sortReverse==false&&sortType=='date'">Date <a ng-click="sorting(true,4)"><span class="glyphicon glyphicon-chevron-down"></span> </a></div>
                            <div ng-if="sortReverse==true&&sortType=='date'">Date<a ng-click="sorting(false,4)"> <span class="glyphicon glyphicon-chevron-up"></span> </a></div>



                        </th>
                        <th>
                            <div ng-if="sortType!='status'">Status <a ng-click="sorting(true,3)"><span class="glyphicon glyphicon-minus"></span> </a></div>
                            <div ng-if="sortReverse==false&&sortType=='status'">Status <a ng-click="sorting(true,3)"><span class="glyphicon glyphicon-chevron-down"></span> </a></div>
                            <div ng-if="sortReverse==true&&sortType=='status'">Status<a ng-click="sorting(false,3)"> <span class="glyphicon glyphicon-chevron-up"></span> </a></div>

                        </th>
                    </tr>
                    </thead>
                    <tbody ng-cloak>
                    <form>
                        <tr ng-repeat="p in tdetail.atasks |filter:search | orderBy:sortType:sortReverse |limitTo:5:spi">
                            <td><input type="radio" name="tid" value={{p.id}} ng-model="$parent.tid.id" required></td>
                            <td>{{p.name}}</td>
                            <td>{{p.label}}</td>
                            <td>{{p.date | date }}</td>
                            <td>{{p.status}}</td>
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


            <div  ng-if="tasklen > 0">
                <ul class="pagination pull-right">

                    <li><a href="" ng-click="prevpage()">&larr;Previous</a> </li>

                    <li ng-repeat="i in getNumber(numpage) track by $index" ng-click="pageset($index)"  ng-class="activeset($index)"><a href=""> {{$index+1}}</a></l1>
                    <li ng-click="nextpage()"><a href="">Next&rarr;</a> </li>
                </ul>
            </div>
                <!--action buttons for tasks -->
                <input type="button" value="Mark As Completed"   ng-click="markc()" class="btn btn-success"/>
                <input type="button" value="Delete"  class="btn btn-danger" ng-click="tdelete()"/>
            </form>
                <!-- Modal -->
                <div id="taskModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title"><span class="glyphicon glyphicon-list-alt"></span> Add Task</h4>
                            </div>
                            <form  ng-submit="addtask()" method="POST">
                                <div class="modal-body">
                                    <label>Task Name</label>
                                    <div id="sm">
                                        <input type="text" class="form-control"  ng-model="task.name" name="name"required>
                                        <br>
                                        Date:
                                        <div id="sm">
                                            <input type="date" class="form-control" name="tdur"  ng-model="task.tdur" name="tdur" required>
                                            <br>
                                            <label >Priority:</label>
                                            <select class="form-control" name="tpriority"  ng-model="task.tpriority" required>
                                                <option value="3">High</option>
                                                <option value="2">Medium</option>
                                                <option value="1">Low</option>
                                            </select>
                                            <br>
                                            <label >Label</label>
                                            <select class="form-control" id="label" name="label.id"  ng-model="task.label.id" required>
                                                <option value={{p.id}} ng-repeat="p in labeldata" >{{p.lname}}</option>
                                            </select>
                                        </div>
                                        <div class="modal-footer">
                                            <input type="submit" class="btn-success" id="addb" value="Add Task">
                                        </div>
                                    </div>
                                </div>
                        </div>
                    </form>
                    </div>
                </div>

        </div>
    </div>
</body>
</html>