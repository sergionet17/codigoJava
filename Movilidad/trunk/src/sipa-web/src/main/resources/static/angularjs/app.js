(function() {
  'use strict';
var module = angular.module('forest5', ['ngResource','ngStorage', 'forest5.directives']);

	module.config(['$provide', function ($provide) {
		
		$provide.decorator('$rootScope', ['$delegate', function($delegate) {
			Object.defineProperty($delegate.constructor.prototype, '$onRootScope', {
				value: function(name, listener){
					var unsubscribe = $delegate.$on(name, listener);
					this.$on('$destroy', unsubscribe);
				},
				enumerable: false
			});
			return $delegate;
		}]); 
		

	}]);

})();
