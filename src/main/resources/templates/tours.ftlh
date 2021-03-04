<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <h2>Все туры</h2>
    </div>
    <form method="get" action="/tour/all">
        <div class="input-group mb-3">
            <input type="text" class="form-control" name="name" value="${filter?ifExists}" placeholder="Поиск по названию тура">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">Поиск</button>
            </div>
        </div>
    </form>

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
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <@p.pager url page/>

</@c.page>