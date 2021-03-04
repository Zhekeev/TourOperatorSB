<#import "parts/common.ftlh" as c>
<#import "parts/input.ftlh" as i>

<@c.page>
    <div class="login-text-login-page">
    <img class="log-in-image" src="/static/img/Adelaide.png">
    <h2>Вход</h2>
    </div>
<@i.login "/login" false/>
</@c.page>