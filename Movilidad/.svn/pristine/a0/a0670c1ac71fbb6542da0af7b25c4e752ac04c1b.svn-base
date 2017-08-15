xui.Class('xui.UI.UploadMultiple', ["xui.UI.Div"], {
	Initialize:function(){
		console.log("inicializando uploadmultiple");
        var ns=this;
        ns._adjustItems = xui.absList._adjustItems;
		//debugger;
    },
	Instance: {
		iniComponents: function () {
			// [[Code created by CrossUI RAD Studio
			console.log("init components");
			var host = this,
			children = [],
			append = function (child) {
				children.push(child.get(0));
			};

			append(
				xui.create("xui.UI.Group")
				.setHost(host, "groupContainer")
				.setLeft("0em")
				.setTop("2.5em")
				.setConDockRelative(true)
				//.setPosition("relative")
				.setDock("fill")
				//.setWidth("18em")
				.setCaption("Archivos")
				.setDockMargin({
                                              "left":0,
                                              "right":0,
                                              "bottom":0,
                                              "top":20
                                          })
				);

			host.groupContainer.append(
				xui.create("xui.UI.Gallery")
				.setHost(host, "galery")
				.setItems([{
							"id": "a",
							"caption": "2007090909-001.pdf",
							"image": "img/pdf.gif"
						}, {
							"id": "b",
							"caption": "2007090909-002.pdf",
							"image": "img/pdf.gif"
						}, {
							"id": "c",
							"caption": "Licitación petroleo cobeñas.docx",
							"image": "img/docx.gif"
						}, {
							"id": "d",
							"caption": "Cuentas relación impuestos.xls",
							"image": "img/xls.gif",
							"disabled": true
						}
					])
				.setDock("fill")
				.setLeft("0em")
				.setTop("0em")
				.setValue("a"));

			append(
				xui.create("xui.UI.ComboInput")
				.setHost(host, "fileInput")
				.setDock("width")
				.setLeft("2.5em")
				.setTop("0em")
				.setWidth("18em")
				.setLabelSize("8em")
				.setLabelCaption("File")
				.setType("file"));

			return children;
			// ]]Code created by CrossUI RAD Studio
		}
	},
	Static:{
		 RenderTrigger: function() {
			console.log("render!!!"); 
			 var instance = this.boxing(); 
			 instance.append(instance.iniComponents());
			 //setTimeout(function(){instance.groupContainer.setTop("1.8em");xxxx=instance;},200);
			  if (this.$inDesign) {
				  instance.galery.setReadonly(true);
				  instance.fileInput.setReadonly(true);
				  instance.galery.onClick(function(){
					  SPA._Designer.selectWidget([instance.get(0).$xid],true);
				  });
				  instance.groupContainer.onClick(function(){
					  SPA._Designer.selectWidget([instance.get(0).$xid],true);
				  });
				  instance.fileInput.onClick(function(){
					  SPA._Designer.selectWidget([instance.get(0).$xid],true);
				  });
			  }
		 },
		DataModel:{
            items:{
                ini:[]
			}
		}
	},
	Appearances:{
		UPLOADMULTIPLE:{}
	},
	EventHandlers:{
		 RenderTrigger:function(){
			 console.log(" RenderTrigger");
		 }
	}
	
});
