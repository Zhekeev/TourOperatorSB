<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/admin/user/edit" method="post">
        <div class="container profile-container">
            <h2>Настройка пользователя</h2>
            <div class="container" style="margin-top: 45px;">
                <div class="row">
                    <div class="col">
                        <div class="card-body">
                            Логин
                            <div>
                                <input type="text" value="${user.username}" name="username">
                            </div>
                            <div>
                                <input type="password" name="password">
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        Роли
                        <#list roles as role>
                            <div>
                                <label><input type="checkbox"
                                              name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                                </label>
                            </div>
                        </#list>
                        <input type="hidden" value="${user.id}" name="userId">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </div>
                </div>
            </div>
        </div>
        <button type="submit">Save</button>
    </form>
</@c.page>