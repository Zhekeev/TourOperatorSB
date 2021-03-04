<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <div class="mt-2">
                    <h1 class="display-7">Настройка аккаунта</h1>
                </div>
                <div class="input-group mt-2 mb-3">
                    <span class="input-group-text">Имя и Фамилия</span>
                    <input type="text" aria-label="First name" class="form-control" value="${user.firstName}">
                    <input type="text" aria-label="Last name" class="form-control" value="${user.lastName}">
                </div>
                <div class="input-group mt-1 mb-3">
                    <span class="input-group-text" id="basic-addon2">Почта</span>
                    <input type="text" class="form-control" placeholder="Recipient's username" value="${user.email}" aria-label="Recipient's username" aria-describedby="basic-addon2">
                </div>
                <div class="input-group mt-1 mb-3">
                    <span class="input-group-text">ул.</span>
                    <input type="text" class="form-control" placeholder="Username" value="${user.street}" aria-label="Username">
                    <span class="input-group-text">дом</span>
                    <input type="text" class="form-control" placeholder="Server" value="${user.houseNumber}" aria-label="Server">
                    <span class="input-group-text">кв.</span>
                    <input type="text" class="form-control" placeholder="Server" value="${user.houseNumber}" aria-label="Server">
                </div>
                <button type="button" class="btn btn-outline-primary">Обновить</button>
            </div>
            <div class="col">
                <div class="mt-2">
                    <h1 class="display-7">Сменить пароль</h1>
                </div>
                <div class="input-group mt-1 mb-3">
                    <span class="input-group-text" id="basic-addon2">&#160&#160Новый пароль&#160&#160&#160</span>
                    <input type="password" class="form-control"  aria-describedby="basic-addon2">
                </div>
                <div class="input-group mt-1 mb-3">
                    <span class="input-group-text" id="basic-addon2">Повторите пароль</span>
                    <input type="password" class="form-control" aria-describedby="basic-addon2">
                </div>
                <button type="button" class="btn btn-outline-primary">Сменить</button>
            </div>
        </div>
    </div>
</@c.page>