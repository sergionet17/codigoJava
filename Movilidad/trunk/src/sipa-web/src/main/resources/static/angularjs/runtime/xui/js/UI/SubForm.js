xui.Class("xui.UI.SubForm", "xui.UI.Div", {
	Initialize: function (xxx) {},
	Instance: {		
	    __subForm:null,
		getSubForm:function(){return this.__subForm;},
		setSubForm:function(sf){this.__subForm = sf;},
		getChildren:function(){return [];},
		iniComponents: function () {
			if(this.getCodForm() && !this.$inDesign){
				this.loadForm(this.getCodForm());
			}
			// [[Code created by CrossUI RAD Studio
			var host = this,
			children = [],
			append = function (child) {
				children.push(child.get(0));
			};

			append(
				xui.create("xui.UI.Div")
				.setHost(host, "______div")
				.setDock("fill")
				.setLeft("3.3333333333333335em")
				.setTop("5.833333333333333em")
				.setWidth("50em")
				.setHeight("30em"));
				return children;
		},
		loadForm: function(codForm){
			var athis = this;
			athis.busy();
			athis.removeChildren(true,true);
			jQuery.getJSON(CONFIG.urlFormas + '/' + CONFIG.contextoFormas + '/bycode/'+codForm, {})
							.done(function(formDef){
								xui.newCom("xui.RenderForm", function() {
                            {
								athis.free();
								if(!athis.get(0).$inDesign)
								  {
									  this.loadData();
								  }
                         	    athis.append(this.rootPane);
								athis.setSubForm(this.rootPane.getHost());
								athis.get(0).children=[];
                        }
                    }, undefined, {form:formDef});
								
							})
							.fail(function(err){
								athis.setSubForm(null);
								athis.free();
								console.log(err);
								});
			
		}
		
	},
	Static: {
		DataModel: {
			parentForm:{
				ini:{},
				hidden:true
			},
			codForm: {			
			ini:"",
			set: function(newVal){
				this.boxing().setSubForm(null);
				if(!this.boxing().$inDesign)
				   {
					   this.boxing().loadForm(newVal);
				   }
			}
			}
			
		},
		RenderTrigger: function () {
			var instance = this.boxing();
			var tmpc=[];
			instance.$inDesign = this.$inDesign;
			instance.append(instance.iniComponents());
			instance.setDisabled(!!this.$inDesign);
			//instance.iniComponents();
			if (!this.$inDesign) {
				
			}
		}
	}
});
