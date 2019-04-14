<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="致力于提供通用版本后台管理解决方案">
    <link rel="shortcut icon" href="${ctx}/static/img/favicon.ico">

    <link rel="stylesheet" href="${ctx}/static/layui_v2/css/layui.css">
    <link rel="stylesheet" href="${ctx}/static/css/global.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/personal.css" media="all">
    <link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_9h680jcse4620529.css">
    <script src="${ctx}/static/layui_v2/layui.js"></script>


<body>
<input id="id" name="id" type="hidden" value="${id}">
<div class="larry-grid layui-anim layui-anim-upbit larryTheme-A">
    <div class="larry-personal">
        <div class="layui-tab">
            <blockquote class="layui-elem-quote mylog-info-tit">
                <div class="layui-inline">
                    <form class="layui-form" id="roleSearchForm">
                        <div class="layui-input-inline" style="width:110px;">
                            <select name="searchTerm" >
                                <option value="homeMessage">角色名称</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="width:145px;">
                            <input type="text" name="searchContent" value="" placeholder="请输入关键字" class="layui-input search_input">
                        </div>
                        <a class="layui-btn roleSearchList_btn" lay-submit lay-filter="roleSearchFilter"><i class="layui-icon larry-icon larry-chaxun7"></i>查询</a>
                    </form>
                </div>

            </blockquote>
            <div class="larry-separate"></div>
            <!-- 角色列表 -->
            <div class="layui-tab-item  layui-show" style="padding: 10px 15px;">
                <table id="roleTableList" lay-filter="roleTableId"></table>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form', 'table', 'layer','common'], function () {
        var ztid =   ${id};
        alert(123);
        var $ =  layui.$,
                form = layui.form,
                table = layui.table,
                layer = layui.layer,
                common = layui.common;

        var loading = layer.load(0,{ shade: [0.3,'#000']});

        /**角色表格加载*/
        table.render({
            elem: '#roleTableList',
            url: '${ctx}/homeMessage/ajax_subjectPeople_list.do?id='+ ztid ,
            id:'roleTableId',
            method: 'post',
            height:'full-140',
            skin:'row',
            even:'true',
            size: 'sm',
            cols: [[
                {type:"numbers"},
                {field:'name', title: '姓名',align:'center' },
                /*{field:'subjectManager', title: '主体法人',align:'center'},
                {field:'loanNum', title: '贷款金额',align:'center'},
                {field:'helpNum', title: '应帮户数',align:'center'},
                {field:'standardNum', title: '达标户数',align:'center'},
                {field:'complianceRate', title: '达标率',align:'center',width: '12%'},
                {field:'qualifyingQualitative', title: '合格定性',align:'center'},
                {field:'scaleOperation', title: '经营规模',align:'center',width: '12%'},
                {title: '操作', align:'center',width: '17%', toolbar: '#roleBar'}*/

            ]],
            page: true,
            done: function (res, curr, count) {
                common.resizeGrid();
                layer.close(loading);

            }
        });

        /**查询*/
        $(".roleSearchList_btn").click(function(){
            var loading = layer.load(0,{ shade: [0.3,'#000']});
            //监听提交
            form.on('submit(roleSearchFilter)', function (data) {
                console.log(data);
                table.reload('roleTableId',{
                    where: {
                        searchTerm:data.field.searchTerm,
                        searchContent:data.field.searchContent
                    },
                    height: 'full-140',
                    page: true,
                    done: function (res, curr, count) {
                        common.resizeGrid();
                        layer.close(loading);

                    }
                });
            });

        });

    });


</script>

<!-- 角色状态tpl-->
<script type="text/html" id="roleStatusTpl">

    {{# if(d.roleStatus == 0){ }}
    <span class="label label-success ">0-有效</span>
    {{# } else if(d.roleStatus == 1){ }}
    <span class="label label-danger ">1-失效</span>
    {{# } else { }}
    {{d.roleStatus}}
    {{# }  }}
</script>


<!--工具条 -->
<script type="text/html" id="roleBar">
    <div class="layui-btn-group">
        <shiro:hasPermission name="moHbdnjz">
            <a class="layui-btn layui-btn-xs role_edit" lay-event="role_edit"><i class="layui-icon larry-icon larry-bianji2"></i> 编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="bSG7LAmU">
            <a class="layui-btn layui-btn-xs layui-btn-warm  role_grant" lay-event="role_grant"><i class="layui-icon larry-icon larry-jiaoseguanli3"></i>主体员工</a>
        </shiro:hasPermission>
    </div>
</script>


</body>
</html>