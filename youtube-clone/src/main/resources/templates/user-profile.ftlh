<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h3 style="text-align: center">Профиль <#if currentUser.id == user.id>${currentUser.name}
    <#else>${user.name}</#if></h3>
    <h5 style="text-align: center"><#if currentUser.id == user.id>${currentUser.followersCount} подписчиков
        <#else>${user.followersCount} подписчиков</#if> | <#if currentUser.id == user.id>${currentUser.watchesCount} просмотров
        <#else>${user.watchesCount} просмотров</#if></h5>
    <hr>
    <div style="text-align: center">
        <img src="/static/images/avatar.jpg"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <hr>
        <#if currentUser.id != user.id>
            <form action="/profile/subscribe?userIdToFollow=${user.id}" method="post">
                <input type="hidden" name="userIdToFollow" value="${user.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-outline-danger mb-3" style="text-align: center;" type="submit">
                    <#if isUserSubscribed>
                        Отписаться
                    <#else>
                        Подписаться
                    </#if></button>
            </form>
        </#if>
        <#if currentUser.id == user.id>
            Электронная почта: <b>${user.email}</b>
            <br>
            Дата регистрации: <b>${user.dateOfCreated.getDayOfMonth()}-${user.dateOfCreated.getMonthValue()}-${user.dateOfCreated.getYear()}</b>
            <br>
            <hr>
        </#if>
        <#if user.isAdmin() && currentUser.id == user.id>
            <a class="btn btn-outline-danger mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a>
            <br>
        </#if>
        <#if currentUser.id == user.id>
            <a class="btn btn-outline-danger mb-3" style="text-align: center; width: 100%"
               href="/my/contentUnits">
                Управление видео
            </a>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                    Выйти из аккаунта
                </button>
            </form>
            <br>
        </#if>
    </div>

    <hr>
    <h3 style="text-align: center">Видео <#if currentUser.id == user.id>${currentUser.name}<#else>${user.name}</#if></h3>
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <#if currentUser.id == user.id>
            <#list currentUserContentUnits as contentUnit>
                <#if contentUnit.user?exists>
                <div class="col">
                    <a href="/contentUnit/edit?id=${contentUnit.id}" class="contentUnit-link">
                        <div class="card text-white bg-dark my-1 contentUnit">
                            <image src="${contentUnit.thumbnailUrl}" class="object-fit-fill"></image>
                            <div class="card-body">
                                <#assign truncatedTitle = contentUnit.title?substring(0, contentUnit.title?length - 3)?trim>
                                <h5 class="card-title">
                                    <h5 class="card-title">
                                        ${contentUnit.title}
                                    </h5>
                                </h5>
                            </div>
                            <div class="card-footer">
                                <form action="/contentUnit/delete?id=${contentUnit.id}" method="post">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" style="width: 100%" class="btn btn-danger">Удалить</button>
                                </form>
                            </div>
                        </div>
                    </a>
                </div>
            </#if>
        <#else>
            <h3 style="text-align: center">Не найдено</h3>
        </#list>
        <#else>
            <#list contentUnits as contentUnit>
                <#if contentUnit.user?exists>
                    <div class="col">
                        <a href="/contentUnit?id=${contentUnit.id}" class="contentUnit-link">
                            <div class="card text-white bg-dark my-1 contentUnit">
                                <image src="${contentUnit.thumbnailUrl}" class="object-fit-fill"></image>
                                <div class="card-body">
                                    <#assign truncatedTitle = contentUnit.title?substring(0, contentUnit.title?length - 3)?trim>
                                    <h5 class="card-title">
                                        <h5 class="card-title">
                                            ${contentUnit.title}
                                        </h5>
                                    </h5>
                                </div>
                            </div>
                        </a>
                    </div>
                </#if>
            <#else>
                <h3 style="text-align: center">Не найдено</h3>
            </#list>
        </#if>
    </div>
</@t.template>