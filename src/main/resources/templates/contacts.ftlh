<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-1">
                    <div class="mb-2">
                        <h1 class="display-6">Наш адрес</h1>
                    </div>
                    <p><img src="/static/img/address.png" width="22" height="22">г.Караганда ул.17 микрорайон дом 57/2
                        кв.8</p>
                    <p><img src="/static/img/phone.png" width="22" height="22"> +7 707 597 84 34</p>
                    <div class="mb-2">
                        <a href="https://t.me/yergazy_zhqv"><img src="/static/img/telegram.png" width="22" height="22">
                            Напиши мне в Telegram</a>
                    </div>
                    <a href="https://www.instagram.com/zhekeevkz"><img src="/static/img/instagram.png" width="22"
                                                                       height="22"> Подписывайся в Instagram</a>
                </div>
            </div>

            <div class="col">
                <form action="/user/text" method="post">
                    <div class="mb-2">
                        <h1 class="display-6">Напишите мне</h1>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">@</span>
                        <input type="text" class="form-control"
                               <#if username??>value="${username}"</#if> <#if username??>readonly</#if>
                               placeholder="Пожалуйста войдите либо зарегистрируйтесь" aria-label="Логин"
                               aria-describedby="basic-addon1">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </div>
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
</@c.page>