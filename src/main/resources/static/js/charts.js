angular.module('charts', [])
    .controller('charts', function($scope, $http) {
        $http.get('/resource/').success(function(data) {
            $scope.greeting = data;
        })
    });