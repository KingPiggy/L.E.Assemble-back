// 공통 스크립트

setUp();

function setUp(){
}


function loginBtnOnClick(){
    location.href="/my-login";
}

function doCreateStore(){

    let sendData = {
        name : ($('#storeNameForm').val()),
        info : $('#storeInfoForm').val(),
        tel : $('#storeTelForm').val(),
        status : $("input[name=storeStatusRadio]:checked").val()
    };

    console.log(sendData);

    $.ajax({
            type: "POST",
            url: "/api/user/stores",
            dataType: "JSON",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(sendData),
            success: function(data) {
                console.log("저장 성공! uID is " + data);
            },
            error:function(request, status, error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
    });

    $("#createStoreModal").modal('hide');
}

function doGoogleLogin(){
    location.href="/oauth2/authorization/google";
}

function doKakaoLogin(){
    location.href="/oauth2/authorization/kakao";
}