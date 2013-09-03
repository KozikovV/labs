function prepareAddForm() {

	// clean fields
	var addPane = $("#add-pane");

	addPane.find("[name=login]").val("");
	addPane.find("[name=password]").val("");
	addPane.find("[name=passwordAgain]").val("");
	addPane.find("[name=email]").val("");
	addPane.find("[name=firstName]").val("");
	addPane.find("[name=lastName]").val("");
	addPane.find("[name=birthDate]").val("");
	addPane.find("[name=captcha]").val("");

	var select = $(addPane).find("#role-select");
	select.empty();

	$(".error").text("");
	$(".error").css("border", "inherit");

	// load all roles from server and input to the 'select' pane
	var roles = getRoles();
	// select = $(addPane).find("#role-select");
	for ( var i = 0; i < roles.length; i++) {
		select.prepend($("<option>" + roles[i].name + "</option>"));
	}

}

/**
 * It adds new user.
 */
function okAdd(link) {

	var form = $(link).parents("#add-form");
	var valid = form.valid();

	if (valid) {

		var login = form.find("[name=login]").val();

		$.ajax({
			data : form.serialize(),
			url : form.attr("action"),
			type : "POST",
			async : false
		});

		var table = $("#users-table");
		var newTr = $(table.find("tr")[1]).clone();

		getUserByLogin(login);

		newTr.find("#login").html(user.login);
		newTr.find("#firstName").html(user.firstName);
		newTr.find("#lastName").html(user.lastName);
		newTr.find("#age").html(user.age);
		newTr.find("#role").html(user.role.name);

		table.append(newTr);

		$("#add-pane").toggle();
		return false;
	}
}

