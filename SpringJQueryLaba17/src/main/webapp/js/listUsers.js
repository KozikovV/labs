/**
 * Current 'tr' item
 */
var tr;

/**
 * Old content of 'tr' item. It keep for cancel operation.
 */
var oldTr;

/**
 * Mark if an any row is edited now.
 */
var editNow = false;

var user;

var editPane;




$().ready(function(){
	
    $('#add-pane').find('#ok').click(function(event){
    	var link = event.currentTarget;
    	if(createUserOnServer(link)){
    		
    	    console.log("1");
    		
    	    var login = $(link).parents("#add-form").find("[name=login]").val();

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
    	    
    	}
    	
    	return false;
    	
    });
    
    $('#add-pane').find('#cancel').click(function(event){
    	var link = event.currentTarget;
    	add();
    });

});
	
	
	
	
	
/**
 * Edit user.
 * 
 * @param item link for editing
 */
function edit(item) {

	if (editNow) {
		cancel();
	}

	editNow = true;

	// find current <tr> item
	tr = $(item).parent().parent();
	oldTr = tr.clone();

	// change its height
	tr.css("height", "50px");

	// find 'edit-pane', vizible and save its body
	editPane = $("#edit-pane").clone();
	var editForm = editPane.find("#edit-form");
	addValid(editForm);
	editPane.css("display", "block");

	insertValuesIntoEditPane();

	// insert <td> item into current tr
	tr.html("<td colspan=\"6\"></tr>");

	// take new <td> item
	var td = $(tr).find("td");

	// insert into new <td> form of edit
	td.html(editPane);

}



function getUserByLogin(login) {
	$.ajax({
		dataType : "json",
		url : "getUser.htm",
		async : false,
		data : "login=" + login,
		success : function(data, textStatus) {
			user = data;
		}
	});
}


/**
 * 
 * Insert values into the edit pane.
 * 
 */
function insertValuesIntoEditPane() {

	// find login of current user. load current user from server. input it to
	// the 'select' editPane
	var login = $(oldTr).find("#login").html();
	getUserByLogin(login);
	
	console.log(user);

	editPane.find("input[name=id]").val(user.id);
	editPane.find("input[name=login]").val(user.login);
	editPane.find("input[name=firstName]").val(user.firstName);
	editPane.find("input[name=lastName]").val(user.lastName);
	editPane.find("input[name=email]").val(user.email);
	editPane.find("input[name=password]").val(user.password);
	editPane.find("input[name=birthDate]").val(user.birthDate);

	// load all roles from server and input to the 'select' pane
	var roles = getRoles();
	var select = $(editPane).find("#role-select");
	for ( var i = 0; i < roles.length; i++) {
		if (roles[i].name == user.role.name) {
			select
					.prepend($("<option selected>" + roles[i].name
							+ "</option>"));
		} else {
			select.prepend($("<option>" + roles[i].name + "</option>"));
		}
	}

}

/**
 * It cancels an edit operation
 */
function cancel() {
	tr.html(oldTr.html());
	tr.css("height", "");
	editNow = false;
}
var it;
/**
 * It removes an item.
 */
function del(item) {
	it = item;
	if (confirm("Delete?")) {
		tr = $(it).parents('tr');
		var login = tr.find('#login').text();
		$.getJSON("removeUserJQ.htm", "login="+login);
		tr.remove();
	}
}

	
	
/**
 * It shows add form.
 */
function add() {
	
    prepareAddForm();

    var addPane = $("#add-pane");
	addPane.toggle();

	
	return false;
}







/**
 * 
 * Invoke when the user submits OK button on an edit form.
 * 
 */
function okEdit(link) {

	var form = $(link).parents("#edit-form");

	var valid = form.valid();

	if(valid){
	 $.ajax({
			data : form.serialize(),
			url : form.attr("action"),
			type : "POST",
			async : false
		});

		var login = oldTr.find("td#login").html();
		getUserByLogin(login);
		oldTr.find("td#login").html(user.login);
		oldTr.find("td#firstName").html(user.firstName);
		oldTr.find("td#lastName").html(user.lastName);
		oldTr.find("td#age").html(user.age);
		oldTr.find("td#role").html(user.role.name);
		tr.html(oldTr.html());
		tr.css("height", "");
		editNow = false;

	return false;
	}
}




//
///**
// * It adds new user.
// */
//function okAdd(link) {
//
//	var form = $(link).parents("#add-form");
//	var valid = form.valid();
//
//	if (valid) {
//
//		var login = form.find("[name=login]").val();
//
//		$.ajax({
//			data : form.serialize(),
//			url : form.attr("action"),
//			type : "POST",
//			async : false
//		});
//
//		var table = $("#users-table");
//		var newTr = $(table.find("tr")[1]).clone();
//
//		getUserByLogin(login);
//
//		newTr.find("#login").html(user.login);
//		newTr.find("#firstName").html(user.firstName);
//		newTr.find("#lastName").html(user.lastName);
//		newTr.find("#age").html(user.age);
//		newTr.find("#role").html(user.role.name);
//
//		table.append(newTr);
//
//		$("#add-pane").toggle();
//		return false;
//	}
//}

