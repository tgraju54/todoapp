<html>
<!--ccs for control cards  -->
<style>
.card {
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
    width: 40%;
}

.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.container {
    padding: 2px 16px;
}
    .formcards{
        position: relative;
        height: 100 px;
    }
</style>


<!--add header for dashboard -->
<g:render template="header"/>

<g:external dir="stylesheets" file="toogle.css" />
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>



<!--script for the card animations -->
<script>

    $(document).ready(function(){
        $("#cemail").hide();
        $("#cpass").hide();
        $("#deac").hide();
        $("#cphone").hide();


        $("#ccemail").click(function(){

            $("#cemail").fadeIn(300);
            $("#ccemail").hide();
            $("#ccpass").hide();
            $("#cdea").hide();
            $("#tfa").hide();
            $("#ccphone").hide();

        });

        $("#ccpass").click(function(){

            $("#cpass").fadeIn(300);
            $("#ccemail").hide();
            $("#ccpass").hide();
            $("#cdea").hide();
            $("#tfa").hide();
            $("#ccphone").hide();

        });
        $("#cdea").click(function(){
            $("#deac").fadeIn(300);
            $("#ccemail").hide();
            $("#ccpass").hide();
            $("#cdea").hide();
            $("#tfa").hide();
            $("#ccphone").hide();


        });
        $("#ccphone").click(function(){
            $("#cphone").fadeIn(300);
            $("#ccemail").hide();
            $("#ccpass").hide();
            $("#cdea").hide();
            $("#tfa").hide();
            $("#ccphone").hide();


        });
        $("#bb").click(function(){


            $("#deac").hide();
            $("#cpass").hide();
            $("#cemail").hide();
            $("#ccpass").fadeIn(300);
            $("#ccemail").fadeIn(300);
            $("#cdea").fadeIn(300);
            $("#tfa").fadeIn(300);
            $("#ccphone").fadeIn(300);

        });
        $("#bb1").click(function(){


            $("#deac").hide();
            $("#cpass").hide();
            $("#cemail").hide();
            $("#ccpass").fadeIn(300);
            $("#ccemail").fadeIn(300);
            $("#cdea").fadeIn(300);
            $("#tfa").fadeIn(300);
            $("#ccphone").fadeIn(300);

        });
        $("#bb2").click(function(){


            $("#deac").hide();
            $("#cpass").hide();
            $("#cemail").hide();
            $("#ccpass").fadeIn(300);
            $("#ccemail").fadeIn(300);
            $("#cdea").fadeIn(300);
            $("#tfa").fadeIn(300);
            $("#ccphone").fadeIn(300);

        });
        $("#bb3").click(function(){

            $("#cphone").hide();
            $("#deac").hide();
            $("#cpass").hide();
            $("#cemail").hide();
            $("#ccpass").fadeIn(300);
            $("#ccemail").fadeIn(300);
            $("#cdea").fadeIn(300);
            $("#tfa").fadeIn(300);
            $("#ccphone").fadeIn(300);

        });
    });
</script>

<body ng-app="todoApp" >
<!--side link controllers-->

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li ><%= link(action:'index',controller:'dashboard') { '<span class="glyphicon glyphicon-list"></span> Todays Tasks' } %></li>
                <li ><%= link(action:'altask',controller:'dashboard') { '<span class="glyphicon glyphicon-calendar"></span> Upcoming Tasks' } %> </li>
                <li><%= link(action:'graph',controller:'dashboard') { '<span class="glyphicon glyphicon-stats"></span> Graph' } %></li>
                <li ><%= link(action:'allabel',controller:'dashboard') { '<span class="glyphicon glyphicon-tag"></span> Labels' } %> </li>
                <li class="active"><%= link(action:'settings',controller:'dashboard') { '<span class="glyphicon glyphicon-cog"></span> Settings' } %> </li>
            </ul>

        </div>
    </div>
</div>








