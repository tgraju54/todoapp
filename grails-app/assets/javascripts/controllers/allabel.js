//creating  application module
var todoAppModule = angular.module("todoApp", []);

//read the data from json file and will pass to the $scope variable
todoAppModule.controller("displaylabelCtrl", function($scope, $http,$interval) {
     $scope.showLoader=true;
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

    $interval(callAtInterval, 1000);//update the tasks
    function callAtInterval()
    {
        $http.get('/api/label/allabel/') //reading the label .json file
            .success(function (data) {
                $scope.ldetail = data;
                // binding the data to the $scope variable
                $scope.showLoader=false;
            })
            .error(function (data, status) {
                console.error('failure loading the student record', status, data);
                $scope.ldetail = {}; //return blank record if something goes wrong
            });
    }

    $scope.ldelete = function() {  //delete label

        $http.post('/api/label/dellabel/',$scope.lid)
        //post data to the api
            .success(function(data, status, headers, config) {
                 console.log(data)

                swal(data.message,"", data.code); //using sweet aleart for delete


            }).error(function(data, status, headers, config) {

        });

    };
    $scope.addlabel = function() {  //add label

        $http.post('/api/label/savelabel/',$scope.label)
        //post data to the api
            .success(function(data, status, headers, config) {
                swal(data.message,"", data.code); //using sweet aleart for delete
            }).error(function(data, status, headers, config) {

        });



    };



});

