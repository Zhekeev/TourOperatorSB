<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.page>
    <div style="margin-top: 50px; margin-bottom: 25px">
        <h1 class="display-5">Поиск тура</h1>
    </div>
    <form method="get" action="/tour/all">
        <div class="container" style="margin-top: 45px;">
            <div class="row">
                <div class="col">
                    <form method="post" enctype="multipart/form-data">
                        <div class="input-group mt-1 mb-3">
                            <span class="input-group-text" id="basic-addon2">Откуда</span>
                            <select name="nameFrom" class="form-select" id="selectTour" disabled>
                                    <option>Караганда</option>
                            </select>
                            <span class="input-group-text" id="basic-addon2">Куда</span>
                            <select name="nameTo" class="form-select" id="selectCountry">
                                <#list countries as country>
                                    <option>${country.name}</option>
                                </#list>
                            </select>
                            <span class="input-group-text">Дата началы</span>
                            <input type="date" name="date" class="form-control" placeholder="Дата тура">
                            <div class="input-group mt-1 mb-3">
                            <span class="input-group-text">Персон</span>
                            <input type="text" name="numberOfPeople" class="form-control" value="2">
                            <span class="input-group-text">Продолжительность</span>
                            <input type="text" name="numberOfDays" class="form-control" value="10">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="submit">Поиск</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </form>

    <div style="margin-top: 50px; margin-bottom: 25px">
        <h1 class="display-5">Список туров</h1>
    </div>
    <@p.pager url page/>
    <div class="mt-25 mb-25">
        <div class="card-group">
            <#list page.content as tour>
                <#if tour.active == true>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="card" style="width: 16rem;">
                                <div class="ml-2">
                                    <div class="item-img ">
                                        <#if tour.image??>
                                            <img src="/img/${tour.image}" class="card-img-top" width="100px" height="250px">
                                        </#if>
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title">${tour.price}. тг</h5>
                                        <p class="card-text">${tour.name}</p>
                                        <span><img src="/static/img/takeoff-the-plane.png" width="15"
                                                   height="15"> ${tour.date}</span>
                                        <span><img src="/static/img/two-men-couple.png" width="15"
                                                   height="15"> ${tour.numberOfPeople}</span>
                                        <span><img src="/static/img/schedule.png" width="15"
                                                   height="15"> ${tour.numberOfDays}</span>
                                        <span><a href="/tour/about/${tour.id}" class="btn btn-link" style="text-decoration: none; color: #0F8FAB">Подробнее</a></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
            </#list>
        </div>
    </div>
    <div style="margin-top: 25px">
        <@p.pager url page/>
    </div>

</@c.page>
