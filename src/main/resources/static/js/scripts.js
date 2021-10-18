// 공통 스크립트

function loginBtnOnClick(){
    location.href="/my-login";
}

function doGoogleLogin(){
    location.href="/oauth2/authorization/google";
}

function doKakaoLogin(){
    location.href="/oauth2/authorization/kakao";
}

// input 숫자타입 세 자리마다 쉼표 찍기
 function inputNumberFormat(obj) {
     obj.value = comma(unComma(obj.value));
 }

 function comma(str) {
     str = String(str);
     return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
 }

 function unComma(str) {
     str = String(str);
     return str.replace(/[^\d]+/g, '');
 }