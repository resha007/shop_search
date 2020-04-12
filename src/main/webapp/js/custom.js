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

var action = '';

 //get provinces
$.ajax({
        url		: './ProvinceServlet',
        type		: 'GET',
        data		: {"type":"cmb"},
    success: function(list) {
        $('#cmbProvince').empty();
        $('#cmbProvince').html('<option value="0" selected="selected" disabled="disabled">Select Province</option>');
        for(var a=0; a<list.length; a++){
                $('#cmbProvince').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
        }
    }
});

 //get category
$.ajax({
        url		: './CategoryServlet',
        type		: 'GET',
        data		: {"type":"cmb"},
    success: function(list) {
        $('#cmbCategory').empty();
        $('#cmbCategory').html('<option value="0" selected="selected" disabled="disabled">Select Category</option>');
        for(var a=0; a<list.length; a++){
                $('#cmbCategory').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
        }
    }
});


//get district according to province
$("#cmbProvince").change(function() {
    province = $("#cmbProvince").val();
    $("#cmbDistrict").prop("disabled",false);
    
    $.ajax({
        url		: './DistrictServlet',
        type		: 'GET',
        data		: {"type":"byProId", "id":province},
        success: function(list) {
            $('#cmbDistrict').empty();
            $('#cmbDistrict').html('<option value="0" selected="selected" disabled="disabled">Select District</option>');
            for(var a=0; a<list.length; a++){
                    $('#cmbDistrict').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
            }
        }
    });
  
});

//get area according to distrcit
$("#cmbDistrict").change(function() {
    province = $("#cmbDistrict").val();
    $("#cmbArea").prop("disabled",false);
    
    $.ajax({
        url		: './AreaServlet',
        type		: 'GET',
        data		: {"type":"byDisId", "id":province},
        success: function(list) {
            $('#cmbArea').empty();
            $('#cmbArea').html('<option value="0" selected="selected" disabled="disabled">Select Area</option>');
            for(var a=0; a<list.length; a++){
                    $('#cmbArea').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
            }
        }
    });
  
});

$("#add").click(function(){
        $("#shopForm").submit();
	action = "add";
	sendData();
});

function sendData(){
        if(validate() != '1'){
                $.ajax({
                    url		: './ShopServlet?action='+action,
                    type	: 'POST',
                    dataType	: 'json',
                    data	: $('form').serialize(),
                    success	:function(data){
                            Toast.fire({
                                type: 'success',
                                title: data.success
                            });
                            //dataLoad();
                            $("#reset").click();
                            reset();
                    }
                });
	}
	
}

function reset(){
    //$("#reset").click();
    $('#cmbCategory').prop('selectedIndex',0);
    $('#cmbProvince').prop('selectedIndex',0);
    $('#cmbDistrict').prop('selectedIndex',0);
    $('#ambArea').prop('selectedIndex',0);
}

$("#reset").click(function(){
    document.getElementById("cmbCategory").selectedIndex = 0;
    $('#cmbCategory').prop('selectedIndex',0);
    $('#cmbProvince').prop('selectedIndex',0);
    $('#cmbDistrict').prop('selectedIndex',0);
    $('#ambArea').prop('selectedIndex',0);
});

//validations
function validate(){
    var err = 0;

    var elem = document.getElementById('shopForm').elements;
    for(var i = 0; i < elem.length; i++){
    	if(elem[i].type != "button" && elem[i].type != "reset" && elem[i].id != "txtUser" && elem[i].id != "txtId" && elem[i].id != "txtOtherName" && elem[i].id != "txtCon2" && elem[i].id != "txtWhatsapp" && elem[i].id != "txtWeb" && elem[i].id != "txtFb" && elem[i].id != "txtEmail" && elem[i].id != "txtFrom" && elem[i].id != "txtTo" && elem[i].id != "fileImg"){
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

//checks the code is already in the database
$("#txtBR").change(function(){
	var code = $("#txtBR").val().toUpperCase();
        
	$.ajax({
            url		: './ShopServlet',
            type	: 'GET',
            data	: {"type":"tbl"},
	    success: function(data) {
	    	for (var i in data) {
                    if(data[i]["brNo"]==code){
                        Toast.fire({
                            type: 'warning',
                            title: 'This BR number is already available'
                        });
                        $("#txtBR").val("");
                        $("#txtBR").focus();
                    }
	    	}
	    }
	});
});