//$("#update").prop("disabled",true);
//$("#delete").prop("disabled",true);
//$("#resetPassword").hide();
var action,data = '';

//$("#txtNIC").focus();

const Toast = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  timer: 3000
});

$("#add").click(function(){
	action = "add";
	sendData();
});

//$("#update").click(function(){
//	action = "update";
//	data = $('form').serialize();
//
//	Swal.fire({
//		width:'400px',
//		padding:null,
//		title: 'Are you sure?',
//		position: 'top-end',
//		text: "You won't be able to revert this!",
//		allowEnterKey: true,
//		showCancelButton: true,
//		confirmButtonColor: '#3085d6',
//		cancelButtonColor: '#d33',
//		confirmButtonText: 'Yes, Update it!'
//	}).then((result) => {
//		if (result.value) {
//			sendData();
//		}
//                //$("#reset").click();
//	})
//
//	
//});
//
//$("#delete").click(function(){
//	action = "delete";
//	data = $('form').serialize();
//
//	Swal.fire({
//		width:'400px',
//		padding:null,
//		title: 'Are you sure?',
//		position: 'top-end',
//		text: "You won't be able to revert this!",
//		allowEnterKey: true,
//		showCancelButton: true,
//		confirmButtonColor: '#3085d6',
//		cancelButtonColor: '#d33',
//		confirmButtonText: 'Yes, delete it!'
//	}).then((result) => {
//		if (result.value) {
//			sendData();
//		}
//                //$("#reset").click();
//	})
//
//	
//});

function sendData(){
        if(validate() != '1'){
                $.ajax({
                    url		: './UserDetailsServlet?action='+action,
                    type	: 'POST',
                    dataType	: 'json',
                    data	: $('form').serialize(),
                    success	:function(data){
                            Toast.fire({
                                type: 'success',
                                title: data.success
                            });
                            dataLoad();
                            $("#reset").click();
                    }
                });
	}
	
}

//checks the nic is in the system as a user
$("#txtNIC").keyup(function() {
  
  if($("#txtNIC").val().length == 10 || $("#txtNIC").val().length == 12){
      //alert( "Handler for .change() called."+ $("#txtNIC").val().length);
      $.ajax({
        url		: './UserDetailsServlet',
        type            : 'GET',
        dataType	: 'json',
        data            : {"type":"nicNo", "nic":$("#txtNIC").val()},
        success	:function(data){
                if(data == ""){
                    //$("#status").html("<b>(NEW)</b>");
                }else{
                    Toast.fire({
                        type: 'warning',
                        title: 'This NIC holder is already exist as a user.'
                    });
                    $("#txtNIC").val("");
                    $("#txtNIC").focus();
                }
        }
    });
  }else{
      $("#status").html("");
  }
});



/*function NIC validation*/
$('#txtNIC').focusout(function () {
	if($("#txtNIC").val().length < 10){
		Toast.fire({
                    type: 'warning',
                    title: 'NIC length should be 10 or 12 charactors.'
                });
                $("#txtNIC").focus();
	}
} );

/*checks the username already exist in the system*/
$('#txtUsername').focusout(function () {
        $.ajax({
        url		: './UserDetailsServlet',
        type            : 'GET',
        dataType	: 'json',
        data            : {"type":"username", "username":$("#txtUsername").val()},
        success	:function(data){
                if(data == ""){
                    //$("#status").html("<b>(NEW)</b>");
                }else{
                    Toast.fire({
                        type: 'warning',
                        title: 'This username is already in use. Please enter another username.'
                    });
                    $("#txtUsername").val("");
                    $("#txtUsername").focus();
                }
        }
    });
});

//validations
function validate(){
    var err = 0;

    var elem = document.getElementById('userForm').elements;
    for(var i = 0; i < elem.length; i++){
    	if(elem[i].type != "button" && elem[i].type != "reset" && elem[i].id != "txtId" && elem[i].id != "txtEmail"){
            if(elem[i].value == '' || elem[i].value == '0'){
                Toast.fire({
                    type: 'warning',
                    title: 'This field is required'
                    });
                    $("#"+elem[i].id).focus();
                err = 1;
                return err;
            }
    	}
    }
}

