'use strict';
angular.module('routerApp').controller('homeCtrl', ['$scope', '$window', '$http', '$timeout', '$state' , '$stateParams', 'ComparatorService', 'fileUpload', 
  function roleCtrl($scope, $window, $http, $timeout, $state, $stateParams, ComparatorService, fileUpload) {
	
	$scope.resultList = [];
	
	$scope.$watch("init", function(){
		$scope.loadMatchedFile();
	});
	
	$scope.loadMatchedFile = function(){
		ComparatorService.findMatchedFile($scope.code).then(function(response){
			console.info('success',response);
			$scope.resultList = response.data.result;
		}).catch(function(response){
			console.error('error',response);
		});
	};
	
	$scope.checkMatchedFile = function(){
		$scope.loadMatchedFile();
	};
	
	$scope.openFile = function(file){
		$window.location.href = "file:///"+file;
	};
	
//    $scope.uploadFile = function(){
//        var file = $scope.myFile;
//     	 angular.forEach(file, function(value, key) {
//             ComparatorService.uploadFileToUrl(value).then(function(response){
//             	console.info('success',response);
//             	
//             	$scope.resultList.push(response.data.result);
//             	
//             }).catch(function(response){
//             	console.info('error',response);
//             });
//      	 });  
//
//     };
	
	$scope.submitFiles = function(){
		ComparatorService.submitFiles($scope.resultList).then(function(response){
			console.info('success',response);
		}).catch(function(response){
			console.error('error',response);
		});
	};
     
    $scope.uploadFiles = function(){
         var files = $scope.myFile;
         
         ComparatorService.uploadFilesToUrl(files).then(function(response){
           	console.info('success',response);
           	$scope.resultList = response.data.result;
           }).catch(function(response){
        	console.error('error',response);
           });

      };     
     
     
}]);
