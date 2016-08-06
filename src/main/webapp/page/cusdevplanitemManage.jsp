<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>  
<body style="margin: 1px">   
<div id="p" class="easyui-panel" title="销售机会信息" style="width: 700px; height: 400px; padding: 10px">
	<table cellspacing="8px">
			<tr>
				<td>客户名称：</td>
				<td><input type="text"  id="customerName" name="customerName"   readonly="readonly"/></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>机会来源：</td>
				<td><input type="text"  id="chanceSource" name="chanceSource" readonly="readonly" /></td>
			<tr>
				<td>联系人：</td>
				<td><input type="text"  id="linkMan" name="linkMan" readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>联系电话：</td>
				<td><input type="text"  id="linkPhone" name="linkPhone"  readonly="readonly"/></td>
			</tr>
			<tr>
				<td>成功几率(%)：</td>
				<td><input type="text"  id="cgjl" name="cgjl"  data-options="min:0, max:100" readonly="readonly"/></td>
				<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>			
			<tr>
				<td>概要：</td>
				<td colspan="4"><input type="text"  id="overView" name="overView" style="width: 420px" readonly="readonly"/></td>
			</tr>			
			<tr>
				<td>机会描述：</td>
				<td colspan="4">
					<textarea rows="5" cols="50" id="description" name="description" readonly="readonly">
					</textarea>
				</td>
			</tr> 
			<tr>
				<td>创建人：</td>
				<td><input type="text"  id="createMan" name="createMan" class="easyui-validatebox" readonly="readonly"  /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>创建时间：</td>
				<td><input type="text"  id="createTime" name="createTime"  class="easyui-validatebox" readonly="readonly"  /></td>
			</tr>				
			<tr>
				<td>指派给：</td>
				<td>
					<input type="text" readonly="readonly" id="assignMan">
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>指派时间：</td>
				<td><input type="text"  id="assignTime" name="assignTime"   readonly="readonly" required="true" /></td>
			</tr>				
		</table>
</div>

  
<div id="dlg" class="easyui-dialog" style="width: 700px; height: 450px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post" action="">
		<table cellspacing="8px">
			<tr>
				<td>客户名称：</td>
				<td><input type="text"  id="customerName" name="customerName" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>机会来源：</td>
				<td><input type="text"  id="chanceSource" name="chanceSource"  /></td>
			<tr>
				<td>联系人：</td>
				<td><input type="text"  id="linkMan" name="linkMan"  /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>联系电话：</td>
				<td><input type="text"  id="linkPhone" name="linkPhone"  /></td>
			</tr>
			<tr>
				<td>成功几率(%)：</td>
				<td><input type="text"  id="cgjl" name="cgjl" class="easyui-numberbox" data-options="min:0, max:100" required="true"/><font color="red">*</font></td>
				<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>			
			<tr>
				<td>概要：</td>
				<td colspan="4"><input type="text"  id="overView" name="overView" style="width: 420px" /></td>
			</tr>			
			<tr>
				<td>机会描述：</td>
				<td colspan="4">
					<textarea rows="5" cols="50" id="description" name="description">
					</textarea>
				</td>
			</tr> 
			<tr>
				<td>创建人：</td>
				<td><input type="text"  id="createMan" name="createMan" class="easyui-validatebox" readonly="readonly" required="true" /><font color="red">*</font></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>创建时间：</td>
				<td><input type="text"  id="createTime" name="createTime"  class="easyui-validatebox" readonly="readonly" required="true" /><font color="red">*</font></td>
			</tr>				
			<tr>
				<td>指派给：</td>
				<td><input class="easyui-combobox" name="assignMan" id="assignMan" data-options="panelHeight:'auto', editable:false, valueField:'trueName', textField:'trueName', url:'${pageContext.request.contextPath}/user/customerManagerComboList.do'"/>
				
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>指派时间：</td>
				<td><input type="text"  id="assignTime" name="assignTime"  class="easyui-validatebox" readonly="readonly" required="true" /><font color="red">*</font></td>
			</tr>				
		</table>
	</form>
</div>

<br/>

<table id="dg" title="开发计划项" style="width: 700px; height: 250px"
	toolbar="#toolbar" idField="id" rownumbers="true" fitColumns="true" singleSelect="true">
	<thead>
  		<tr>
  			<th field="id" width="50" align="center">编号</th>
  			<th field="planDate" width="100" editor="{type:'datebox', options:{required:true}}">日期</th>
  			<th field="planItem" width="100" align="center" editor="{type:'validatebox', options:{required:true}}">计划内容</th>
  			<th field="exeAffect" width="100" align="center" editor="{type:'validatebox', options:{required:true}}">执行效果</th>
   		</tr>
  	</thead>

</table>

<div id="toolbar">
	<c:if test="${param.show != 'true' }">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">添加计划</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow');$('#dg').edatagrid('reload')">保存计划</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">撤销</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-kfcg" plain="true" onclick="updateSaleChanceDevResult(2)">开发成功</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" onclick="updateSaleChanceDevResult(3)">终止开发</a>
	</c:if>
</div>

<script type="text/javascript">

$(function(){
	$.post('${pageContext.request.contextPath}/saleChance/findById.do', {id: '${param.saleChanceId}'},function(result){
		$('#customerName').val(result.customerName);
		$('#chanceSource').val(result.chanceSource);
		$('#linkMan').val(result.linkMan);
		$('#linkPhone').val(result.linkPhone);
		$('#cgjl').val(result.cgjl);
		$('#overView').val(result.overView);
		$('#description').val(result.description);
		$('#createMan').val(result.createMan);
		$('#createTime').val(result.createTime);
		$('#assignMan').val(result.assignMan);
		$('#assignTime').val(result.assignTime);
	}, "json");
	
	$('#dg').edatagrid({
		url:'${pageContext.request.contextPath}/cusDevPlan/list.do?saleChanceId=${param.saleChanceId}',
		saveUrl:'${pageContext.request.contextPath}/cusDevPlan/save.do?saleChance.id=${param.saleChanceId}',
		updateUrl:'${pageContext.request.contextPath}/cusDevPlan/save.do?saleChance.id=${param.saleChanceId}',
		destroyUrl:'${pageContext.request.contextPath}/cusDevPlan/delete.do'
	});
	
});

function updateSaleChanceDevResult(devResult){
	$.post('${pageContext.request.contextPath}/cusDevPlan/updateSaleChanceDevResult.do', {id: '${param.saleChanceId}', devResult:devResult},function(result){
		if(result.success){
			$.messager.alert("系统提示", "执行成功");
		} else {
			$.messager.alert("系统提示", "执行失败");
		}
	}, "json");
}


	function searchSaleChance(){
		$('#dg').datagrid('load', {
			'customerName':$('#s_customerName').val(),
			'overView':$('#s_overView').val(),
			'createMan':$('#s_createMan').val(),
			'state':$('#s_state').combobox('getValue')
		});
	}
	
	function formatState(val, row){
		switch (val){
		 case 0:
			 return '未分配';
		 case 1:
			 return '已分配';
	 }
	}
	
	
</script>
  
</body>  

</html>  
