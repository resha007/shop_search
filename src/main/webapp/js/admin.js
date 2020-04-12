/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("#update").prop("disabled",true);
$("#delete").prop("disabled",true);
//alert("ddd");
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
	action = "add";
	sendData();
});

$("#update").click(function(){
	action = "update";
	data = $('form').serialize();

	Swal.fire({
		width:'400px',
		padding:null,
		title: 'Are you sure?',
		position: 'top-end',
		text: "You won't be able to revert this!",
		allowEnterKey: true,
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, Update it!'
	}).then((result) => {
		if (result.value) {
			sendData();
		}
                //$("#reset").click();
	})

	
});

$("#delete").click(function(){
	action = "delete";
	data = $('form').serialize();

	Swal.fire({
		width:'400px',
		padding:null,
		title: 'Are you sure?',
		position: 'top-end',
		text: "You won't be able to revert this!",
		allowEnterKey: true,
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, delete it!'
	}).then((result) => {
		if (result.value) {
			sendData();
		}
                //$("#reset").click();
	})

	
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
                            dataLoad();
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
    	if(elem[i].type != "button" && elem[i].type != "reset" && elem[i].id != "txtId" && elem[i].id != "txtOtherName" && elem[i].id != "txtCon2" && elem[i].id != "txtWhatsapp" && elem[i].id != "txtWeb" && elem[i].id != "txtFb" && elem[i].id != "txtEmail" && elem[i].id != "txtFrom" && elem[i].id != "txtTo"){
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
    $(document).ready(function() {
        if(dataTables1==null){//alert("ggg");
        //$('#bootstrap-data-table1').DataTable();
            dataTables1 = $('#bootstrap-data-table1').DataTable({
                    responsive: true,
                    "columnDefs": [
                {
                    "targets": [ 0 ],
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
                url                 : './ShopServlet',
                type		: 'GET',
                data		: {"type":"tbl"},
                success: function(data) {//alert(data);
                for (var i in data) {
                    if(data[i]["status"]=='1')
                            data[i]["status"]="Verified";
                    else if(data[i]["status"]=='2')
                            data[i]["status"]="Unverified";

                    dataTables1.row.add([ 
                        data[i]["id"],
                        data[i]["name"],
                        data[i]["brNo"],
                        data[i]["category"]["name"],
                        data[i]["contactNo1"],
                        data[i]["address"],
                        data[i]["status"]
                   ]).draw();
                }
            }
         });

    } );
    }
var eleme = ["id","name", "otherName", "brNo","category"];

//onclick on the row of the data table, fill the textboxes to update and delete
$('#bootstrap-data-table1 tbody').on('click','tr',function(){
    $("#add").prop("disabled",true);
    $("#update").prop("disabled",false);
    $("#delete").prop("disabled",false);
    
    var rowData = dataTables1.row(this).data();
    //console.log('rowData: '+rowData);
    //alert(rowData);
    var id = 0;
    var elem = document.getElementById('shopForm').elements;
//    for(var i = 0; i < elem.length; i++)
//    {
//        id = rowData[0];
////    	if(elem[i].type != "button" && elem[i].type != "reset"){
////    		elem[i].value = rowData[i];
////    	}
//        //alert(elem[i].type);
//    }
    id = rowData[0];
    
    //province = $("#cmbPro").val();//alert(province);
    $.ajax({
        url		: './ShopServlet',
        type		: 'GET',
        data		: {"type":"byId", "id":id},
        success: function(list) {//alert(list[0]['area']['district']['id']);
            
            elem[0].value = list[0]['id'];
            elem[1].value = list[0]['name'];
            elem[2].value = list[0]['otherName'];
            elem[3].value = list[0]['brNo'];
            $("#cmbCategory").val(list[0]['category']['id']);
            $("#cmbProvince").val(list[0]['area']['district']['province']['id']);
            
            var province = list[0]['area']['district']['province']['id'];
            //$("#cmbDistrict").prop("disabled",false);

            $.ajax({
                url		: './DistrictServlet',
                type		: 'GET',
                data		: {"type":"byProId", "id":province},
                async           : false,
                success: function(list) {
                    $('#cmbDistrict').empty();
                    $('#cmbDistrict').html('<option value="0" selected="selected" disabled="disabled">Select District</option>');
                    for(var a=0; a<list.length; a++){
                            $('#cmbDistrict').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
                    }
                    
                }
            });
            $("#cmbDistrict").val(list[0]['area']['district']['id']);
            
            //get area according to distrcit
            
                var dis = list[0]['area']['district']['id'];
                $("#cmbArea").prop("disabled",false);

                $.ajax({
                    url		: './AreaServlet',
                    type		: 'GET',
                    data		: {"type":"byDisId", "id":dis},
                    async           : false,
                    success: function(list) {
                        $('#cmbArea').empty();
                        $('#cmbArea').html('<option value="0" selected="selected" disabled="disabled">Select Area</option>');
                        for(var a=0; a<list.length; a++){
                                $('#cmbArea').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
                        }
                    }
                });
            
            $("#cmbArea").val(list[0]['area']['id']);
            elem[8].value = list[0]['address'];
            elem[11].value = list[0]['contactNo1'];
            elem[12].value = list[0]['contactNo2'];
            elem[13].value = list[0]['whatsappViber'];
            elem[14].value = list[0]['email'];
            elem[15].value = list[0]['deliveryTimeMin'];
            elem[16].value = list[0]['deliveryTimeMax'];
            elem[17].value = list[0]['deliveryKm'];
            elem[18].value = list[0]['website'];
            elem[19].value = list[0]['fbLink'];
            //elem[20].value = list[0]['status'];
            
            if(list[0]['status'] == '1'){
                elem[20].value = 1;
            }else if(list[0]['status'] == '2'){
                elem[20].value = 2;
            }
            
            
            $("#txtShopName").focus();
            
//            for(var i = 0; i < elem.length; i++)
//            {
//
//                if(elem[i].type != "button" && elem[i].type != "reset"){
//                        elem[i].value = list[0][eleme[i]];
//                }
//                //alert(elem[i].type);
//            }
        }
    });
} );

//checks the code is already in the database
$("#txtBrNo").change(function(){
	var code = $("#txtCode").val().toUpperCase();
        
	$.ajax({
            url		: './CityServlet',
            type	: 'GET',
            data	: {"type":"tbl"},
	    success: function(data) {
	    	for (var i in data) {
                    if(data[i]["code"]==code){
                        Toast.fire({
                            type: 'warning',
                            title: 'This code is already available'
                        });
                        $("#txtCode").val("");
                        $("#txtCode").focus();
                    }
	    	}
	    }
	});
});