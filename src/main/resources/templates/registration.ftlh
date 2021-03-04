<#import "parts/common.ftlh" as c>
<#import "parts/input.ftlh" as i>

<@c.page>
    <div class="login-text-login-page">
        <img class="log-in-image" src="/static/img/Adelaide.png">
        <h2>Регистрация</h2>
    </div>
    ${message?ifExists}
    <@i.login "/registration" true/>
</@c.page>