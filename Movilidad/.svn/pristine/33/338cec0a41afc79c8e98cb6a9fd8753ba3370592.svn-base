//http://54.197.37.166:8080/engine-rest/process-definition

(function () {
    'use strict';

    angular.module('forest5').factory('procesos', function ($resource, CONFIG) {
        var param = {key : '@key'};
        //var paramInstancia = @proceso;
        return $resource( CONFIG.url+'/'+CONFIG.contexto+'/process-definition', param,{
            'listar': {
                method: 'GET',
                isArray: true,
                headers: {
                    'Content-Type': 'application/json',
                }
            },
            'crearInstancia': {
                method: 'POST',
            //    url = CONFIG.url+'/'+CONFIG.contexto+'/process-definition/'+param.key+'/start',
                headers: {'Content-Type': 'application/json'}
            },
            interceptor: {
                response: function (response) {
                    var result = response;
                    result.$status = response.status;
                    return response;
                }
            }
        });
    });
})();
