angular.module("app", ["chart.js"])
    .controller("PieCtrl", function ($scope, $http) {

    //$http.get('/chart/pie').success(function(data) {
    //    $scope.pieData = data;
    //})

    $scope.labels = ["Additions", "Deletions"];
    //$scope.data = [$scope.pieData.adds, $scope.pieData.deletes];
        $scope.data = [100,120];
    }
    );