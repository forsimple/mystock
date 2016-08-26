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
					<th width="150">总资产</th>
					<th width="150">流动资产</th>
					<th width="150">固定资产</th>
					<th width="150">pe</th>
					<th width="150">pb</th>
					<th width="150">公积金</th>
					<th width="150">每股公积金</th>
					<th width="150">总股本</th>
					<th width="150">流通股本</th>
                </tr>
            </thead>
            <tbody>
                <#list ticketPage.contents as ticket>
                <tr>
                    <td>${ticket.id}</td>
                    <td>${ticket.name}</td>
                    <td>${ticket.totalAssets}</td>
                    <td>${ticket.liquidAssets}</td>
                    <td>${ticket.fixedAssets}</td>
                    <td>${ticket.pe}</td>
                    <td>${ticket.pb}</td>
                    <td>${ticket.reserved}</td>
                    <td>${ticket.reservedPerShare}</td>
                    <td>${ticket.totals}</td>
                    <td>${ticket.outstanding}</td>
                    <td>${ticket.bvps}</td>
                    <td>${ticket.esp}</td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
            <@dwz.pageNav pageModel=ticketPage />
        </div>
    </div>
</div>