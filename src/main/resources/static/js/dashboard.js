// 게시글 등록
function btnSave(){
    
    var data = {
        boardTitle: $("#boardTitle").val()
        , regDate: $("#regDate").val()
        , catgCd: $("#catgCd").val()
        , boardSubject: $('#boardSubject').val()
    }

    console.log(data);
    /*$.ajax({
        url: "./save"
        , type: 'GET'
        , data: data
        , dataType: 'text'
        , contentType : 'application/charset=utf-8'
        , success: function (data) {
            if(data == "SUCCESS"){
                location.href="/dashboard/List";
            }
        }
    });*/
}
