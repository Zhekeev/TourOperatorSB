<#macro login path isRegistredForm>
    <div>
    <form action="${path}" method="post">
        <div class="login-input">
            <div class="form-group">
                <div class="login-text-login-page">
                    <img class="log-in-image" src="/static/img/Adelaide.png">
                </div>
                <h2 class="login-input-label">Вход</h2>
                <label for="exampleInputEmail1">Логин</label>
                <div class="col-sm-6">
                    <input type="text"
                           name="username"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           aria-describedby="emailHelp"
                           value="<#if user??>${user.username}</#if>"
                           placeholder="Введите ваш логин">
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Пароль</label>
                <div class="col-sm-6">
                    <input type="password"
                           name="password"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           placeholder="Введите ваш пароль">
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <#if isRegistredForm>
                <div class="form-group">
                    <label for="exampleInputPassword1">Повторите пароль</label>
                    <div class="col-sm-6">
                        <input type="password"
                               name="secondPasswordForValidation"
                               class="form-control ${(secondPasswordForValidationError??)?string('is-invalid', '')}"
                               placeholder="Введите ваш пароль">
                        <#if secondPasswordForValidationError??>
                            <div class="invalid-feedback">
                                ${secondPasswordForValidationError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Имя</label>
                    <div class="col-sm-6">
                        <input type="text"
                               name="firstName"
                               class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                               aria-describedby="emailHelp"
                               value="<#if user??>${user.firstName}</#if>"
                               placeholder="Имя">
                        <#if firstNameError??>
                            <div class="invalid-feedback">
                                ${firstNameError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Фамилия</label>
                    <div class="col-sm-6">
                        <input type="text" name="lastName"
                               class="form-control  ${(lastNameError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.lastName}</#if>"
                               placeholder="Фамилия">
                        <#if lastNameError??>
                            <div class="invalid-feedback">
                                ${lastNameError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Телефон</label>
                    <div class="col-sm-6">
                        <input type="text" name="phone"
                               class="form-control  ${(phoneError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.phone}</#if>"
                               placeholder="+7 000 000 00 00">
                        <#if phoneError??>
                            <div class="invalid-feedback">
                                ${phoneError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Почта</label>
                    <div class="col-sm-6">
                        <input type="email" name="email"
                               class="form-control ${(emailError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.email}</#if>"
                               placeholder="example@example.com">
                        <#if emailError??>
                            <div class="invalid-feedback">
                                ${emailError}
                            </div>
                        </#if>
                    </div>
                </div>
                <h2>Адрес</h2>
                <div class="form-group">
                    <label for="exampleInputPassword1">Улица</label>
                    <div class="col-sm-6">
                        <input type="text" name="street"
                               class="form-control ${(streetError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.street}</#if>"
                               placeholder="Улица">
                        <#if streetError??>
                            <div class="invalid-feedback">
                                ${streetError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Номер дома</label>
                    <div class="col-sm-6">
                        <input type="text" name="houseNumber"
                               class="form-control ${(houseNumberError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.houseNumber}</#if>"
                               placeholder="Номер дома">
                        <#if houseNumberError??>
                            <div class="invalid-feedback">
                                ${houseNumberError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Номер квартиры</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control"
                               name="apartmentNumber"
                               value="<#if user??>${user.apartmentNumber}</#if>"
                               placeholder="Номер квартиры если есть">
                    </div>
                </div>
            </#if>
            <div>
                </br>
                <#if !isRegistredForm><span>У вас нету аккаунта? <a href="/registration">Создать</a></span></#if>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                </br>
                <button class="btn btn-primary" type="submit">Войти</button>
                </br>
            </div>
        </div>
    </form>
    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="image" src="/static/img/log-in.png" width="22" height="22" class="log-in"/>
    </form>
</#macro>

