<#include 'comparendo-header.ftl' />
<div>&nbsp;</div>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-primary panel-heading"><strong>Audiencia de Impugnación</strong></div>
        <div class="panel-body">
            <form class="form-horizontal" action="/audiencia/continuar/crear" method="post">
                <div class="form-group">
                    <label for="tipo" class="col-sm-2 control-label">Tipo de audiencia</label>
                    <#-- Propuesta para el select ya que en movil no es adecuada con select -->
                    <div class="col-sm-6">
                        <select name="tipo" id="tipo" class="form-control">
                            <option>Audiencia en que se comparece con abogado o practica de pruebas sobre inmovilización de vehículo</option>
                            <option>Audiencia de práctica de pruebas o notificación de fallo</option>
                            <option>Rango cuando no encuentra cupo para audiencia se habilitan todos los horarios</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="vigencia" class="col-sm-2 control-label">Fecha</label>
                    <div class="col-sm-6" id="vigencia">
                        <input type="text" class="form-control datepicker" placeholder="Fecha de la audiencia">
                    </div>
                </div>
                <div class="form-group">
                    <label for="dispo" class="col-sm-2 control-label">Disponibilidad</label>
                    <div class="col-sm-10" id="dispo">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                07:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                08:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                09:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                10:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                11:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                12:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                13:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                14:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                15:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                16:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                17:00
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="">
                                18:00
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a href="/comparendo/audiencias" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
<!--
$(document).ready(function() {
var view="week";
var DATA_FEED_URL = "php/datafeed.php";
var op = {
view: view,
theme:3,
showday: new Date(),
EditCmdhandler:Edit,
DeleteCmdhandler:Delete,
ViewCmdhandler:View,
onWeekOrMonthToDay:wtd,
onBeforeRequestData: cal_beforerequest,
onAfterRequestData: cal_afterrequest,
onRequestDataError: cal_onerror,
autoload:true,
url: DATA_FEED_URL + "?method=list",
quickAddUrl: DATA_FEED_URL + "?method=add",
quickUpdateUrl: DATA_FEED_URL + "?method=update",
quickDeleteUrl: DATA_FEED_URL + "?method=remove"
};
var $dv = $("#calhead");
var _MH = document.documentElement.clientHeight;
var dvH = $dv.height() + 2;
op.height = _MH - dvH;
op.eventItems =[];
var p = $("#gridcontainer").bcalendar(op).BcalGetOp();
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
                         $("#caltoolbar").noSelect();
                         $("#hdtxtshow").datepicker({ picker: "#txtdatetimeshow", showtarget: $("#txtdatetimeshow"),
                         onReturn:function(r){
                         var p = $("#gridcontainer").gotoDate(r).BcalGetOp();
                         if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
}
});
function cal_beforerequest(type)
{
var t="Loading data...";
switch(type)
{
case 1:
t="Loading data...";
break;
case 2:
case 3:
case 4:
t="The request is being processed ...";
break;
}
$("#errorpannel").hide();
$("#loadingpannel").html(t).show();
}
function cal_afterrequest(type)
{
switch(type)
{
case 1:
$("#loadingpannel").hide();
break;
case 2:
case 3:
case 4:
$("#loadingpannel").html("Success!");
window.setTimeout(function(){ $("#loadingpannel").hide();},2000);
break;
}
}
function cal_onerror(type,data)
{
$("#errorpannel").show();
}
function Edit(data)
{
var eurl="edit.php?id={0}&start={2}&end={3}&isallday={4}&title={1}";
if(data)
{
var url = StrFormat(eurl,data);
OpenModelWindow(url,{ width: 600, height: 400, caption:"Manage  The Calendar",onclose:function(){
$("#gridcontainer").reload();
}});
}
}
function View(data)
{
var str = "";
$.each(data, function(i, item){
str += "[" + i + "]: " + item + "\n";
});
alert(str);
}
function Delete(data,callback)
{
$.alerts.okButton="Ok";
$.alerts.cancelButton="Cancel";
hiConfirm("Are You Sure to Delete this Event", 'Confirm',function(r){ r && callback(0);});
}
function wtd(p)
{
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
$("#caltoolbar div.fcurrent").each(function() {
$(this).removeClass("fcurrent");
})
$("#showdaybtn").addClass("fcurrent");
}
//to show day view
$("#showdaybtn").click(function(e) {
//document.location.href="#day";
$("#caltoolbar div.fcurrent").each(function() {
$(this).removeClass("fcurrent");
})
$(this).addClass("fcurrent");
var p = $("#gridcontainer").swtichView("day").BcalGetOp();
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
});
//to show week view
$("#showweekbtn").click(function(e) {
//document.location.href="#week";
$("#caltoolbar div.fcurrent").each(function() {
$(this).removeClass("fcurrent");
})
$(this).addClass("fcurrent");
var p = $("#gridcontainer").swtichView("week").BcalGetOp();
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
});
//to show month view
$("#showmonthbtn").click(function(e) {
//document.location.href="#month";
$("#caltoolbar div.fcurrent").each(function() {
$(this).removeClass("fcurrent");
})
$(this).addClass("fcurrent");
var p = $("#gridcontainer").swtichView("month").BcalGetOp();
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
});
$("#showreflashbtn").click(function(e){
$("#gridcontainer").reload();
});
//Add a new event
$("#faddbtn").click(function(e) {
var url ="edit.php";
OpenModelWindow(url,{ width: 500, height: 400, caption: "Create New Calendar"});
});
//go to today
$("#showtodaybtn").click(function(e) {
var p = $("#gridcontainer").gotoDate().BcalGetOp();
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
});
//previous date range
$("#sfprevbtn").click(function(e) {
var p = $("#gridcontainer").previousRange().BcalGetOp();
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
});
//next date range
$("#sfnextbtn").click(function(e) {
var p = $("#gridcontainer").nextRange().BcalGetOp();
if (p && p.datestrshow) {
$("#txtdatetimeshow").text(p.datestrshow);
}
});
});
-->
</script>