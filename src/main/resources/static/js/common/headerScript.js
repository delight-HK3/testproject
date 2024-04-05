// 아이디 중복체크
function idCheck() {
    var data = {
        userId: $("#userId").val()
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
                $('#check').text("중복이있는 아이디 입니다.");
                $('#checkYn').val('N');
            }
        }
    });
}

// 회원가입 기능
function signin() {
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
function clear(){

}