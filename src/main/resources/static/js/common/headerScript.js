/** 아이디 중복체크 */
function idCheck() {
    var data = {
        userId: $("#userId").val()
    }

    if(data.userId == ""){
        $('.idCheck').css({"color": "red", "font-size": "13px"});
        $('#idCheck').text("아이디를 입력하시기 바랍니다.");
        $('#idCheckYn').val('N');
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
                $('.idCheck').css({"color": "green", "font-size": "13px"});
                $('#idCheck').text("사용가능한 아이디 입니다.");
                $('#idCheckYn').val('Y');
            } else { // 중복있음
                $('.idCheck').css({"color": "red", "font-size": "13px"});
                $('#idCheck').text("사용불가능한 아이디 입니다.");
                $('#idCheckYn').val('N');
            }
        }
    });
}

/** 닉네임 중복체크 */
function nickCheck(){
    var data = {
        userNickName: $("#userNickName").val()
    }

    if(data.userNickName == ""){
        $('.nickCheck').css({"color": "red", "font-size": "13px"});
        $('#nickCheck').text("닉네임을 입력하시기 바랍니다.");
        $('#nickCheckYn').val('N');
        return;
    }

    $.ajax({
        url: "./app/accounts/nickNameCheck"
        , type: 'GET'
        , data: data
        , dataType: 'text'
        , contentType : 'application/charset=utf-8'
        , success: function (data) {
            if(data == "SUCCESS"){ // 중복 없음
                $('.nickCheck').css({"color": "green", "font-size": "13px"});
                $('#nickCheck').text("사용가능한 닉네임 입니다.");
                $('#nickCheckYn').val('Y');
            } else { // 중복있음
                $('.nickCheck').css({"color": "red", "font-size": "13px"});
                $('#nickCheck').text("사용불가능한 닉네임 입니다.");
                $('#nickCheckYn').val('N');
            }
        }
    });
}

/** 이메일 체크 */
function emailCheck(){
    var mail = $("#userEmail").val();

    if (mail == "") {
        alert("메일 주소가 입력되지 않았습니다.");
    } else {
        $.ajax({
            type : 'post',
            url : '/app/accounts/CheckMail',
            data : {
                email : mail
            },
            dataType :'json',
        });
        alert("인증번호가 전송되었습니다.");
        sendAuthNum();
    }

}

var timer;
var isRunning = false;

/** 인증번호 발송하고 타이머 함수 실행 */
function sendAuthNum(){
    // 남은 시간
    var leftSec = 300,
    //var leftSec = 60,
    display = document.querySelector('#timer');
    // 이미 타이머가 작동중이면 중지
    if (isRunning){
        clearInterval(timer);
    } else {
        isRunning = true;
    }

    startTimer(leftSec, display);
}

/** 타이머 함수 */
function startTimer(count, display) {
    var minutes, seconds;
    timer = setInterval(function () {
    minutes = parseInt(count / 60, 10);
    seconds = parseInt(count % 60, 10);

    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    display.textContent = "남은시간 " + minutes + ":" + seconds;

    // 타이머 끝
    if (--count < 0) {
        clearInterval(timer);
        display.textContent = "남은시간 00:00";
        isRunning = false;
        resetCheckNum();
    }
    }, 1000);
}

/** 타이머가 0가 된경우 인증번호 초기화 */
function resetCheckNum(){
    $.ajax({
        type : 'get',
        url : '/app/accounts/resetCheckNum',
        dataType :'json',
    });
}

/** 입력값 체크 */
function inputcheck_1(){
    var userName = $("#userName").val();            // 입력한 이름
    var userId = $("#userId").val();                // 입력한 아이디
    var userPwd = $("#userPwd").val();              // 입력한 암호 
    var userPhone = $("#userPhone").val();          // 입력한 전화번호
    var userNickName = $("#userNickName").val();    // 입력한 유저닉네임

    var nickCheckYn = $("#nickCheckYn").val();      // 닉네임 중복체크 여부
    var idCheckYn = $("#idCheckYn").val();          // 아이디 중복체크 여부

    if(userName == "" || userId == "" || userPwd == "" || userPhone == "" || userNickName == ""){
        alert("필수 입력값을 확인 하시기 바랍니다.");
        return;
    }

    if(nickCheckYn == "N") {
        alert("닉네임을 다시 입력하시기 바랍니다.");
        return; 
    }

    if(idCheckYn == "N") {
        alert("아이디를 다시 입력하시기 바랍니다.");
        return; 
    }

    $('#signUpModal').modal('hide');
    $('#emailModal').modal('show');
}

/** 회원가입 기능 */
function signin() {

    var userEmail = $("#userEmail").val();
    var userChecknum = $("#userName").val();

    if(userEmail == ""){
        alert("메일주소가 입력되지 않았습니다.");
    }
    if(userChecknum == ""){
        alert("인증번호가 입력되지 않았습니다.");
    }

    var data = {
        userChecknum: $("#userChecknum").val()
        , userName: $("#userName").val()
        , userNickName: $("#userNickName").val()
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
            if(data == "SUCCESS"){
                alert("회원가입이 완료 되었습니다.");
                $('#emailModal').modal('hide');
            }
        }
    });
}

/** modal창 닫을시 입력한 값 초기화 */
function clear_1(){
    $('#userName').val("");     // 사용자명 지우기

    $('#userNickName').val(""); // 닉네임 지우기
    $('#nickCheck').text("");   // 닉네임 체크문구 지우기
    $('#nickCheckYn').val("");  // 닉네임 중복여부 지우기

    $('#userId').val("");       // 아이디 지우기
    $('#idCheck').text("");     // 이이디 체크문구 지우기
    $('#idCheckYn').val("");    // 아이디 중복여부 지우기
    
    $('#userPwd').val("");      // 비밀번호 지우기
    $('#userPhone').val("");    // 전화번호 지우기
}

/** modal창 닫을시 입력한 값 초기화 */
function clear_2(){
    this.clear_1();
    
    $('#userEmail').val("");
    $('#userChecknum').val();
    $('#timer').text("");

    resetCheckNum();

    $('#email')[0].reset();
}