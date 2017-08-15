xui.Class('xui.UI.SelectorMultiple', 'xui.UI.Div', {
	Instance: {
		
		getUIValue: function () {
			var items = this.ldestino.getItems();
			var value = "";
			for (var i = 0; i < items.length; ++i) {
				if (i > 0) {
					value += ";";
				}
				value += items[i].id;
			}
			return value;
		},
		getItems: function () {
			return this.lorigen.getItems();
		},
		setItems: function (items) {
			this.get(0).properties.items=items;
			this.lorigen.setItems(items);
		},
		setItems2: function (items) {
			this.lorigen.setItems(items);
		},
		getCaptionOrigen:function(){
			return this.gorigen.getCaption();
		},
		getCaptionDestino:function(){
			return this.gdestino.getCaption();
		},
		setCaptionOrigen:function(caption){
			this.get(0).properties.captionOrigen = caption;
			this.gorigen.setCaption(caption);
		},
		setCaptionDestino:function(caption){
			this.get(0).properties.captionDestino=caption;
			this.gdestino.setCaption(caption);
		},
		iniComponents: function () {
			// [[Code created by CrossUI RAD Studio
			var host = this,
			children = [],
			append = function (child) {
				children.push(child.get(0));
			};

			append(
				xui.create("xui.UI.Div")
				.setHost(host, "xui_ui_div13")
				//.setLeft("0em")
				//.setTop("0em")
				//.setWidth("45em")
				//.setHeight("24.166666666666668em")
				.setDock('fill')
				.setCustomStyle({
					"KEY": {
					//	"background-color": "#FFA07A"
					}
				}));

			host.xui_ui_div13.append(
				xui.create("xui.UI.Group")
				.setHost(host, "gdestino")
				.setLeft("26.666666666666668em")
				.setTop("0.8333333333333334em")
				.setWidth("18em")
				.setHeight("22.5em")
				.setCaption("Destino")
				.setToggleBtn(false));

			host.gdestino.append(
				xui.create("xui.UI.List")
				.setHost(host, "ldestino")
				.setDock("fill")
				.setLeft("-2.5833333333333335em")
				.setTop("0.08333333333333333em")
				.setWidth("26.666666666666668em")
				.setSelMode("multi")
				.setLabelSize("8.333333333333334em")
				.setLabelPos("none")
				.setLabelGap("0em")
				.setLabelHAlign("left")
				.setValue("a"));

			host.xui_ui_div13.append(
				xui.create("xui.UI.Group")
				.setHost(host, "gorigen")
				.setLeft("0.8333333333333334em")
				.setTop("0.8333333333333334em")
				.setWidth("18em")
				.setHeight("22.5em")
				.setCaption("Origen")
				.setToggleBtn(false));

			host.gorigen.append(
				xui.create("xui.UI.List")
				.setHost(host, "lorigen")
		.setItems([])
				.setDock("fill")
				.setLeft("-2.5833333333333335em")
				.setTop("0.08333333333333333em")
				.setWidth("26.666666666666668em")
				.setSelMode("multi")
				.setLabelSize("8.333333333333334em")
				.setLabelPos("none")
				.setLabelGap("0em")
				.setLabelHAlign("left")
				.setValue("aa"));

			host.xui_ui_div13.append(
				xui.create("xui.UI.Button")
				.setHost(host, "brmove")
				.setLeft("20.833333333333332em")
				.setTop("5.833333333333333em")
				.setWidth("3.9166666666666665em")
				.setCaption(">")
				.onClick("_brmove_onclick"));

			host.xui_ui_div13.append(
				xui.create("xui.UI.Button")
				.setHost(host, "brmoveall")
				.setLeft("20.833333333333332em")
				.setTop("8.333333333333334em")
				.setWidth("3.9166666666666665em")
				.setCaption(">>")
				.onClick("_brmoveall_onclick"));

			host.xui_ui_div13.append(
				xui.create("xui.UI.Button")
				.setHost(host, "bleftmove")
				.setLeft("20.833333333333332em")
				.setTop("10.833333333333334em")
				.setWidth("3.9166666666666665em")
				.setCaption("<")
				.onClick("_bleftmove_onclick"));

			host.xui_ui_div13.append(
				xui.create("xui.UI.Button")
				.setHost(host, "bleftmoveall")
				.setLeft("20.833333333333332em")
				.setTop("13.333333333333334em")
				.setWidth("3.9166666666666665em")
				.setCaption("<<")
				.onClick("_bleftmoveall_onclick"));

			return children;
			// ]]Code created by CrossUI RAD Studio
		},
		moveElements: function (source, sourceItems, dest, oper) {
			xxx = this;
			var items = [];
			var selection = "";
			for (var i = 0; i < sourceItems.length; ++i) {
				var item = xui.clone(sourceItems[i]);
				if (oper == "lock") {
					source.updateItem(sourceItems[i].id, {
						disabled: true
					});
				}
				if (oper == "unlock") {
					dest.updateItem(sourceItems[i].id, {
						disabled: false
					});
				}
				if (item.disabled) {
					continue;
				}
				item.disabled = false;
				selection += ";" + item.id;
				items.push(item);
			}
			if (oper == "lock") {
				dest.insertItems(items);
			} else if (oper == "unlock") {
				source.removeItems(items);
			}
			source.setUIValue("");
			dest.setUIValue(selection);
		},
		_brmove_onclick: function (profile, e, src, value) {
			var ns = this,
			uictrl = profile.boxing();
			var sourceItems = this.lorigen.getSelectedItem();
			if (sourceItems.length == 0) {
				return;
			}

			this.moveElements(this.lorigen, sourceItems, this.ldestino, 'lock');
		},
		_brmoveall_onclick: function (profile, e, src, value) {
			var ns = this,
			uictrl = profile.boxing();
			this.moveElements(this.lorigen, this.lorigen.getItems(), this.ldestino, 'lock');
		},
		_bleftmove_onclick: function (profile, e, src, value) {
			var ns = this,
			uictrl = profile.boxing();
			var sourceItems = this.ldestino.getSelectedItem();
			if (sourceItems.length == 0) {
				return;
			}

			this.moveElements(this.ldestino, sourceItems, this.lorigen, 'unlock');
		},
		_bleftmoveall_onclick: function (profile, e, src, value) {
			var ns = this,
			uictrl = profile.boxing();
			this.moveElements(this.ldestino, this.ldestino.getItems(), this.lorigen, 'unlock');
		}
	},
	Static: {
		DataModel: {
			urlService: "",
			parametersService: {
				ini: [],
				caption: "parametersService",
				trigger: function () {
					if (this.getProperties().parametersService && typeof(this.getProperties().parametersService) == 'string') {
						// compatibilidad hacia atras cuando era un string
						var parts = this.getProperties().parametersService.replace(/\s/g, '').split(',');
						var result = [];
						for (var i = 0; i < parts.length; ++i) {
							result.push({
								name: parts[i],
								value: parts[i]
							});
						}
						this.setParametersService(result);
					}
					CONF.editParameters(this, this.getProperties().parametersService);
				}
			},
			captionOrigen:"Origen",
			captionDestino:"Destino",
			items:{
				ini:[],
				set: function(items){
					if(this.boxing().lorigen){
					this.boxing().lorigen.setItems(items);
					}
					this.properties.items = items;
				}
			}
		},
		RenderTrigger: function () {
			var instance = this.boxing();
			instance.append(instance.iniComponents());
			var properties = ['captionOrigen','captionDestino','items'];
			if(this.properties.items){
				instance.lorigen.setItems(this.properties.items);
			}
			for(var i=0;i<properties.length;++i){
				if(this.properties[properties[i]]){
					var setter =  "set" + properties[i].substring(0,1).toUpperCase()+  properties[i].substring(1);
					instance[setter](this.properties[properties[i]]);
				}
			}
		},
		_adjustItems: function(items){
			return items;
		}
	}

});
