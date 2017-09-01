//var forest5 = angular.module('forest5', []);

angular.module('forest5').controller('loginController', function($scope,$window,$localStorage) {
    var self = this;
    var usuario = '';
    var password = '';

    $scope.validar = function(){
        console.log(self.usuario + '--' + self.password);
        $scope.$storage = $localStorage;
        $localStorage.usuario = '';
        if(self.usuario == 'demo' && self.password == 'demo'){
          $localStorage.usuario = 'demo';
          localStorage.setItem('usuario','demo');
        }

        if(self.usuario == 'john' && self.password == 'john'){
          $localStorage.usuario = 'john';
          localStorage.setItem('usuario','john');
        }
        if($localStorage.usuario == '')
          alert('Credenciales incorrectas');
        else{
          console.log('antes de redireccion');
          console.log($localStorage.usuario);
          //selecciona usuarios antes de ir al dashboard
      /*    usuarios1.listar(function(data){
            console.log('usuarios');
            //self.usuarios = data;*/
  //          localStorage.setItem('usuarios',data);
            $window.location.href = './dashboard.html';
    //      });

        }
    };

});
