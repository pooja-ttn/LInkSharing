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



$("#userName").focusout(function () {

    var uname = $("#userName").val();

    $.ajax({
        url: "/checkUsernameAvailability",
        data: {uname: uname},
        method: "GET"
    }).done(function (data) {
        if (data===true) {
            $('#unameMsg').text("Username already exists");
            console.log(data);
        }
        else
        {
            $('#unameMsg').text(" ");
        }

    });

});






$("#").focusout(function () {

    var uname = $("#userName").val();

    $.ajax({
        url: "/checkUsernameAvailability",
        data: {uname: uname},
        method: "GET"
    }).done(function (data) {
        if (data===true) {
            $('#unameMsg').text("Username already exists");
            console.log(data);
        }
        else
        {
            $('#unameMsg').text(" ");
        }

    });

});


/*
$("#Login").onclick(function () {

    var uname = $("#userName").val();
    var pwd=$("#inputPassword").val();

    $.ajax({
        url: "/checkUsernameOrEmailAndPasswordAvailability",
        data: {uname: uname,password:pwd},
        method: "POST"
    }).done(function (data) {
        if (data===true) {
            $('#passwordMsg').text("Incorrect credendentials");
            console.log(data);
        }
        else
        {
            $('#passwordMsg').text(" ");
        }

    });

});*/





$("#email").focusout(function () {

    var email=$("#email").val();

    $.ajax({
        url: "/checkEmailAvailability",
        data: {email:email},
        method: "GET"
    }).done(function (data) {
        if (data==="true") {
            $('#emailMsg').text("Email already exists");
            console.log(data);
        }
        else
        {
            $('#emailMsg').text(" ");
        }

    });

});



$(document).ready(function () {
    $(document).on('click','.edit-topic',function () {
        console.log("Entered into js");
        var topicId=$("#myinput").val();
        console.log(topicId);
        $('.old-topic'+topicId).hide();
        $('.new-topic'+topicId).show();
    });
});

$(document).on('click','.close-topic-edit',function () {
    var topicId = $(this).parent().find(".myinput").val();
    $('.old-topic-name'+topicId).show();
    $('.new-topic-name'+topicId).hide();
});

$(document).on('click','.save-new-topic-name',function () {
    var topicname=$(this).parent().find('.new-topic-name-text').val();
    console.log(topicname);
    var topicId=$(this).parent().find(".myinput").val();
    console.log(topicId);
    var status=$.ajax({
        url:"/changeTopicName",
        data:{'topicname':topicname,'topicId':topicId},
        method:"POST"
    });
    status.done(function (data) {
        console.log("done");
        $(".old-topic"+topicId).text(data);
        $(".old-topic-name"+topicId).show();
        $(".new-topic-name"+topicId).hide();
        $("body").load("/dashboard");

    });

    status.fail(function () {
        console.log("Failure")
    })
});


$(document).on('focusout','.new-topic-name-text',function () {
    var topicname=this.value;
    console.log(topicname);
    var check=$.ajax({
        url:'checkTopicNameUnique',
        data:{'topicname':topicname},
        method:"GET"
    });
    check.done(function (data) {
        if(!data){
            document.getElementById('topic-name').value=null;
            $('.tname-msg').text("topic already exists");
            console.log(data)
        }else {
            $('.tname-msg').text("topic unique...");
        }
    });
    check.fail(function (jqxhr,textStatus) {
        document.getElementById('new-topic-name-text').value="not valid";
        console.log("Error in fetching Usernames");
    })
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

