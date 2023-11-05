<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h3 style="text-align: center">Профиль ${user.name}</h3>
    <hr>
    <div style="text-align: center">
        <img src="/static/images/avatar.jpg"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <hr>
        Электронная почта: <b>${user.email}</b>
        <br>
        Дата регистрации: <b>${user.dateOfCreated}</b>
        <br>
        <hr>
        <#if user.isAdmin()>
            <a class="btn btn-outline-danger mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a>
        </#if>
        <br>
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
    </div>
    <br>
    <hr>
    <h3 style="text-align: center">Мои видео</h3>
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <#list contentUnits as contentUnit>
            <#if contentUnit.user?exists>
                <div class="col">
                    <a href="/contentUnit/${contentUnit.id}" class="contentUnit-link">
                        <div class="card text-white bg-dark my-1 contentUnit">
                            <video src="${contentUnit.video.url}" class="object-fit-contain"></video>
                            <div class="card-body">
                                <#assign truncatedTitle = contentUnit.title?substring(0, contentUnit.title?length - 3)?trim>
                                <h5 class="card-title">
                                    <h5 class="card-title">
                                        ${contentUnit.title}
                                    </h5>
                                </h5>
                            </div>
                            <div class="card-footer">
                                <form action="/contentUnit/delete/${contentUnit.id}" method="post">
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
    </div>
</@t.template>