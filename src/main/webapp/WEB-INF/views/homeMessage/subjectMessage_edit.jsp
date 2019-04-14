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


    <script src="${ctx}/static/layui_v2/layui.js"></script>

</head>
<body class="childrenBody" style="font-size: 12px;margin: 10px 10px 0;">
<form class="layui-form layui-form-pane">
    <input id="id" name="id" type="hidden" value="${subjectMessage.id}">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">主体名称</label>
            <div class="layui-input-inline">
                <input type="text" id="subjectName" name="subjectName" class="layui-input" maxlength="20" value="${subjectMessage.subjectName}" lay-verify="required|resName" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">主体法人</label>
            <div class="layui-input-inline">
                <input type="text" id="subjectManager" name="subjectManager" class="layui-input" maxlength="20" value="${subjectMessage.subjectManager}" lay-verify="required|resName" >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">贷款金额</label>
            <div class="layui-input-inline">
                <input type="text" id="loanNum" name="loanNum" class="layui-input" maxlength="20" value="${subjectMessage.loanNum}" lay-verify="required|resName" >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">应帮户数</label>
            <div class="layui-input-inline">
                <input type="text" id="helpNum" name="helpNum" class="layui-input" maxlength="20" value="${subjectMessage.helpNum}" lay-verify="required|resName">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">达标户数</label>
            <div class="layui-input-inline">
                <input type="text" id="standardNum" name="standardNum" class="layui-input" maxlength="20" value="${subjectMessage.standardNum}"  >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">达标率</label>
            <div class="layui-input-inline">
                <input type="text" id="complianceRate" name="complianceRate" class="layui-input" maxlength="20" value="${subjectMessage.complianceRate}"  >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">合格定性</label>
            <div class="layui-input-inline">
                <input type="text" id="qualifyingQualitative" name="qualifyingQualitative" class="layui-input" maxlength="20" value="${subjectMessage.qualifyingQualitative}"  >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">经营规模</label>
            <div class="layui-input-inline">
                <input type="text" id="scaleOperation" name="scaleOperation" class="layui-input" maxlength="20" value="${subjectMessage.scaleOperation}"  >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否正常经营</label>
            <div class="layui-input-inline">
                <input type="text" id="isNormal" name="isNormal" class="layui-input" maxlength="20" value="${subjectMessage.isNormal}"  >
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <button class="layui-btn" lay-submit="" lay-filter="saveRole">保存</button>
        <button type="layui-btn" id="cancle" class="layui-btn layui-btn-primary">取消</button>
    </div>
</form>
<script type="text/javascript">
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form','layer','jquery','common'],function(){
        var $ = layui.$,
                form = layui.form,
                common = layui.common,
                layer = parent.layer === undefined ? layui.layer : parent.layer;

        //保存
        form.on("submit(saveRole)",function(data){
            alert(123321);
            var roleSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            $.ajax({
                url : '${ctx}/homeMessage/updateSubjectMessage.do',
                type : 'post',
                async: false,
                data : data.field,
                success : function(data) {
                    if(data.returnCode == 0000){
                        top.layer.close(roleSaveLoading);
                        common.cmsLaySucMsg("信息保存成功！");
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭                        //刷新父页面
                        parent.location.reload();
                    }else{
                        top.layer.close(roleSaveLoading);
                        common.cmsLayErrorMsg(data.returnMessage);
                    }
                },error:function(data){
                    top.layer.close(index);

                }
            });
            return false;
        });

        //取消
        $("#cancle").click(function(){
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
        });

    });

</script>
</body>
</html>