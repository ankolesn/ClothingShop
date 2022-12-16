<#import "template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои заказы</h1>
    <hr>
    <br>
    <table class="table table-hover table-dark">
        <tr>
            <th>Номер заказа</th>
            <th>Сумма</th>
            <th>Дата</th>
            <th>Статус</th>
            <th>Информация о заказе</th>
            <th>Отменить заказ</th>
        </tr>
        <#list orders as order>
            <tr>
                <th>${order.id}</th>
                <th>${order.price}</th>
                <th>${order.date}</th>
                <th>${order.status}</th>
                <th><a href="/order/${order.id}">Подробнее</a></th>
                <th>
                    <form action="/my/order/delete/${order.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="Отменить">
                    </form>
                </th>

            </tr>
        <#else >
            <h3>Нет заказов</h3>
        </#list>
    </table>


</@t.template>