$(document).ready(function()
		{
	$("#submit").click(function()
			{	
		$.validity.start();
		$("#email").require().match('email');
		$(prodID).require();
		var result = $.validity.end();
		if(result.valid)
		{

			$.post("SubSrv",{

				email:$("#email").val(),
				prodID:$("#prodID").val()
			}, function(data)
			{
				$("#submitStatus").text("You have been successfully registered for notifications");
			});
		}
		else
		{
			$("#submitStatus").text("Please try again with correct inputs");
		}
			});
		}
);