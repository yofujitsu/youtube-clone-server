<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#if user.isEnabled()>
        <a class="btn btn-danger mt-3 mb-3" style="text-align: center; width: 100%"
           data-bs-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="false" aria-controls="collapseExample">
            Создать видео
        </a>
        <div class="collapse mb-3" id="collapseExample" style="">
            <form action="/contentUnit/create" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="contentUnitName">Название видео</label>
                    <input type="text" required class="form-control"
                           id="contentUnitName" name="title">
                </div>
                <div class="form-group">
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Загрузка видео</label>
                        <input required class="form-control" type="file" id="formFile" name="file1">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Описание видео</label>
                    <textarea required class="form-control" id="description" name="description"></textarea>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="width: 100%" class="btn btn-danger">Опубликовать</button>
            </form>
        </div>
    </#if>
</@t.template>