<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <h1 class="display-6">Пользователи</h1>
        <table class="table">
            <thead class="table-dark">
            <tr>
                <th>Логин</th>
                <th>Роли</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td><a href="/admin/user/${user.id}">Настройка</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>