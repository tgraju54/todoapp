//creating an application module
var todoAppModule = angular.module("todoApp", []);

//code will get graph data from controller and plot the graph
todoAppModule.controller("graphCtrl", function($scope, $rootScope, $http, $location,$window){

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







    $http.get('/api/graph/index/') //reading the json file
        .success(function (data) {

            $scope.graphdata=data;
            console.log(data);
            // binding the data to the $scope variable
        })
        .error(function (data, status) {
            console.error('failure loading the student record', status, data);
            $scope.tdetail = {}; //return blank record if something goes wrong
        });
    //plotting the google graphs
    google.charts.load('current', {'packages':['line']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var data = new google.visualization.DataTable();
        data.addColumn('number', 'Day');
        data.addColumn('number', 'Productivity');


        data.addRows([
            [1,  $scope.graphdata[0] ],
            [2,$scope.graphdata[1]  ],
            [3, $scope.graphdata[2]  ],
            [4,  $scope.graphdata[3] ],
            [5,  $scope.graphdata[4] ],
            [6,   $scope.graphdata[5] ],
            [7,  $scope.graphdata[6] ]

        ]);

        var options = {
            chart: {
                title: 'Productivity Graph',
                subtitle: 'your productivity this week '
            },
            width: 900,
            height: 500,
            axes: {
                x: {
                    0: {side: 'top'}
                }
            }
        };

        var chart = new google.charts.Line(document.getElementById('line_top_x'));

        chart.draw(data, google.charts.Line.convertOptions(options));
    }


});//end