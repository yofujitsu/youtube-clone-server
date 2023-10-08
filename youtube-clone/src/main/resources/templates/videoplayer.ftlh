<#import "blocks/template.ftlh" as t>
<@t.template user>
    <p><h3 style="text-align: center">${contentUnit.title}</h3></p>
    <div class="row" style="justify-content: space-around">
        <div class="card border-danger" style="width: 100%; height: 100%">
            <div class="card-body">
                <video src="/videos/${video.id}" class="object-fit-contain" controls="controls"></video>
            </div>
        </div>
        <br>
        ${contentUnit.description}
    </div>

</@t.template>