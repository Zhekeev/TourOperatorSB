<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-5">
                    <h1 class="display-6">${tour.name}</h1>
                    <p>${tour.description}</p>
                    <div><b>Цена:</b> <p class="tour_about">${tour.price}.тг</p></div>
                    <div><b>Вылет:</b> <p class="tour_about">${tour.date}</p></div>
                    <div><b>Персон:</b> <p class="tour_about">${tour.numberOfPeople}</p></div>
                    <div><b>Продолжительность дней:</b> <p class="tour_about">${tour.numberOfDays}</p></div>
                    <hr>
                </div>
                <a href="/tour/cheque/${tour.id}" type="button" class="button-login-for-login-page"
                   style="color: white">Купить</a>

            </div>
            <div class="col">
                <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="/static/img/test/123514889.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="/static/img/test/121284801.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="/static/img/test/121252869.jpg" class="d-block w-100" alt="...">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</@c.page>
