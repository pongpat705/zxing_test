var routerApp = angular.module('routerApp', 
								['ui.router', 
								 'oc.lazyLoad', 
								 'ui.bootstrap', 
								 'ngSanitize',
								 'textAngular',
								 'satellizer',
								 'angularFileUpload',
								 'ngFileUpload',
								 'valdr'
								 ]);

routerApp.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'resource/view/home.html',
            controller: 'homeCtrl',
        	resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                  return $ocLazyLoad.load([{
                      files: [
                              'resource/script/controller/homeCtrl.js',
                              'resource/script/service/ComparatorService.js'
                              ]
                    }]);
                }]
              }
        })
        
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('about', {
            // we'll get to this in a bit       
        });
        
});



routerApp.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var isMultiple = attrs.multiple;
          var modelSetter = model.assign;
          
          element.bind('change', function(){
        	 var values = [];
         	 angular.forEach(element[0].files, function(value, key) {
        		 values.push(value);
        		});
             scope.$apply(function(){
                 if (isMultiple) {
                     modelSetter(scope, values);
                 } else {
                     modelSetter(scope, values[0]);
                 }
//                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);

routerApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
       var fd = new FormData();
       fd.append('file', file);
    
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined,
        	  		enctype:'multipart/form-data'}
       })
    
       .success(function(){
       })
    
       .error(function(){
       });
    }
 }]);