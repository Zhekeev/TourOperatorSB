<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
              crossorigin="anonymous">
        <meta charset="UTF-8">
        <title>Adelaide | Tour Operator</title>
        <link rel="shortcut icon" href="/static/img/travel-agency-logo-4BC8875532-seeklogo.com.png"/>
        <link rel="stylesheet" href="/static/css/style.css">
    </head>
    <body>
    <#include "navbar.ftlh">
    <div class="container">
        <#nested>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
    </body>
    <#include "footer.ftlh">
    </html>
</#macro>