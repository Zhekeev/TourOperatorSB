<#macro login path isRegistredForm>
    <form action="${path}" method="post">
        <div class="login-input">
            <div class="form-group">
                <label for="exampleInputEmail1">Логин</label>
                <div class="col-sm-6">
                <input type="text" class="form-control" name="username" aria-describedby="emailHelp" placeholder="Введите ваш логин">
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Пароль</label>
                <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="Введите ваш пароль">
                </div>
            </div>
        <#if isRegistredForm>
            <div class="form-group">
                <label for="exampleInputEmail1">Имя</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="firstName" aria-describedby="emailHelp" placeholder="Имя">
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Фамилия</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="lastName" placeholder="Фамилия">
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Телефон</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="phone" placeholder="+7 000 000 00 00">
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Почта</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" name="email" placeholder="example@example.com">
                </div>
            </div>
            <h2>Адрес</h2>
            <div class="form-group">
                <label for="exampleInputPassword1">Улица</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="street" placeholder="Улица">
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Номер дома</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="houseNumber" placeholder="Номер дома">
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Номер квартиры</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="apartmentNumber" placeholder="Номер квартиры если есть">
                </div>
            </div>
        </#if>
            <div>
                <#if !isRegistredForm><span>У вас нету аккаунта? <a href="/registration">Создать</a></span></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
                </br>
        <button class="btn btn-primary" type="submit">Войти</button>
                </br>
            </div>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="image" src="/static/img/log-in.png" width="22" height="22" class="log-in"/>
    </form>
</#macro>

