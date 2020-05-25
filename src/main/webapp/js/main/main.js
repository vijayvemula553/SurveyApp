'use strict';

var baseUrl = "/survey/survey/";

(function() {

	var app = angular.module("surveyApp",
			[ 'ngCookies', 'ui.bootstrap', 'ngRoute','ngGrid', 'ui.bootstrap.datetimepicker','googlechart']);
	

	app.config(function($routeProvider){
		$routeProvider.
			when('/', {
			  templateUrl:'htm/application/survey.htm?v=1.0',
			  controller:'surveyController'
			  }).
			when('/thanks', {
				templateUrl:'htm/application/thanks.htm?v=1.0',
				controller:'thanksController'
					  }).
			when('/admin', {
				templateUrl:'htm/application/admin.htm?v=1.0',
				controller:'adminController'
								  }).		  
			otherwise( {
			  redirectTo : '/survey',
			  });

		});

}());