<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>500 Error Page - Ace Admin</title>

    <meta name="description" content="500 Error Page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/font-awesome/4.2.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctx}/assets/fonts/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${ctx}/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${ctx}/assets/js/html5shiv.min.js"></script>
    <script src="${ctx}/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="no-skin">

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                </li>

                <li>
                    Other Pages
                </li>
                <li class="active">Error 500</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->

                    <div class="error-container">
                        <div class="well">
                            <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-random"></i>
												500
											</span>
                                Something Went Wrong
                            </h1>

                            <hr />
                            <h3 class="lighter smaller">
                                But we are working
                                <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
                                on it!
                            </h3>

                            <div class="space"></div>

                            <div>
                                <h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

                                <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                    <li>
                                        <i class="ace-icon fa fa-hand-o-right blue"></i>
                                        Read the faq
                                    </li>

                                    <li>
                                        <i class="ace-icon fa fa-hand-o-right blue"></i>
                                        Give us more info on how this specific error occurred!
                                    </li>
                                </ul>
                            </div>

                            <hr />
                            <div class="space"></div>
                            <div class="center">
                                <a href="javascript:history.back()" class="btn btn-grey">
                                    <i class="ace-icon fa fa-arrow-left"></i>
                                    Go Back
                                </a>
                            </div>

                        </div>
                    </div>

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${ctx}/assets/js/jquery.2.1.1.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${ctx}/assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!-- ace scripts -->
<script src="${ctx}/assets/js/ace-elements.min.js"></script>
<script src="${ctx}/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
</body>
</html>
