<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>证书申请</title>
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
                        证书申请
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form class="form-horizontal" role="form" id="cerCreate" action="${ctx}/cermanager/saveCer" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">业务单元名</label>

                            <div class="col-sm-9">
                                <input type="text" name="busiName" id="form-field-1" placeholder="业务单元名英文缩写" class="col-xs-10 col-sm-5" required />
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
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-4">证书库密码</label>

                            <div class="col-sm-9">
                                <input type="password" name="cerPassword" id="form-field-4" placeholder="访问证书内容时需提供的密码" class="col-xs-10 col-sm-5" required/>
                            </div>
                        </div>


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
<script>
    $.validator.setDefaults({
        submitHandler: function() {
            alert("提交事件!");
        }
    });
    $().ready(function() {
        $("#cerCreate").validate();
    });
</script>

</body>


</html>