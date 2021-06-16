<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="col">
        <form action="/user/password/edit" method="post">
            <div class="mt-2">
                <h1 class="display-7">Сменить пароль</h1>
            </div>
            <div class="input-group mt-1 mb-3">
                <span class="input-group-text" id="basic-addon2">&#160&#160Новый пароль&#160&#160&#160</span>
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       aria-describedby="basic-addon2">
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
            <div class="input-group mt-1 mb-3">
                <span class="input-group-text" id="basic-addon2">Повторите пароль</span>
                <input type="password" name="verificationPassword" class="form-control ${(verificationPasswordError??)?string('is-invalid', '')}"
                       aria-describedby="basic-addon2">
                <#if verificationPasswordError??>
                    <div class="invalid-feedback">
                        ${verificationPasswordError}
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-outline-primary">Сменить</button>
        </form>
    </div>
</@c.page>
