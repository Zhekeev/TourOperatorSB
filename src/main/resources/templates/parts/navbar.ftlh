<#import "input.ftlh" as i>
<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand-logo" href="/"> <img src="/static/img/travel-agency-logo-4BC8875532-seeklogo.com.png" width="60" height="55"></a>
        <a class="brand-name" href="/"> <img src="/static/img/Adelaide.png" class="brand-name"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/" style=" color: white;">Домой</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/tour/all" style=" color: white;">Туры</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/contacts" style=" color: white;">Контакты</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/about" style=" color: white;">О нас</a>
                </li>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="profile-href active" aria-current="page" href="/admin" style=" color: white;"><img class="profile-href" src="/static/img/admin-with-cogwheels.png" width="24px" height="24px"></a>
                    </li>
                </#if>
                <li class="nav-item">
                    <a class="profile-href active" aria-current="page" href="/user/profile" style=" color: white;"><img class="profile-href" src="/static/img/user-profile.png"></a>
                </li>
                <li class="nav-item">
                    <@i.logout/>
                </li>
            </ul>
        </div>
    </div>
</nav>