<!-- settings cards  -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" >
         <h1 class="page-header"><span class="glyphicon glyphicon-cog"></span> Settings</h1>
    <div  ng-controller="settingsCtrl">
        <!--form cards -->

        <div class="container" id="cpass">
            <div class="row">
                <div class="col-sm-4" >
                    <form  ng-submit="changepass()">
                        <b>Enter New Password</b><br><br>
                        <input type="password" class="form-control" ng-model="puser.old" minlength="8" placeholder="Enter Current Password" ng-disabled="loader"  name="old" required><br>
                        <input type="password" class="form-control" ng-model="puser.new" minlength="8" placeholder="Enter New Password"  ng-disabled="loader" name="new" required ><br>
                        <input type="password" class="form-control"  ng-model="puser.rnew" minlength="8" placeholder="Repeat Password" ng-disabled="loader" name="newr" required ><br>
                        <img ng-if="loader=='disabled'" src="${resource(dir: 'images', file: 'loader.gif')}"  height="30" width="30" alt="loading"/><br>
                        <input type="submit" value="Change Password" action="cpass" class="btn btn-primary"/>
                        <input type="button" class="btn btn-danger" value="Back" id="bb">
                    </form>
                </div>
            </div>
        </div>
        <div class="container" id="cemail">
            <div class="row">
                <div class="col-sm-4" >
                    <form method="POST" ng-submit="changeemail()">
                        <input type="email" class="form-control"  placeholder="me@example.com" name="uname" ng-model="euser.email" ng-disabled="loader" required ><img ng-if="loader=='disabled'" src="${resource(dir: 'images', file: 'loader.gif')}"  height="30" width="30" alt="loading"/> <br>
                        <input type="submit" value="Change Email"  class="btn btn-primary"/>
                        <input type="button" class="btn btn-danger" value="back" id="bb1">
                    </form>
                </div>
            </div>
        </div>
        <div class="container" id="deac">
            <div class="row">
                <div class="col-sm-4" >
                    <form method="POST" ng-submit="deactivate()">
                        Are You sure to Delete your account??<br><br>
                        <input type="hidden" name="uid" ng-model="drec.user" value="setform">
                        <img ng-if="loader=='disabled'" src="${resource(dir: 'images', file: 'loader.gif')}"  height="30" width="30" alt="loading"/><br><br>
                        <input type="submit" value="Yes" action="drec"  ng-disabled="loader"  class="btn btn-success"/>
                        <input type="button" class="btn btn-danger"  ng-disabled="loader" value="Back" id="bb2">
                    </form>
                </div>
            </div>
        </div>
        <div class="container" id="cphone">
            <div class="row">
                <div class="col-sm-4" >
                    <form method="POST" ng-submit="changephone()">
                        <input type="number" class="form-control"  placeholder="phone number with country code eg 91966106XXXX"  ng-model="puser.pno" ng-disabled="loader" minlength="12" required><br>
                        <img ng-if="loader=='disabled'" src="${resource(dir: 'images', file: 'loader.gif')}"  height="30" width="30" alt="loading"/><br>
                        <input type="submit" value="Modify Number "  class="btn btn-primary"/>
                        <input type="button" class="btn btn-danger" value="back" id="bb3">
                    </form>
                </div>
            </div>
        </div>


    <div ng-init="gettsastatus()"  id="tfa" ng-click="changetsa()">
        <b >Enable two step Authentication:</b>&nbsp;&nbsp;&nbsp;&nbsp;

        <input type="checkbox" id="tsa_stat"  data-toggle="toggle"  ng-click="changetsa()">
    </div><br><BR>
    <div class="card" id="ccemail">
        <div class="container" >
            <g:img dir="images" file="em.png" height="50" width="50"/>
            <h4><b>Change Email Address:</b></h4>

        </div>
    </div><Br><br>
    <div class="card" id="ccpass">
        <div class="container">
            <g:img dir="images" file="pass.png" height="50" width="50"/>
            <h4><b>Change Password</b></h4>
            <p>Be more Secure</p>
        </div>
    </div><Br><BR>
    <div class="card" id="cdea">
        <div class="container">
            <g:img dir="images" file="del.png" height="50" width="50"/>
            <h4><b>Delete You Account</b></h4>
            <p>Delete and logout</p>

        </div>
    </div><Br><BR>
        <div class="card" id="ccphone">
            <div class="container">
                <g:img dir="images" file="phone.png" height="50" width="50"/>
                <h4><b>Phone Number</b></h4>
                <p>Add/Edit phone number</p>
            </div>
        </div>



