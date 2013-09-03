$().ready(function() {

	$('#listUsersPane').find('tbody').find('tr').mouseenter(function(event) {
		var tr = event.currentTarget;
		$(tr).addClass('mouse-enter');
		
		var id = $(tr).find('#id').text();
		var login = $(tr).find('#login').text();
		var email = $(tr).find('#email').text();
		var firstName = $(tr).find('#firstName').text();
		var lastName = $(tr).find('#lastName').text();
		var birthDate = $(tr).find('#birthDate').text();
		var age = $(tr).find('#age').text();
		var role = $(tr).find('#role').text();
		
		var userData = $('#userData');
		userData.find('#id').text(id);
		userData.find('#login').text(login);
		userData.find('#email').text(email);
		userData.find('#firstName').text(firstName);
		userData.find('#lastName').text(lastName);
		userData.find('#birthDate').text(birthDate);
		userData.find('#age').text(age);
		userData.find('#role').text(role);
		
	});

	$('#listUsersPane').find('tbody').find('tr').mouseout(function(event) {
		var tr = event.currentTarget;
		$(tr).removeClass('mouse-enter');
		
	});
	
	$('#listUsersPane').find('tbody').mouseout(function(event) {
		var userData = $('#userData');
		userData.find('#id').text('');
		userData.find('#login').text('');
		userData.find('#email').text('');
		userData.find('#firstName').text('');
		userData.find('#lastName').text('');
		userData.find('#birthDate').text('');
		userData.find('#age').text('');
		userData.find('#role').text('');
		
	});

});