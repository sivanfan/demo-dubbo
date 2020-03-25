<%@ page pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet"  type="text/css"  href="${ctx}/static/css/style.css"/>
</head>

<title>定时任务监控</title>
<body class="bgray">
<form id="addForm" method="post">
    <table class="list_table">
        <thead>
        <tr>
            <td>id</td>
            <td style="width: 100px;">name</td>
            <td style="width: 100px;">group</td>
            <td style="width: 100px;">状 态</td>
            <td >cron表达式</td>
            <td style="width: 100px;">描 述</td>
            <td style="width: 100px;">执行方式</td>
            <td >类路径</td>
            <td style="width: 100px;">spring id</td>
            <td style="width: 100px;">方法名</td>
            <td>上次执行时间</td>
            <td style="width: 150px;">操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="job" items="${taskList}">
            <tr>
                <td>${job.id }</td>
                <td>${job.jobName }</td>
                <td>${job.jobGroup }</td>
                <td><c:if test="${job.jobStatus==0 }">未生效</c:if>
                    <c:if test="${job.jobStatus==1 }">已生效</c:if>
                    <c:choose>
                        <c:when test="${job.jobStatus=='1' }">
                            <a href="javascript:;"
                               onclick="changeJobStatus('${job.id}','stop')">停止</a>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:;"
                               onclick="changeJobStatus('${job.id}','start')">开启</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${job.cronExpression }</td>
                <td>${job.description }</td>
                <td>
                    <c:if test="${job.isConcurrent ==1 }">并发执行</c:if>
                    <c:if test="${job.isConcurrent ==0 }">同步执行</c:if>
                </td>
                <td>${job.beanClass }</td>
                <td>${job.springId }</td>
                <td>${job.methodName }</td>
                <td>${timemap[job.jobName]}</td>
                <td>
                    <a href="javascript:;" onclick="updateCron('${job.id}')">更新cron</a>
                    <a href="javascript:;" onclick="runAJobNow('${job.id}')">立即执行</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>n</td>
            <td><input type="text" name="jobName" id="jobName"/></td>
            <td><input type="text" name="jobGroup" id="jobGroup"/></td>
            <td>未生效<input type="hidden" name="jobStatus" value="0"/></td>
            <td><input type="text" name="cronExpression" id="cronExpression"/></td>
            <td><input type="text" name="description" id="description"/></td>
            <td>
                <select name="isConcurrent" id="isConcurrent">
                    <option value="1">并发执行</option>
                    <option value="0">同步执行</option>
                </select>
            </td>
            <td><input type="text" name="beanClass" id="beanClass"/></td>
            <td><input type="text" name="springId" id="springId"/></td>
            <td><input type="text" name="methodName" id="methodName"/></td>
            <td/>
            <td><input type="button" onclick="add()" value="保存" /></td>
        </tr>
        </tbody>
    </table>
</form>
<script>
    function validateAdd() {
        if ($.trim($('#jobName').val()) == '') {
            alert('name不能为空！');
            $('#jobName').focus();
            return false;
        }
        if ($.trim($('#jobGroup').val()) == '') {
            alert('group不能为空！');
            $('#jobGroup').focus();
            return false;
        }
        if ($.trim($('#cronExpression').val()) == '') {
            alert('cron表达式不能为空！');
            $('#cronExpression').focus();
            return false;
        }
        if ($.trim($('#beanClass').val()) == '' && $.trim($('#springId').val()) == '') {
            $('#beanClass').focus();
            alert('类路径和spring id至少填写一个');
            return false;
        }
        if ($.trim($('#methodName').val()) == '') {
            $('#methodName').focus();
            alert('方法名不能为空！');
            return false;
        }
        return true;
    }

    function add() {
        if (validateAdd()) {
            showWaitMsg();
            $.ajax({
                type : "POST",
                async : false,
                dataType : "JSON",
                cache : false,
                url : "${ctx}/task/add",
                data : $("#addForm").serialize(),
                success : function(data) {
                    hideWaitMsg();
                    if (data.flag) {
                        location.reload();
                    } else {
                        alert(data.msg);
                    }
                }//end-callback
            });//end-ajax
        }
    }

    function changeJobStatus(jobId, cmd) {
        showWaitMsg();
        $.ajax({
            type : "POST",
            async : false,
            dataType : "JSON",
            cache : false,
            url : "${ctx}/task/changeJobStatus",
            data : {
                jobId : jobId,
                cmd : cmd
            },
            success : function(data) {
                hideWaitMsg();
                if (data.flag) {
                    location.reload();
                } else {
                    alert(data.msg);
                }
            }//end-callback
        });//end-ajax
    }

    function updateCron(jobId) {
        var cron = prompt("输入cron表达式！", "")
        if (cron) {
            showWaitMsg();
            $.ajax({
                type : "POST",
                async : false,
                dataType : "JSON",
                cache : false,
                url : "${ctx}/task/updateCron",
                data : {
                    jobId : jobId,
                    cron : cron
                },
                success : function(data) {
                    hideWaitMsg();
                    if (data.flag) {
                        location.reload();
                    } else {
                        alert(data.msg);
                    }
                }//end-callback
            });//end-ajax
        }
    }
    function runAJobNow(jobId) {
        showWaitMsg();
        $.ajax({
            type : "POST",
            async : false,
            dataType : "JSON",
            cache : false,
            url : "${ctx}/task/runAJobNow",
            data : {
                jobId : jobId,
            },
            success : function(data) {
                hideWaitMsg();
                if (data.flag) {
                    alert("执行成功");
                } else {
                    alert(data.msg);
                }
            }//end-callback
        });//end-ajax
    }
    function showWaitMsg(msg) {
        if (msg) {

        } else {
            msg = '正在处理，请稍候...';
        }
        var panelContainer = $("body");
        $("<div id='msg-background' class='datagrid-mask' style=\"display:block;z-index:10006;\"></div>").appendTo(panelContainer);
        var msgDiv = $("<div id='msg-board' class='datagrid-mask-msg' style=\"display:block;z-index:10007;left:50%\"></div>").html(msg).appendTo(
            panelContainer);
        msgDiv.css("marginLeft", -msgDiv.outerWidth() / 2);
    }

    function hideWaitMsg() {
        $('.datagrid-mask').remove();
        $('.datagrid-mask-msg').remove();
    }
</script>
</body>
</html>