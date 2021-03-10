<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-2">
                    <h1 class="display-7">Настройка аккаунта</h1>
                </div>
                <form action="/user/edit" method="post">
                    <div class="input-group mt-2 mb-3">
                        <span class="input-group-text">Имя и Фамилия</span>
                        <input type="text" name="firstName" class="form-control" value="${user.firstName}">
                        <input type="text" name="lastName" class="form-control" value="${user.lastName}">
                    </div>
                    <div class="input-group mt-1 mb-3">
                        <span class="input-group-text" id="basic-addon2">Почта</span>
                        <input type="text" name="email" class="form-control" value="${user.email}"
                               aria-label="Recipient's username" aria-describedby="basic-addon2">
                    </div>
                    <div class="input-group mt-2 mb-3">
                        <span class="input-group-text">Номер телефона</span>
                        <input type="text" name="phoneNumber" class="form-control" value="${user.phone}">
                    </div>
                    <div class="input-group mt-1 mb-3">
                        <span class="input-group-text">ул.</span>
                        <input type="text" name="street" class="form-control" value="${user.street}">
                        <span class="input-group-text">дом</span>
                        <input type="text" name="houseNumber" class="form-control" value="${user.houseNumber}">
                        <span class="input-group-text">кв.</span>
                        <input type="text" name="apartmentNumber" class="form-control" value="${user.apartmentNumber}">
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-primary">Обновить</button>
                </form>
            </div>
            <div class="col">
                <form action="/user/password/edit" method="post">
                    <div class="mt-2">
                        <h1 class="display-7">Сменить пароль</h1>
                    </div>
                    <div class="input-group mt-1 mb-3">
                        <span class="input-group-text" id="basic-addon2">&#160&#160Новый пароль&#160&#160&#160</span>
                        <input type="password" name="password" class="form-control" aria-describedby="basic-addon2">
                    </div>
                    <div class="input-group mt-1 mb-3">
                        <span class="input-group-text" id="basic-addon2">Повторите пароль</span>
                        <input type="password" name="verificationPassword" class="form-control"
                               aria-describedby="basic-addon2">
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-outline-primary">Сменить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>