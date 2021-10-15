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
                location.href="/my-store";
            },
            error:function(request, status, error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
    });

    $("#createStoreModal").modal('hide');
}