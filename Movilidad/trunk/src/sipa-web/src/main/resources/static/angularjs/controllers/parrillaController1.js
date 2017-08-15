angular.module('forest5').factory('factoryx',function(){
  console.log('funciona');
  return 'x';
});

angular.module('forest5').controller('parrillaController', function($scope,$window,$http,$resource,factoryx,CONFIG) {
    var self = this;
    var tareasUsuario = [];
    var tareasSinUsuario = [];
    var usuarios = [];
    var procesos = [];
    var numeroTareasUsuario = 0;

    $scope.usuarioSeleccionado = '';

    /*$http.get('http://localhost:8080/engine-rest/task').
        then(function(response) {
            console.log(response.data);
        });*/


      console.log(localStorage.getItem('usuario'));




    //listado de usuarios
    var urlUsuarios = CONFIG.url+'/'+CONFIG.contexto+'/user';
    var servicioUsuarios = $resource(urlUsuarios,{},{
    });


    servicioUsuarios.query(function(data){
      $scope.usuarios = data;
      console.log(data);
    });



//http://54.197.37.166:8080/engine-rest/process-definition?key=vacacionesV1
    var urlProcesos = CONFIG.url+'/'+CONFIG.contexto+'/process-definition?key=vacacionesV1';

    var procesosLista = $resource(urlProcesos,{},{
    });


    procesosLista.query(function(data){
        console.log('procesos');
        console.log(data);
        self.procesos = data;
    },function(error){
        console.log(error);
    });

    //declarando servicio para las tareas sin asignar, para reparto
    var assignee = {assignee : ''};
    var User = $resource(CONFIG.url+'/engine-rest/task/?unassigned=true', {},{
      'listarxUsuario':{
          method: 'GET',
          isArray: true,
          headers: {
              'Content-Type': 'application/json'
          }
      }
    });


    var UserA = $resource(CONFIG.url+'/engine-rest/task/', {},{
      'listarxUsuario':{
          method: 'GET',
          isArray: true,
          headers: {
              'Content-Type': 'application/json'
          }
      }
    });

    User.listarxUsuario({},function(data){
      console.log('vacias');
      self.tareasSinUsuario = data;
      console.log(self.tareasSinUsuario);

      //llamar a las tareas de usuario
      var assignee1 = {assignee : localStorage.getItem('usuario')};
      console.log(assignee1);
      UserA.listarxUsuario(assignee1,function(data){
        console.log("con cosas");
        self.tareasUsuario = data;
        self.numeroTareasUsuario = self.tareasUsuario.length;
        console.log(self.tareasUsuario);
      });


    });

    this.cambiaTab = function(tab){
      $('ul.tabs').tabs('select_tab', tab);
    }


    this.cargarTarea = function(tarea){
      var urlForma = CONFIG.urlFormas+'/'+CONFIG.contextoFormas+'/'+tarea.formKey;
      console.log(urlForma);
      var formaAsociada = $resource(urlForma,{},{
        'formaxId':{
            method: 'GET',
            isArray: false,
            headers: {
                'Content-Type': 'application/json'
            }
        }
      });
      formaAsociada.formaxId(function(data){
        console.log(data);
        $scope.formData = data;
      });
      self.cambiaTab('test3');
      $scope.tareaACompletar = tarea;
    };

    $scope.cambioUsuarioG = function(tarea){

      console.log(tarea);
      console.log($scope.usuarioSeleccionado);
      var e = document.getElementById("usuariosel");
      var strUser = e.options[e.selectedIndex].value;
      console.log(strUser);
      var usuarioAsignacion = {"userId": strUser};
      var urlAsignacion = CONFIG.url+'/'+CONFIG.contexto+'/'+'task/'+tarea.id+'/assignee';
      console.log(usuarioAsignacion);
      console.log(urlAsignacion);
      if(strUser == '' || strUser == undefined){
        alert('Debe seleccionar un usuario para realizar la asignacion');
        return;
      }
      var asignacionUsuario = $resource(urlAsignacion,{},{
        'asignar':{
          method:'POST',
          headers:{
            'Content-Type': 'application/json'
          }
        }
      });
      asignacionUsuario.asignar(usuarioAsignacion,function(data){
        console.log(data);
        alert('Tarea asignada');
      },function(error){
        console.log(error);
      });

  /*    //lamar a las tareas sin asignar, para reparto
      var assignee = {assignee : ''};
      var UserA = $resource(CONFIG.url+'/engine-rest/task', assignee,{
        'listarxUsuario':{
            method: 'GET',
            isArray: true,
            headers: {
                'Content-Type': 'application/json'
            }
        }
      });

      UserA.listarxUsuario(assignee,function(data){
        console.log('vacias');
        self.tareasSinUsuario = data;
        console.log(self.tareasSinUsuario);

        //llamar a las tareas de usuario
        var assignee1 = {assignee : $localStorage.usuario};

        User.listarxUsuario(assignee1,function(data){
          console.log("con cosas");
          self.tareasUsuario = data;
          self.numeroTareasUsuario = self.tareasUsuario.length;
          console.log(self.tareasUsuario);
        });
      });*/

    }

    $scope.crearInstancia = function(proceso1){
      console.log(proceso1);

      //http://54.197.37.166:8080/engine-rest/process-definition/vacacionesV1:5:70db20a4-1ad3-11e7-b336-0a35727e87ca/start
      var urlInstanciarProceso = CONFIG.url+ '/' + CONFIG.contexto + '/process-definition/' + proceso1 + '/start';
      console.log(urlInstanciarProceso);
      var usuarioAsignacionInstancia = {};

      $http.post(urlInstanciarProceso, {}, {});
      alert('Instancia creada');
    }


    $scope.$onRootScope('task.values', function(event, values) {
    		//alert(JSON.stringify(values));
        console.log(values);
        console.log('tarea asignada');
        console.log($scope.tareaACompletar);
        /*
        {
        "variables":
           {
           	"fechaInicio":
           	{"value": "valor de value", "type":"String"},
           	"valor2":
           	{"value": "valor de valor2","type":"String"},
           	"valido":
           	{"esValido": true, "type":"boolean"}
           }
        }
    */

        //http://54.197.37.166:8080/engine-rest/task/24470892-1ae4-11e7-b336-0a35727e87ca/complete
        var urlAvanzar = CONFIG.url+'/'+CONFIG.contexto+'/'+'task/' + $scope.tareaACompletar.id + '/complete';
        var jsonEnviar = {
                            "variables":
                               {
                               	"fechaInicio":
                               	{"value": values.fechaInicio, "type":"String"},
                               	"fechaFin":
                               	{"value": values.fechaFin,"type":"String"},
                               	"comentarios":
                               	{"value": values.comentarios, "type":"String"},
                                "aprobado":
                                {"value": values.aprobado, "type":"boolean"},
                                "aprobadoVacaciones":
                                {"value": values.aprobadoVacaciones, "type":"boolean"}
                               }
                            };
        $http.post(urlAvanzar,jsonEnviar,{});
        alert('Tarea completada');


      //  $http.post(urlAvanzar, jsonEnviar, {});
      /*    .success(function (data, status, headers, config) {
              console.log(data);
            })
          .error(function (data, status, header, config) {
            console.log(data);
          });*/

    	});


    });

  /*  var forest5 = angular.module('forest5',[]);

    forest5.factory('factoryx', function(){
      console.log('cause fck u');
    });*/
