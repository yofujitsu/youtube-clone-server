<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Профиль ${user.name}</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/avatar.jpg"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        Электронная почта: <b>${user.email}</b>
        <br>
        <br>
        Дата регистрации: <b>08.10.2023</b>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a><br>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/my/contentUnits">
                Управление видео
            </a>
        </#if>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>