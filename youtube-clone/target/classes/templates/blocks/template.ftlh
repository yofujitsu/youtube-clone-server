<#macro template user>
    <!doctype html>
    <html lang="ru">
    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/styles.css">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
              crossorigin="anonymous">

        <link rel="icon" href="https://avatars.mds.yandex.net/i?id=639a8f4386132bf73ea0a065bde0ea158b407d33-8899644-images-thumbs&n=13">
        <style>
            body {
                background-color: #212529;
            }
            h1, h2, h3, h4, h5, h6, h7, h8 {
                color: #fff;
            }
            a {
                text-decoration: none;
            }
            label {
                color: white;
            }
        </style>
        <b><title>Youtube 2.0</title></b>
    </head>
    <body>
    <nav class="navbar bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/youtube" style="text-decoration: none; color: #fff">
               Youtube 2.0
            </a>
            <#if user.email??>
                <div class="dropdown text-end">
                    <a href="/profile?id=${user.id}">
                        <img src="/static/images/avatar.jpg" alt="mdo"
                             width="32" height="32" class="rounded-circle">
                    </a>
                </div>
            <#else>
                <button class="btn btn-outline-danger" type="button"
                        onclick="window.location.href = '/login'">
                    Вход / Регистрация
                </button>
            </#if>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
            integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
            integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
            crossorigin="anonymous"></script>

    </body>
    </html>
</#macro>