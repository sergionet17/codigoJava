<@l.main>
<div class="well sipa-well">
  <h1>Últimos mensajes del sistema</h1>
  <#if mensajes?? && mensajes?size != 0>
    <ul class="list-group">
    <#list mensajes as m>
      <#assign class><#if !m.leido><#else>list-group-item-warning</#if></#assign>
      <li class="list-group-item ${class}">
        <div class="row">
          <div class="col-md-2"><i class="glyphicon glyphicon-user"></i> ${m.usuarioFuente.persona.obtenerNombreCompleto()}</div>
          <div class="col-md-8">${m.mensaje}</div>
          <div class="col-md-2"><i class="glyphicon glyphicon-calendar"></i> <@l.dateFormat m.fechaCreacion /></div>
        </div>
      </li>
    </#list>
    </ul>
  <#else>
    <p>No hay mensajes más mensajes por mostrar</p>
    <p><a href="#" class="btn btn-primary">Ver todos los mensajes anteriores</a></p>
  </#if>
</div>

<div class="well sipa-well">
<h1>Tablero de Control</h1>
<div class="row">
    <div class="col-md-6">
        <h3>Carga de trabajo</h3>
        <div class="row">
            <div class="col-md-12">
                Álvaro Cediel <span class="badge sipa-m-bottom-top">12</span>
                <div class="progress">
                  <div class="progress-bar progress-bar-success" style="width: 25%">3</div>
                  <div class="progress-bar progress-bar-warning" style="width: 33%">4</div>
                  <div class="progress-bar progress-bar-danger" style="width: 42%">5</div>
                </div>
        </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                Ana Calderón <span class="badge sipa-m-bottom-top">13</span>
                <div class="progress">
                  <div class="progress-bar progress-bar-success" style="width: 77%">10</div>
                  <div class="progress-bar progress-bar-warning" style="width: 23%">3</div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                Carolina Prieto <span class="badge sipa-m-bottom-top">15</span>
                <div class="progress">
                  <div class="progress-bar progress-bar-success" style="width: 7%">1</div>
                  <div class="progress-bar progress-bar-warning" style="width: 26%">4</div>
                  <div class="progress-bar progress-bar-danger" style="width: 67%">10</div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <h3>Devoluciones por revisor</h3>
        <div class="row">
            <div class="col-md-12">
            Álvaro Cediel <span class="badge sipa-m-bottom-top">180</span>
            

        <div class="progress">
          <div class="progress-bar progress-bar-danger" style="width: 25%">25</div>
          <div class="progress-bar progress-bar-success" style="width: 75%">75</div>
        </div>
        Ana Calderón <span class="badge sipa-m-bottom-top">13</span>
        <div class="progress">
          <div class="progress-bar progress-bar-danger" style="width: 30%">30</div>
          <div class="progress-bar progress-bar-success" style="width: 70%">70</div>
        </div>
        Carolina Prieto <span class="badge sipa-m-bottom-top">15</span>
        <div class="progress">
          <div class="progress-bar progress-bar-danger" style="width: 72%">72</div>
          <div class="progress-bar progress-bar-success" style="width: 28%">28</div>
        </div>
        </div>
    </div>
    </div>
    <div class="col-md-6">
        <h3>Conteo por tipo de expediente</h3>
        Embriaguez
    <div class="row">
    <div class="col-md-12">
        <div class="progress">
          <div class="progress-bar" style="width: 28%">78</div>
        </div>
        D12
        <div class="progress">
          <div class="progress-bar" style="width: 16%">45</div>
        </div>
        C02
        <div class="progress">
          <div class="progress-bar" style="width: 44%">124</div>
        </div>
        D15
        <div class="progress">
          <div class="progress-bar" style="width: 12%">35</div>
        </div>
        </div>
    </div>
</div>

    
</div>
</div>

<div class="col-md-12">
 <#if (mensajes?? && mensajes?size != 0)>
 <hr class="sipa-hr">
 <h1 class="text-center">MENSAJES DEL SISTEMA</h1>
 <div class="col-md-12">
 <table>
   <tbody>
     <#list mensajes as mensaje>
     <tr>
       <td>- ${(mensaje.mensaje)!""}</td>
     </tr>
     </#list>
   </tbody>
   </table>
   </div>
   </div>
   </#if>
<hr class="sipa-hr">
<div class="well sipa-well">
<h1 class="text-center">SIPA</h1>
<br>
<p>El proyecto SIPA (Sistema de Información de Procesos Administrativos) surge de la necesidad de la Secretaria Distrital de Movilidad de Bogotá de mejorar el desempeño (eficiencia y eficacia) y la optimización de los procesos administrativos sancionatorios misionales, mediante la metodología B.P.M. (Business Process Management).</h4>
</p>
    <center>
    <img class="img-responsive sipa" src="<@s.url '/static/img/sipa.png' />">
    </center>
</div>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <hr class="sipa-hr">
    <div class="well sipa-well">
    <h1>Noticias</h1>
    <br>
    <div id="carousel1" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner" role="listbox">
    <div class="item active">
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia1.png' />"> 
    <h3>Noticia #1</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia2.png' />"> 
    <h3>Noticia #2</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia3.png' />"> 
    <h3>Noticia #3</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
</div>
<a class="left carousel-control sipa-left visible-lg" href="#carousel1" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Anterior</span>
  </a>
  <a class="right carousel-control sipa-right visible-lg" href="#carousel1" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Siguiente</span>
  </a>
<div class="item">
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia4.png' />"> 
    <h3>Noticia #4</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia5.png' />"> 
    <h3>Noticia #5</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia6.png' />"> 
    <h3>Noticia #6</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
</div>
<a class="left carousel-control sipa-left visible-lg" href="#carousel1" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Anterior</span>
  </a>
  <a class="right carousel-control sipa-right visible-lg" href="#carousel1" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Siguiente</span>
  </a>
<div class="item">
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia7.png' />"> 
    <h3>Noticia #7</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia8.png' />"> 
    <h3>Noticia #8</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
<div class="col-md-4">
    <center>
   <img class="noticia" src="<@s.url '/static/img/noticia9.png' />"> 
    <h3>Noticia #9</h3>
    <p class="text-left">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
    </center>
</div>
</div>
<a class="left carousel-control sipa-left visible-lg" href="#carousel1" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Anterior</span>
  </a>
  <a class="right carousel-control sipa-right visible-lg" href="#carousel1" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Siguiente</span>
  </a>
</div>
</div>
   </@l.main>