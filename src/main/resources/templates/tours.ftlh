<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <h2>Все туры</h2>
    </div>
    <form method="get" action="/tour/all">
        <div class="container" style="margin-top: 45px;">
            <div class="row">
                <div class="col">
                    <div class="mt-2">
                        <h1 class="display-6">Поиск тура</h1>
                    </div>
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