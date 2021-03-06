<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>证书上传</title>
</head>

<body >
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">

            <div class="page-header">
                <h1>
                    证书管理
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        证书上传
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form class="form-horizontal" role="form" id="uploadCer" action="${ctx}/cermanager/saveUploadCer" method="post"   enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">业务单元名</label>

                            <div class="col-sm-9">
                                <input type="text" name="busiName" id="form-field-1" placeholder="业务单元名英文缩写" class="col-xs-10 col-sm-5" required="required"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-2">模块名</label>

                            <div class="col-sm-9">
                                <input type="text" name="sysName" id="form-field-2" placeholder="模块名英文缩写" class="col-xs-10 col-sm-5" required/>
                            </div>
                        </div>

                        <div class="space-4"></div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-3">服务访问密码</label>

                            <div class="col-sm-9">
                                <input type="password" name="downPassword" id="form-field-3" placeholder="访问证书服务器时需提供的密码" class="col-xs-10 col-sm-5" required/>
                            </div>
                        </div>

                        <div class="space-4"></div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-4">通道名称</label>

                            <div class="col-sm-9">
                                <input type="text" name="channelCode" id="form-field-4" placeholder="证书提供方节点英文缩写" class="col-xs-10 col-sm-5" required/>
                            </div>
                        </div>

                       <%-- <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-4">证书过期时间</label>

                            <div class="input-group" style="width: 400px;left: 12px;">
                                <input class="form-control date-picker" id="id-date-picker-1" type="text"
                                       name="expirationDate" data-date-format="yyyy-mm-dd" required>
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar bigger-110"></i>
                                </span>
                            </div>

                        </div>--%>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-5">电子邮箱</label>

                            <div class="col-sm-9">
                                <input type="text" name="email" id="form-field-5" placeholder="电子邮箱" class="col-xs-10 col-sm-5" required/>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-select-1">证书使用环境</label>
                            <div class="col-sm-9">
                                <select name="cer_env" class="form-control" id="form-field-select-1" style="width: 314px;">
                                    <option value="1">Dev</option>
                                    <option value="2">Beate</option>
                                    <option value="3">Pro</option>
                                </select>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-5">证书类型</label>

                            <div class="radio">
                                <label>
                                    <input name="certype" value="1" type="radio" checked="checked" class="ace" id="filecer" onclick="changefile1();">
                                    <span class="lbl"> 文件证书</span>
                                </label>

                                <label>
                                    <input name="certype" value="2" type="radio" class="ace" id="stringcer" onclick="changefile2();">
                                    <span class="lbl"> 秘钥串证书</span>
                                </label>
                            </div>
                        </div>



                        <div class="space-4"></div>
                        <div class="form-group" id="cerfilediv">
                            <label class="col-sm-3 control-label no-padding-right" for="cerfile">证书文件</label>

                            <div class="col-sm-9">
                                <input type="file" name="cerfile" id="cerfile" placeholder="请上传证书文件" class="col-xs-10 col-sm-5" />
                                <span class="help-inline col-xs-12 col-sm-7">
                                    <span class="middle">文件类证书点此上传</span>
                                </span>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group" id="cerstringdiv">
                            <label class="col-sm-3 control-label no-padding-right" for="cerString">秘钥串</label>

                            <div class="col-sm-9">
                                <textarea class="form-control" name="cerString" id="cerString" id="form-field-8" placeholder="请输入秘钥串"
                                          style="margin: 0px 729.25px 0px 0px; width: 522px; height: 80px;"></textarea>
                            </div>
                        </div>


                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    提交
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    重置
                                </button>
                            </div>
                        </div>

                        <div class="hr hr-24"></div>
                    </form>

                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<script src="${ctx}/assets/js/jquery.2.1.1.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("#cerstringdiv").hide();
        $("#cerfile").attr("required","");
    });
    function changefile1() {
        $("#cerstringdiv").hide();
        $("#cerfilediv").show();

        $("#cerfile").attr("required","");
        $("#cerString").removeAttr("required");
    }
    function changefile2() {
        $("#cerfilediv").hide();
        $("#cerstringdiv").show();

        $("#cerString").attr("required","");
        $("#cerfile").removeAttr("required");
    }


    $.validator.setDefaults({
        submitHandler: function() {
            alert("提交事件!");
        }
    });
    $().ready(function() {
        $("#uploadCer").validate();
    });
</script>
</body>
</html>