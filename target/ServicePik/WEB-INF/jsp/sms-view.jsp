<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">   
<style>
.Maincontainer {
width: 100%;
background: #eee;
overflow: hidden;
padding: 50px;
}
.formContainer {

background: #FFF;
margin: 0 auto;
overflow: hidden;
padding: 20px;
border-radius: 4px;
}
.formfields {
float: left;
width: 50%;
}
.formfields select, .formfields textarea{ width:100%;float: left; border:1px solid #aaa; background: none;}
.formfields select {
width: 50%;
float: left;
border: 1px solid #b4d5e0;
padding: 6px 10px;
border-radius: 3px;
}
.formfields textarea{  border: 1px solid #b4d5e0;
padding: 6px 10px; height: 80px;
border-radius: 3px;}
#recordTable {

border: 1px solid #aaa;
margin: 0 auto;
}
#recordTable th {

}
#recordTable td{}
.formfields label {
display: block;
margin: 10px 0;
}
.recordsTable{background: #FFF;  padding: 20px;}
    
#alertMessage, #messagePrint {
    margin-bottom: 12px;
}    
#messagePrint .alert-success {
    position: relative;
    background-position: 18px;
}
    #messagePrint .alert-success {
    color: #399c3a !important;
    background-color: #f7fdf5 !important;
    border-color: #7fc345 !important;
    border: 1px dashed !important;
    text-shadow: 1px 0 0 #fff !important;
    font-size: 14px;
}
#messagePrint .alert-success:after {
    content: 'SUCCESS !';
    position: absolute;
    left: 60px;
    top: 5px;
    color: #139e13;
    font-weight: bold;
    font-size: 14px;
}
#messagePrint .alert {
    padding: 24px 60px 8px;
    text-align: left;
    margin-bottom: 10px;
}    
    
#messagePrint .alert-danger {
    color: #d82320 !important;
    background-color: #fffafa !important;
    border-color: #fda4a4 !important;
    border: dashed 1px !important;
    text-shadow: 1px 0 0 #fff !important;
    font-size: 14px;
    word-wrap: break-word;
}
    #messagePrint .alert-danger {
    position: relative;
    text-align: left;
}
#messagePrint .alert-danger:after {
    content: 'ERROR !';
    position: absolute;
    left: 60px;
    top: 5px;
    color: #e25050;
    font-weight: bold;
    font-size: 14px;
}
    
</style>


</head>
<body>
<div class="Maincontainer">
<div class="container">
    <div id="messagePrint" style="display:none;"><div class="alert"></div></div>
<div class="formContainer">

<div class="formfields">
<label>Select Template</label>
<select class="selectfields form-control" onchange="changeTemplateName(this);">
<option value="0" selected>Select Template</option>
</select> 
</div>
<div class="formfields">
<textarea class="tempMessage form-control" placeholder="Template Message"></textarea>
</div>        

</div>

<div class="recordsTable">
<table id="recordTable" class="table table-bordered">
<thead>
<tr>
<th>Name</th>
<th>Mobile No</th>
</tr>            
</thead>
<tbody id="recordTableTbody">

</tbody>


</table>

<button class="btn btn-default" onclick="sendFinalJSON();"> Submit</button>

</div>

</div>


</div>


<div id="getJSON" style="display: none;">${customerData}</div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script>

$(document).ready(function() {
$('#recordTable').DataTable();
getTemplatedata(); 
getRecordTableList();
});    

var getjsondata = $('#getJSON').html(); 

var data = JSON.parse(getjsondata);

console.log(data);

function getTemplatedata(){

for(var x =0; x<data.TemplateList.length;x++){

$('.selectfields').append('<option value="'+data.TemplateList[x].name+'">'+data.TemplateList[x].name+'</option>');
}

}
/* Change template name */ 
function changeTemplateName(e){
var templateval = $(e).val(); 

if(templateval=='0'){

$('.tempMessage').val('');
}
else{
var templateMessage =  data[templateval].templateMessage;
$('.tempMessage').val(templateMessage);
}
}

/* Records Table */
function getRecordTableList(){

    var tbody = document.getElementById("recordTableTbody");
    for(var i=0; i<data.Sms.length;i++){

    var recordsTable = tbody.insertRow(0);
    $(recordsTable).attr({
    'class': "recordsTR"  
    }).appendTo(tbody);

    var name = recordsTable.insertCell(0);
    var mobileNo = recordsTable.insertCell(1);   

    name.innerHTML = '<label>'+data.Sms[i].name+'</label';
    mobileNo.innerHTML = '<label> '+data.Sms[i].mobile+' </label';

    } 

}



/* Send data with ajax call */
function sendFinalJSON() {
	var TemplateMessage = $('.tempMessage').val();
    var customerDataValue = $('#getJSON').html();

	
jQuery.ajax({
          url:"sendsms",
          type: "POST",
          data: {message: TemplateMessage,customerData : customerDataValue },
         
          success: function(rsp) {
              console.log(rsp);
              
 	   /* Response Message conditions */
        if(rsp == 'success'){
          
          $("#messagePrint .alert").addClass('alert-success');
          $("#messagePrint").show().delay(3000).fadeOut();
          $("#messagePrint .alert.alert-success").text('your message has been sent successfully');
            $(window).scrollTop(0);
      }

      else{
          
           $("#messagePrint .alert").addClass('alert-danger');
           $("#messagePrint .alert.alert-danger").text(rsp);
           $("#messagePrint").show().delay(3000).fadeOut();
           $(window).scrollTop(0);
          
        }
    
          }
});
} 
    
    
    
    
    
    
</script>
</html>
