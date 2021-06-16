<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.page>
<div>
    <div class="container" style="margin-top: 45px;">
        <div class="row" style="margin-right: 20px">
            <div class="col">
                <div class="mt-5">
                    <h2>Путешествовать доступнее с нами!</h2>
                    </br>
                    <p class="home-text">Онлайн представитель услуг тур оператора отправит тебя в любую точку земли за
                        один клик!</p>
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
                <a href="/tour/about/8"><img src="/static/img/Dubai_slider.png" class="d-block w-100"></a>
            </div>
            <div class="carousel-item">
                <a href="/tour/about/7"><img src="/static/img/Turkish_slider.jpg" class="d-block w-100"></a>
            </div>
            <div class="carousel-item">
                <a href="tour/about/14"><img src="/static/img/Maldives_slider.png" class="d-block w-100"></a>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <div class="hr-home">
        <hr>
    </div>
    <div style="margin-top: 50px; margin-bottom: 25px">
        <h1 class="display-5">Горящие туры</h1>
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
                                            <img src="/img/${tour.image}" class="card-img-top" width="100px"
                                                 height="250px">
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
                                        <span><a href="/tour/about/${tour.id}" class="btn btn-link"
                                                 style="text-decoration: none; color: #0F8FAB">Подробнее</a></span>
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
