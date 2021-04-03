<#import "parts/common.ftlh" as c>
<#import "parts/input.ftlh" as i>
<#import "parts/pager.ftlh" as p>
<#include "parts/security.ftlh">

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-2">
                    <h1 class="display-7">Настройка аккаунта</h1>
                </div>
                <form method="post" enctype="multipart/form-data">
                    <div class="input-group mt-1 mb-3">
                        <span class="input-group-text" id="basic-addon2">Название</span>
                        <input type="text" name="text" class="form-control" placeholder="Название тура"
                               aria-describedby="basic-addon2">
                    </div>
                    <div class="input-group mt-2 mb-3">
                        <span class="input-group-text">Описание тура</span>
                        <textarea class="form-control" name="description" placeholder="Описание тура"></textarea>
                    </div>
                    <div class="input-group mt-1 mb-3">
                        <span class="input-group-text">₸</span>
                        <input type="text" name="priceFromFront" class="form-control" placeholder="Цена">
                        <span class="input-group-text">Дата</span>
                        <input type="date" name="date" class="form-control" placeholder="Дата тура">
                    </div>
                    <div class="input-group mt-1 mb-3">
                        <span class="input-group-text"><img src="/static/img/calendar.png" width="22"
                                                            height="22"></span>
                        <input type="text" name="numberOfDays" class="form-control" placeholder="Продолжительность">
                        <span class="input-group-text"><img src="/static/img/user/two-persons-silhouettes.png"
                                                            width="22" height="22"></span>
                        <input type="text" name="numberOfPeople" class="form-control" placeholder="Количество людей">
                    </div>
                    <div class="input-group mt-1 mb-3">
                        <input type="file" name="file">
                        <label>Страна тура</label>
                        <select name="name">
                            <#list countries as country>
                                <option>${country.name}</option>
                            </#list>
                        </select>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-primary">Добавить</button>
                </form>
            </div>
            <div class="col">
                <div class="mt-2">
                    <h1 class="display-7">Сменить пароль</h1>
                </div>
                <div>
                    <a href="/admin/user/all" class="btn btn-outline-primary">Настройка пользователей</a>
                </div>
                <div>
                    <a href="/admin/user/text" class="btn btn-outline-primary">Сообщений пользователей</a>
                </div>
            </div>
        </div>
    </div>
    <div>
        Список туров
    </div>
    <@p.pager url page/>
    <div class="mt-25 mb-25">
        <div class="card-group mb-25">
            <#list page.content as tour>
                <div class="row">
                    <div class="col-sm-3">
                        <div class="card" style="width: 16rem;">
                            <div class="item-img ">
                                <#if tour.image??>
                                    <img src="/img/${tour.image}" class="card-img-top" width="100px" height="250px">
                                </#if>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${tour.price}. тг</h5>
                                <p class="card-text">${tour.name}</p>
                                <a href="/tour/about/${tour.id}" class="btn btn-link">Подробнее...</a>
                                <#if isAdmin>
                                    <a href="/admin/delete/${tour.id}" class="btn btn-link">Удалить...</a>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <@p.pager url page/>
    </div>
</@c.page>