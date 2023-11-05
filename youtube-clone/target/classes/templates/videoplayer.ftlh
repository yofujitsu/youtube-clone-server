<#import "blocks/template.ftlh" as t>
<@t.template user>
    <p><h3 style="text-align: center">${contentUnit.title}</h3></p>
    <div class="row" style="justify-content: space-around">
        <div class="card border-danger bg-dark text-white" style="width: 100%; height: 100%">
            <div class="card-body bg-dark text-white">
                <video src="${video.url}" class="object-fit-fill" controls="controls"></video>
                <hr>
                <div class="row">
                    <div class="col">
                        ${contentUnit.description}
                    </div>
                    <#if user.isEnabled()>
                        <div class="col">
                            <form action="/contentUnit/${contentUnit.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" style="width: 100%" class="btn btn-danger">Мне нравится</button>
                            </form>
                            <span>${contentUnit.likes} likes</span>
                        </div>
                    </#if>
                </div>
            </div>
        </div>

    </div>
    


</@t.template>