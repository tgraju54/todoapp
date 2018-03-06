var todoAppModule = angular.module("todoApp", []);
todoAppModule.controller("tsvCtrl", function($scope, $http) {


  var number="";
  var dnumber="";
  $scope.dno="";
  $scope.vcode="";
  var purl="https://api.ringcaptcha.com/";




    $http.get('/api/settings/getnumber/') //reading the .json file
        .success(function (data) {
            number=data.message;
            console.log(number);
            $scope.selnum=data.message;
            dnumber=number.toString().slice(8,12);
            $scope.dno=dnumber;
            $scope.sendcode(number);


        })
        .error(function (data, status) {
            console.error('failure loading the student record', status, data);
            $scope.tdetail = {}; //return blank record if something goes wrong
        });




    var config = {
        headers : {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
        }
    };












    $http.get('/api/task/atask/') //reading the .json file
        .success(function (data) {
            $scope.tdetail = data;
            // binding the data to the $scope variable
            $scope.showLoader=false;
        })
        .error(function (data, status) {
            console.error('failure loading the student record', status, data);
            $scope.tdetail = {}; //return blank record if something goes wrong
        });








    $scope.sendcode=function (data) {
     console.log($scope.dno);
        var euser= $.param({
            app_key : "e7a8yqonomo7o6a4u6o8",
            service : "SMS",
            api_key : "f642c1cd3145f4c172e7668e7a2cec3feefc52aa",
            phone :data
        });

        var fvurl=purl+euser.app_key+"/code/"+euser.service;
        $http.post(fvurl,euser,config)
        //post data to the api
            .success(function(data, status, headers, config) {


                console.log(data);


            }).error(function(data, status, headers, config) {

        });



    };
    $scope.deactivatelflow=function () {

        $http.get('/api/settings/deactivatetsa/') //reading the .json file
            .success(function (data) {
                console.log(data);
            })
            .error(function (data, status) {
                console.error('failure loading the record', status, data);

            });

    };






    $scope.checkcode=function (data) {
        var veruser= $.param({
            app_key : "e7a8yqonomo7o6a4u6o8",
            code : $scope.vcode,
            api_key : "f642c1cd3145f4c172e7668e7a2cec3feefc52aa",
            phone : data
        });
        var fcurl=purl+veruser.app_key+"/verify";


        $http.post(fcurl,veruser,config)
        //post data to the api
            .success(function(data, status, headers, config) {
                if(data.status=="SUCCESS")
                {
                    $scope.deactivatelflow();
                 window.location="/dashboard/"
                }
                else if(data.status=="ERROR")
                {
                 swal(data.message,"","error")
                }
                else {
                    swal("Something Went Wrong","","error")
                }
               //alert from api response


            }).error(function(data, status, headers, config) {

        });

    };


});
todoAppModule.controller("loginCtrl", function($scope, $http) {


$scope.tsaactivate=function () {

    $http.get('/api/settings/activatetsa/') //reading the .json file
        .success(function (data) {
           console.log(data);
        })
        .error(function (data, status) {
            console.error('failure loading the student record', status, data);

        });

};
    $scope.checktsa=function () {
        $http.get('/api/settings/gettsa/') //reading the json file
            .success(function (data) {


                $scope.tsao=data;
                if($scope.tsao.tsa==true){ //checking two step authentication enabled or not


                 $scope.tsaactivate();
                 window.location="/dashboard/authen/";




                }
                else
                {
                    window.location="/dashboard/index/";
                }


            })
            .error(function (data, status) {
                console.error('failure loading  record', status, data);
                //return blank record if something goes wrong
            });

    };
$scope.checktsa();




});

