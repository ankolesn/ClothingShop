<#import "template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Заказ</h1>
    <hr>
    <br>
    <h3>Номер заказа: ${order.id}</h3>
    <h3>Дата заказа: ${order.date}</h3>
    <h3>Статус заказа: ${order.status}</h3>
    <h3>Общая цена: ${order.price}</h3>

    <h3>Товары: </h3>
    <table class="table table-hover table-dark">
        <tr>
            <th>Наименование</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Колличество</th>
        </tr>
        <#list cartPairs as cartPair>
            <tr>
                <th><a href="/product/${cartPair.product.id}">${cartPair.product.title}</a></th>
                <th>${cartPair.product.description}</th>
                <th>${cartPair.product.price}</th>
                <th>${cartPair.amount}</th>
            </tr>
        </#list>
    </table>


</@t.template>