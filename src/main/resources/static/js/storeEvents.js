function doCreateStoreEvents(){
    let sendData = {
        name : ($('#storeEventsNameForm').val()),
        info : $('#storeEventsInfoForm').val(),
        startDate : $('#storeEventsStartDateForm').val(),
        endDate : $('#storeEventsEndDateForm').val()
    };

    var storeId = $('#storeId').text();

    $.ajax({
        type: "POST",
        url: "/api/user/stores/" + storeId +
            "/store-events",
        dataType: "JSON",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(sendData),
        success: function() {
            location.href="/my-store/" + storeId + "/store-events";
        },
        error:function(request, status, error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

    $("#createStoreEventsModal").modal('hide');
}

function openUpdateModal(e){
    var clickedRow = $(e).closest("#storeEventsRow");
    var storeIdData = $(clickedRow).children("#tdStoreId").text();
    var storeEventsIdData = $(clickedRow).children("#tdStoreEventsId").text();
    var nameData = $(clickedRow).children("#tdStoreEventsName").text();
    var infoData = $(clickedRow).children("#tdStoreEventsInfo").text();
    var startDateData = $(clickedRow).children("#tdStoreEventsStartDate").text();
    var endDateData = $(clickedRow).children("#tdStoreEventsEndDate").text();

    $("#updateStoreId").text(storeIdData);
    $("#updateStoreEventsId").text(storeEventsIdData);
    $('#updateStoreEventsNameForm').text(nameData);
    $('#updateStoreEventsInfoForm').text(infoData);
    $('#updateStoreEventsStartDateForm').text(startDateData);
    $('#updateStoreEventsEndDateForm').text(endDateData);

    $("#updateStoreEventsModal").modal('show');
}

function doUpdateStoreEvents(){
    var storeId = $('#updateStoreId').text();
    var storeEventsId = $('#updateStoreEventsId').text();

    let sendData = {
        name : $('#updateStoreEventsNameForm').val(),
        info : $('#updateStoreEventsInfoForm').val(),
        startDate : $('#updateStoreEventsStartDateForm').val(),
        endDate : $('#updateStoreEventsEndDateForm').val()
    }

    console.log(sendData);

    $.ajax({
        type: "PUT",
        url: "/api/user/stores/" + storeId +
            "/store-events/" + storeEventsId,
        dataType: "JSON",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(sendData),
        success: function(data) {
            location.href="/my-store/" + storeId + "/store-events";
        },
        error:function(request, status, error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

    $("#updateStoreEventsModal").modal('hide');
}

function openDeleteModal(e){
    var clickedRow = $(e).closest("#storeEventsRow");
    var storeIdData = $(clickedRow).children("#tdStoreId").text();
    var storeEventsIdData = $(clickedRow).children("#tdStoreEventsId").text();

    $("#deleteStoreId").text(storeIdData);
    $("#deleteStoreEventsId").text(storeEventsIdData);

    $("#deleteStoreEventsModal").modal('show');
}

function doDeleteStoreEvents(){
    var storeId = $('#deleteStoreId').text();
    var storeEventsId = $('#deleteStoreEventsId').text();

    $.ajax({
        type: "DELETE",
        url: "/api/user/stores/" + storeId +
            "/store-events/" + storeEventsId,
        contentType: 'application/json; charset=utf-8',
        success: function() {
            location.href="/my-store/" + storeId + "/store-events";
        },
        error:function(request, status, error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

    $("#deleteStoreEventsModal").modal('hide');
}