<#import "blocks/template.ftlh" as t>
<@t.template user>
    <form method="get" action="/youtube">
        <div class="row">
            <div class="input-group md-3">
<#--                <label for="searchWord"></label>-->
                <input type="text" name="searchWord" id="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control text-white bg-dark" placeholder="Введите запрос" aria-label="Введите запрос" aria-describedby="button-addon2">
                <button type="submit" class="btn btn-outline-light" id="button-addon2">Искать</button>
            </div>
        </div>
    </form>
    <hr>
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <#list contentUnits as contentUnit>
            <div class="col">
                <a href="/contentUnit/${contentUnit.id}" class="contentUnit-link">
                    <div class="card text-white bg-dark my-1 contentUnit">
                        <video src="${contentUnit.video.url}" class="object-fit-contain"></video>
                        <div class="card-body">
                            <h5 class="card-title">
                                    ${contentUnit.title}
                            </h5>
                        </div>
                        <div class="card-footer">
                            <h8 class="card-description">${contentUnit.user.name}</h8>
                        </div>
                    </div>
                </a>
            </div>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>