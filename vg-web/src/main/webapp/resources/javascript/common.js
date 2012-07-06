// common js file

var clientAppId = "247266912049837";
var appSecret = "5624e24f6f98a2835da033422ba93798";
var state = "fbr123fbr";

function loginToFacebookServer() {
	var fbrform = document.forms['homeForm'];
//	fbrform.action = 'homeBean.loginToFacebook';
	fbrform.action = '#{homeBean.loginToFacebook}';
	fbrform.submit();
    return false;
}



function loginToFacebookClient() {
	var redirect_uri = "http://localhost:8080/vg-web/dashboard.jsf&state=fbr123fbr";
	
	window.location.href = "https://www.facebook.com/dialog/oauth?client_id=" + clientAppId +
		"&redirect_uri=" + redirect_uri ;
    return true;
}

function getAccessToken() {
	var redirect_uri = "http://localhost:8080/vg-web/dashboard.jsf&state=fbr123fbr" ;
	var code = getUrlVars()["code"];
	
	var url = "https://graph.facebook.com/oauth/access_token?" +
	   "client_id="+ clientAppId +
	   "&redirect_uri=" + redirect_uri +
	   "&client_secret="+ appSecret +
	   "&code="+ code;
	   
	   
	window.location.href = url;
    return false;
}

/**
 * Get URL parameters & values with jQuery. Ex: To get the value of code
 * var code = getUrlVars()["code"];
 */
function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)  {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
    
}
