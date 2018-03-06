//creating an application module
var todoAppModule = angular.module("todoApp", []);

// settings page controller for the todoapp
todoAppModule.controller("settingsCtrl", function($scope, $http) {

    $scope.tsa_indicator=null;
    $scope.loader=null

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

