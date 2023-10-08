<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Youtube 2.0</h1><hr>
    <form method="get" action="/youtube">
        <div class="row">
            <div class="form-group col-md-10">
                <label for="searchWord">Искать видео...</label>
                <input type="text" name="searchWord" id="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control">
                <br>
            </div>
            <button style="width: 30%" type="submit" class="btn btn-outline-danger">Найти</button>
        </div>
    </form>
    <hr>
    <hr>
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <#list contentUnits as contentUnit>
            <div class="col">
                <a href="/contentUnit/${contentUnit.id}" class="contentUnit-link">
                    <div class="card text-black bg-white my-1 contentUnit">
                        <video src="/videos/${contentUnit.previewVideoId}" class="object-fit-contain" autoplay></video>
                        <div class="card-body">
                            <h5 class="card-title">${contentUnit.title}</h5>
                        </div>
                        <div class="card-footer">
                            <h5 class="card-description">${contentUnit.description}</h5>
                        </div>
                    </div>
                </a>
            </div>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>