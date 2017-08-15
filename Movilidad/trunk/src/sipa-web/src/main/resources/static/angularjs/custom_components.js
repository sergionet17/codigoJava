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
	parametersService:""
}
);
