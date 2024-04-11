/** 아이디 중복체크 */
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
    $('#check').text("");
    $('#signUp')[0].reset();
}

/** modal창 닫을시 입력한 값 초기화 */
function clear_2(){
    $('#check').text("");
    $('#timer').text("");
    $('#signUp')[0].reset();
    $('#email')[0].reset();
}