<#import "template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Корзина</h1>
    <hr>
    <br>

    <#if cartPairs?size gt 0>
        <h2>Товары:</h2>
    </#if>
    <h3><ol>
    <#list cartPairs as cartPair>
        <li>
            <h3><a href="/product/${cartPair.product.id}">${cartPair.product.title}</a></h3>
            <h3>Цена: ${cartPair.product.price}</h3>
            <h3>Колличество: ${cartPair.amount}</h3>
            <br>
        </li>
        <#else>
            <h3>Корзина пуста</h3>
    </#list>
    </ol></h3>


    <#if cartPairs?size gt 0>
        <h3>Общая цена: ${totalPrice}</h3>
        <form method="post" action="../order/add">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input class="btn btn-dark mb-3" style="text-align: center; width: 20%"
                   type="submit" value="Заказать">
        </form>
    </#if>



</@t.template>