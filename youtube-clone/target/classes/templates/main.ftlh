<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Youtube 2.0 - Новый видеохостинг</h2><hr>
    <div id="carouselExampleIndicators" class="carousel slide">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://images.wallpaperscraft.ru/image/single/devushka_trava_gorod_213102_1280x720.jpg" class="d-block w-100">
                <div class="carousel-caption d-none d-md-block">
                    <a href="/youtube"><button type="button" class="btn btn-outline-danger btn-lg">Смотреть видео</button></a>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://images.wallpaperscraft.ru/image/single/devushka_listia_vetki_875709_1280x720.jpg" class="d-block w-100">
                <div class="carousel-caption d-none d-md-block">
                    <a href="/register"><button type="button" class="btn btn-outline-danger btn-lg">Регистрация</button></a>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</@t.template>