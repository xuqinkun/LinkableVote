var myAjax = function(params, succCallback, failCallback, allCallback) {
	
	$.ajax(params).done(function(data, textStatus, jqXHR){
		if (succCallback && typeof succCallback == "function") {
			succCallback(data);
		}
	})
	.fail(function(jqXHR, textStatus, errorThrown){
		console.log("===fail===");
		console.log(textStatus);
		console.log(jqXHR.responseJSON.returnMessage)
	})
	.always(function(){
		
	});
};
