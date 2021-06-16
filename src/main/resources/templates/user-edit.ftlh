<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/admin/user/edit" method="post">
        <div class="container profile-container">
            <h1 class="display-5">Настройка пользователя</h1>
            <div class="container" style="margin-top: 45px;">
                <div class="row">
                    <div class="col">
                        <div class="card-body">
                            <div class="input-group mt-2 mb-3">
                                <span class="input-group-text">Логин и Пароль</span>
                                <input type="text" name="username" class="form-control" value="${user.username}">
                                <input type="password" name="password" class="form-control">
                            </div>
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
                                <input type="text" name="phone" class="form-control" value="${user.phone}">
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
                        </div>
                    </div>
                    <div class="col">
                        <h1 class="display-5">Роли</h1>

                            <div>
                                <label>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                                        <label class="form-check-label" for="flexSwitchCheckDefault">ADMIN</label>
                                    </div>
                                </label>
                                <label>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                                        <label class="form-check-label" for="flexSwitchCheckDefault">USER</label>
                                    </div>
                                </label>
                            </div>
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </div>
                </div>
            </div>
        </div>
    </form>
</@c.page>
