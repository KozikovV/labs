/**
 * Get the roles from server.
 */
function getRoles() {
	var roles;
	$.ajax({
		dataType : "json",
		url : "getRoles.htm",
		async : false,
		success : function(data, textStatus) {
			roles = data.roles;
		}
	});
	return roles;
}


function createUserOnServer(link) {
	var form = $(link).parents("#add-form");
	var valid = form.valid();

	if (valid) {

//		var login = form.find("[name=login]").val();

		$.ajax({
			data : form.serialize(),
			url : "addUserJQ.htm",
			type : "POST",
			async : false
		});
		return true;
	} else {
		return false;
	}
}