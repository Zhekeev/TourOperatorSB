<#macro pager url page>
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Страницы</a>
        </li>
        <#list 1..page.getTotalPages() as p>
            <#if (p+1) == page.getNumber()>
                <li class="page-item active">
                    <a class="page-link" href="#">${p}</a>
                </li>
            <#else >
                <li class="page-item">
                    <a class="page-link" href="${url}?page=${p}">${p}</a>
                </li>
            </#if>
        </#list>
    </ul>
</#macro>