var dataTables1 = null;

//body onload function
function dataLoad(){
	//initialize the data table
	if(dataTables1==null){
		dataTables1 = $('#bootstrap-data-table1').DataTable({
			responsive: true,
			"columnDefs": [
	            {
	                "targets": [ 0,9,10 ],
	                "visible": false,
	                "searchable": false
	            },
                    {
	                "targets": [ 0,1,2],
                    "defaultContent": ""
	            }
	        ]
		});
	}
        dataTables1.clear().draw();
	//load data to the data table using ajax
        $.ajax({
                url		: './UserDetailsServlet',
                type		: 'GET',
                data		: {"type":"tbl"},
                success: function(data) {
                for (var i in data) {
                    if(data[i]["status"]==1)
                            data[i]["status"]="Active";
                    else if(data[i]["status"]==2)
                            data[i]["status"]="Inactive";

                    dataTables1.row.add([ 
                        data[i]["id"],
                        data[i]["firstName"],
                        data[i]["lastName"],
                        data[i]["nic"],
                        data[i]["email"],
                        data[i]["username"],
                        data[i]["designation"]["name"],
                        data[i]["userLevel"]["name"],
                        data[i]["status"],
                        data[i]["designation"]["id"],
                        data[i]["userLevel"]["id"]
                   ]).draw();
                }
            }
         });
         
         //get designation
        $.ajax({
		url		: './DesignationServlet',
		type		: 'GET',
		data		: {"type":"cmb"},
	    success: function(list) {
	    	$('#cmbDesig').empty();
	    	$('#cmbDesig').html('<option value="0" selected="selected" disabled="disabled">-Select-</option>');
	        for(var a=0; a<list.length; a++){
	        	$('#cmbDesig').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
	        }
	    }
	});
        
         //get user levels
        $.ajax({
		url		: './UserLevelServlet',
		type		: 'GET',
		data		: {"type":"cmb"},
	    success: function(list) {
	    	$('#cmbUserLevel').empty();
	    	$('#cmbUserLevel').html('<option value="0" selected="selected" disabled="disabled">-Select-</option>');
	        for(var a=0; a<list.length; a++){
	        	$('#cmbUserLevel').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
	        }
	    }
	});
}

//onclick on the row of the data table, fill the textboxes to update and delete
$('#bootstrap-data-table1 tbody').on('click','tr',function(){
    $("#add").prop("disabled",true);
    $("#update").prop("disabled",false);
    $("#delete").prop("disabled",false);
    
    var rowData = dataTables1.row(this).data();
    //console.log('rowData: '+rowData);
    //alert(rowData);
    var elem = document.getElementById('dataForm').elements;
    elem[0].value = rowData[0];
    elem[1].value = rowData[1];
    elem[2].value = rowData[2];
    elem[3].value = rowData[3];
    elem[4].value = rowData[4];
    elem[5].value = rowData[5];
    elem[6].value = rowData[9];
    elem[7].value = rowData[10];
    elem[8].value = rowData[8];
    
    $("#resetPassword").show();
    $("#txtUsername").prop("readonly",true);
    
} );

//reset button
$("#reset").click(function(){
    $("#add").prop("disabled",false);
    $("#update").prop("disabled",true);
    $("#delete").prop("disabled",true);
    $("#txtUsername").prop("readonly",false);
    $("#resetPassword").hide();
    $("#txtId,#txtFName,#txtLName,#txtEmail,#txtNIC,#txtUsername").val("");
});

//reset password
$("#resetPassword").click(function(){
	$.ajax({
        url		: './UserDetailsServlet?action='+'resetPassword',
        type            : 'POST',
        dataType	: 'json',
        data            : $('form').serialize(),
        success         :function(data){
                        Toast.fire({
                            type: 'success',
                            title: 'Password has been reset.'
                        });
            }
        });
});


