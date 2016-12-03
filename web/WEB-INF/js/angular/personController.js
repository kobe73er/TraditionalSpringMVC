/**
 * Created by kobe73er on 16/10/31.
 */
var app = angular.module('myApp', []);
app.controller('personCtrl', function ($scope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.fullName = function () {
        return $scope.firstName + " " + $scope.lastName;
    };
});

