// 아이디 중복체크
function idCheck() {
    var data = {
        userId: $("#userId").val()
    }

    if(data.userId == ""){
        $('.check').css({"color": "red", "font-size": "13px"});
        $('#check').text("아이디를 입력하시기 바랍니다.");
        $('#checkYn').val('N');
        return;
    }

    $.ajax({
        url: "./app/accounts/idCheck"
        , type: 'GET'
        , data: data
        , dataType: 'text'
        , contentType : 'application/charset=utf-8'
        , success: function (data) {
            if(data == "SUCCESS"){ // 중복 없음
                $('.check').css({"color": "green", "font-size": "13px"});
                $('#check').text("사용가능한 아이디 입니다.");
                $('#checkYn').val('Y');
            } else { // 중복있음
                $('.check').css({"color": "red", "font-size": "13px"});
                $('#check').text("사용불가능한 아이디 입니다.");
                $('#checkYn').val('N');
            }
        }
    });
}

// 이메일 체크
function emailCheck(){
    var mail = $("userEmail").val();
    if (mail == "") {
        alert("메일 주소가 입력되지 않았습니다.");
    } else {
        $.ajax({
            type : 'post',
            url : '/app/accounts/CheckMail',
            data : {
                email:mail
            },
            dataType :'json',

        });
        alert("인증번호가 전송되었습니다.");
    }

}

// 입력값 체크
function inputcheck_1(){
    var userName = $("#userName").val();    // 입력한 이름 초기화
    var userId = $("#userId").val();        // 입력한 아이디 초기화
    var userPwd = $("#userPwd").val();      // 입력한 암호 초기화
    var userPhone = $("#userPhone").val();      // 입력한 전화번호 초기화

    var checkYn = $("#checkYn").val();      // 중복체크 여부

    if(userName == "" || userId == "" || userPwd == "" || userPhone == ""){
        alert("필수 입력값을 확인 하시기 바랍니다.");
        return;
    }

    if(checkYn == "N") {
        alert("아이디를 다시 입력하시기 바랍니다.");
        return; 
    }

    $('#signUpModal').modal('hide');
    $('#emailModal').modal('show');
}

// 회원가입 기능
function signin() {

    var userEmail = $("#userEmail").val();

    if(userEmail == ""){
        alert("메일주소가 입력되지 않았습니다.");
    }

    var data = {
        userName: $("#userName").val()
        , userId: $("#userId").val()
        , userPwd: $("#userPwd").val()
        , userEmail: $("#userEmail").val()
        , userPhone: $("#userPhone").val()
    }

    $.ajax({
        url: "./app/accounts/signin"
        , type: 'GET'
        , data: data
        , dataType: 'text'
        , contentType : 'application/charset=utf-8'
        , success: function (data) {
             
        }
    });
}

// modal창 닫을시 입력한 값 초기화
function clear_1(){
    $('#signUp')[0].reset();
}

// modal창 닫을시 입력한 값 초기화
function clear_2(){
    $('#signUp')[0].reset();
    $('#phone')[0].reset();
}