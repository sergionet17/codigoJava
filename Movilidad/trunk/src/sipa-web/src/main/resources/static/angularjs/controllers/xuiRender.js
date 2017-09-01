xui.Class('xui.RenderForm', 'xui.Module', {
	Instance: {

		iniComponents: function (objectRef) {
			// [[Code created by CrossUI RAD Studio
			var host = this,
			children = [],
			append = function (child) {
				children.push(child.get(0));
			};
			append((new xui.UI.Pane()).setHost(host, "rootPane").setDock("fill"));
			//host.rootPane.setOverflow("hidden");
			host.rootPane.setHeight("100%");
			if (objectRef.properties.form.conf) {
				var conf = objectRef.properties.form.conf;
				if (conf.formAlign && !(conf.formAlign == 'width')) {
					host.rootPane.setWidth(conf.width);
					host.rootPane.setHeight(conf.height);
				}
				else{
					$(window).resize(function () {
                                                console.log(1,jQuery(window).width())
                                                
						jQuery("#formxui").width(conf.parent?conf.parent.width():jQuery(window).width());
						jQuery("#formxui").height(jQuery(window).height());
						host.rootPane.refresh();
					});
					jQuery("#formxui").width(conf.parent?conf.parent.width():jQuery(window).width());
					jQuery("#formxui").height(jQuery(window).height());
					host.rootPane.refresh();
				}
				host.rootPane.setDock(conf.formAlign ? conf.formAlign : 'fill');
			}

			this.rootPane = host.rootPane;
			var map = {};
			var hiddens = [];
			function customize(object) {
				var o = object.get(0).boxing();
				console.log(o.getAlias());
				if (o.getStartHidden && o.getStartHidden()) {
					//start Hidden Element
					o.setDisplay("none");
					hiddens.push(o);
				}
				if (o.getDataBinder && o.getDataBinder()) {
					var ref = o.getHost()[o.getDataBinder()];

					if (ref) {
						var profile = ref.get(0);
						if (!profile._n) {
							profile._n = [];
						}
						//console.log("referencia databinder profile",profile);
						profile._n.push(o.get(0));
					}
				}
				if (!map[o.KEY]) {
					map[o.KEY] = [];
				}
				map[o.KEY].push(o);
			}
			this.map = map;
			this.currentForm = {};
			var result = objectRef.properties.form;
			result.xml = result.xml.replace(new RegExp("\"host\":this,", 'g'), "");
			result.xml = result.xml.replace(/\n/g, "");
			var jso = eval("temp=" + result.xml + ";"); //JSON.parse(result.xml);
			host.rootPane.append(xui.UI.pack(this.unserialize(
						jso.arrayForm ? jso.arrayForm : jso, undefined, this.currentForm, customize)));
			setTimeout(function(){
				for(var i=0;i<hiddens.length;++i){
					hiddens[i].hide();
					hiddens[i].setDisplay("");
				}
			},1000)
			return children;
			// ]]Code created by CrossUI RAD Studio
		},
		events: {
			onReady: "_onready"
		},
		_onready: function () {},
		unserialize: function (target, keepSerialId, host, customize) {
			var c = customize ? customize : function () {}
			if (typeof target == 'string')
				target = xui.unserialize(target);
			var f = function (o) {
				if (xui.isArr(o))
					o = o[0];
				delete o.serialId;
				if (o.children)
					xui.arr.each(o.children, f);
			},

			a = [];
			var ahost = host;
			var reHost = function (arr) {
				for (var i = 0; arr && i < arr.length; ++i) {
					if (typeof arr[i] == 'string') {
						continue;
					}
					var sub = arr[i];
					for (var j = 0; j < sub.length; ++j) {
						if (!sub[j] || typeof sub[j] == 'string') {
							continue;
						}
						var b = sub[j].boxing();
						b.setHost(ahost);
						c(b);
						reHost(sub[j].children);
					}
				}
			}

			xui.arr.each(target, function (o) {
				if (!keepSerialId)
					f(o);
				var obj = (new(xui.SC(o.key))(o));
				var o0 = obj.get(0);
				a.push(o0);
				obj.setHost(ahost, o.alias);
				c(obj);
				reHost(o0.children);
			});
			return xui.UI.pack(a, false);
		},
		loadData: function () {
			var athis = this;
			athis.totalListas = 0;
			athis.totalListasCargadas = 0;
			setTimeout(function () {
				var listas = ['xui.UI.SelectorMultiple', 'xui.UI.ComboInput', 'xui.UI.List', 'xui.UI.Gallery', 'xui.UI.TreeGrid', 'xui.UI.RadioBox', 'xui.DataBinder', 'xui.UI.AutoComplete', 'xui.UI.TreeGridPaginated'];
				//iterar en las listas para cargar aquellas que tienen url de servicio

				for (var index = 0; index < listas.length; ++index) {
					var key = listas[index];
					var components = athis.map[key];
					if (!components) {
						continue;
					}

					for (var indexComponents = 0; indexComponents < components.length; ++indexComponents) {
						var o = components[indexComponents];
						if (o.getRemote && o.getRemote()) {
							if (o.getParametersService().length > 0) {
								//si el servicio tiene parametros, se construyen los eventos necesarios onchangue
								athis.createDependencies(o);
							}
							continue;
						}
						if (o.KEY == 'xui.UI.TreeGrid' || o.KEY == 'xui.UI.TreeGridPaginated') {
							athis.loadDataGrid(o);
						}

						if (o && o.getUrlService()) {
							if (o.KEY == 'xui.UI.TreeGrid' || o.KEY == 'xui.UI.TreeGridPaginated') {
								o.setRows([]);
							} else if (o.KEY == 'xui.DataBinder') {
								//..
							} else {
								o.setItems([]);
							}
						}
						if (o && o.getUrlService() && o.getParametersService().length == 0) {
							//solo se pueden cargar los objetos que tienen definida una url y no tienen parametros
							athis.totalListas++;
							jQuery.getJSON(o.getUrlService(), {})
							.done(function (items) {
								if (this.KEY == 'xui.UI.TreeGrid' || this.KEY == 'xui.UI.TreeGridPaginated') {
									this.setRows(items);
								} else if (this.KEY == 'xui.DataBinder') {
									this.setData(items);
									this.updateDataToUI();
								} else {
									this.setItems(items);
								}
								athis.totalListasCargadas++;
							}
								.bind(o))
							.fail(
								function (e) {
								console.log(e[1]);
								if (this.KEY == 'xui.UI.TreeGrid' || this.KEY == 'xui.UI.TreeGridPaginated') {
									this.setRows([]);
								} else if (this.KEY == 'xui.DataBinder') {
									this.setData({});
								} else {
									this.setItems([]);
								}
								athis.totalListasCargadas++;
							}
								.bind(o));
						} else if (o.getParametersService().length > 0) {
							//si el servicio tiene parametros, se construyen los eventos necesarios onchangue
							athis.createDependencies(o);
						}
					}
				}

			}, 500);

		},
		loadDataGrid: function (grid) {
			var h = grid.getHeader();
			var mapKeys = {};
			for (var i = 0; i < h.length; ++i) {
				var urlService = h[i].urlService;
				var parametersService = h[i].parametersService;
				mapKeys[h[i].id] = i;
				if (urlService && !(parametersService && parametersService.length > 0)) {

					console.log(urlService);
					jQuery.getJSON(urlService, {})
					.done(function (items) {
						this.editorListItems = items;
					}
						.bind(h[i]))
					.fail(
						function (e) {
						console.log(e[1]);
						this.editorListItems = [];
					}
						.bind(h[i]));
				}
				//registrar mapeo para eventos
				if (parametersService && parametersService.length > 0) {
					//Regla los parametros deben estar siempre antes del campo
					for (var j = 0; j < parametersService.length; ++j) {
						var index = mapKeys[parametersService[j].value];
						if (typeof(index) != 'number') {
							console.log("Referencia (" + parametersService[j].value + ") no encontrada en parametros de servicio  de la columna " + h[i].id + " del  grid " + grid.getAlias());
							continue;
						}
						var col = h[index];
						if (!col.refColServices) {
							col.refColServices = [];
						}
						col.refColServices.push(h[i].id);
					}
					console.log("parameters ", h[i].id);
				}
			}

		},

		/**
		 * busca las  dependencias y les crea el evento on change para cargar la lista
		 */
		createDependencies: function (objDest) {

			var depsArr = objDest.getParametersService();

			for (var ii = 0; ii < depsArr.length; ++ii) {
				var host = objDest.getHost();
				var obj = host[depsArr[ii].value];
				if (!obj) {
					console.log("element ", depsArr[ii].value, " not found");
					continue;
				}
				if (obj.deps) {
					//console.log("another event to ", objDest.getAlias(), "->", obj.getAlias());
					obj.deps.push(objDest.getAlias());
					continue;
				}
				obj.deps = [objDest.getAlias()];
				//console.log("event to ", objDest.getAlias(), "->", obj.getAlias());
				var oldOc = obj.getEvents().onChange;
				var athis = this;
				var customOc = function (profile) {
					var ctrlui = profile.boxing();
					for (var i2 = 0; i2 < ctrlui.deps.length; i2++) {
						athis.loadListServiceFromParams(ctrlui.getHost()[ctrlui.deps[i2]]);
					}
				}
				if (!oldOc) {
					obj.onChange(customOc);
					return;
				}
				if (Array.isArray(oldOc)) {

					var oc = (function (actions, o) {
						return function () {
							var temp = {},
							host = this,
							args = arguments;
							for (var i = 0; i < actions.length; ++i) {
								var a = actions[i];
								console.log("pseudocode action:", a.desc);
								xui.pseudocode.exec(a, args, host, temp);
							}

							console.log("actions", actions);
							customOc.apply(this, arguments);
						}
					})(oldOc, obj);
					obj.onChange(oc);
				} else {
					//is string
					var fun = typeof(oldOc) == 'string' ? this[oldOc] : oldOc;
					obj.onChange(function () {
						customOc.apply(this, arguments);
						if (fun) {
							return fun.apply(this, arguments);
						}
					});
				}
			}
		},
		/**
		 * Carga una lista de un elemento dependiendo del parametro
		 */
		loadListServiceFromParams: function (objRef) {
			objRef.resetValue(); //TODO probar onchange en cascada array deps
			objRef.setItems([]);
			var extra = objRef.getUrlService().indexOf("?") >= 0;
			var psArray = objRef.getParametersService();
			var query = "";
			for (var i = 0; i < psArray.length; ++i) {
				var host = objRef.getHost();
				var c = host[psArray[i].value];
				if (extra || i > 0) {
					query += '&';
				}
				var pvalue = c.getRefUIValue ? c.getRefUIValue() : c.getUIValue();
				if (pvalue == null) {
					if (objRef.setItems) {
						objRef.setItems([]);
					}
					return;
				}
				query += psArray[i].name + "=" + (pvalue);
			}
			var o = objRef;

			jQuery.getJSON(o.getUrlService(), query)
			.done(function (items) {
				this.setItems(items);
			}
				.bind(o))
			.fail(
				function (e) {
				console.log(e);
				this.setItems([]);
			}
				.bind(o));
		}
	}
});
