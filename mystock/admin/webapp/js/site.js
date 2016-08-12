function apitestSubmit(btn, format) {
	var form = $(btn).closest("form"); 
	var dest = $("fieldset:last > textarea", $(btn).unitBox()); 
	$.ajax({
        type: "POST",
        url: form.attr("action") + "." + format,
        data: form.serialize(),
        dataType: "text",
        success: function(data) {
        	dest.val("");
        	dest.val(data);
        	dest.format({method: format});
        }
    });
}