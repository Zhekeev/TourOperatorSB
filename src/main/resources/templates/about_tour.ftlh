<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-5">
                    <h1 class="display-6">${tour.name}</h1>
                    <p>${tour.description}</p>
                    <div><img src="/static/img/money.png" width="22" height="22"> ${tour.price}</div>
                    <div><img src="/static/img/takeoff-the-plane.png" width="22" height="22"> ${tour.date}</div>
                    <div><img src="/static/img/two-men-couple.png" width="22" height="22"> ${tour.numberOfPeople}</div>
                    <div><img src="/static/img/schedule.png" width="22" height="22"> ${tour.numberOfDays}</div>
                    <hr>
                </div>
                <a href="/tour/cheque/${tour.id}" type="button" class="button-login-for-login-page"
                   style="color: white">Купить</a>

            </div>
            <div class="col">
                <img src="/img/${tour.image}" width="720" height="460">
            </div>
        </div>
    </div>
</@c.page>