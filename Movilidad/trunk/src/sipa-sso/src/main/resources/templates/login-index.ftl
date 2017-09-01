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
            <form action="<@spring.url '/login' />" method="post">
                <#if (_csrf.parameterName)??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </#if>
                <label for="username"><@spring.message "login.username"/></label>
                <div class="form-group ">
                    <input type="text" name="username" id="username" class="form-control" />
                </div>
                <label for="password"><@spring.message "login.password"/></label>
                <div class="form-group">
                    <input type="password" name="password" id="password" class="form-control" />
                </div>
                <div class="checkbox">
                    <label for="record"><@spring.message "login.record"/>
                        <input type="checkbox" name="recordar" class="sipa-record">
                    </label>
                </div>
                <div><a href="<@spring.url '/cambiar-password' />">Cambiar Contraseña</a></div>
                <div><a href="<@spring.url '/reset-password' />"><@spring.message "login.reset"/></a></div>
                <div><button class="btn btn-primary" style="float: right; margin-bottom: 10px;"><@spring.message "login.button"/></button></div>
            </form>
        </div>
    </div>
    <#if error ??>
        <div class="alert alert-danger">
          ${errorMensaje}
        </div>
    </#if>
    <#if FLASH_MESSAGE_OK??>
      <div class="alert alert-success"><p>${FLASH_MESSAGE_OK}</p></div>
    </#if>
    </div>
    <br>
    <#include 'login-footer.ftl'/>

<script type="text/javascript">
    $("#btnSiguiente").click(function() {
     var $form = $('#formEditarPasswd');
     $form.submit();
 });
</script>
</@layout.main>