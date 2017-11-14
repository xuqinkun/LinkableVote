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


var check = function(item) {
	var name = $(item).attr("placeholder");
	var fieldName = $(item).attr("name");
	var min = parseInt($(item).attr("min"));
	var max = parseInt($(item).attr("maxLength"));
	Validator.init(fieldName, name, min, max);
	if (name == "password2") {
		if ($("input[password]").val() == $("input[password2]").val()) {
			if (Validator.validate()) {
				Validator.render("", "bg-image", "error");
			}
		} else {
			Validator.render("两次密码必须一致", "error", "bg-image");
		}
	} 
	else if (fieldName == "email") {
		Validator.init("email", "邮箱", 6, 15);
		var email = $("#email").val();
		if (email == null || email == "") {
			Validator.render("邮箱不能为空", "error", "bg-image");
		}
		
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!myreg.test(email)) {
			Validator.render("邮箱格式不正确:[用户名]+@+[域名]，如xxx@163.com", "error", "bg-image");
		} else {
			Validator.render("", "bg-image", "error");
		}
	} 
	else {
		Validator.validate();
	}
}
function collectFormData(fields) {
	var data = {};
	for (var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		if ($item != undefined) {
			data[$item.attr('name')] = $item.val();
		}
	}
	return data;
}

$(document).ready(function(){
	$("a[href='#login-form']").click(function(e){
		$("#login-form").css("display","block");	
		$("#register-form").css("display","none");
		e.preventDefault();
	});
	$("a[href='#register-form']").click(function(e){
		$("#register-form").css("display","block");	
		$("#login-form").css("display","none");
		e.preventDefault();
	});
	
	var basePath = $("base").attr("href");
	var registerUrl = basePath + "/" +'${registerUrl}';
		
	var $form = $('#register-form');
	var $inputs = $form.find('input');
	
//	for (var i = 0; i < $inputs.length; i++) {
//		var $item = $($inputs[i]);
//		if ($item.attr("type") == undefined) {
//			$item.on("blur", check($item));
//		}
//		
//	}
		
	$form.bind('submit', function(e) {
		// Ajax validation
		var data = collectFormData($inputs);
		
		var params = {
			url : registerUrl,
			type: "post",
			dataType: "json",
			data: data
		}; 
		var succCallback = function(data) {
			if (data.status == "SUCCESS") {
				console.log("register success!");
			}
			if (data.status == "ERROR") {
				$.each(data.errorMessageList, function(index, value) {
					var $span = $("#" + value.fieldName + "control");
					$span.html(message);
				});
			}
		};
		e.preventDefault();
		return false;
	});
	
	
});