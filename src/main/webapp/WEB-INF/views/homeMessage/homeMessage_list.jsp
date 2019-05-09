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
<div class="larry-grid layui-anim layui-anim-upbit larryTheme-A">
    <div class="larry-personal">
        <div class="layui-tab">
            <blockquote class="layui-elem-quote mylog-info-tit">
                <div class="layui-inline">
                    <form class="layui-form" id="roleSearchForm">
                        <div class="layui-input-inline" style="width:110px;">
                            <select name="searchTerm" >
                                <option value="homeMessage">户主姓名</option>
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
        var $ =  layui.$,
                form = layui.form,
                table = layui.table,
                layer = layui.layer,
                common = layui.common;

        var loading = layer.load(0,{ shade: [0.3,'#000']});

        /**角色表格加载*/
        table.render({
            elem: '#roleTableList',
            url: '${ctx}/homeMessage/ajax_homeMessage_list.do',
            id:'roleTableId',
            method: 'post',
            height:'full-140',
            skin:'row',
            even:'true',
            size: 'sm',
            cols: [[
                {type:"numbers"},
                {field:'homeMessage', title: '户主姓名',align:'center' },
                {field:'cardNum', title: '身份证号',align:'center'},
                {field:'checkPeople', title: '核对成员',align:'center'},
                {field:'approvalDate', title: '审批日期',align:'center'},
                {field:'chcekDate', title: '核对日期',align:'center'},
                {field:'businessMessgae', title: '工商登记',align:'center',width: '12%'},
                {field:'taxation', title: '税务登记',align:'center'},
                {field:'vehicle', title: '机动车登记',align:'center',width: '12%'},
                {title: '操作', align:'center',width: '17%', toolbar: '#roleBar'}

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

                        homeMessage:data.field.searchContent
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
        /**角色新增*/
        $(".roleAdd_btn").click(function(){
            var url = "${ctx}/role/role_add.do";
            common.cmsLayOpen('新增角色',url,'550px','340px');
        });


        /**导出角色信息*/
        $(".excelRoleExport_btn").click(function(){
            var url = '${ctx}/role/excel_role_export.do';
            $("#roleSearchForm").attr("action",url);
            $("#roleSearchForm").submit();
        });


        /**批量失效*/
        $(".roleBatchFail_btn").click(function(){

            //表格行操作
            var checkStatus = table.checkStatus('roleTableId');

            if(checkStatus.data.length == 0){
                common.cmsLayErrorMsg("请选择要失效的角色信息");
            }else{
                var roleStatus = false;
                var roleIds = [];
                $(checkStatus.data).each(function(index,item){
                    roleIds.push(item.roleId);
                    //角色已失效
                    if(item.roleStatus == 0){
                        roleStatus = true;
                    }else{
                        roleStatus = false;
                        return false;
                    }
                });

                if(roleStatus==false){
                    common.cmsLayErrorMsg("当前选择的角色已失效");
                    return false;
                }
                var url = "${ctx}/role/ajax_role_batch_fail.do";
                var param = {roleIds:roleIds};
                common.ajaxCmsConfirm('系统提示', '失效角色、解除角色、用户、菜单绑定关系?',url,param);

            }
        });

        /**监听工具条*/
        table.on('tool(roleTableId)', function(obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值

            //查看图片录音
            if(layEvent === 'mzfile_more') {
                var homeMessageId = data.id;
                var url = "${ctx}/homeMessage/mzfile_more.do?homeMessageId="+homeMessageId;
                common.cmsLayOpen('查看图片录音',url,'1000px','800px');

            //角色授权
            }
            <%--}else if(layEvent === 'role_grant'){--%>

                <%--var roleId = data.roleId;--%>
                <%--var roleStatus = data.roleStatus;--%>
                <%--if(roleStatus == 1){--%>
                    <%--common.cmsLayErrorMsg("当前角色已失效,不能授权");--%>
                    <%--return false;--%>
                <%--}--%>
                <%--var url =  "${ctx}/role/role_grant.do?roleId="+roleId;--%>
                <%--common.cmsLayOpen('角色授权',url,'255px','520px');--%>


            //角色失效
            <%--}else if(layEvent === 'role_fail') {--%>
                <%--var roleId = data.roleId;--%>
                <%--var roleStatus = data.roleStatus;--%>
                <%--if(roleStatus == 1){--%>
                    <%--common.cmsLayErrorMsg("当前角色已失效");--%>
                    <%--return false;--%>
                <%--}--%>

                <%--var url = "${ctx}/role/ajax_role_fail.do";--%>
                <%--var param = {roleId:roleId};--%>
                <%--common.ajaxCmsConfirm('系统提示', '失效角色、解除角色、用户、菜单绑定关系?',url,param);--%>

            <%--}--%>
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
            <a class="layui-btn layui-btn-xs role_edit" lay-event="mzfile_more"><i class="layui-icon larry-icon larry-bianji2"></i>查看图片录音</a>
        </shiro:hasPermission>
    </div>
</script>


</body>
</html>