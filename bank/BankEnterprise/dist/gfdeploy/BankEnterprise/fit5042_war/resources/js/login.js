/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('loginApp', []);
app.controller('loginController', function ($scope) {
    $scope.IsVisible = document.getElementById("redirect") === null ? true : false;
});

