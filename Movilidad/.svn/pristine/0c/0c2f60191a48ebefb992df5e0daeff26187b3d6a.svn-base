var directives = angular.module('forest5.directives', []);

directives.directive('xui', ['$window', '$http', '$rootScope', function($window, $http, $rootScope) {
	return {
		restrict: 'EA',
		scope: {
			mpFormValues: '='
		},
	    link: function (scope, element, attrs) {
			
			var form = {};
			
			scope.$watch('mpFormValues', function (val) {
				if (scope.mpFormValues) {
					buildApp(JSON.parse(val.xml));
				}
            }, true);
			
			function buildApp(data) {
				
				$window.xui.Class('App','xui.Module',{
					Instance:{
						Dependencies:[],
						Required:[],
						properties:{},
						initialize:function(){},
						iniComponents:function(){
							var host=this,
							children=[],
							append=function(child){
								children.push(child.get(0));
							};
							return children;
						}, 
						customAppend:function(parent,subId,left,top) {
							return false;
						}
					},
                    Instance:{
						iniComponents:function() {
							// [[Code created by CrossUI RAD Studio
                            var host=this, 
							children=[], 
							append = function(child){
								children.push(child.get(0));
							};
							
							xui.each(data, function(o,i) {
								if (o.key !== "library") {
									o.key = o.key.replace("linb", "xui"); 
									var obj = xui.create(o);
									if (o.key === 'xui.UI.Input') {
										form[o.alias] = obj;
									}
									xui(element[0]).append(obj);
								}
							});
									
							var button = (new xui.UI.Button)
								.setHost(host,"button22")
								.setLeft(500).setTop(360)
								.setCaption("Get form values")
								.onClick("_button22_onclick");
							xui(element[0]).append(button);	
									
							return children;
							// ]]Code created by CrossUI RAD Studio
						},
						_button22_onclick:function (profile, e, value) {
							var values = {}
							for (var i in form) {
								values[i] = form[i].getUIValue();
							}
							$rootScope.$emit('task.values', values);
						}
					}
				});
				xui.launch('App', function(){xui('loading').remove();},'en','default');
			}
		}
	}
}]);