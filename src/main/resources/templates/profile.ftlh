<#import "parts/common.ftlh" as c>

<@c.page>
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <div class="container profile-container">
        <h2>Профиль</h2>
        <div class="container" style="margin-top: 45px;">
            <div class="row">
                <div class="col">
                    <div class="card-body">
                        <p><img src="/static/img/user/user_square.png" width="22"
                                height="22"> ${user.firstName}   ${user.lastName}</p>
                        <p><img src="/static/img/mail.png" width="22" height="22"> ${user.email}</p>
                        <p><img src="/static/img/device/mobile.png" width="22" height="22"> ${user.phone}</p>
                        <p><img src="/static/img/home/home_alt_outline.png" width="22" height="22"> ${user.street}
                            , ${user.houseNumber}, ${user.apartmentNumber}, </p>
                    </div>
                </div>
                <div class="col mb-20"></div>
                <div class="col mb-20"></div>
                <div class="col mb-20">
                    <img src="/static/img/settings.png" width="22" height="22"> Настройка профиля
                    <div class="card-body">
                        <div class="btn-group-vertical">
                            <a href="/user/settings" class="btn btn-outline-primary">Настройка аккаунта</a>
                            <a href="/user/bank" class="btn btn-outline-success">Банк</a>
                            <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal">
                                Удалить аккаунт
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Удалить аккаунт</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-danger" role="alert">
                            Вы действительно хотите удалить аккаунт?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <a href="/user/delete" class="btn btn-danger">Удалить</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <h2>История заказов</h2>
        <#list contracts as contract>
                <div class="card mb-3 mt-10">
                    <div class="card-body">
                        <h5 class="card-title">${contract.tour.name}</h5>
                        <p class="card-text">${contract.tour.description}</p>
                        <p class="card-text"><small class="text-muted">${contract.purchaseDate}</small></p>
                        <p class="card-text"><small <#if contract.confirmPurchase??>style="color:red"<#else>style="color:green"</#if>><#if contract.confirmPurchase??>Не подтвержден<#else>Подтвержден</#if></small></p>
                        <#if contract.confirmPurchase??><div class="alert alert-danger" role="alert">Подтвердите покупку тура через почту</div></#if>
                    </div>
                </div>
                <#else>
                    <p class="home-text" style="text-align: center">Вы пока не купили туров. <a href="/tour/all">Посмотреть туры</a></p>
        </#list>
    </div>

</@c.page>
