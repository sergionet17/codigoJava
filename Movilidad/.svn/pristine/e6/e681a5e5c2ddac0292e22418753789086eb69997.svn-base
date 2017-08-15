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
            <h2 class="sipa-login-title sipa-login">Olvide mi Contraseña</h2>
                <#if (_csrf.parameterName)??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="tipoUsuario" value="${tipoUsuario}" id="tipoUsuario"/>
                </#if>
                <form id="formEditarPasswd" action="<@spring.url '/reset' />" method="post">
                    <div class="form-group">
                        <label for="username">Correo de usuario</label>
                        <input type="text" name="email" id="email" class="form-control" value="" />
                    </div>
                    </br>
                    <div><button class="btn btn-primary" id="btnResetPasswd" style="float: left; margin-bottom: 10px;">Siguiente</button></div>
                </form>
        </div>
    </div>
    <#if errorMessage ??>
        <div class="alert alert-danger">
          ${errorMessage}
        </div>
    </#if>
    </div>
    <br>
    <#include 'login-footer.ftl'/>

  <script type="text/javascript">
      $("#btnResetPasswd").click(function() {
          var $form = $('#formEditarPasswd');
          $form.submit();
      });
  </script>

</@layout.main>