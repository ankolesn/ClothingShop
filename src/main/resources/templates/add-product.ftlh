<#import "template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Добавить продукт</h1>
    <hr>
    <br>
<form id="addProduct" method="post" action="/product/create" enctype="multipart/form-data">
    Заголовок: <input type="text" name="title"><br>
    Описание: <input type="text" name="description"><br>
    Цена: <input type="number" name="price"><br>
    Количество: <input type="number" name="amount" min="1" max="50"><br>


    <#--Пол: <input type="checkbox" name="category_gender" value="0"><br>
    Кросовки: <input type="checkbox" name="category_sneakers" value="0"><br>
    Футболка: <input type="checkbox" name="category_tshirt" value="0"><br>
    Худи: <input type="checkbox" name="category_hoodies" value="0"><br>
    Штаны: <input type="checkbox" name="category_pants" value="0"><br>-->
    Пол:<br>
    <select name="gender" form="addProduct">
        <option value="0">Мужкой</option>
        <option value="1">Женский</option>
    </select><br><br>

    Категория:<br>
    <select name="category" form="addProduct">
        <option value="sneakers">Кроссовки</option>
        <option value="tshirt">Футбока</option>
        <option value="hoodies">Худи</option>
        <option value="pants">Шатаны</option>
    </select><br><br>

    <input type="file" name="file1"><br>
    <input type="file" name="file2"><br>
    <input type="file" name="file3"><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}"><br>
    <input type="submit">
</form>

</@t.template>