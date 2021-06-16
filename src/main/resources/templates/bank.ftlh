<#import "parts/common.ftlh" as c>

<@c.page>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <div class="container profile-container">
        <h1 class="display-5">Личный счет</h1>
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <form action="<#if card_secret_number??>/user/bank/delete<#else>/user/bank/add</#if>"
                          method="<#if card_secret_number??>get<#else>post</#if>">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    Детали банковской карты
                                </h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="cardNumber">
                                        Номер карточки</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="cardNumber" id="cardNumber"
                                               <#if card_number??>readonly</#if>
                                               placeholder="0123 4567 8910 1112" <#if card_number??>value="${card_number}"</#if>
                                               required autofocus/>
                                        <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-lock"></span></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label for="cvCode">
                                                Дата срока действия</label>
                                            <input type="text" class="form-control" id="cvCode" placeholder="ММ/ГГ"
                                                   <#if card_date??>value="${card_date}"</#if> <#if card_date??>readonly</#if>
                                                   name="cardDate" required maxlength="5"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label for="cvCode">
                                                CVV</label>
                                            <input type="password" class="form-control" id="cvCode" placeholder="CVV"
                                                   <#if card_secret_number??>
                                                       value="***"</#if>
                                                    <#if card_secret_number??>readonly</#if>
                                                   name="cvv" required maxlength="5"/>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit"
                                        class="btn <#if card_secret_number??>btn-danger<#else>btn-success</#if> btn-lg btn-block"><#if card_secret_number??>Удалить<#else>Добавить</#if></button>
                            </div>
                        </div>
                        <br/>
                    </form>
                </div>
                <div class="col-sm">
                    <div class="col-sm">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-muted">Пополнить счет</span>
                        </h4>
                        <div class="input-group">
                            <form action="/user/bank/cash/add" method="post">
                                <input type="text" class="form-control" name="money" placeholder="Введите сумму в ₸"
                                       <#if card_secret_number??><#else>readonly</#if>>
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                </br>
                                <button type="submit" class="btn btn-secondary" <#if card_secret_number??><#else>disabled</#if>>Пополнить</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-sm">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Сумма на счету
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label for="cardNumber">
                                    Сумма</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="cardNumber" id="cardNumber"
                                           readonly
                                           placeholder="На счету пусто" <#if card_summ??>value="${card_summ}"</#if>
                                           required autofocus/>
                                    <span class="input-group-addon">₸</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>
