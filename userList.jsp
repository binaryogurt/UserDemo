<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户增删改查demo</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="/css/bootstrap.min.css" rel="stylesheet">
	    <link href="/css/bootstrap-treeview.css" rel="stylesheet">
	    <link href="/css/bootstrap-table.css" rel="stylesheet">
	    <link href="/css/bootstrap-table.min.css" rel="stylesheet">
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/bootstrap-treeview.js"></script>
		<script src="/js/bootstrap-table.js"></script>
		<script src="/js/bootstrap-table.min.js"></script>
		<script src="/js/bootstrap-table-zh-CN.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#table').bootstrapTable({
					method : 'post',
					url : "../user/userList.do",//请求路径
					striped : true, //是否显示行间隔色
					contentType : "application/x-www-form-urlencoded",
					pageNumber : 1, //初始化加载第一页
					pagination : true,//是否分页
					sidePagination : 'server',//server:服务器端分页|client：前端分页
					pageSize : 5,//单页记录数
					pageList : [ 5, 10, 20, 30 ],//可选择单页记录数
					queryParams : "queryParams",
					columns : [ 
						{
	                        field: 'state',
	                        checkbox: true,
	                        align: 'center',
	                        valign: 'middle',
	                        width: 40
	                    }, {
	                        field: 'id',
	                        visible: false
	                    }, {
							title : '编号',
							formatter : function(value, row, index) {
	                            var pageSize = $('#table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	                            var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
	                            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	                        },
	                        width: 50
						}, {
	                        field: 'loginname',
	                        title: '登录名',
	                        width: 150
	                    }, {
	                        field: 'password',
	                        title: '密码',
	                        width: 150
	                    }, {
	                        field: '',
	                        title: '操作',
	                        width: 50,
	                        formatter : function(value, row) {
	                        	return "<button class='btn btn-success' style='float:left;margin-left:10px;' onClick='delete_user(\"" + row.id + "\")'>删除</button>";
	                        }
	                    }
	                ]
				});
				
				//搜索
				$("#search_btn").click(function () {
					$('#table').bootstrapTable('refresh');
				});
				
				//重置,清空搜索框的值并且刷新表格
				$("#clear_btn").click(function () {
					$("#loginname_condition").val("");
					$('#table').bootstrapTable('refresh');
				});
				
				//保存
				$("#save").click(function () {
					//封装保存的参数对象
					var obj = {};
					obj.id = $("#id").val();
					obj.loginname = $("#loginname").val();
					obj.password = $("#password").val();
					$.ajax({
						type : "post",
						url : "../user/saveUser.do",
						data : obj,
						dataType : "json",
						success : function(data){//msg-->page
							if(data.status == "success"){
								alert("保存成功!");
								$("#modal").modal("toggle");
								$('#table').bootstrapTable('refresh');
							}else{
								alert("保存失败!");
								$("#modal").modal("toggle");
							}
						},
						error : function(error){
							alert("系统错误!");
						}
					});
					//清空输入框的值
					$("#id").val("");
					$("#loginname").val("");
					$("#password").val("");
				});
				
				//编辑
				$("#update_btn").click(function () {
					var rows = $("#table").bootstrapTable('getSelections');
					if (rows.length > 0) {
						if (rows.length == 1) {
							//有选中数据,将选中行的值赋给模态框的输入框进行编辑
							$("#id").val(rows[0].id);
							$("#loginname").val(rows[0].loginname);
							$("#password").val(rows[0].password);
						} else {
							alert("只能选择一条记录进行编辑！");
							return false;
						}
					} else {
						alert("请选中要编辑的数据！");
						return false;
					}
				});
				
				//批量删除
				$("#batch_delete_btn").click(function () {
					var rows = $("#table").bootstrapTable('getSelections');
					var ids = [];
					if (rows != null && rows.length > 0) {
		                for (var i = 0; i < rows.length; i++) {
		                    ids.push(rows[i].id);
		                }
		            }
					if(rows.length > 0){
	                    $.ajax({
	                        type: "post",
	                        data: '',
	                        url: "../user/deleteBatchUser.do?ids=" + ids,
	                        cache: false,
	                        dataType: "json",
	                        success: function (data) {
	                            if(data.status == "success"){
	                                alert("删除成功！");
	                                $('#table').bootstrapTable('refresh');
	                            }else{
	                                alert("操作失败！");
	                            }
	                        },
	                        error: function (res) {
	                            alert("操作失败！");
	                        }
	                    });
		            }else{
		                alert("请选择删除的数据!");
		            }
				});
			});
			
			function queryParams (params) {//上传服务器的参数
				var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
					limit : params.limit, // 每页显示数量
					offset : params.offset, // SQL语句起始索引
					page : (params.offset / params.limit) + 1,   //当前页码
					loginname : $("#loginname_condition").val()
				};
				return temp;
			}
			
			//删除一条用户
			function delete_user (id) {
				$.ajax({
                    type: "post",
                    data: {
                    	id : id
                    },
                    url: "../user/deleteUser.do",
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if(data.status == "success"){
                            alert("删除成功！");
                            $('#table').bootstrapTable('refresh');
                        }else{
                            alert("操作失败！");
                        }
                    },
                    error: function (res) {
                        alert("操作失败！");
                    }
                });
			}
		</script>
	</head>
	<body>
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body form-group" style="margin-bottom:0px;">
				<label class="col-sm-1 control-label" style="text-align: right; margin-top:5px; padding-right:0;">登录名：</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="loginname" id="loginname_condition"/>
				</div>
				<div style="float:left;">
					<button class="btn btn-primary" id="search_btn" style="float:left;">查询</button>
					<button class="btn btn-success" id="clear_btn" style="float:left;margin-left:10px;">重置</button>
					<button class="btn btn-success" id="add_btn" style="float:left;margin-left:10px;" data-toggle="modal" data-target="#modal">新增用户</button>
					<button class="btn btn-success" id="update_btn" style="float:left;margin-left:10px;" data-toggle="modal" data-target="#modal">编辑用户</button>
					<button class="btn btn-success" id="batch_delete_btn" style="float:left;margin-left:10px;">批量删除</button>
				</div>
			</div>	
		</div>			
		<table id="table" class="table table-hover"></table>
		<!-- 用户信息模态框 -->
		<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title">用户信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<input type="hidden" id="id" />
				  		<div class="form-group">
				    		<label for="loginname" class="col-sm-3 control-label">登录名: </label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="loginname" placeholder="请输入登录名">
								</div>
							</div>
				  			<div class="form-group">
								<label for="password" class="col-sm-3 control-label">密码: </label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="password" placeholder="请输入密码">
								</div>
				  			</div>
						</form>
					</div>
		      		<div class="modal-footer">
				        <button type="button" id="cancel" class="btn btn-default" data-dismiss="modal">取消</button>
				        <button type="button" id="save" class="btn btn-primary">保存</button>
		      		</div>
		    	</div>
			</div>
		</div>
	</body>
</html>