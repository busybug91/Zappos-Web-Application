$(document).ready(function(){
	
	$("#email").blur(function()
			{
		$("#email").require().match('email');
			});
	$(prodID).require();
	
});