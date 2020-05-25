'use strict';

var thankCtrl = angular.module("surveyApp");

thankCtrl.controller("thanksController", function($scope, $rootScope, $http) {
	$scope.thanksMessage = "Thank you for 'taking our Survey!";
});