$(document).ready(function() {




    $("#password").focusout(function(){
        var pwd=$("#password").val();
        var len=pwd.length;
        if(len<15 && len>6){
            $("#passwordMsg").text(" ");
        }
        else
        {
            $("#passwordMsg").text("Your password must between 6 and 15 characters");
        }
    });


    $("#email").focusout(function () {
        var email = $("#email").val();
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (email.match(mailformat)) {
            $("#error1").text(" ");

            return true;
        } else {
            $("#error1").text("Enter the correct email format");
            return false;
        }

    });

    $("#exampleFormControlFile1").focusin(function () {
        var pwd = $("#password").val();
        var cpwd = $("#confirmPassword").val();
        if (pwd ===cpwd) {
            $("#error").text(" ");
            return true;
        }
        else if(pwd!==cpwd) {
            $("#error").text("Confirm passwords do not match");
            return false;
        }

    });


});

/*
   $(document).on('click', '#registerButton1', function (event) {
        var uname = $("#userName").val();
        var email=$("#email").val();

        alert("ssd");


        $.ajax({
            url: "/checkUsernameAvailability",
            data: {uname: uname,email:email},
            method: "GET"
        }).done(function (data) {
            if (data) {
                document.getElementById('userName').value = null;
                document.getElementById('email').value = null;

                $('#unameMsg').text("Username already exists");
                console.log(data);
            }

        });
*/

$("#registerButton1").click(function () {

    var uname = $("#userName").val();
    var email=$("#email").val();

    $.ajax({
        url: "/checkUsernameAvailability",
        data: {uname: uname,email:email},
        method: "GET"
    }).done(function (data) {
        if (data=="true") {
            $('#unameMsg').text("Email or Username already exists");
            console.log(data);
        }

    });

});




