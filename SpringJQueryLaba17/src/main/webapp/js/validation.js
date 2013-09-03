$(document).ready( function() {

			/**
			 * Add a Login validation logic.
			 */
			$.validator.addMethod("isLogin", function(value) {
				var isLogin = false;
				$.ajax({
					dataType : "json",
					url : "isLogin.htm",
					async : false,
					data : "login=" + value,
					success : function(data, textStatus) {
						isLogin = data.isLogin;
					}
				});
				if (isLogin) {
					return false;
				} else {
					return true;
				}
			});

			/**
			 * Add a Password validation logic.
			 */
			$.validator.addMethod("checkPassw", function(value) {
				var passw = $("#add-form").find("[name=password]").val();

				if (passw == value) {
					return true;
				} else {
					return false;
				}
			});

			/**
			 * Add an E-mail validation logic. This logic will be correct when
			 * user is added.
			 * 
			 */
			$.validator.addMethod("isEmail", function(value) {
				var isEmail = false;
				$.ajax({
					dataType : "json",
					url : "isEmail.htm",
					async : false,
					data : "email=" + value,
					success : function(data, textStatus) {
						isEmail = data.isEmail;
					}
				});
				if (isEmail) {
					return false;
				} else {
					return true;
				}
			});

			/**
			 * Add an E-mail validation logic. This logic will be correct when
			 * user is edited.
			 * 
			 */
			$.validator.addMethod("isEmailForEdit", function(value) {
				var isEmailForEdit = false;
				var id = $('#edit-pane').find('[name=id]').val();
				$.ajax({
					dataType : "json",
					url : "isEmailForEdit.htm",
					async : false,
					data : "email=" + value + "&id=" + id,
					success : function(data, textStatus) {
						isEmailForEdit = data.isEmailForEdit;
					}
				});
				if (isEmailForEdit) {
					return false;
				} else {
					return true;
				}
			});

			/**
			 * 
			 * Add a bithday validation logic.
			 * 
			 */
			$.validator.addMethod("birthDateValid", function(value) {

				var patt = /[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}/;
				if (patt.test(value)) {
					return true;
				} else {
					return false;
				}
			});

			/**
			 * 
			 * Add a captcha value validation logic.
			 * 
			 */
			$.validator.addMethod("checkCaptcha", function(value) {
				var checkCaptcha = false;
				$.ajax({
					dataType : "json",
					url : "checkCaptcha.htm",
					async : false,
					data : "captcha=" + value,
					success : function(data, textStatus) {
						checkCaptcha = data.checkCaptcha;
					}
				});

				if (checkCaptcha) {
					return true;
				} else {
					return false;
				}
			});

			/**
			 * 
			 * It validates add form
			 * 
			 */
			$("#add-form").validate(
					{

						rules : {
							login : {
								required : true,
								isLogin : true
							},
							password : "required",
							passwordAgain : {
								required : true,
								checkPassw : true
							},
							email : {
								required : true,
								email : true,
								isEmail : true
							},
							firstName : "required",
							lastName : "required",
							birthDate : {
								required : true,
								birthDateValid : true
							},

							captcha : {
								required : true,
								checkCaptcha : true
							}
						},
						messages : {
							login : {
								required : "Required!",
								isLogin : "This login already exist!"
							},
							password : {
								required : "Required!"
							},
							passwordAgain : {
								required : "Required!",
								checkPassw : "Check your passwords!"
							},
							email : {
								required : "Required!",
								email : "Format is wrong!",
								isEmail : "This email already exist!"
							},
							firstName : {
								required : "Required!"
							},
							lastName : {
								required : "Required!"
							},
							birthDate : {
								required : "Required!",
								birthDateValid : "Format is wrong!"
							},
							captcha : {
								required : "Required!",
								checkCaptcha : "Captcha is wrong!"
							}

						},

						errorPlacement : function(error, element) {
							err = error;
							if (element.attr("name") == "login")
								error.insertAfter($('#add-form').find(
										"input[name=login]"));
							if (element.attr("name") == "password")
								error.insertAfter($('#add-form').find(
										"input[name=password]"));
							if (element.attr("name") == "passwordAgain")
								error.insertAfter($('#add-form').find(
										"input[name=passwordAgain]"));
							if (element.attr("name") == "email")
								error.insertAfter($('#add-form').find(
										"input[name=email]"));
							if (element.attr("name") == "firstName")
								error.insertAfter($('#add-form').find(
										"input[name=firstName]"));
							if (element.attr("name") == "lastName")
								error.insertAfter($('#add-form').find(
										"input[name=lastName]"));
							if (element.attr("name") == "birthDate")
								error.insertAfter($('#add-form').find(
										"input[name=birthDate]"));
							if (element.attr("name") == "captcha")
								error.insertAfter($('#add-form').find(
										"input[name=captcha]"));
						}

					});



		
});

/**
 * 
 * This method adds validator for new created form for editing user.
 * 
 */
function addValid(form) {


	$(form).validate(
			{

				rules : {
					password : "required",

					email : {
						required : true,
						email : true,
						isEmailForEdit : true
					},
					firstName : "required",
					lastName : "required",
					birthDate : {
						required : true,
						birthDateValid : true
					},
				},
				messages : {
					password : {
						required : "Required!"
					},

					email : {
						required : "Required!",
						email : "Format is wrong!",
						isEmailForEdit : "This email already exist!"
					},
					firstName : {
						required : "Required!"
					},
					lastName : {
						required : "Required!"
					},
					birthDate : {
						required : "Required!",
						birthDateValid : "Format is wrong!"
					},
				},

				errorPlacement : function(error, element) {
					if (element.attr("name") == "password")
						error.insertAfter($('#edit-form').find(
								"input[name=password]"));
					if (element.attr("name") == "passwordAgain")
						error.insertAfter($('#add-form').find(
								"input[name=passwordAgain]"));
					if (element.attr("name") == "email")
						error.insertAfter($('#edit-form').find(
								"input[name=email]"));
					if (element.attr("name") == "firstName")
						error.insertAfter($('#edit-form').find(
								"input[name=firstName]"));
					if (element.attr("name") == "lastName")
						error.insertAfter($('#edit-form').find(
								"input[name=lastName]"));
					if (element.attr("name") == "birthDate")
						error.insertAfter($('#edit-form').find(
								"input[name=birthDate]"));
					if (element.attr("name") == "captcha")
						error.insertAfter($('#edit-form').find(
								"input[name=captcha]"));
				}
			});
	
	}
			
