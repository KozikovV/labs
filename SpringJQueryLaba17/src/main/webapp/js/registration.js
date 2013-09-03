$().ready(function() {

	$('#registrationModal').find('#ok').click(function(event) {

		var link = event.currentTarget;

		if (createUserOnServer(link)) {
			hideRegistrModal();
		}

		return false;
	});

	$('#registrationModal').find('#cancel').click(function() {
		hideRegistrModal();
		return false;
	});

});

function hideRegistrModal() {
	$('#registrationModal').modal('hide');
}

function registration() {
	prepareAddForm();
}
