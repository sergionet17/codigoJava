<#import "/spring.ftl" as spring/>
<#import "/layout.ftl" as layout/>
<#include 'login-header.ftl'/>
<@l.main>
<br>
<div class="col-xs-12 col-sm-offset-2 col-sm-6 col-md-4">
    <div class="panel panel-default">
    <div>
    <img class="img-responsive alcaldia" src="<@s.url '/static/img/alcaldiaylogo_color.png' />">
        <br>
        </div>
        <div class="panel-body login">
            <h2 class="sipa-login-title sipa-m-10"><@s.message "login.title"/></h2>
            <form action="<@s.url '/j_security_check' />" method="post">
            <label for="username"><@s.message "login.username"/></label>
            <div class="form-group">
                <input type="text" name="usarname" id="username" class="form-control" />
            </div>
            <label for="password"><@s.message "login.password"/></label>
            <div class="form-group">
                <input type="password" name="password" id="password" class="form-control" />
            </div>
            <div class="checkbox">
                <label for="record"><@s.message "login.record"/>
                    <input type="checkbox" name="recordar">
                </label>
            </div>
            <div><a href="<@s.url '/reset-password' />" style="color: #003c65"><@s.message "login.reset"/></a></div>
            <div><button class="btn btn-success" style="float: right;"><@s.message "login.button"/></button></div>
            </form>
        </div>
    </div>
    </div>
    <br>
    <#include 'footer.ftl'/>
   </@l.main>