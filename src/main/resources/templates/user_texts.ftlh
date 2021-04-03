<#import "parts/common.ftlh" as c>

<@c.page>
    <h1 class="display-6">Сообщений пользователей</h1>
    <#list users as usertext>
        <div class="card mt-5">
            <div class="card-header">
                <h5 class="card-title">@ ${usertext.user.username}</h5>
            </div>
            <div class="card-body">
                <h5 class="card-title">${usertext.user.firstName} ${usertext.user.lastName}</h5>
                <p class="card-text">${usertext.text}</p>
            </div>
        </div>
    </#list>
</@c.page>