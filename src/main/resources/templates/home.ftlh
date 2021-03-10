<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.page>
<div>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-5">
                <h2>Путешествовать доступнее с нами!</h2>
                </br>
                   Онлайн представитель услуг тур оператора отправит тебя в любую точку земли за один клик!
                </div>
                <div class="button-page">
                    <a href="/tour/all" class="button-login-for-login-page" style="color: white">Посмотреть туры</a>
                </div>
            </div>
            <div class="col">
                <img src="/static/img/dubai-1.png" width="482" height="367">
            </div>
        </div>
    </div>
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel" style="margin-top: 70px;">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="/static/img/Dubai_slider.png" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="/static/img/Turkish_slider.jpg" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="/static/img/Maldives_slider.png" class="d-block w-100">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"  data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"  data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <div class="hr-home">
    <hr>
    </div>
    <div class="container" style="margin-top: 45px;">
        <h2>Горящие предложения!</h2>
    </div>
    <@p.pager url page/>
    <div class="mt-25 mb-25">
    <div class="card-group mb-25">
    <#list page.content as tour>
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
                            <a href="/tour/about/${tour.id}" class="btn btn-link">Подробнее...</a>
                        </div>
                        </div>
                    </div>
        </div>
    </div>
    </#list>
    </div>
    </div>
    <div class="mt-25px">
    <@p.pager url page/>
    </div>
</@c.page>