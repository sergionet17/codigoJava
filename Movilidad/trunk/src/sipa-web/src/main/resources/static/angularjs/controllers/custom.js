new function(){
    if(!window.CONF)window.CONF={};

    xui.merge(CONF,{
        //To set service path
        servicePath:xui.ini.appPath + 'request.php',        //servicePath:"http://localhost:19286/portal.ashx",
        //To set service request key
        requestKey:'FormDesigner',

        // To rewrite all in the left Tool Box region
        //widgets:{},

        // To hide widgets in the left Tool Box region
        widgets_hidden:{},
        // to hide prop in the right Component Config region
        widgets_hiddenProps:{},
        // to hide events in the right Component Config region
        widgets_hiddenEvents:{},
        // to hide prop in the right Component Config region for page
        page_hiddenPropEvents:{},
        //  add properties to the commonly used properties in the component config.
        widgets_xprops:{
            'xui.Com':["onReady","onRender"],
            'xui.Module':["onReady","onRender"],
            'xui.DataBinder':["name","dataSourceType","queryURL","queryArgs",'tokenParams',"queryModel","requestType","responseType","proxyInvoker","onData","onError"],
            'xui.UI.CSSBox':['html','className','normalStatus','hoverStatus','activeStatus','focusStatus'],
            'xui.UI.Element':['html','nodeName','position','className','attributes','tabindex','zIndex'],
            'xui.UI.HTMLButton':['html','position','className','attributes','tabindex','zIndex','onClick'],
            'xui.UI.Span':['html','position','className','tabindex','zIndex','dock'],
            'xui.UI.Div':['html','position','className','tabindex','zIndex','dock'],
            'xui.UI.Pane':['html','dropKeys','tabindex','zIndex','dock','overflow'],
            'xui.UI.Block':['html','dropKeys','borderType','tabindex','zIndex','dock','overflow'],
            'xui.UI.SLabel':['caption','hAlign','className','tabindex','zIndex','onClick'],
            'xui.UI.Label':['caption','hAlign','vAlign','tabindex','zIndex'],
            'xui.UI.Link':['caption','tabindex','zIndex','target','onClick'],
            'xui.UI.SButton':['caption','image','imagePos','tabindex','zIndex','onClick'],
            'xui.UI.Button':['type','value','caption','image','imagePos','tabindex','zIndex','dirtyMark','onClick','onChange'],
            'xui.UI.SCheckBox':['value','caption','image','imagePos','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.CheckBox':['value','caption','image','imagePos','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.Input':['value','labelSize','labelCaption','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.RichEditor':['value','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.List':['items','value','tabindex','zIndex','dirtyMark','onItemSelected','onChange'],
            'xui.UI.ComboInput':['type','value','labelSize','labelCaption','tabindex','zIndex','dirtyMark','items','onClick','onCommand','onChange'],
            'xui.UI.ProgressBar':['value','captionTpl','tabindex','type','fillBG','zIndex','dirtyMark','onChange'],
            'xui.UI.Slider':['value','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.Range':['value','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.RadioBox':['items','value','tabindex','zIndex','dirtyMark','onItemSelected','onChange'],
            'xui.UI.DatePicker':['value','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.TimePicker':['value','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.ColorPicker':['value','tabindex','zIndex','dirtyMark','onChange'],
            'xui.UI.Layout':['type','items','tabindex','zIndex','dock','overflow','onGetContent'],
            'xui.UI.Group':['caption','html','dropKeys','tabindex','zIndex','dock','overflow','beforeExpand','afterFold','onIniPanelView'],
            'xui.UI.Panel':['caption','html','dropKeys','tabindex','zIndex','dock','overflow','beforeExpand','afterFold','onIniPanelView'],
            'xui.UI.Tabs':['items','value','tabindex','zIndex','dock','overflow','onItemSelected','onIniPanelView'],
            'xui.UI.Stacks':['items','value','tabindex','zIndex','dock','overflow','onItemSelected','onIniPanelView'],
            'xui.UI.ButtonViews':['items','value','tabindex','zIndex','dock','overflow','onItemSelected','onIniPanelView'],
            'xui.UI.FoldingTabs':['items','value','tabindex','zIndex','dock','overflow','onItemSelected','onIniPanelView'],
            'xui.UI.StatusButtons':['items','value','tabindex','zIndex','onItemSelected'],
            'xui.UI.FoldingList':['items','tabindex','zIndex','onItemSelected'],
            'xui.UI.IconList':['items','value','tabindex','zIndex','dock','onItemSelected'],
            'xui.UI.Gallery':['items','value','tabindex','zIndex','dock','onItemSelected'],
            'xui.UI.Dialog':['caption','modal','html','dropKeys','tabindex','zIndex','dock','overflow','beforeClose'],
            'xui.UI.PageBar':['caption','value','textTpl','prevMark','nextMark','tabindex','zIndex','onClick','onPageSet'],
            'xui.UI.PopMenu':['items','onMenuSelected'],
            'xui.UI.MenuBar':['items','tabindex','zIndex','dock','onMenuSelected'],
            'xui.UI.ToolBar':['items','tabindex','zIndex','dock','onClick'],
            'xui.UI.TreeBar':['items','value','tabindex','zIndex','dock','onItemSelected','onChange'],
            'xui.UI.TreeView':['items','value','tabindex','zIndex','dock','onItemSelected','onChange'],
            'xui.UI.TreeGrid':['header','rows','value','tabindex','dock','zIndex','onClickCell','onRowSelected'],
            'xui.UI.Image':['src','items','activeItem','cursor','tabindex','zIndex','dock','onClick'],
            'xui.UI.Flash':['src','parameters','flashvars','tabindex','zIndex','dock'],
            'xui.UI.Audio':['src'],
            'xui.UI.Video':['src'],
            'xui.UI.FusionChartsXT':['chartType','JSONData','configure','onDataClick','onLabelClick','onAnnotationClick'],
            'xui.UI.SVGPaper':['tabindex','zIndex','dock'],
            'xui.svg.circle':['attr','onClick'],
            'xui.svg.ellipse':['attr','onClick'],
            'xui.svg.rect':['attr','onClick'],
            'xui.svg.image':['attr','onClick'],
            'xui.svg.text':['attr','onClick'],
            'xui.svg.path':['attr','onClick'],
            'xui.svg.rectComb':['attr','hAlign','vAlign','onClick'],
            'xui.svg.circleComb':['attr','hAlign','vAlign','onClick'],
            'xui.svg.ellipseComb':['attr','hAlign','vAlign','onClick'],
            'xui.svg.pathComb':['attr','hAlign','vAlign','onClick'],
            'xui.svg.imageComb':['attr','hAlign','vAlign','onClick'],
            'xui.svg.connector':['attr','hAlign','vAlign','onClick']
        },

        // custom themes for the widget
        CustomThemes:{
            "xui.UI.SCheckBox":["demo1","demo2"]
        }
    },'all');
};
