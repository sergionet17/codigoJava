(function () {
    'use strict';

    angular.module('forest5').factory('tareas', function ($resource,CONFIG) {
        var assignee = {assignee : '@assignee'};
        var variables = {variables : '@variables'};
        console.log(CONFIG.url+'/'+CONFIG.contexto+'/task');
        return $resource(CONFIG.url+'/'+CONFIG.contexto+'/task', assignee,{
            'listar': {
                method: 'GET',
                isArray: true,
                headers: {
                    'Content-Type': 'application/json',
                }
            },
            'actualizar': {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'}
            },
            'sinAsignar':{
                method: 'GET',
                url: CONFIG.url+'/'+CONFIG.contexto+'/task/?unassigned=true',
                headers: {'Content-Type': 'application/json'},
                isArray: true
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
