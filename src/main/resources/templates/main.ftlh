<#import "parts/common.ftlh" as c>
<#import "parts/input.ftlh" as i>
<#import "parts/pager.ftlh" as p>
<#include "parts/security.ftlh">

<@c.page>
    <div>
        <a href="/admin/user/all" class="btn btn-outline-primary">Настройка пользователей</a>
    </div>
    <div>
        <a href="/admin/user/text" class="btn btn-outline-primary">Сообщений пользователей</a>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите название тура">
            <input type="text" name="description" placeholder="Введите описание тура">
            <input type="text" name="priceFromFront" placeholder="Введите цену тура">
            <input type="date" name="date" placeholder="Введите дату тура">
            <input type="text" name="numberOfDays" placeholder="Введите продолжительность тура">
            <input type="text" name="numberOfPeople" placeholder="Введите количество человек тура">
            <input type="file" name="file">
            <label>Country</label>
            <select name="name">
                <#list countries as country>
                    <option>${country.name}</option>
                </#list>
            </select>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
        <div>
            Поиск по названию тура
        </div>
        <form method="get" action="/main">
            <input type="text" name="name" placeholder="Введите название тура" value="${filter?ifExists}">
            <button type="submit">Найти</button>
        </form>
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