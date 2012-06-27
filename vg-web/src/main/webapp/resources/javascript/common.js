// common js file



function loginToFacebookOld() {
	document.forms['homeForm'].submit();
    return false;
}

function loginToFacebook() {
	window.location.href = 'https://www.facebook.com/dialog/oauth?client_id=247266912049837&redirect_uri=http://localhost:8080/vg-web/hello.jsf&state=fbr123fbr';
    return true;
}