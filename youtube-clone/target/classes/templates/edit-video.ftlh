<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#if user.isEnabled()>
        <div class="col">
            <a href="/contentUnit?id=${contentUnit.id}" class="contentUnit-link">
                <div class="card text-white bg-dark my-1 contentUnit">
                    <image src="${contentUnit.thumbnailUrl}" class="object-fit-fill"></image>
                    <div class="card-body">
                        <h5 class="card-title">
                            <h5 class="card-title">
                                ${contentUnit.title}
                            </h5>
                        </h5>
                    </div>
                </div>
            </a>
        </div>
        <a class="btn btn-danger mt-3 mb-3" style="text-align: center; width: 100%"
           data-bs-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="false" aria-controls="collapseExample">
            Редактировать данные видео
        </a>
        <div class="collapse mb-3" id="collapseExample" style="">
            <form action="/contentUnit/edit?id=${contentUnit.id}" method="post">
                <div class="form-group">
                    <label for="title">Название видео</label>
                    <input type="text" required class="form-control bg-dark text-white outline-danger"
                           id="title" name="title" value=${contentUnit.title}>
                </div>
                <div class="form-group">
                    <label for="thumbnailUrl">Превью видео</label>
                    <input required class="form-control bg-dark text-white outline-danger" id="thumbnailUrl" name="thumbnailUrl" value=${contentUnit.thumbnailUrl}>
                </div>
                <div class="form-group">
                    <label for="description">Описание видео</label>
                    <textarea required class="form-control bg-dark text-white outline-danger" id="description" name="description" value=${contentUnit.description}></textarea>
                </div>
                <br>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="width: 100%" class="btn btn-danger" id="saveButton">Редактировать</button>
            </form>
        </div>
    </#if>
    <script>
        document.getElementById("saveButton").addEventListener("click", function() {
            const title = document.getElementById("title").value;
            const description = document.getElementById("description").value;
            const thumbnailUrl = document.getElementById("thumbnailUrl").value;

            const data = {
                "title": title,
                "description": description,
                "thumbnailUrl": thumbnailUrl
            };
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "/contentUnit/edit?id=${contentUnit.id}", true);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

            xhr.send(JSON.stringify(data));

            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    alert("Изменения сохранены успешно!");
                }
            };
        });
    </script>
</@t.template>