</div>
</div>
</div>
</div>
</body>
<script>
    //creating an application module
    var todoAppModule = angular.module("todoApp", []);

    // settings page controller for the todoapp
    todoAppModule.controller("settingsCtrl", function($scope, $http) {

        $scope.tsa_indicator=null;
        $scope.loader=null;

        $scope.checktsa=function () {
            $http.get('/api/settings/gettsa/') //reading the json file
                .success(function (data) {


                    $scope.tsao=data;
                    if($scope.tsao.tsa==true){ //checking two step authentication enabled or not

                        if($scope.tsao.tsacurr==true){ //check two step authentication login flow
                            window.location="/dashboard/authen/" //redirecting to authentication page
                        }
                    }


                })
                .error(function (data, status) {
                    console.error('failure loading  record', status, data);
                    //return blank record if something goes wrong
                });

        };
        $scope.checktsa();
        $scope.changeemail = function() {  //change email fucntion
            $scope.loader='disabled';
            $http.post('/api/settings/cemail/',$scope.euser)
            //post data to the api

                .success(function(data, status, headers, config) {
                    $scope.loader=null;
                    $scope.euser.email=null;

                    swal(data.message,"",data.code); //alert from api response


                }).error(function(data, status, headers, config) {

            });

        };
        $scope.changepass = function() {  //change password
            if($scope.puser.new==$scope.puser.rnew) {
                $scope.loader="disabled";
                $http.post('/api/settings/cpass/', $scope.puser)
                //post data to the api
                    .success(function (data, status, headers, config) {
                        console.log(data);
                        $scope.loader=null
                        swal(data.message, "", data.code); //alert from api response


                    }).error(function (data, status, headers, config) {

                });


            }else
            {
                swal("Passwords Don't Match","","info") //if passwords dont match
            }
        };
        $scope.gettsastatus = function() {
            $http.get('/api/task/gettsa/') //reading the json file
                .success(function (data) {
                    console.log("invoked");
                    $scope.tsastatus = data;

                    if($scope.tsastatus.status=="true")
                    {
                        $('#tsa_stat').bootstrapToggle('on')

                    }
                    else
                    {
                        $('#tsa_stat').bootstrapToggle('off')
                    }
                    // binding the data to the $scope variable
                })
                .error(function (data, status) {
                    console.error('failure loading status', status, data);
                    $scope.tsastatus = {}; //return blank record if something goes wrong
                });
        };
        $scope.changetsa=function () {

            $http.post('/api/task/changetsastat/',$scope.tsa_indicator)
            //post data to the api
                .success(function (data, status, headers, config) {
                    if(data.code=="sccuess")
                    {
                        swal(data.message,"",data.code);
                        $scope.gettsastatus();
                    }
                    else
                    {
                        swal(data.message,"",data.code);
                        $scope.gettsastatus();
                    }





                }).error(function (data, status, headers, config) {
                swal(data.message, "", data.code);
            });

        };
        $scope.changephone=function () {
            $scope.loader="disabled";
            $http.post('/api/settings/cphone/',$scope.puser)
            //post data to the api
                .success(function(data, status, headers, config) {
                    $scope.loader=null;
                    swal(data.message,"",data.code); //alert from api response


                }).error(function(data, status, headers, config) {

            });
        };
        $scope.deactivate = function() {  //deativate the user account
            //sweetaleart dialog for interface
            swal({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover your account!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                if (willDelete) {
                    $scope.loader="disabled";
                    $http.post('/api/settings/drec/',$scope.drec)
                    //post data to the api
                        .success(function(data, status, headers, config) {

                            $scope.loader=null;
                            swal(data.message,"",data.code);
                            $window.location.reload();


                        }).error(function(data, status, headers, config) {

                    });


                } else {
                    swal("Your account is safe");
        }
        });



        };





    });




</script>
</html>

