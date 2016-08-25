<div class="page">
    <div class="pageHeader">
        <@dwz.pageForm action="/company/employee-list" />
    </div>
    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li><@dwz.a href="/ticket/ticket-sync" target="ajaxTodo" title="您确定要同步吗？">同步</@dwz.a></li>
            </ul>
        </div>
        <table class="table" width="100%" layoutH="112">
            <thead>
                <tr>
                    <th width="100">代码</th>
					<th width="150">名称</th>
                </tr>
            </thead>
            <tbody>
                <#list ticketPage.contents as ticket>
                <tr>
                    <td>${ticket.id}</td>
                    <td>${ticket.name}</td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
            <@dwz.pageNav pageModel=ticketPage />
        </div>
    </div>
</div>