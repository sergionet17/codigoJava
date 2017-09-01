angular.module('forest5').factory('instanciaprocesos', function ($resource,$http, CONFIG) {
    var param = {key : '@key'};
    var respuesta = false;

  // var paramInstancia = '@procesoId';

return{
  post: function(idProceso) {
    var parametro = {};
    //$http.post(CONFIG.url+'/'+CONFIG.contexto+'/process-definition/'+idProceso+'/start', {}, {}).then(function(data){console.log('Instancia creada')},function(error){console.log('Fallo creando instancia')});
    return $http.post(CONFIG.url+'/'+CONFIG.contexto+'/process-definition/'+idProceso+'/start', {}, {});
  }
}

});
