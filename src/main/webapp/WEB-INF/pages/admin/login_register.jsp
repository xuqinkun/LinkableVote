<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

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
	
	body {
		background-image:url('assets/picture/bg-img-0.jpg');
	}
	
	.error {
		color:red;
		vertical-align:middle;
		text-align:left;
	}
	span.error {
		display:block;
		margin:8px auto;
		height:40px;
		left:0;
		width:90%;
	}
	.global-error {
		color:red;
	}
	input.error {
		border-color:red;
	}
	input.bg-image {
		background: url(assets/images/pass.png)  no-repeat right ;
		background-size: contain;
		background-color: white;
	}
	.left {
		position:absolute;
		left:10px;
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
		<!-- <div id="bg-overlay"></div> -->
		
		<s:url value="admin_register" var="registerUrl"/>
		<s:url value="admin_login" var="loginUrl"/>
		
		<!-- LOGIN FORM -->
		<!--===================================================-->
		<div class="cls-content" id="login-form">
		    <div class="cls-content-sm panel">
		        <div class="panel-body">
		            <div class="mar-ver pad-btm">
		                <h2 class="h2 mar-no">登录</h2>
		                <span id="login-error" class="global-error"></span>
		            </div>
		            <form id="login-form">
		                <div class="form-group">
		                    <input type="text" name="username_login" class="form-control" placeholder="用户名/邮箱" autofocus required="required">
		                </div>
		                <div class="form-group">
		                    <input type="password" name="password_login" class="form-control" placeholder="密码" required="required">
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
		                <span id="global-error" class="global-error"></span>
		            </div>
		            <form  id="register-form" >
		                	<div class="col-lg-2"></div>
		                    <div class="col-lg-8">
		                        <div class="form-group" >
		                            <input name="username" onkeyup="check(this)" class="form-control" min="2" maxLength="12" placeholder="用户名"  required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <input name="realname" onkeyup="check(this)" class="form-control" min="2" maxLength="10" placeholder="真实姓名" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <input name="password" type="password" onkeyup="check(this)" class="form-control" min="6" maxLength="15" placeholder="密码" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <input name="password2" type="password" onkeyup="check(this)" class="form-control" min="6" maxLength="15" placeholder="确认密码" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <input name="email"  onkeyup="check(this)" class="form-control" maxLength="20" placeholder="电子邮箱" required="required"/>
		                        </div>
		                        <div class="form-group">
		                            <input name="authCode"  class="form-control" maxLength="10" placeholder="验证码" required="required"/>
		                            
		                        </div>
		                        <div class="checkbox pad-btm text-left">
				                    <input name="agreement" class="magic-checkbox" type="checkbox">
				                    <label for="demo-form-checkbox">我同意 <a href="#" class="btn-link">安全协议</a></label>
			               		</div>
		                		<button class="btn btn-primary btn-block" type="submit">注册</button>
			                </div> <!-- col-lg-8 -->
			                <div class="col-lg-2">
			                    <div style="width:280px">
			                    	<span id="usernameControl" class="error"></span>
			                    	<span id="realnameControl" class="error"></span>
			                    	<span id="passwordControl" class="error"></span>
			                    	<span id="password2Control" class="error"></span>
			                    	<span id="emailControl" class="error"></span>
			                    	<button class="btn btn-primary btn-sm left"  name="auth">获取验证码</button>
			                    </div>
		                    </div>
		                
		            </form>
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
		
	</div>
	<!--===================================================-->
	<!-- END OF CONTAINER -->

	</body>
</html>
