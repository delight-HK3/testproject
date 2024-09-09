// 게시글 등록
function btnSave(editorData){
    
    var data = {
        boardTitle: $("#boardTitle").val()
        , regDate: $("#regDate").val()
        , catgCd: $("#catgCd").val()
        , boardSubject: editorData
    }

    $.ajax({
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
    });
}

// 게시글 수정
function btnEdit(editorData){
    
    var data = {
        seq: $("#seq").val()
        , boardTitle: $("#boardTitle").val()
        , updtDate: $("#updtDate").val()
        , catgCd: $("#catgCd").val()
        , boardSubject: editorData
    }

    $.ajax({
        url: "./updt"
        , type: 'GET'
        , data: data
        , dataType: 'text'
        , contentType : 'application/charset=utf-8'
        , success: function (data) {
            if(data == "SUCCESS"){
                location.href="/dashboard/List";
            }
        }
    });
}

