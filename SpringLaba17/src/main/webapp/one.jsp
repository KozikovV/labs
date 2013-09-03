<html>
<head>
<script type="text/javascript">
	
	var EMAIL_WRONG = "E-mail is wrong!";

	function validEmail(my, his) {
		reg = "^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$";
		//alert(my.value);
		//alert(his.value);

		var b = my.value.match(reg);
		if (!b) {
			//document.getElementById("error").innerHTML = EMAIL_WRONG;
		}else{
			window.location.pathname = one;
		}
		
		

		//alert(b.value);

		// 		if(b){
		// 			alert('OK');
		// 		}else{
		// 			alert('ne ok');
		// 		}
	}
	
	function change(){
		document.getElementById("error").innerHTML = EMAIL_WRONG;
		return false;
	}
	
</script>



</head>
<body>

	<form onsubmit="change()">
		<input type="text" name="my"> <input type="text" name="his">
		<p id="error"></p>
		<input type="submit" value="  tinc  "
			onclick="validEmail(this.form.my, this.form.his)">
	</form>
	
	
	<input type="button" value="  zam  " onclick="change()"/>



</body>
</html>