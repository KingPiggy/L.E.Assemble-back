function doCreateProduct(){
    let sendData = {
            name : ($('#productNameForm').val()),
            info : $('#productInfoForm').val(),
            price : $('#productPriceForm').val().replace(/\,/g,'')
    };

    var storeId = $('#storeId').text();

    console.log(sendData);

    $.ajax({
                type: "POST",
                url: "/api/user/stores/" + storeId +
                    "/products",
                dataType: "JSON",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(sendData),
                success: function(data) {
                    console.log("저장 성공! uID is " + data);
                    location.href="/my-store/" + storeId + "/products";
                },
                error:function(request, status, error){
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
    });

    $("#createProductModal").modal('hide');
}

function openUpdateModal(e){
    var clickedCard = $(e).closest("#myCard");
    var storeIdData = $(clickedCard).children("div").children("#cardStoreId").text();
    var productIdData = $(clickedCard).children("div").children("#cardProductId").text();
    var nameData = $(clickedCard).children("div").children("h5").text();
    var infoData = $(clickedCard).children("div").children("p").text();
    var priceData = $(clickedCard).children("ul").children("li").children("span").text();

    $("#updateStoreId").text(storeIdData);
    $("#updateProductId").text(productIdData);
    $('#updateProductNameForm').text(nameData);
    $('#updateProductInfoForm').text(infoData);
    $('#updateProductPriceForm').val(priceData);

    $("#updateProductModal").modal('show');
}

function doUpdateProduct(){
    let sendData = {
        name : ($('#updateProductNameForm').val()),
        info : $('#updateProductInfoForm').val(),
        price : $('#updateProductPriceForm').val().replace(/\,/g,'')
    };

    var storeId = $('#updateStoreId').text();
    var productId = $('#updateProductId').text();

    console.log(storeId);
    console.log(productId);
    console.log(sendData);

    $.ajax({
        type: "PUT",
        url: "/api/user/stores/" + storeId +
            "/products/" + productId,
        dataType: "JSON",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(sendData),
        success: function(data) {
            console.log("수정 성공! uID is " + data);
            location.href="/my-store/" + storeId + "/products";
        },
        error:function(request, status, error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

    $("#updateProductModal").modal('hide');
}

function openDeleteModal(e){
    var clickedCard = $(e).closest("#myCard");
    var storeIdData = $(clickedCard).children("div").children("#cardStoreId").text();
    var productIdData = $(clickedCard).children("div").children("#cardProductId").text();

    $("#deleteStoreId").text(storeIdData);
    $("#deleteProductId").text(productIdData);

    $("#deleteProductModal").modal('show');
}

function doDeleteProduct(){
    var storeId = $('#deleteStoreId').text();
    var productId = $('#deleteProductId').text();

    $("#deleteProductModal").modal('hide');

    $.ajax({
        type: "DELETE",
        url: "/api/user/stores/" + storeId +
            "/products/" + productId,
        contentType: 'application/json; charset=utf-8',
        success: function() {
            location.href="/my-store/" + storeId + "/products";
        },
        error:function(request, status, error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}