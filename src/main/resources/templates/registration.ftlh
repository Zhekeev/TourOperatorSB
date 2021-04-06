<#import "parts/common.ftlh" as c>
<#import "parts/input.ftlh" as i>

<@c.page>
    ${message?ifExists}
    <@i.login "/registration" true/>
</@c.page>