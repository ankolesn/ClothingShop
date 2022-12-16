<#import "../template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Товары</h1>
    <br>
    <table class="table table-hover table-dark">
        <tr>
            <th>Наименование</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Количесво</th>
            <th>Добавить</th>
        </tr>
        <#list products as product>
            <tr>
                <th><a href="/product/${product.id}">${product.title}</th>
                <th>${product.description}</th>
                <th>${product.price}</th>
                <th>${product.amount}</th>
                <th>
                    <form action="/product/amount" method="post">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="number" value="0" min="1" max="20" name="amount">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="Добавить">
                    </form>
                </th>
            </tr>
        <#else>
            <h3>no products</h3>
        </#list>
    </table>
    <br>
    <a class="btn btn-dark mb-3" style="text-align: center;
     width: 20%" href="/product/add">
        Добавить товар
    </a>


</@t.template>