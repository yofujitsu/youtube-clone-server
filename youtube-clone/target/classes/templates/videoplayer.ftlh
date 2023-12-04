<#import "blocks/template.ftlh" as t>
<@t.template user>
    <p><h3 style="text-align: center">${contentUnit.title}</h3></p>
    <div class="row" style="justify-content: space-around">
        <div class="card border-danger bg-dark text-white" style="width: 100%; height: 100%">
            <div class="card-body bg-dark text-white">
                <video src="${contentUnit.video.url}" class="object-fit-fill" controls="controls"></video>
                <hr>
                <div class="row">
                    <div class="col">
                        <a href="/profile?id=${contentUnit.user.id}" style="text-decoration: none; color: darkred">${contentUnit.user.name}</a> •
                        ${contentUnit.description} •
                        <#if contentUnit.watches % 10 == 1 && contentUnit.watches % 100 != 11>
                            ${contentUnit.watches} просмотр
                        <#elseif contentUnit.watches % 10 < 5 && contentUnit.watches % 10 != 0 && contentUnit.watches % 100 != 12
                        && contentUnit.watches % 100 != 13 && contentUnit.watches % 100 != 14>
                            ${contentUnit.watches} просмотра
                        <#else>
                            ${contentUnit.watches} просмотров
                        </#if>
                        • ${contentUnit.updateDateDesc()}
                    </div>
                </div>
            </div>
        </div>

    </div>
    


</@t.template>