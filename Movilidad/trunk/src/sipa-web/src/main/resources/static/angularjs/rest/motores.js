(function () {
    'use strict';
    angular.module('administracion').factory('motores', function ($resource,CONFIG) {
        return $resource(CONFIG.url+'/'+CONFIG.contexto+'/engine', {},{
            'listar': {
                method: 'GET',
                isArray: true,
                headers: {
                    'Content-Type': 'application/json',
                }
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
