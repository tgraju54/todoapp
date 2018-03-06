//creating an application module

var todoAppModule = angular.module("todoApp", []);
//code will do the signup process
todoAppModule.controller("signupCtrl", function($scope, $rootScope, $http, $location,$window){
    $scope.signup = function () {
        $http.post('/user/create/',$scope.nuser) //post data to the api
            .success(function(data, status, headers, config) {
                if(data.id=="error") {

                    $scope.nuser.errmsg = data.errmsg
                }
                else {

                    $window.location.href = 'signup/';

                }
            }).error(function(data, status, headers, config) {

        });
    };

});//end