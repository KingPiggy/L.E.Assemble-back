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