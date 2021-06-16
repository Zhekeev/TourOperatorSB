<#import "parts/common.ftlh" as c>
<#import "parts/input.ftlh" as i>
<#import "parts/pager.ftlh" as p>
<#include "parts/security.ftlh">

<@c.page>
    <div class="col">
        <div class="mt-2">
            <h1 class="display-7">Настройка аккаунта</h1>
        </div>
        <form method="post" enctype="multipart/form-data">
            <div class="input-group mt-1 mb-3">
                <span class="input-group-text" id="basic-addon2">Название</span>
                <input type="text" name="text" class="form-control" placeholder="Название тура"
                       value="${tour.name}"
                       aria-describedby="basic-addon2">
            </div>
            <div class="input-group mt-2 mb-3">
                <span class="input-group-text">Описание тура</span>
                <textarea class="form-control" name="description"
                          placeholder="Описание тура"></textarea>
            </div>
            <div class="input-group mt-1 mb-3">
                <span class="input-group-text">₸</span>
                <input type="text" name="priceFromFront" class="form-control" value="${tour.price}" placeholder="Цена">
                <span class="input-group-text">Дата</span>
                <input type="date" name="date" class="form-control" value="${tour.date}" placeholder="Дата тура">
            </div>
            <div class="input-group mt-1 mb-3">
                        <span class="input-group-text"><img src="/static/img/calendar.png" width="22"
                                                            height="22"></span>
                <input type="text" name="numberOfDays" class="form-control" value="${tour.numberOfDays}" placeholder="Продолжительность">
                <span class="input-group-text"><img src="/static/img/user/two-persons-silhouettes.png"
                                                    width="22" height="22"></span>
                <input type="text" name="numberOfPeople" class="form-control" value="${tour.numberOfPeople}" placeholder="Количество людей">
            </div>
            <div class="input-group mt-1 mb-3">
                <input type="file" class="form-control" name="file">
            </div>
            <div class="input-group mt-1 mb-3">
                <input type="file" class="form-control" name="firstPhoto">
            </div>
            <div class="input-group mt-1 mb-3">
                <input type="file" class="form-control" name="secondPhoto">
            </div>
            <div class="input-group mt-1 mb-3">
                <input type="file" class="form-control" name="thirdPhoto">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-outline-primary">Добавить</button>
        </form>
    </div>
</@c.page>
