(function () {
    'use strict';
    angular.module('forest5').constant('CONFIG', {
        //Equpo de franklin
  		  // url: 'http://192.168.129.118:8081',
        //url: 'http://54.197.37.166:8080',
        //url: 'http://localhost:8080',
        url: 'http://45.33.34.167:8080',
        contexto: 'engine-rest',

        urlLogin: 'http://54.197.37.166:8080',
        contextoLogin: '',
        //Equipo de franklin
        //urlFormas: 'http://192.168.129.118:8080',
        //EC2
        urlFormas: 'http://45.33.34.167:8092',
        //Equipo de franklin
        //contextoFormas: 'configComponent/mpGrlForma'
        //EC2
        contextoFormas: 'api/form'
    });
})();
