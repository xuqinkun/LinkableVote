var myAjax = function(params, succCallback, failCallback, allCallback) {
	
	$.ajax(params).done(function(data, textStatus, jqXHR){
		if (succCallack && typeof succCallback == "function") {
			succCallback(data);
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown){
		alert("fail");
	})
	.always(function(){
		alert("always");
	});
};
