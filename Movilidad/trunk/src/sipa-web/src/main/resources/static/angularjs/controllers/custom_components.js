//**
// * Permite extender las propiedades de los componentes indicados
// * @param components array de nombres de componentes
// * @param propiedades objeto que tiene las propiedades que se desean agregar
// * //
function extDataModel(components,propiedades){
	for(var i=0;i< components.length;++i){
		var obj = (components[i].indexOf("xui")>=0)?eval(components[i]):eval("xui.UI."+components[i]);
		if(obj==undefined ){
			if(window.console)console.log("undefined: " + components[i]);
			continue;
		}
		obj.setDataModel(propiedades);
	}
}

extDataModel(['xui.UI.ComboInput','xui.UI.Input','xui.UI.CheckBox', 'xui.UI.HiddenInput','xui.UI.RadioBox'],
{
	dataType:{
		ini:'string'
		}
}
);

//atributos adicionales en comboInput y listas para cargar datos de servicios
extDataModel(['xui.UI.ComboInput','xui.UI.List','xui.UI.Gallery','xui.UI.TreeGrid','xui.UI.RadioBox','xui.DataBinder'],
{
	urlService:"",
	parametersService:{
		ini:[],
		type:"cmd"
	}
}
);

var allUI = {
	startHidden:false
};
for(a in xui.UI){
if(xui.UI[a] && xui.UI[a]['xui.UI']){
	if(xui.UI[a].$DataModel.required && xui.UI[a].$DataModel.required.action){
		xui.UI[a].$DataModel.required.action= function(v){
                    if(this.keys['LABEL']){
                        var node = this.getSubNode('LABEL');
                        if(v)node.addClass('xui-required-ext');
                        else node.removeClass('xui-required-ext');
                    }
                };
		
	}
xui.UI[a].setDataModel(allUI);
}
}