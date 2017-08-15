<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<#include 'login-header.ftl'/>
<@layout.main>

<div class="col-xs-12 col-md-offset-4 col-md-4 col-md-4">
    <div class="panel panel-default">
    <div class="sipa-m">
    <img class="img-responsive sipa-bogota" src="<@spring.url '/static/img/alcaldiaylogo_color.png' />">
        <br>
        </div>
        <div class="panel-body login sipa-ml-mr-10 sipa-login-m">
            <h2 class="sipa-login-title sipa-login">Cambio de contraseña</h2>
                <#if (_csrf.parameterName)??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="tipoUsuario" value="${tipoUsuario}" id="tipoUsuario"/>
                </#if>
                <form id="formEditarPasswd" action="<@spring.url '/editar' />" method="post">
                    <div class="form-group">
                        <label for="username">Nombre de usuario</label>
                        <input type="text" name="username" id="username" class="form-control" value="" />
                    </div>
                    <div><button class="btn btn-primary" id="btnCambiarPasswd" style="float: left; margin-bottom: 10px;">Siguiente</button></div>
                </form>
        </div>
    </div>
    <#if error ??>
        <div class="alert alert-danger">
          ${errorMensaje}
        </div>
    </#if>
    </div>
    <br>
    <#include 'login-footer.ftl'/>

  <script type="text/javascript">
      $("#btnCambiarPasswd").click(function() {
          var $form = $('#formEditarPasswd');
          $form.submit();
      });
  </script>

</@layout.main>