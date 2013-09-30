/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getClientInfo(){
    var clientinfo = "";
    clientinfo+="Browser:" + navigator.appName + ",";
    var b_version=navigator.appVersion;
    clientinfo+=" Version: " + b_version + ",";
    clientinfo+=" Screensize: " + screen.width + "x" + screen.height;
    document.getElementById('loginForm:clientinfo').value = clientinfo;    
//    alert((document.getElementById('loginForm:clientinfo')).value);
    document.getElementById('loginForm:clientinfo').value = screen.width + "x" + screen.height;
};

