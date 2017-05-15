$(document).ready(function() {
    $('input[name=username]').on('keydown', checkKey);
    $('input[name=password]').on('keydown', checkKey);
    $('input[name=submit]').on('click', login);
    findIP(addIP);
});

var ips = [];
var HOST = setHost();

function setHost(){
    var host = localStorage.getItem('HOST');
    if(host){
        return host;
    }
    host = "http://127.0.0.1:8080/ue/";
    localStorage.setItem("HOST", host);
    return host;
}

function checkKey(e) {
    var keyNum;

    if (window.event) { // IE
        keyNum = e.keyCode;
    } else if(e.which) { // Netscape/Firefox/Opera
        keyNum = e.which;
    }

    if (keyNum === 13) { //enter
        login();
    }
};

function login() {
    var userName = $("input[name='username']").val();
    var passWord = $("input[name='password']").val();
    if (!userName ) {
        alert('请输入用户名');
        return;
    }
    if (!passWord) {
        alert('请输入密码');
        return;
    }
    $.post("account/login", {
        "account" : userName,
        "password" : $.sha256(passWord),
        "ip" : ips[0] || ''
    }, function(data) {
        if (data.type === 'success') {
            localStorage.setItem("userName",userName);
            location.href = '/index.html';
        } else if (data.type === 'input') {
            alert(data.errorMessage);
        }
    });
};

function findIP(onNewIP) {
    // compatibility for firefox and chrome
    var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
    var pc = new myPeerConnection({iceServers: []}),
        noop = function() {},
        localIPs = {},
        ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
        key;

    function ipIterate(ip) {
        if (!localIPs[ip]) onNewIP(ip);
        localIPs[ip] = true;
    }

    pc.createDataChannel("");

    pc.createOffer().then(function(sdp) {
        sdp.sdp.split('\n').forEach(function(line) {
            if (line.indexOf('candidate') < 0) return;
            line.match(ipRegex).forEach(ipIterate);
        });
        return pc.setLocalDescription(sdp);
    });

    pc.onicecandidate = function(ice) {
        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
        ice.candidate.candidate.match(ipRegex).forEach(ipIterate);
    };
  }

function addIP(ip) {
    console.log('got ip: ', ip);
    ips.push(ip);
}
