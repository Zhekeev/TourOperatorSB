<#import "parts/common.ftlh" as c>

<@c.page>
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
                <div class="col mb-20">
                    <img src="/static/img/settings.png" width="22" height="22"> Настройка профиля
                    <div class="card-body">
                        <div class="btn-group-vertical">
                                <a href="/user/settings" class="btn btn-outline-primary">Настройка аккаунта</a>
                                <#--<a href="/user/text" class="btn btn-outline-primary">Мои сообщения</a>-->
                                <a href="/user/bank" class="btn btn-outline-success">Банк</a>
                            <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                Удалить аккаунт
                            </button>
                        </div>
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
                    <div class="accordion accordion-flush" id="accordionFlushExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-controls="flush-collapseOne">
                                    Вы больше не можете восстановить аккаунт
                                </button>
                            </h2>
                            <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">Все ваши данные будут стерты включая банковские данные и в дальнейшем у вас не будет
                                возможности восстановить их</div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="flush-headingTwo">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                    Про вас счет
                                </button>
                            </h2>
                            <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                                <div class="accordion-body">Все ваши деньги на банковском счету будут тоже удалены с вашего аккаунта</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <a href="/" class="btn btn-danger">Удалить</a>
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
            </div>
        </div>
    </#list>

</@c.page>