angular.module('forest5').factory('tareasCompletar', function($resource,$http,CONFIG){
  var variables = {variables : '@variables'};
  return {
    post: function(idTarea, variables){
          console.log(variables);
          $http.post(CONFIG.url+'/'+CONFIG.contexto+'/task/'+idTarea+'/complete',variables,{}).then(function(data){console.log('Tarea finalizada')},function(error){console.log('Fallo finalizando tarea')});
    }
  }
});
