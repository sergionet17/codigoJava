(function () {
    'use strict';

    angular.module('forest5').factory('formas', function ($resource,$http,$rootScope, CONFIG) {

        //CONFIG.urlFormas+'/'+CONFIG.contextoFormas+'/'+tarea.formKey
        return{
          get: function(formKey) {
            var parametro = {};
              return $http.get(CONFIG.urlFormas+'/'+CONFIG.contextoFormas+'/'+formKey);
          }
        };
    });
})();
