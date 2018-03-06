//creating an application module
var todoAppModule = angular.module("todoApp", [ ]);

// read the data from today task.json file and will pass to the $scope variable
todoAppModule.controller("ttaskCtrl", function($scope, $http,$filter,$interval) {
    $scope.showLoader=true;
    $scope.sortType='';
    $scope.sortReverse=false;
    $scope.spi=0;
    $scope.curpage=0;

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

    $scope.getlabel=function () {


        $http.get('/api/label/allabel/') //reading the json file
            .success(function (data2) {

                $scope.labeldata = data2;

                // binding the data to the $scope variable
            })
            .error(function (data, status) {
                console.error('failure loading the student record', status, data);
                $scope.tdetail = {}; //return blank record if something goes wrong
            });

    };
    $scope.getlabel();
    updatettask();
    function updatettask() {
        $http.get('/api/task/ttask/') //reading data from api
            .success(function (data) {
                $scope.tdetail = data;
                $scope.tasklen=$scope.tdetail.ttasks.length;
                if($scope.tasklen==0) {
                   $scope.numpage=0;
                }
                else
                {
                    $scope.numpage = ($scope.tasklen / 6)+1;
                    $scope.numpage = parseInt($scope.numpage);

                }
                if($scope.numpage!=0){
                if($scope.curpage+1>$scope.numpage)
                {
                    $scope.pageset($scope.curpage-1);
                }}
                // binding the data to the $scope variable
                $scope.showLoader=false;



            })
            .error(function (data, status) {
                console.error('failure loading the  record', status, data);
                $scope.tdetail = {}; //return blank record if something goes wrong
            });
    }



    $scope.tdelete = function() {  //delete task

        $http.post('/api/task/deltask/',$scope.tid)
        //post data to the api
            .success(function(data, status, headers, config) {
                updatettask();
                swal(data.message, "", data.code); //using sweet aleart

            }).error(function(data, status, headers, config) {

        });



    };
    $scope.markc = function() {  //mark task as completed
        $http.post('/api/task/updatetask/',$scope.tid)
        //post data to the api
            .success(function(data, status, headers, config) {
                updatettask();
                swal(data.message,"",data.code);
            }).error(function(data, status, headers, config) {

        });
    };
    $scope.addtask = function() { //add a new task

        $scope.task.tdur = $filter('date')($scope.task.tdur, "yyyy-MM-dd");
        $http.post('/api/task/savetask/',$scope.task)
        //post data to the api
            .success(function(data, status, headers, config) {
                updatettask();
                swal(data.message,"",data.code);
                $("#taskModal").modal('hide');
            }).error(function(data, status, headers, config) {

        });


    };
    $scope.setsorttype=function (data) {

       $scope.sortType=data;
    };
    $scope.sorting=function (order,data2) {
        $scope.sortReverse=order;

        if(data2==0) {
            $scope.setsorttype('priority');
        }
        else if(data2==1)
        {
            $scope.setsorttype('name');
        }
        else if(data2==3)
        {
            $scope.setsorttype('status');
        }
        else
        {
            $scope.setsorttype('label');
        }

    };
    $scope.changelabel=function () {


        $scope.clabel.taskno=$scope.selectask;

        $http.post('/api/task/chlabel/',$scope.clabel)
        //post data to the api
            .success(function(data, status, headers, config) {
                updatettask();
                swal(data.message,"",data.code);
                $("#labelmodal").modal('hide');
            }).error(function(data, status, headers, config) {

        });



    };
    $scope.changePriority=function () {


        $scope.cpri.taskno=$scope.selectask;
        $http.post('/api/task/chpriority/',$scope.cpri)
        //post data to the api
            .success(function(data, status, headers, config) {
                updatettask();
                swal(data.message,"",data.code);
                $("#priorityModal").modal('hide');
            }).error(function(data, status, headers, config) {

        });



    };

    $scope.cmodal=function (taskid) {

        $scope.selectask=taskid;

        $("#labelmodal").modal('show');

    };
    $scope.pmodal=function (taskid) {

        $scope.selectask=taskid;

        $("#priorityModal").modal('show');

    };



    $scope.getNumber = function(num) {
        return new Array(num);
    };
    $scope.pageset=function (pageno) {
      $scope.spi=5*pageno;
      $scope.curpage=pageno;
    };

    $scope.prevpage=function () {
        if($scope.curpage!=0) {
            $scope.curpage = $scope.curpage - 1;
            $scope.pageset($scope.curpage);
        }
        else
            swal("Displaying","page 1 of" + $scope.numpage,"info")
    };
    $scope.nextpage=function () {
        if(($scope.curpage+1)!=$scope.numpage) {
            $scope.curpage = $scope.curpage + 1;
            $scope.pageset($scope.curpage);
        }
        else
        {
            swal("Displaying","Page " + $scope.numpage+ "of" +  $scope.numpage,"info")
        }
    };
    $scope.activeset=function (pageno) {

        if($scope.curpage==pageno) {

            return "active"
        }
        else {
            return null;
        }
    }







});

