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
                <p>
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                    Ответить
                </button>
                </p>
                <div class="collapse" id="collapseExample">
                    <form  action="/admin/user/text/answer/user/${usertext.user.id}/text/${usertext.id}" method="post">
                        <div class="input-group">
                            <span class="input-group-text">Ваш текст</span>
                            <textarea class="form-control" name="text" aria-label="With textarea"></textarea>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </div>
                        <div class="mt-2">
                            <button class="btn btn-outline-primary">Отправить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#list>
</@c.page>