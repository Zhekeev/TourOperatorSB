<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-5">
                    <h2>Итоговая стоимость тура</h2>
                    </br>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Название тура</th>
                            <th scope="col">Цена</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>${tour.name}</td>
                            <td>${tour.price}.тг</td>
                        </tr>
                        </tbody>
                    </table>
                    <h1 class="display-6">Итоговая стоимость: ${tour.price}.тг</h1>
                </div>
            </div>
            <div class="col">
                <div class="mb-25">
                    <img src="/static/img/check-chequebook-template-blank-blue-business-bank-cheque-with-guilloche-pattern-rosette-abstract-watermark_119217-151.jpg"
                         width="626" height="305">
                </div>
            </div>
        </div>
    </div>
    <div class="button-page">
        <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#forTour">
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
                            <#if user_error??>Войдите или зарегайтесь</#if>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <a <#if cash_error??>href="/user/bank" <#else>href="/tour/buy/${tour.id}"</#if>
                                <#if cash_error??>class="btn btn-outline-primary"<#else>class="btn btn-outline-success"</#if>>
                            <#if cash_error??>Пополнить<#else>Купить</#if></a>
                    </div>
                </div>
            </div>
        </div>
        <#-- <a href="/tour/buy/${tour.id}" class="button-login-for-login-page" style="color: white">Купить тур</a>-->
    </div>
</@c.page>