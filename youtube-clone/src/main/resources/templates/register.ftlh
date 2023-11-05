<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <form action="/register" method="post">
        <div class="input-group has-validation">
            <span class="input-group-text">@</span>
            <div class="form-floating is-invalid">
                <input type="text" class="form-control bg-dark text-white outline-danger" id="floatingInputGroup2" placeholder="username" required name="name">
                <label for="floatingInputGroup2 text-white">Имя пользователя</label>
            </div>
        </div>
        <hr>
        <div class="form-group">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label text-white">Электронная почта</label>
                <input type="email" class="form-control bg-dark text-white outline-danger" id="exampleFormControlInput1" required name="email" placeholder="name@example.com">
            </div>
        </div>
        <div class="form-group ">
            <label for="inputPassword5" class="form-label text-white">Пароль</label>
            <input type="password" id="inputPassword5" name="password" class="form-control bg-dark text-white outline-danger" aria-labelledby="passwordHelpBlock" placeholder="Пароль">
            <div id="passwordHelpBlock" class="form-text">
                Ваш пароль должен состоять из 8-20 символов, содержать буквы и цифры и не должен содержать пробелов, специальных символов или эмодзи.
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-danger">Зарегистрироваться</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: rebeccapurple">${errorMessage}</h2>
    </#if>
</@t.template>