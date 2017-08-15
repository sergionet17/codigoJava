  xui.Class("xui.UI.Report", "xui.UI.Div",{
      Static:{
          Behaviors:{
              DroppableKeys:['KEY'],
              PanelKeys:['KEY']
          },
          RenderTrigger:function(){
              // only div
              var ns=this;
              if(ns.box.KEY=="xui.UI.Pane")
                  if(ns.properties.iframeAutoLoad||ns.properties.ajaxAutoLoad)
                      ns.box._applyAutoLoad(this);
          }
          ,
          DataModel:{
        	  reportId:"",
        	  displayType:{ini:"frameset",listbox:['run', 'frameset']},
              scrollHorizontal:{ini:false},
              scrollVertical:{ini:false},
              overflow:{ini:'hidden',hidden:true},
              selectable:{ini:true,hidden:true},
              right:{ini:'auto',hidden:true},
              iframeAutoLoad:{ini:'',hidden:true},
              ajaxAutoLoad:{ini:'',hidden:true},
              bottom:{ini:'auto',hidden:true},
              html:{ini:'',hidden:true},
              dock:{ini:'none',hidden:true},
              disableClickEffect:{ini:false,hidden:true},
              disableHoverEffect:{ini:false,hidden:true},
              dropKeys:{ini:'',hidden:true}
          },
          RenderTrigger:function(){
        	  if(!this.$inDesign){
        		  var self=this,
                  instance=self.boxing(),
                  p=self.properties;
        		  instance.setCustomStyle({"KEY":""});
        		  instance.setOverflow('hidden');
        		  var dinamicStyle="";
        		  var dinamicProp="";
        		  //overflow-x: hidden;
    			  //overflow-y: scroll;
        		  if(instance.getScrollHorizontal()==false)
        		  dinamicStyle+=";overflow-x: hidden";
        		  if(instance.getScrollVertical()==false)
        		  dinamicStyle+=";overflow-y: hidden";
                  var params= "currentTab="+instance.getAlias();
                  params+="&id_rpt="+instance.getReportId();
                  var baseReport = window.rootReport?rootReport:"";
                  params+="&__report="+baseReport+"/rpts/"+instance.getReportId()+".rptdesign";
                  if(window.currentContext && currentContext.codTraEsc)
                	  params+="&codTraEsc="+currentContext.codTraEsc;
                  else
                	  params+="&codTraEsc=0";
                  if(window.currentContext && currentContext.numPro)
                	  params+="&numPro="+currentContext.numPro;
                  else
                	  params+="&numPro=0";

                  //alert("dinamicStyle "+dinamicStyle);
        		  instance.setHtml("<iframe src='"+(window.rootPath?window.rootPath:"")+instance.getDisplayType()+"?"+params+"' name='"+instance.getAlias()+"' marginheight='0' marginwidth='0' frameborder='0' style=';width:"+p.width+"px; height:"+p.height+"px"+dinamicStyle+"' "+dinamicProp+"></iframe>");

        	  }
        	  }
      }
  });