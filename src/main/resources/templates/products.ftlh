<#import "template.ftlh" as t>
<@t.template user>

<h1><center>Clothing shop</center></h1><hr>


    <form class="search" method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="gender">Пол</label>
                <select id="gender" name="gender" class="form-control">
                    <option value="-1">Сбросить</option>
                    <option value="0">Женский</option>
                    <option value="1">Мужской</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="category">Категория</label>
                <select id="category" name="category" class="form-control" >
                    <option value="-1">Без категории</option>
                    <option value="tshirt">Футболки</option>
                    <option value="hoodies">Худи</option>
                    <option value="sneakers">Кроссовки</option>
                    <option value="pants">Штаны</option>
                </select>
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
            <#--<input type="submit" class="btn btn-dark" value="Поиск">-->
        </div>
    </form>
    <hr>

    <div class="card-columns">
        <#list products as product>
            <#if (product.amount > 0)>
                <a href="/product/${product.id}" class="product-link">
                    <div class="card text-white bg-dark my-3 product">

                        <img src="/images/${product.previewImageId}"
                             class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">${product.title}</h5>
                            <p class="card-text">${product.price} ₽</p>
                        </div>
                    </div>
                </a>
            </#if>
            <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>
