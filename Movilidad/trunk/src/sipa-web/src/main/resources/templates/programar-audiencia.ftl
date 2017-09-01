<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>

<@layout.main>
<div class="col-md-12">
<div class="well sipa-well">
	<h1>Horarios de Audiencia</h1>
	<div class="panel panel-info">
		<div class="panel-heading">Programar audiencia de continuación.</div>
		<!--<#if (agendas?? && agendas?size != 0)>
				<div>
				 <@s.bind "agenda.id"/> 
				 <@s.formRadioButtons "agenda.id", agendas, "<br>", ""/> 
				</div>
			</#if> -->
			<div class="radio">
			<#if (agendas2?? && agendas2?size != 0)>
				<#list agendas2 as agenda>
				<div class="radio">	
				 <label>
					  <input type="radio" name="tipo" id="tipo" value="${(agenda.tipo.id)!""}" checked> ${(agenda.tipo.nombre)!""}
				</label>
				</div>
				</#list>
			</#if> 
			</div> 
		</div>
	</div>
	<div class="col-md-10">
		<div id="myScheduler"></div>
	</div>
  </div>
<@l.script>

<script type="text/javascript">
YUI().use(
  'aui-scheduler',
  function(Y) {
    var events = [


 
    ];
 var config = {
             color: '#5594DB',
             id: 'CUSTOM-ID',
             allDay: true,
             borderColor: '#DD1144',
             strings: {
             	  disabled: true,
             save: 'Guardar',
             delete: 'Eliminar',
             cancel: 'Cancelar',
          },
          popover : {
              width: 400,
          },
     }

     var eventRecorder = new Y.SchedulerEventRecorder(config);
 var weekView = new Y.SchedulerWeekView();
 var monthView = new Y.SchedulerMonthView();

    new Y.Scheduler(
      {
        activeView: weekView,
        boundingBox: '#myScheduler',
        date: new Date(),
        eventRecorder: eventRecorder,
        items: events,
        render: true,
        views: [weekView, monthView]
      }
    );
  }
);


</script>
</@l.script>

</div>
</@layout.main>