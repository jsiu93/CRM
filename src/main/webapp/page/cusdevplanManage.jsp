<%@ page language="java" pageEncoding="UTF-8"%>  

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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>  
<body style="margin: 1px">   
  <table id="dg" title="客户开发计划管理" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true" 
  url="${pageContext.request.contextPath}/saleChance/list.do?state=1" fit="true" toolbar="#tb">
  	<thead>
  		<tr>
  			<th field="cb" checkbox="true" align="left"></th>
  			<th field="id" width="50" align="center">编号</th>
  			<th field="chanceSource" width="200" align="center" hidden="true">机会来源</th>
  			<th field="customerName" width="100" align="center">客户名称</th>
  			<th field="cgjl" width="50" align="center" hidden="true">成功几率</th>
  			<th field="overView" width="100" align="center" >概要</th>
  			<th field="linkMan" width="80" align="center">联系人</th>
  			<th field="linkPhone" width="100" align="center" hidden="true">联系电话</th>
  			<th field="description" width="200" align="center" hidden="true">机会描述</th>
  			<th field="createMan" width="100" align="center">创建人</th>
  			<th field="createTime" width="100" align="center">创建时间</th>
  			<th field="assignMan" width="50" align="center" >指派人</th>
  			<th field="assignTime" width="200" align="center" >指派时间</th>
  			<th field="state" width="80" align="center" formatter="formatState" hidden="true">状态</th>
  			<th field="devResult" width="50" align="center" formatter="formatDevResult">客户开发状态</th>
  			<th field="a" width="80" align="center" formatter="formatAction">操作</th>
  		</tr>
  	</thead>
  </table>
  <div id="tb">
  	<div>
  		<a href="javascript:openSaleChanceAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>
  		<a href="javascript:openSaleChanceModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
  		<a href="javascript:deleteSaleChance()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
  	</div>
  
  	<div>
  		&nbsp;客户名称：&nbsp;<input type="text" id="s_customerName" size="20" onkeydown="if(event.keyCode==13) searchSaleChance()" />
  		&nbsp;概要：&nbsp;<input type="text" id="s_overView" size="20" onkeydown="if(event.keyCode==13) searchSaleChance()" />
  		&nbsp;客户状态：&nbsp;
  		<select class="easyui-combobox" id="s_devResult" editable="false" panelHeight="auto" >
  			<option  value="">请选择</option>
  			<option  value="0">未开发</option>
  			<option  value="1">开发中</option>
  			<option  value="2">开发成功</option>
  			<option  value="3">开发失败</option>
  		</select>
  		<a href="javascript:searchSaleChance()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
  	</div>
  </div>
<div id="dlg-buttons">
	<a href="javascript:saveSaleChance()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeSaleChanceDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<script type="text/javascript">

	function searchSaleChance(){
		$('#dg').datagrid('load', {
			'customerName':$('#s_customerName').val(),
			'overView':$('#s_overView').val(),
			'devResult':$('#s_devResult').combobox('getValue')
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
	
	function formatDevResult(val, row){
		switch (val){
		 case 0:
			 return '未开发';
		 case 1:
			 return '开发中';
		 case 2:
			 return '开发成功';
		 case 3:
			 return '开发失败';
	 }
	}
	
	function formatAction(val, row){
		if(row.devResult == 0 || row.devResult == 1){
			return "<a href='javascript:openCusDevPlanTab("+row.id+")'>开发</a>";
		} else {
			return "<a href='javascript:openCusDevPlanTab2("+row.id+")'>查看详细信息</a>";
		}
		
	}
	
	function openCusDevPlanTab(id){
		window.parent.openTab('客户开发计划项管理', 'cusdevplanitemManage.jsp?saleChanceId='+id, 'icon-khkfjh')
	}
	
	function openCusDevPlanTab2(id){
		window.parent.openTab('查看客户开发计划项', 'cusdevplanitemManage.jsp?saleChanceId='+id+'&show=true', 'icon-khkfjh')
	}
	
</script>
  
</body>  

</html>  
