<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + 
					":" + request.getServerPort() + request.getContextPath() + "/admin";
%>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="<%=basePath%>"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>

	<style type="text/css">
	.error-msg {
		color:red;
		vertical-align:middle;
		text-align:left;
	}
	span.error-msg {
		display:block;
		margin:8px auto;
		height:40px;
		left:0;
		width:90%;
	}
	</style>

</head>

<!--TIPS-->
<!--You may remove all ID or Class names which contain "demo-", they are only used for demonstration. -->

<body>
	<%@include file="../common/scripts.jsp" %>
	<div id="container" class="cls-container">
		
		
		<!-- BACKGROUND IMAGE -->
		<!--===================================================-->
		<div id="bg-overlay"></div>
		
		<!-- LOGIN FORM -->
		<!--===================================================-->
		<div class="cls-content" id="login-form">
		    <div class="cls-content-sm panel">
		        <div class="panel-body">
		            <div class="mar-ver pad-btm">
		                <h2 class="h2 mar-no">登录</h2>
		            </div>
		            <form id="form_login">
		                <div class="form-group">
		                    <input type="text" name="username" class="form-control" placeholder="用户名/邮箱" autofocus required="required">
		                </div>
		                <div class="form-group">
		                    <input type="password" name="password" class="form-control" placeholder="密码" required="required">
		                </div>
		                <div class="checkbox pad-btm text-left">
		                    <input id="demo-form-checkbox" class="magic-checkbox" type="checkbox">
		                    <label for="demo-form-checkbox">记住我</label>
		                </div>
		                <button class="btn btn-primary btn-lg btn-block" name="login">登录</button>
		            </form>
		        </div>
		
		        <div class="pad-all">
		            <a href="pages-password-reminder.html" class="btn-link mar-rgt">忘记密码?</a>
		            <a href="#register-form" class="btn-link mar-lft">创建账户</a>
		
		            <div class="media pad-top bord-top">
		                <div class="pull-right">
		                    <a href="#" class="pad-rgt"><i class="demo-psi-facebook icon-lg text-primary"></i></a>
		                    <a href="#" class="pad-rgt"><i class="demo-psi-twitter icon-lg text-info"></i></a>
		                    <a href="#" class="pad-rgt"><i class="demo-psi-google-plus icon-lg text-danger"></i></a>
		                </div>
		                <div class="media-body text-left">
		                    Login with
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<!--===================================================-->
		
		<!-- REGISTRATION FORM -->
		<!--===================================================-->
		<div class="cls-content" id="register-form" style="display:none">
		    <div class="cls-content-lg panel">
		        <div class="panel-body" >
		            <div class="mar-ver pad-btm">
		                <h2 class="h2 mar-no">创建新用户</h2>
		            </div>
		            <sf:form commandName="admin">
		                	<div class="col-lg-2"></div>
		                    <div class="col-lg-8">
		                        <div class="form-group">
		                            <sf:input path="username" id="username" class="form-control" placeholder="用户名" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <sf:input path="realname" id="realname" class="form-control" placeholder="真实姓名" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <sf:password path="password" id="pwd" class="form-control" placeholder="密码" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <sf:password path="password2" id="pwd2" class="form-control" placeholder="确认密码" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <sf:input path="email" id="email"  class="form-control" placeholder="电子邮箱" required="required"/>
		                        </div>
		                        <div class="checkbox pad-btm text-left">
				                    <input id="demo-form-checkbox" class="magic-checkbox" type="checkbox">
				                    <label for="demo-form-checkbox">我同意 <a href="#" class="btn-link">安全协议</a></label>
			               		</div>
		                		<button class="btn btn-primary btn-block" name="register">注册</button>
			                </div> <!-- col-lg-8 -->
			                <div class="col-lg-2">
			                    <div style="width:280px">
			                    	<sf:errors cssClass="error-msg" path="username" name="username"/>
			                    	<sf:errors cssClass="error-msg" path="realname" name="realname"/>
			                    	<sf:errors cssClass="error-msg" path="password" name="pwd"/>
			                    	<sf:errors cssClass="error-msg" path="password2" name="pwd2"/>
			                    	<sf:errors cssClass="error-msg" path="email" name="email"/>
			                    </div>
		                    </div>
		                
		            </sf:form>
		        </div>
		        <div class="pad-all">已有账户? <a href="#login-form" class="btn-link mar-rgt">登录</a>
		
		            <div class="media pad-top bord-top">
		                <div class="pull-right">
		                    <a href="#" class="pad-rgt"><i class="demo-psi-facebook icon-lg text-primary"></i></a>
		                    <a href="#" class="pad-rgt"><i class="demo-psi-twitter icon-lg text-info"></i></a>
		                    <a href="#" class="pad-rgt"><i class="demo-psi-google-plus icon-lg text-danger"></i></a>
		                </div>
		                <div class="media-body text-left text-muted">
		                    Sign Up with
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<!--===================================================-->
		
		
		<!-- DEMO PURPOSE ONLY -->
		<!--===================================================-->
		<div class="demo-bg">
		    <div id="demo-bg-list">
		        <div class="demo-loading"><i class="psi-repeat-2"></i></div>
		        <img class="demo-chg-bg bg-trans active" src="assets/picture/bg-img-0.jpg" alt="Background Image">
		        <img class="demo-chg-bg" src="assets/picture/bg-img-1.jpg" alt="Background Image">
		        <img class="demo-chg-bg" src="assets/picture/bg-img-2.jpg" alt="Background Image">
		        <img class="demo-chg-bg" src="assets/picture/bg-img-3.jpg" alt="Background Image">
		        <img class="demo-chg-bg" src="assets/picture/bg-img-4.jpg" alt="Background Image">
		        <img class="demo-chg-bg" src="assets/picture/bg-img-5.jpg" alt="Background Image">
		        <img class="demo-chg-bg" src="assets/picture/bg-img-6.jpg" alt="Background Image">
		        <img class="demo-chg-bg" src="assets/picture/bg-img-7.jpg" alt="Background Image">
		    </div>
		</div>
		<!--===================================================-->
		
		
		
	</div>
	<!--===================================================-->
	<!-- END OF CONTAINER -->

	<script type="text/javascript">
		
		$(document).ready(function(){
			$("a[href='#login-form']").click(function(){
				$("#login-form").css("display","block");	
				$("#register-form").css("display","none");	
			});
			$("a[href='#register-form']").click(function(){
				$("#register-form").css("display","block");	
				$("#login-form").css("display","none");		
			});
			var checkUserName = function() {
				var username = $("#username").val();
				var unLen = username.length;
				if (unLen < 2 || unLen > 12) {
					alert("用户名长度必须在2和12之间");
					return false;
				}
			}
			var checkRealName = function() {
				var realname = $("#realname").val();
				var rnLen = realname.length;
				if (rnLen < 2 || rnLen > 12) {
					alert("真实姓名长度必须在2和12之间");
					return false;
				}
			}  
			var checkPwd = function() {
				var pwd = $("#pwd").val();
				var pwdLen = pwd.length;
				if (pwdLen < 6 || pwdLen > 15) {
					alert("密码长度必须在6到15之间");
					return false;
				}
			}
			var checkPwd2 = function() {
				var pwd = $("#pwd").val();
				var pwd2 = $("#pwd2").val();
				if (pwd2 != pwd) {
					alert("两次密码必须相同！");
					return false;
				}
			}
			var checkEmail = function() {
				var email = $("#email").val();
				var emailLen = email.length;
				if (emailLen == 0) {
					alert("邮箱不能为空！");
				}
			}
			
			$("#username").blur(function(){
				//checkUserName();
			});
			var basePath = $("base").attr("href");
			$("button[name='register']").click(function(){
				
				$.ajax({
					url: basePath + "/admin_register/",
					type: "POST",
					data: {
						username:$("#username").val(),
						password:$("#password").val()
					},
					dataType: "json",
					success: function(result) {
						console.log(result);
					}
				});
			});
			
			$("button[name='login']").click(function(){
				$.ajax({
					url: basePath + "/admin_login/",
					type: "POST",
					data: {
						username:$("input[name='username']").val(),
						password:$("input[name='password']").val()
					},
					dataType: "json",
					success: function(result) {
						console.log(result);
					}
				});
			});
		});
		
		
		
	</script>
	
	</body>
</html>
