var Validator;
if (Validator == undefined) {
	Validator = {};
}

Validator = {
	field: "", 
	len: 0,
	name: "",
	min: 0,
	max: 1000,
	input: null,
	span: null, 
	init: function(fieldName, name, min, max) {
		this.field = fieldName;
		this.name = name;
		this.min = min;
		this.max = max;
		this.input = $("input[name='" + fieldName + "']");
		this.len = this.input.val().length;
		this.span = $("#" + fieldName + "Control");
	},
	removeClass: function(className) {
		var input = this.input;
		if (input.hasClass(className)) {
			input.removeClass(className);
		}
	},
	render: function(errorMsg, addClass, removeClass) {
		this.span.html(errorMsg);
		this.input.addClass(addClass);
		this.removeClass(removeClass);
	},
	validate: function() {
		var min = this.min;
		var max = this.max;
		if (this.len == 0) {
			this.render(this.name + "不能为空", "error", "bg-image");
		}
		else if (this.len < min || this.len > max) {
			var errorMsg = this.name + "长度必须在" + min + "和" + max + "之间";
			this.render(errorMsg, "error", "bg-image");
		}
		else {
			this.render("", "bg-image", "error");
			return true;
		}
		return false;
	}
};

function collectFormData(fields) {
	var data = {};
	for (var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val();
	}
	return data;
}
$(document).ready(function(){
	$("a[href='#login-form']").click(function(){
		$("#login-form").css("display","block");	
		$("#register-form").css("display","none");	
	});
	$("a[href='#register-form']").click(function(){
		$("#register-form").css("display","block");	
		$("#login-form").css("display","none");		
	});
	
	$("#username").blur(function(){
		Validator.init("username", "用户名", 2, 12);
		Validator.validate();
	});
	
	$("#realname").blur(function(){
		Validator.init("realname", "真实姓名", 2, 10);
		Validator.validate();
	});
	
	$("#pwd").blur(function(){
		Validator.init("password", "密码", 6, 15);
		Validator.validate();
	});
	
	$("#pwd2").blur(function(){
		Validator.init("password2", "密码", 6, 15);
		if ($("#pwd2").val() == $("#pwd").val()) {
			if (Validator.validate()) {
				Validator.render("", "bg-image", "error");
			}
		} else {
			Validator.render("两次密码必须一致", "error", "bg-image");
		}
	});
	
	$("#email").blur(function(){
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		Validator.init("email", "邮箱", 6, 15);
		if(!myreg.test($("#email").val())) {
			Validator.render("邮箱格式不正确:[用户名]+@+[域名]，如xxx@163.com", "error", "bg-image");
		} else {
			Validator.render("", "bg-image", "error");
		}
	});
	
	var basePath = $("base").attr("href");
	var registerUrl = basePath + "/" +'${registerUrl}';
		
	var $form = $('#register-form');
	$form.bind('submit', function(e) {
		// Ajax validation
		var $inputs = $form.find('input');
		var data = collectFormData($inputs);
		
		$.post(registerUrl, data, function(response) {
			$form.find('.control-group').removeClass('error');
			$form.find('.help-inline').empty();
			$form.find('.alert').remove();
			
			if (response.status == 'ERROR') {
				for (var i = 0; i < response.errorMessageList.length; i++) {
					var item = response.errorMessageList[i];
					//var $controlGroup = $('#' + item.fieldName);
					//$controlGroup.addClass('error');
					console.log(item.message);
				}
			} else {
				$form.unbind('submit');
				$form.submit();
			}
		}, 'json');
		
		e.preventDefault();
		return false;
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