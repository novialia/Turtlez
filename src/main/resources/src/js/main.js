$(document).ready(function(){

   $('#form-submit').on('submit', function(event) {
     event.preventDefault();
     var data = {};
     data['name'] = $('#select-name').find(":selected").text();

     $.ajax({
  type : "POST",
  contentType : "application/json",
  url : "${home}home",
  data : JSON.stringify(data),
  dataType : 'json',
  timeout : 100000,
  success : function(data) {
  console.log("SUCCESS: ", data);
  var avg = data.average_rate;
  var results = data.result;
  var me = data.me;
  var rates = data.rates;

  var str = "<div class=\"container\" id=\"res\"><div class=\"wrap\"><div class=\"row\" id=\"user-head\"><h4>Here's your information :</h4></div>"+
"<div class=\"row\"><p>"+ me + "</p></div><div class=\"row\">";
  str += "<div class=\"row\"><h4>Your top 5 recommendation :</h4></div>";
  str += "<div class=\"row\"><ol id=\"list-result\"></ol></div></div><div class=\"row button-back\">"+
  "<div><button class=\"button-black\">Back</button></div></div></div>";

  $('#result-list').html(str);

  for (var i = 0; i < results.length; i++) {
  var rea = results[i];
  var rate = rates[i];
         $('#list-result').append("<li>name: "+rea.name+", gender: "+rea.gender+", batch: "+rea.batch+
          ", faculty: "+rea.faculty+", religion: "+rea.religion+", interest: "+rea.interests+
          ", compatibility rate: "+rate+"</li>");
     }

  },
  error : function(e) {
  console.log("ERROR: ", e);
  alert("There's something wrong with your input. Please try again.");
  }
  });
   });

});
