xui.Class("xui.UI.AutoComplete", "xui.UI.ComboInput", {
	Initialize: function (xxx) {},
	Instance: {
		setForId:function(id){
			if(!this.getUrlServiceLoadFromId()){
				xui.message("urlServiceLoadFromId not Defined");
				return;
			}
			var ns = this;
			var idRef = id;
			//xui.Thread.observableRun(function (threadid) {
				var url = ns.getUrlServiceLoadFromId();
				if(url.indexOf("?")<0){
					url+="?";
				}else{
					url+="&";
				}
				url += "id=" + idRef + "&___rand=" + Math.random();
				console.log(url);
				jQuery.getJSON(url,{}).done( function (x) {
					ns.setItems(x);
					ns.setUIValue(x[0].caption);
				}).fail( function (error) {
					ns.setItems([]);
					console.log(error);					
				});//.start();
			//});
		},
		getRefUIValue: function(){
			return this.getRefValue(this.getUIValue());
		},
		getRefValue: function(uiValue){
			var items=this.getItems();
			for(var i=0;i< items.length;++i){
				if(items[i].caption == uiValue){
					return items[i].id;
				}
			}
			return null;
		}
	},
	Static: {
		DataModel: {
			urlServiceLoadFromId:"",
			remote:false,
			type: {
				ini: 'combobox',
				hidden: true
			},
			commandBtn: 'delete',
			correct: {
				ini: false,
				hidden: true
			},
			currentValue: {
				hidden: true,
				ini: null
			},
			minLength: 2
		},
		RenderTrigger: function () {

			this.box.$drop = {};
			if (!this.$inDesign && !window.SPA) {
				var instance = this.boxing();
				instance.setDynCheck(true);
				instance.checkValid = function () {
					return false;
				};
				instance.getValue = function () {
					var cv = instance.getCurrentValue();
					if (!cv) {
						return null;
					}
					return cv.item.id;
				};
				instance.onCommand(function () {
					instance.setCurrentValue(null);
					instance.resetValue();
				});
				var ref = true;
				var gl = this;
				var input = this.getSubNode("INPUT").get(0);
				instance.beforeFormatCheck(function (profile, value) {
					if (input.value == '')
						return true;
					return !!instance.getRefValue(value);
					//return instance.getCorrect();
				});
				instance.beforeComboPop(function () {
					return false;
				});
				var accentMap = {
					"\00f3": "o"
				};
				var normalize = function (term) {
					var ret = "";
					for (var i = 0; i < term.length; i++) {
						ret += accentMap[term.charAt(i)] || term.charAt(i);
					}
					return ret;
				};

				xxx = jQuery(input);
				xxx.keyup(function (e) {
					instance.setCorrect(false);
				});
				var source;
				if (instance.getRemote()) {
					source = function (request, response) {
						//Fix verificar dependencias
						var depsArr = instance.getParametersService();
						var params = {
							search: request.term
						};
						if(typeof(depsArr)=='string'){
							 var tmp = depsArr.replace(/\s/g, '').split(',');
							 depsArr =[];
							 for(var j=0;j<tmp-length;++j){
								depsArr.push({name:tmp[j],value:tmp[j]}); 
							 }
						} 
 						if(depsArr && depsArr.length>0){
						
						var host = instance.getHost();
						for (var ii = 0; ii < depsArr.length; ++ii) {
							 var value = host[depsArr[ii].value].getRefUIValue?host[depsArr[ii].value].getRefUIValue():host[depsArr[ii].value].getUIValue();
							 if(value){
								 params[depsArr[ii].name]=value;
							 }
							 else{
								 response([]);
								 return;
							 }
						}
						}
						jQuery.getJSON(instance.getUrlService(), params)
						.done(function (items) {
							instance.setItems(items);
							for (var i = 0; i < items.length; ++i) {
								items[i].label = items[i].caption;
							}
							response(items);
						})
						.fail(function (err) {
							console.log("err autocomplete", err);
							instance.setItems([]);
						});
					};
				} else {
					source = function (request, response) {
						var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
						response(jQuery.grep(instance.getItems(), function (value) {
								value.label = value.caption;
								value = value.caption || value.id || value;
								return matcher.test(value) || matcher.test(normalize(value));
							}));
					}

				}
			if(xxx.autocomplete2){
				xxx.autocomplete2({
					autoFocus: true,
					minLength: instance.getMinLength(),
					source: source,
					onInvalidateSelection: function () {
						console.log(new Date() + " invalid: " + input.value);
					},
					select: function (event, ui) {
						instance.setCorrect(true);
						instance.setCurrentValue(ui);
						input.blur();
					}

				});
			}

			}
		}
	}
});
