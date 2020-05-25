'use strict';

var surveyModule = angular.module("surveyApp");

surveyModule.controller("surveyController", function($scope, $rootScope, $http, $location) {
	
	$rootScope.isSuperAdmin = false;
	$rootScope.username = "";
	
	$http({
		method : "POST",
		url : baseUrl + "validateSuperAdmin",
		headers : {'Content-Type' : 'application/json'}
	}).then(function(response) {
		var JSONObject = response.data;
		
		for (var i=0; i < JSONObject.length; i++){

			var arrayItem = JSONObject[i];
			
		    var key = arrayItem.key;
		    
		    if(key == "username"){
		    	$rootScope.username = arrayItem.value;
		    }else{
		    	if(key == "message"){
			    	var itemVal = arrayItem.value;
			    	if(itemVal == "Success"){
			    		$rootScope.isSuperAdmin = true;
			    	}else{
			    		$rootScope.isSuperAdmin = false;
			    	}
			    }
		    }
		}
			
	});
	
	$scope.issueTypeErr = "";
	$scope.serviceTimeRatingErr = "";
	$scope.serviceRatingErr = "";
			
	$scope.doSubmitSurvey = function() {
		
		if($scope.issue == "" || $scope.issue == undefined){
			$scope.issueTypeErr = "Issue Type is Manadatory";
			$scope.serviceRatingErr = "";
			$scope.serviceTimeRatingErr = "";
			return false;
		}
		
		if($scope.servicerating == "" || $scope.servicerating == undefined){
			$scope.serviceRatingErr = "Service Rating is Manadatory";
			$scope.issueTypeErr = "";
			$scope.serviceTimeRatingErr = "";
			return false;
		}
		
		if($scope.servicetimetating == ""|| $scope.servicetimetating == undefined){
			$scope.serviceTimeRatingErr = "Service Time Rating is Manadatory";
			$scope.serviceRatingErr = "";
			$scope.issueTypeErr = "";
			return false;
		}
		
		if(($scope.servicetimetating == 1) && ($scope.servicerating == 1) && ($scope.feedback == "" || $scope.feedback == undefined)){
			$scope.feedbackErr = "Please Fill";
			$scope.issueTypeErr = "";
			$scope.serviceTimeRatingErr = "";
			$scope.serviceRatingErr = "";
			return false;
		}

		$('#loading_Overlay').show();
		$('#loading_img').show();
		
		var dataObj = {
				"issue" : $scope.issue,
				"servicerating" : $scope.servicerating,
				"servicetimetating" : $scope.servicetimetating,
				"feedback" : $scope.feedback,
				"optional" : $scope.optional,
				"username" : $scope.username
		};	
		
		$http({
			method : "POST",
			url : baseUrl + "addSurvey",
			data : JSON.stringify(dataObj),
			headers : {'Content-Type' : 'application/json'}
		}).then(function(response) {
			$('#loading_Overlay').hide();
			$('#loading_img').hide();
			window.location = "#/thanks";
		});
		
	};
	
$scope.downloadAdminStuff = function() {
	window.location = "#/admin";
}

});