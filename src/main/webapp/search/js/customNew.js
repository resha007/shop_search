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

 //get provinces
$.ajax({
        url		: '../ProvinceServlet',
        type		: 'GET',
        data		: {"type":"cmb"},
        success: function(list) {
        $('#cmbProvince').empty();
        $('#cmbProvince').html('<option value="0" selected="selected">All</option>');
        for(var a=0; a<list.length; a++){
                $('#cmbProvince').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
        }
    }
});

 //get category
$.ajax({
        url		: '../CategoryServlet',
        type		: 'GET',
        data		: {"type":"cmb"},
    success: function(list) {
        $('#cmbCategory').empty();
        $('#cmbCategory').html('<option value="0" selected="selected">All</option>');
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
        url		: '../DistrictServlet',
        type		: 'GET',
        data		: {"type":"byProId", "id":province},
        success: function(list) {
            $('#cmbDistrict').empty();
            $('#cmbDistrict').html('<option value="0" selected="selected">All</option>');
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
        url		: '../AreaServlet',
        type		: 'GET',
        data		: {"type":"byDisId", "id":province},
        success: function(list) {
            $('#cmbArea').empty();
            $('#cmbArea').html('<option value="0" selected="selected">All</option>');
            for(var a=0; a<list.length; a++){
                    $('#cmbArea').append($("<option></option>").attr("value",list[a]['id']).text(list[a]['name']));
            }
        }
    });
  
});

$("#btnSearch").click(function(){
        var province = $('#cmbProvince').val();
        var district = $('#cmbDistrict').val();
        var area = $('#cmbArea').val();
        var category = $('#cmbCategory').val();
        
        
    
	$.ajax({
            url                 : '../ShopServlet',
            type		: 'GET',
            data		: {"type":"search", "province":$('#cmbProvince').val(), "district":$('#cmbDistrict').val(), "area":$('#cmbArea').val(), "category":$('#cmbCategory').val()},
            //dataType            : JSON,
            success: function(list) {
                var shopList = '';
                for(var a=0; a<list.length; a++){
                        var status = list[a]['status'];
                        if(status == '1'){
                            status = "VERIFIED";
                        }else if(status == '2'){
                            status = "UNVERIFIED";
                        }
                    
                        shopList += "<div class='col-lg-4 intro_col'> <div class='intro_item'> <div class='intro_item_overlay'></div> <!-- Image by https://unsplash.com/@dnevozhai --> <div class='intro_item_background' style='background-image:url(images/shop.jpg)'></div> <div class='intro_item_content d-flex flex-column align-items-center  justify-content-center'> <div class='intro_date'>"+ list[a]['category']['name'] +"</div> <div class='button intro_button'><div  class='button_bcg'></div><a href='shop_details.jsp?id="+ list[a]['id'] +"'>More Details<span></span><span></span><span></span></a></div> <div class='intro_center text-center'> <h1>"+list[a]['name']+"</h1> <div class='intro_price'>("+list[a]['otherName']+")</div> <div class='intro_price_new'>"+list[a]['contactNo1']+"</div> <div class='rating rating_4' style='color:white'> Delivered Within "+ list[a]['deliveryKm'] +"Km </div><div class='intro_date_new'>"+ status +"</div> </div><div class='intro_date_new1'><i class='fa fa-map-marker'></i> "+ list[a]['area']['name'] +"</div> </div> </div> </div>";
                }
                $('#shopList').html(shopList);
            }
        });
});