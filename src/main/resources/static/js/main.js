$(document).ready(function(){
	console.log("heheh");
    for (var i = 0; i < 6; i++) {
      $("#select-name").append('<option value=\"john doe\">john doe</option>');
    }

        //$.ajax({
          //     type: "POST",
            //   url: url,
              // data: $("#idForm").serialize(), // serializes the form's elements.
        //       success: function(data)
          //     {
            //       alert(data); // show response from the php script.
              // }
          //   });

        //e.preventDefault(); // avoid to execute the actual submit of the form.

    $('#form-submit').submit(function(event) {

      $('#result-list').html("<div class=\"container\" id=\"res\"><div class=\"wrap\"><div class=\"row\" id=\"user-head\"><h4>Here's your information :</h4></div><div class=\"row\"><p>name: jane doe, gender: F, batch: 2, faculty: fasilkom, religion: hindu, interest: [nari, menyanyi, makan]</p></div><div class=\"row\"><h4>Your top 5 recommendation :</h4></div><div class=\"row\"><ol id=\"list-result\"></ol></div></div><div class=\"row button-back\"><div><button class=\"button-black\">Back</button></div></div></div>");

      for (var i = 0; i < 6; i++) {
        $('#list-result').append("<li>name: jane doe, gender: F, batch: 2, faculty: fasilkom, religion: hindu, interest: [nari, menyanyi, makan]</li>")
      }

      event.preventDefault();
    });
});
