<#import "template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Добавить продукт</h1>
    <hr>
    <br>
<form id="addProduct" method="post" action="/product/create" enctype="multipart/form-data">
    Заголовок: <input type="text" name="title"><br>
    Описание: <input type="text" name="description"><br>
    Цена: <input type="number" name="price" min="1" ><br>
    Количество: <input type="number" name="amount" min="1" max="50"><br>

    Пол:<br>
    <select name="gender" form="addProduct">
        <option value="0">Мужской</option>
        <option value="1">Женский</option>
    </select><br><br>

    Категория:<br>
    <select name="category" form="addProduct">
        <option value="sneakers">Кроссовки</option>
        <option value="tshirt">Футболки</option>
        <option value="hoodies">Худи</option>
        <option value="pants">Штаны</option>
    </select><br><br>

    <input type="file" name="file1"><br>
    <input type="file" name="file2"><br>
    <input type="file" name="file3"><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}"><br>
    <input type="submit">
</form>

</@t.template>