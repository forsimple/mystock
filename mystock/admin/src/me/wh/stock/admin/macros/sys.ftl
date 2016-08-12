<#macro organTree organ>
	<ul class="tree" layoutH="36">
		<@organTreeNode organ=organ />
	</ul>
</#macro>

<#macro organTreeNode organ>
	<li>
		<@dwz.a href="/system/organ-edit?organ=${organ.id}" target="ajax" rel="organBox" organId="${organ.id}" style="color:${organ.enabled.color}">${organ.name}</@dwz.a>
		<#if organ.isRoot()>
			<@dwz.a href="/system/organ-add" target="dialog" class="right">[新增机构]</@dwz.a>
	    <#else>
			<#if organ.enabled == EnabledStatus.ENABLED>
	       		<@dwz.a href="/system/organ-disable?organ=${organ.id}" target="ajaxTodo" title="您是否确定要停用该机构？" class="right">[停用]</@dwz.a>
	       	<#else>
	       		<@dwz.a href="/system/organ-enable?organ=${organ.id}" target="ajaxTodo" title="您是否确定要启用该机构？" class="right">[启用]</@dwz.a>
	       	</#if>
	    	
       	</#if>
		<#if organ.childs?size gt 0>
			<ul>
		        <#list organ.childs as childOrgan>
		        	<@organTreeNode organ=childOrgan />
		        </#list>
        	</ul>
        </#if>
    </li>
</#macro>

<#macro permissions permissionGroups>
    <#list permissionGroups as permissionGroup>
		<fieldset>
			<legend>${permissionGroup.name}</legend>
	        <#list permissionGroup.permissions as permission>
		        <label>
		            <input type="checkbox" name="permissionIds" value="${permission.id}"<#if permissionIds?seq_contains(permission.id)> checked="checked"</#if> />
		            ${permission.name}
		        </label>
	        </#list>
		</fieldset>
    </#list>
</#macro>

<#macro log_view log>
	<dl>
		<dt>操作时间：</dt>
		<dd>${log.createDate?datetime}</dd>
	</dl>
	<dl>
		<dt>操作用户：</dt>
		<dd>${log.creator}</dd>
	</dl>
	<dl class="nowrap">
		<dt>操作内容：</dt>
		<dd>${log.message}</dd>
	</dl>
	<#if log.hasData()>
		<table class="list" width="98%">
			<thead>
				<tr>
					<th width="20%">字段</th>
					<th width="40%">原值</th>
					<th width="40%">新值</th>
				</tr>
			</thead>
			<tbody>
				<#list log.toLogData() as data>
					<tr>
						<td style="font-weight:bold">${data.text}</td>
						<td style="word-break:break-all">${data.origData}</td>
						<td style="word-break:break-all;<#if data.isChanged()>color:red;</#if>">${data.newData}</td>
					</tr>
				</#list>
			</tbody>
		</table>
	</#if>
</#macro>