/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const Toast = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  timer: 3000
});


var user;

//login button click
$("#btnLogin").click(function(){
    if(validate() == 0){
        $.ajax({
			url         : '../LoginServlet?action='+'auth',
			type        : 'POST',
			dataType    : 'json',
			data        : $('form').serialize(),
			success     :function(data){alert(data);
				if(data){
					user = data;
					Toast.fire({
						type: 'success',
						title: 'Login Success!'
					});
					window.location.href = "../shop.html";
				}else{
					Toast.fire({
						type: 'error',
						title: 'Username or Password is incorrect. Please try again.'
					});
					//$("#txtUsername").val("");
					$("#txtPassword").val("");
					$("#txtUsername").focus();
				}
			}
		});
    }
});

//validations
function validate(){
    var err = 0;
    
    if($("#txtUsername").val() == "" || $("#txtUsername").val() == null){
        Toast.fire({
            type: 'warning',
            title: 'Username is required'
        });
        $("#txtUsername").focus();
        err = 1;
    }else if($("#txtPassword").val() == "" || $("#txtPassword").val() == null){
        Toast.fire({
            type: 'warning',
            title: 'Password is required'
        });
        $("#txtPassword").focus();
        err = 1;
    }
    
    return err;
}

//type password and press enter
$('#txtPassword').on('keypress', function (e) {
         if(e.which === 13){
            if(validate() == 0){
            $.ajax({
                            url         : './LoginServlet?action='+'auth',
                            type        : 'POST',
                            dataType    : 'json',
                            data        : $('form').serialize(),
                            success     :function(data){//alert(data);
                                    if(data){
                                            user = data;
                                            Toast.fire({
                                                    type: 'success',
                                                    title: 'Login Success!'
                                            });
                                            window.location.href = "index.jsp";
                                    }else{
                                            Toast.fire({
                                                    type: 'error',
                                                    title: 'Username or Password is incorrect. Please try again.'
                                            });
                                            //$("#txtUsername").val("");
                                            $("#txtPassword").val("");
                                            $("#txtUsername").focus();
                                    }
                            }
                    });
        }
         }
   });

//alert(user[0]["username"]);
//$("#username").html(user[0]["username"]);
