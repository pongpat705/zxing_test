angular.module('routerApp')
  .factory('ComparatorService', function($http) {
    return {
      //one by one
      uploadFileToUrl: function(file){
          var fd = new FormData();
          fd.append('files', file);
        return $http.post(_config.contextPath+'/test', fd, {transformRequest: angular.identity, headers: {'Content-Type': undefined,enctype:'multipart/form-data'}});
      },
      //same Time
      uploadFilesToUrl: function(files){
          var fd = new FormData();
          for (i in files){
        	  fd.append('files', files[i]);
          }
        return $http.post(_config.contextPath+'/test', fd, {transformRequest: angular.identity,headers: {'Content-Type': undefined,enctype:'multipart/form-data'}});
      },
      findMatchedFile: function(code){
    	return $http.get(_config.contextPath+'/findMatchedFile?code='+code);
      },
      submitFiles: function(files){
    	return $http.post(_config.contextPath+'/uploadSelectedFiles',files);  
      }
    };
  });