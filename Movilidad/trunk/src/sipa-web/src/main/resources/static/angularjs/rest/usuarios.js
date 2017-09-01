(function () {
    'use strict';
    angular.module('forest5').factory('usuarios1', function ($resource,CONFIG) {
        return $resource(CONFIG.url+'/'+CONFIG.contexto+'/user', {},{
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
