<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-5">
                    <h1 class="display-6">${tour.name}</h1>
                    <p>${tour.description}</p>
                    <span>${tour.price}</span>
                </div>
                <div class="button-page">
                    <button type="button" class="button-login-for-login-page" data-bs-toggle="modal" data-bs-target="#forTour" style="color: white">
                        Купить
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="forTour" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Купить тур</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="alert <#if cash_error??>alert-danger<#else>alert-info</#if>" role="alert">
                                        <#if cash_error??>Вам не хватает средств для покупки тура<#else>Подтвердите покупку тура</#if>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                    <a <#if cash_error??>href="/user/bank"<#else>href="/tour/buy/${tour.id}"</#if>
                                       <#if cash_error??>class="btn btn-outline-primary"<#else>class="btn btn-outline-success"</#if>>
                                       <#if cash_error??>Пополнить<#else>Купить</#if></a>
                                </div>
                            </div>
                        </div>
                    </div>
                   <#-- <a href="/tour/buy/${tour.id}" class="button-login-for-login-page" style="color: white">Купить тур</a>-->
                </div>
            </div>
            <div class="col">
                <!-- Button trigger modal -->
                <button type="button" class="image-button" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    <img src="/img/${tour.image}" width="720" height="460">
                </button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">${tour.name}</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel" style="margin-top: 70px;">
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img src="/static/img/Dubai_slider.png" class="d-block w-100">
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
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Закрыть</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>