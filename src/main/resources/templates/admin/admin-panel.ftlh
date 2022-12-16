<#import "../template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Admin panel</h1>

    <table class="table table-hover table-dark">
        <tr>
            <th>Email</th>
            <th>Phone number</th>
            <th>Role</th>
            <th>Edit</th>
            <th>More info about user</th>
        </tr>
        <#list users as user>
            <tr>
                <th><#if user.email??>${user.email}<#else> no email</#if></th>
                <th>${user.phoneNumber}</th>
                <th><#list user.roles as role>${role}<#sep>, </#list></th>
                <th>
                    <form method="post" action="/admin/user/edit">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="Change role">
                    </form>
                </th>
                <th><a href="/user/${user.id}">More info about user</a></th>

            </tr>
        <#else >
            <h3>no users</h3>
        </#list>
    </table>


</@t.template>