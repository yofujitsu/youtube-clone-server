<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Авторизация</h1><hr>
    <form action="/login " method="post">
        <div class="form-group">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Электронная почта</label>
                <input type="email" class="form-control" <#if RequestParameters.error??>is-invalid</#if> id="exampleFormControlInput1" name="username" placeholder="name@example.com">
            </div>
            <div id="email" class="invalid-feedback">
                Неверная электронная почта
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword5" class="form-label">Пароль</label>
            <input type="password" <#if RequestParameters.error??>is-invalid</#if> id="inputPassword5" name="password" class="form-control" aria-labelledby="passwordHelpBlock">
            <div id="passwordHelpBlock" class="form-text">
                Ваш пароль должен состоять из 8-20 символов, содержать буквы и цифры и не должен содержать пробелов, специальных символов или эмодзи.
            </div>
            <div id="passwordHelpBlock" class="invalid-feedback">
                Неверный пароль
            </div>
        </div>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-danger">Войти</button>
    </form>
    <hr>
    <h5>Заходите впервые? <a href="/register" style="text-decoration: darkred">Зарегистрируйся!</a></h5>
</@t.template>