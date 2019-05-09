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
    <style>
        .layui-upload .mark_button {
            position: absolute;
            right: 15px;
        }

        .upload-img {
            position: relative;
            display: inline-block;
            width: 300px;
            height: 200px;
            margin: 0 10px 10px 0;
            transition: box-shadow .25s;
            border-radius: 4px;
            box-shadow: 0px 10px 10px -5px rgba(0, 0, 0, 0.5);
            transition: 0.25s;
            -webkit-transition: 0.25s;
            margin-top: 15px;
        }

        .layui-upload-img {
            width: 300px;
            height: 200px;
            border-radius: 4px;
        }

        .upload-img:hover {
            cursor: pointer;
            box-shadow: 0 0 4px rgba(0,0,0,1);
            transform: scale(1.2);
            -webkit-transform: scale(1.05);
        }

        .upload-img input {
            position: absolute;
            left: 0px;
            top: 0px;
        }

        .upload-img button {
            position: absolute;
            right: 0px;
            top: 0px;
            border-radius: 6px;
        }
    </style>

<body>
<%--<div class="layui-carousel" id="test2">--%>
    <%--<div carousel-item>--%>
        <%--<c:forEach items="${mzFileList}" var="menus">--%>
            <%--<div><img src="${menus.fileUrl}" height = 100%  width = 100%></div>--%>
        <%--</c:forEach>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<!-- 条目中可以是任意内容，如：<img src=""> -->--%>
<%--<script>--%>
    <%--layui.use('carousel', function(){--%>
        <%--var carousel = layui.carousel;--%>
        <%--//建造实例--%>
        <%--carousel.render({--%>
            <%--elem: '#test2'--%>
            <%--,width: '100%' //设置容器宽度--%>
            <%--,arrow: 'always' //始终显示箭头--%>
            <%--//,anim: 'updown' //切换动画方式--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>


<div class="layui-upload ">

    <input id="homeMessageId" value="${homeMessageId}" style="display: none;"/>

    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
        <div class="layui-upload-list" id="imgs" style="text-align: center;">
            <c:forEach items="${mzFileList}" var="menus">
                <div class="upload-img" filename="${menus.id}">
                    <input type="checkbox" name="" lay-skin="primary" value="${menus.id}">
                    <img src="${menus.fileUrl}" alt="${menus.id}" class="layui-upload-img">
                </div>
            </c:forEach>
        </div>
    </blockquote>
    <div class="mark_button">
        <button type="button" class="layui-btn layui-btn-normal" id="sele_imgs">选择文件</button>
        <button type="button" class="layui-btn" id="upload_imgs" disabled>开始上传</button>

        <button type="button" class="layui-btn layui-btn-danger" id="dele_imgs">删除选中图片</button>
    </div>

</div>

<script id="img_template" type="text/html">
    <div class="upload-img" filename="{{ d.index }}">
        <input type="checkbox" name="" lay-skin="primary">
        <img src="{{  d.result }}" alt="{{ d.name }}" class="layui-upload-img">
    </div>
</script>


<script>

    layui.use(['upload', 'laytpl', 'form'], function () {
        var $ = layui.jquery
                , upload = layui.upload
                , laytpl = layui.laytpl
                , form = layui.form;

        //批量删除 单击事件
        $('#dele_imgs').click(function () {
            $('input:checked').each(function (index, value) {
                var mzfileId=$(this).val();
                $.ajax({
                    type : "post",
                    url : "${ctx}/homeMessage/delete_mzfile.do",
                    dataType : "json",
                    data : {mzfileId:mzfileId},
                    success : function(data) {
                        if(data.returnCode == 0000){
                            top.layer.msg('删除成功', {icon: 1});
                            location.reload();
                        }else{
                            layer.alert(data.returnMessage, {icon: 2});
                        }
                    }
                });
            });
        });


        var imgFiles;

        var homeMessageId = $("#homeMessageId").val();
        //多图片上传
        var uploadInst = upload.render({
            elem: '#sele_imgs'  //开始
            , acceptMime: 'image/*'
            , url: '${ctx}/homeMessage/moreUpload.do'
            , auto: false
            , bindAction: '#upload_imgs'
            , multiple: true
            , size: 0
            , data:{homeMessageId:homeMessageId}
            , choose: function (obj) {  //选择图片后事件
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                imgFiles = files;

                $('#upload_imgs').prop('disabled',false);

                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    var data = {
                        index: index,
                        name: file.name,
                        result: result
                    };

                    //将预览html 追加
                    laytpl(img_template.innerHTML).render(data, function (html) {
                        $('#imgs').append(html);
                    });

                    //绑定单击事件
                    $('#imgs div:last-child>img').click(function () {
                        var isChecked = $(this).siblings("input").prop("checked");
                        $(this).siblings("input").prop("checked", !isChecked);
                        return false;
                    });

                });
            }
            , before: function (obj) { //上传前回函数
                layer.load(); //上传loading
            }
            , done: function (res,index,upload) {    //上传完毕后事件
                layer.closeAll('loading'); //关闭loading
                //上传完毕
                $('#imgs').html("");//清空操作
                top.layer.msg("上传成功！");
                location.reload();
//                return delete imgFiles[index]; //删除文件队列已经上传成功的文件

            }
            , error: function (index, upload) {

                layer.closeAll('loading'); //关闭loading

                top.layer.msg("上传失败！");

            }
        });

    });
</script>
</body>
</html>