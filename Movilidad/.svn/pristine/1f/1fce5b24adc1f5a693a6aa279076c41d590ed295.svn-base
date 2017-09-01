angular.module('forest5').controller('dashboardController', function($scope, $window, $http, $resource, tareas, tareasCompletar, procesos, instanciaprocesos, tareasCompletar, formas, usuarios1, tareasAsignar, CONFIG) {

    var self = this;

    var definicionesprocesos = [];
    var definicionesprocesosfiltrados = [];
    var tareasUsuario = [];
    var tareasSinUsuario = [];
    var tareaActual = {};
    var usuarioSeleccionado = '';
    var usuarios = [];

    var key = {
        key: 'Expedientes'
    };

    usuarios[0] = {};
    usuarios[0] = {
        email: 'demo@demo.com',
        firstName: 'demo',
        id: 'demo',
        lastName: 'demo'
    };
    usuarios[1] = {};
    usuarios[1] = {
        email: 'john@john.com',
        firstName: 'john',
        id: 'john',
        lastName: 'john'
    };

    var refrescar = function() {
        $window.location.href = './dashboard.html';
    }


    var consultarDatos = function() {
        //llama todas las definiciones
        procesos.listar(function(data) {
            self.definicionesprocesos = data;
        });

        //llama listado de usuarios
        usuarios1.listar(function(data) {
            self.usuarios = data;
        });

        //llama solo las definiciones de vacaciones
        //procesos.listar(key, function(data) {
        procesos.listar(function(data) {
            self.definicionesprocesosfiltrados = data;
        });

        //llama las tareas de usuario
        var assignee = {
            assignee: localStorage.getItem('usuario')
        };
        tareas.listar(assignee, function(data) {
            self.tareasUsuario = data;
            console.log(self.tareasUsuario);
        });

        //llama tareas para asignar
        tareas.sinAsignar(function(data) {
            self.tareasSinUsuario = data;
        });
    }

    consultarDatos();

    $scope.clickedShit = function() {
        console.log('shit is clicked');
    };

    $scope.cambioUsuario = function() {
        console.log('cambio');
    };

    //métodos del controller

    //Crea una Instancia
    self.crearInstancia = function(proceso) {
        var instanciaCreada = instanciaprocesos.post(proceso);
        instanciaCreada.then(function(data) {
            alert('Instancia creada!');
            refrescar();
        }, function(error) {
            alert('Problemas creando instancia!');
        })
    };

    //asigna tareas a usuario
    self.asignarTarea = function(tarea) {
        var e = document.getElementById("usuariosel");
        var strUser = e.options[e.selectedIndex].value;

        if (strUser == undefined || strUser == '') {
            alert("Debe seleccionar un usuario para asignar");
            return;
        }
        var usuarioAsignacion = {
            "userId": strUser
        };
        //machete para asignar usuario a tareas
        var urlAsignacion = CONFIG.url + '/' + CONFIG.contexto + '/' + 'task/' + tarea.id + '/assignee';
        var asignacionUsuario = $resource(urlAsignacion, {}, {
            'asignar': {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        });
        asignacionUsuario.asignar(usuarioAsignacion, function(data) {
            alert('Tarea asignada');
            refrescar();
        }, function(error) {
            alert('Problemas asignando la tarea');
        });
    };

    //va a una tarea, cargando la respectiva forma asociada
    self.cargarTarea = function(tarea) {
        self.tareaActual = tarea;
        //carga las variabes de instancia

        //http://54.197.37.166:8080/engine-rest/process-instance/74142842-25d1-11e7-98bf-0a35727e87ca/variables/

        var urlConsultaVariables = CONFIG.url + '/' + CONFIG.contexto + '/process-instance/' + self.tareaActual.processInstanceId + '/variables/';
        var variablesProceso = $http.get(urlConsultaVariables);
        var dataVariables = {};
        variablesProceso.then(function(data) {
            dataVariables = data.data;
        }, function(error) {
            console.log(error);
        });



        //La linea comenatariada obtiene las formas de archivos json, la segunda llama al rest
        //jQuery.getJSON("test/" + tarea.formKey + ".json", function(result) {
        // http://54.197.37.166:38080/configComponent/form/bycode/

        if (tarea.formKey == '' || tarea.formKey == undefined) {
            alert('Forma no asociada a la tarea');
            return;
        }
        jQuery("#formxui").empty();
        jQuery.getJSON(CONFIG.urlFormas + '/' + CONFIG.contextoFormas + '/bycode/' + tarea.formKey, function(result) {
                var jso = JSON.parse(result.xml);
                xui.newCom("xui.RenderForm", function() {
                    {
                        this.loadData();
                        self.cambiaTab('test3');
                        //relaciona la forma para manejo global
                        $scope.formaFinal = this.currentForm;
                        console.log("form", this.currentForm);
                        //setea valores a la forma de variables de proceso
                        console.log(dataVariables);
                        if (dataVariables != undefined || dataVariables == {}) {
                            angular.forEach(dataVariables, function(valor, llave) {



                                if ($scope.formaFinal[llave] != undefined && valor.type != "Object") {
                                  console.log(llave);
                                  console.log($scope.formaFinal[llave]);
                                  console.log($scope.formaFinal[llave].KEY);
                                  var tipo = 'xxx';
                                  if(($scope.formaFinal[llave].KEY).indexOf("Link") > 0)
                                    tipo = 'link'
                                  if(($scope.formaFinal[llave].KEY).indexOf("Label")> 0)
                                    tipo = 'label';
                                  console.log(tipo);

                                    switch (tipo) {
                                        case 'label':
                                            console.log('label!');
                                            $scope.formaFinal[llave].setCaption(valor.value);
                                            break;
                                        case 'link':
                                            console.log('link!');
                                            $scope.formaFinal[llave].setHref(valor.value);
                                            break;
                                        default:
                                            console.log('default!');
                                            $scope.formaFinal[llave].setValue(valor.value);
                                    }
                                }

                                //si la variable de proceso es tipo object, pasó por un dmn
                                if (valor.type == "Object") {
                                    console.log($scope.formaFinal);
                                    angular.forEach(valor.value, function(subValor, subLlave) {
                                        if ($scope.formaFinal[subLlave] != undefined) {
                                                  var tipo = 'xxx';
                                                  if(($scope.formaFinal[subLlave].KEY).indexOf("Link") > 0)
                                                    tipo = 'link'
                                                  if(($scope.formaFinal[subLlave].KEY).indexOf("Label")> 0)
                                                    tipo = 'label';
                                                switch (tipo) {
                                                    case 'label':
                                                        console.log('label!');
                                                        $scope.formaFinal[subLlave].setCaption(subValor);
                                                        break;
                                                    case 'link':
                                                        console.log('link!');
                                                        $scope.formaFinal[subLlave].setHref(subValor);
                                                        break;
                                                    default:
                                                        console.log('default!');
                                                        $scope.formaFinal[subLlave].setValue(subValor);
                                                }
                                        }
                                    });
                                }
                            });
                        }

                        var x = jQuery("#formxui");
                        x.width("100%");
                        x.width(x.width() + "px");
                        x.height("600px")
                        self.cambiaTab('test3');
                        xui("formxui").append(this.rootPane);
                        return this;
                    }
                }, undefined, {
                   // form: result
                  
                   form: {xml: result.xml, conf: jso} 
                });
            }).fail(function(e) {
                //TODO definir que hacer si falla
                console.log(e);
            });



    };

    //escucha la respuesta de la forma
    //deprecated por nuevo formuibuilder
    $scope.$onRootScope('task.values', function(event, values) {
        var obj = {};
        //var respuesta = '"variables" : {';
        var respuesta = ' { ';
        console.log(values);
        respuesta.variables = {};

        angular.forEach(values, function(valor, llave) {
            console.log(llave + ':' + valor);
            respuesta = respuesta + '"' + llave + '":{ "value":' + valor + '},';

        });

        respuesta = respuesta + '}';
        respuesta = respuesta.replace(',}', '}');
        obj = JSON.parse(respuesta);
        console.log(obj);
        console.log(self.tareaActual);
        tareasCompletar.post(self.tareaActual.id, obj);
    });

    //avanza la tarea
    this.avanzaTarea = function() {
        var respuesta = ' { "variables": { ';
        var temp = "";
        var tipo = "";

        //ciclo para recorrer los datos de la forma
        for (var key in $scope.formaFinal) {
            console.log(key);
            console.log($scope.formaFinal);
            if ($scope.formaFinal[key].getUIValue) {
                temp = $scope.formaFinal[key].getUIValue();
                tipo = $scope.formaFinal[key].n0.properties.desc;
                if (tipo == 'date')
                    temp = this.formatoFechas(temp);
                respuesta = respuesta + '"' + key + '":{ "value": "' + temp + '", "type": "' + tipo + '"},';
            }
        }
        //termina de armar las variables que van a hacer de proceso
        respuesta = respuesta + '}}';
        respuesta = respuesta.replace(',}', '}');
        jsonEnviar = JSON.parse(respuesta);
        //machetazo, cuadrar en un resource a futuro
        //configura url para avanzar la tarea
        var urlAvanzar = CONFIG.url + '/' + CONFIG.contexto + '/' + 'task/' + self.tareaActual.id + '/complete';
        console.log(jsonEnviar);
        var avance = $http.post(urlAvanzar, jsonEnviar, {});
        avance.then(function(data) {
            alert('Tarea avanzada');
            refrescar();
        }, function(error) {
            alert('Error avanzando tarea');
        });
    }

    //cambia de tab
    this.cambiaTab = function(tab) {
        jQuery('ul.tabs').tabs('select_tab', tab);
    }

    this.eventoCambioTab = function() {
        alert('cambio tab');
    }

    this.formatoFechas = function(fecha) {

        //{ "variables": { "fechaInicio":{ "value": "2017-04-08", "type": "date"},"fechaFin":{ "value": "2017-04-12", "type": "date"},"comentarios":{ "value": "asdf123", "type": "text"}}}
        var day = fecha.getDate();
        if (String(day).length == 1)
            day = '0' + day;
        var monthIndex = fecha.getMonth();
        if (String(monthIndex).length == 1)
            monthIndex = '0' + monthIndex
        var year = fecha.getFullYear();

        //return day + '/' + monthIndex + '/' + year;
        return year + '-' + monthIndex + '-' + day + 'T 00:00:00.0';
    }
});
