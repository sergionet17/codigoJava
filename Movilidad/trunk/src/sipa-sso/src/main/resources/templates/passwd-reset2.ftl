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
            <h2 class="sipa-login-title sipa-login"><@spring.message "login.title"/></h2>
                <#if (_csrf.parameterName)??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="tipoUsuario" value="${tipoUsuario}" id="tipoUsuario"/>
                </#if>
                <div class="form-group">
                    <form id="formResetPasswd" action="<@spring.url '/resetPasswd' />" method="post">
                    <@spring.bind "usuario.id"/>
                    <input type="hidden" id="usuario" name="usuario" value="${(usuario.id)!''}"/>
                    <legend>Formulario para cambio de contraseña</legend>
                        <div class="form-group">
                            <label for="username">Ingrese la contraseña nueva</label>
                            <@spring.bind "usuario.password"/>
                            <input type="password" name="password" id="password" class="form-control" value="" />
                            <div class="has-error">
                                <span class="help-block"><@spring.showErrors "<br>"/></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="username">Confirme la contraseña nueva</label>
                            <input type="password" name="passwordNuevoConf" id="passwordNuevoConf" class="form-control" value="" />
                        </div>
                        </br>
                        <div><button class="btn btn-primary" id="btnResetPasswd" style="float: left; margin-bottom: 10px;">Cambiar Contraseña</button></div>
                    </form>
                </div>
        </div>
    </div>
    <#if error ??>
        <div class="alert alert-danger">
          ${error}
        </div>
    </#if>
    </div>
    <br>
    <#include 'login-footer.ftl'/>

  <script type="text/javascript">
      $("#btnResetPasswd").click(function() {
          var $form = $('#formResetPasswd');
          $form.submit();
      });
  </script>

</@layout.main>