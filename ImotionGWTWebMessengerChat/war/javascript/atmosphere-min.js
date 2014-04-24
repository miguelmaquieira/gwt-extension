(function(){var c="2.0.3-javascript",a={},d,f=[],e=[],b=Object.prototype.hasOwnProperty;
a={onError:function(g){},onClose:function(g){},onOpen:function(g){},onReopen:function(g){},onMessage:function(g){},onReconnect:function(h,g){},onMessagePublished:function(g){},onTransportFailure:function(h,g){},onLocalMessage:function(g){},onFailureToReconnect:function(h,g){},onClientTimeout:function(g){},AtmosphereRequest:function(L){var N={timeout:300000,method:"GET",headers:{},contentType:"",callback:null,url:"",data:"",suspend:true,maxRequest:-1,reconnect:true,maxStreamingLength:10000000,lastIndex:0,logLevel:"info",requestCount:0,fallbackMethod:"GET",fallbackTransport:"streaming",transport:"long-polling",webSocketImpl:null,webSocketBinaryType:null,dispatchUrl:null,webSocketPathDelimiter:"@@",enableXDR:false,rewriteURL:false,attachHeadersAsQueryString:true,executeCallbackBeforeReconnect:false,readyState:0,lastTimestamp:0,withCredentials:false,trackMessageLength:false,messageDelimiter:"|",connectTimeout:-1,reconnectInterval:0,dropAtmosphereHeaders:true,uuid:0,async:true,shared:false,readResponsesHeaders:false,maxReconnectOnClose:5,enableProtocol:true,onError:function(ay){},onClose:function(ay){},onOpen:function(ay){},onMessage:function(ay){},onReopen:function(az,ay){},onReconnect:function(az,ay){},onMessagePublished:function(ay){},onTransportFailure:function(az,ay){},onLocalMessage:function(ay){},onFailureToReconnect:function(az,ay){},onClientTimeout:function(ay){}};
var V={status:200,reasonPhrase:"OK",responseBody:"",messages:[],headers:[],state:"messageReceived",transport:"polling",error:null,request:null,partialMessage:"",errorHandled:false,id:0};
var Y=null;
var n=null;
var u=null;
var D=null;
var F=null;
var aj=true;
var k=0;
var av=false;
var Z=null;
var ap;
var p=null;
var I=a.util.now();
var J;
ax(L);
function aq(){aj=true;
av=false;
k=0;
Y=null;
n=null;
u=null;
D=null
}function z(){al();
aq()
}function K(az,ay){if(V.partialMessage===""&&(ay.transport==="streaming")&&(az.responseText.length>ay.maxStreamingLength)){V.messages=[];
ah(true);
C();
al();
Q(az,ay,0)
}}function C(){if(N.enableProtocol&&!N.firstMessage){var az="X-Atmosphere-Transport=close&X-Atmosphere-tracking-id="+N.uuid;
a.util.each(N.headers,function(aA,aC){var aB=a.util.isFunction(aC)?aC.call(this,N,N,V):aC;
if(aB!=null){az+="&"+encodeURIComponent(aA)+"="+encodeURIComponent(aB)
}});
var ay=N.url.replace(/([?&])_=[^&]*/,az);
ay=ay+(ay===N.url?(/\?/.test(N.url)?"&":"?")+az:"");
N.attachHeadersAsQueryString=false;
N.dropAtmosphereHeaders=true;
N.url=ay;
N.transport="polling";
m("",N)
}}function am(){N.reconnect=false;
av=true;
V.request=N;
V.state="unsubscribe";
V.responseBody="";
V.status=408;
V.partialMessage="";
B();
C();
al()
}function al(){V.partialMessage="";
if(N.id){clearTimeout(N.id)
}if(D!=null){D.close();
D=null
}if(F!=null){F.abort();
F=null
}if(u!=null){u.abort();
u=null
}if(Y!=null){if(Y.webSocketOpened){Y.close()
}Y=null
}if(n!=null){n.close();
n=null
}ar()
}function ar(){if(ap!=null){clearInterval(J);
document.cookie=encodeURIComponent("atmosphere-"+N.url)+"=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
ap.signal("close",{reason:"",heir:!av?I:(ap.get("children")||[])[0]});
ap.close()
}if(p!=null){p.close()
}}function ax(ay){z();
N=a.util.extend(N,ay);
N.mrequest=N.reconnect;
if(!N.reconnect){N.reconnect=true
}}function o(){return N.webSocketImpl!=null||window.WebSocket||window.MozWebSocket
}function R(){return window.EventSource
}function s(){if(N.shared){p=ag(N);
if(p!=null){if(N.logLevel==="debug"){a.util.debug("Storage service available. All communication will be local")
}if(p.open(N)){return
}}if(N.logLevel==="debug"){a.util.debug("No Storage service available.")
}p=null
}N.firstMessage=true;
N.isOpen=false;
N.ctime=a.util.now();
if(N.transport!=="websocket"&&N.transport!=="sse"){r(N)
}else{if(N.transport==="websocket"){if(!o()){P("Websocket is not supported, using request.fallbackTransport ("+N.fallbackTransport+")")
}else{ai(false)
}}else{if(N.transport==="sse"){if(!R()){P("Server Side Events(SSE) is not supported, using request.fallbackTransport ("+N.fallbackTransport+")")
}else{H(false)
}}}}}function ag(aC){var aD,aB,aG,ay="atmosphere-"+aC.url,az={storage:function(){function aH(aL){if(aL.key===ay&&aL.newValue){aA(aL.newValue)
}}if(!a.util.storage){return
}var aK=window.localStorage,aI=function(aL){return a.util.parseJSON(aK.getItem(ay+"-"+aL))
},aJ=function(aL,aM){aK.setItem(ay+"-"+aL,a.util.stringifyJSON(aM))
};
return{init:function(){aJ("children",aI("children").concat([I]));
a.util.on(window,"storage",aH);
return aI("opened")
},signal:function(aL,aM){aK.setItem(ay,a.util.stringifyJSON({target:"p",type:aL,data:aM}))
},close:function(){var aL=aI("children");
a.util.off(window,"storage",aH);
if(aL){if(aE(aL,aC.id)){aJ("children",aL)
}}}}
},windowref:function(){var aH=window.open("",ay.replace(/\W/g,""));
if(!aH||aH.closed||!aH.callbacks){return
}return{init:function(){aH.callbacks.push(aA);
aH.children.push(I);
return aH.opened
},signal:function(aI,aJ){if(!aH.closed&&aH.fire){aH.fire(a.util.stringifyJSON({target:"p",type:aI,data:aJ}))
}},close:function(){if(!aG){aE(aH.callbacks,aA);
aE(aH.children,I)
}}}
}};
function aE(aK,aJ){var aH,aI=aK.length;
for(aH=0;
aH<aI;
aH++){if(aK[aH]===aJ){aK.splice(aH,1)
}}return aI!==aK.length
}function aA(aH){var aJ=a.util.parseJSON(aH),aI=aJ.data;
if(aJ.target==="c"){switch(aJ.type){case"open":M("opening","local",N);
break;
case"close":if(!aG){aG=true;
if(aI.reason==="aborted"){am()
}else{if(aI.heir===I){s()
}else{setTimeout(function(){s()
},100)
}}}break;
case"message":E(aI,"messageReceived",200,aC.transport);
break;
case"localMessage":ab(aI);
break
}}}function aF(){var aH=new RegExp("(?:^|; )("+encodeURIComponent(ay)+")=([^;]*)").exec(document.cookie);
if(aH){return a.util.parseJSON(decodeURIComponent(aH[2]))
}}aD=aF();
if(!aD||a.util.now()-aD.ts>1000){return
}aB=az.storage()||az.windowref();
if(!aB){return
}return{open:function(){var aH;
J=setInterval(function(){var aI=aD;
aD=aF();
if(!aD||aI.ts===aD.ts){aA(a.util.stringifyJSON({target:"c",type:"close",data:{reason:"error",heir:aI.heir}}))
}},1000);
aH=aB.init();
if(aH){setTimeout(function(){M("opening","local",aC)
},50)
}return aH
},send:function(aH){aB.signal("send",aH)
},localSend:function(aH){aB.signal("localSend",a.util.stringifyJSON({id:I,event:aH}))
},close:function(){if(!av){clearInterval(J);
aB.signal("close");
aB.close()
}}}
}function ac(){var az,ay="atmosphere-"+N.url,aD={storage:function(){function aE(aG){if(aG.key===ay&&aG.newValue){aA(aG.newValue)
}}if(!a.util.storage){return
}var aF=window.localStorage;
return{init:function(){a.util.on(window,"storage",aE)
},signal:function(aG,aH){aF.setItem(ay,a.util.stringifyJSON({target:"c",type:aG,data:aH}))
},get:function(aG){return a.util.parseJSON(aF.getItem(ay+"-"+aG))
},set:function(aG,aH){aF.setItem(ay+"-"+aG,a.util.stringifyJSON(aH))
},close:function(){a.util.off(window,"storage",aE);
aF.removeItem(ay);
aF.removeItem(ay+"-opened");
aF.removeItem(ay+"-children")
}}
},windowref:function(){var aF=ay.replace(/\W/g,""),aE=document.getElementById(aF),aG;
if(!aE){aE=document.createElement("div");
aE.id=aF;
aE.style.display="none";
aE.innerHTML='<iframe name="'+aF+'" />';
document.body.appendChild(aE)
}aG=aE.firstChild.contentWindow;
return{init:function(){aG.callbacks=[aA];
aG.fire=function(aH){var aI;
for(aI=0;
aI<aG.callbacks.length;
aI++){aG.callbacks[aI](aH)
}}
},signal:function(aH,aI){if(!aG.closed&&aG.fire){aG.fire(a.util.stringifyJSON({target:"c",type:aH,data:aI}))
}},get:function(aH){return !aG.closed?aG[aH]:null
},set:function(aH,aI){if(!aG.closed){aG[aH]=aI
}},close:function(){}}
}};
function aA(aE){var aG=a.util.parseJSON(aE),aF=aG.data;
if(aG.target==="p"){switch(aG.type){case"send":ak(aF);
break;
case"localSend":ab(aF);
break;
case"close":am();
break
}}}Z=function aC(aE){az.signal("message",aE)
};
function aB(){document.cookie=encodeURIComponent(ay)+"="+encodeURIComponent(a.util.stringifyJSON({ts:a.util.now()+1,heir:(az.get("children")||[])[0]}))
}az=aD.storage()||aD.windowref();
az.init();
if(N.logLevel==="debug"){a.util.debug("Installed StorageService "+az)
}az.set("children",[]);
if(az.get("opened")!=null&&!az.get("opened")){az.set("opened",false)
}aB();
J=setInterval(aB,1000);
ap=az
}function M(aA,aD,az){if(N.shared&&aD!=="local"){ac()
}if(ap!=null){ap.set("opened",true)
}az.close=function(){am()
};
if(k>0&&aA==="re-connecting"){az.isReopen=true;
ad(V)
}else{if(V.error==null){V.request=az;
var aB=V.state;
V.state=aA;
var ay=V.transport;
V.transport=aD;
var aC=V.responseBody;
B();
V.responseBody=aC;
V.state=aB;
V.transport=ay
}}}function y(aA){aA.transport="jsonp";
var az=N,ay;
if((aA!=null)&&(typeof(aA)!=="undefined")){az=aA
}F={open:function(){var aC="atmosphere"+(++I);
function aB(){var aD=az.url;
if(az.dispatchUrl!=null){aD+=az.dispatchUrl
}var aF=az.data;
if(az.attachHeadersAsQueryString){aD=W(az);
if(aF!==""){aD+="&X-Atmosphere-Post-Body="+encodeURIComponent(aF)
}aF=""
}var aE=document.head||document.getElementsByTagName("head")[0]||document.documentElement;
ay=document.createElement("script");
ay.src=aD+"&jsonpTransport="+aC;
ay.clean=function(){ay.clean=ay.onerror=ay.onload=ay.onreadystatechange=null;
if(ay.parentNode){ay.parentNode.removeChild(ay)
}};
ay.onload=ay.onreadystatechange=function(){if(!ay.readyState||/loaded|complete/.test(ay.readyState)){ay.clean()
}};
ay.onerror=function(){ay.clean();
ae(0,"maxReconnectOnClose reached")
};
aE.insertBefore(ay,aE.firstChild)
}window[aC]=function(aF){if(az.reconnect){if(az.maxRequest===-1||az.requestCount++<az.maxRequest){if(!az.executeCallbackBeforeReconnect){Q(F,az,0)
}if(aF!=null&&typeof aF!=="string"){try{aF=aF.message
}catch(aE){}}var aD=w(aF,az,V);
if(!aD){E(V.responseBody,"messageReceived",200,az.transport)
}if(az.executeCallbackBeforeReconnect){Q(F,az,0)
}}else{a.util.log(N.logLevel,["JSONP reconnect maximum try reached "+N.requestCount]);
ae(0,"maxRequest reached")
}}};
setTimeout(function(){aB()
},50)
},abort:function(){if(ay.clean){ay.clean()
}}};
F.open()
}function i(ay){if(N.webSocketImpl!=null){return N.webSocketImpl
}else{if(window.WebSocket){return new WebSocket(ay)
}else{return new MozWebSocket(ay)
}}}function j(){return a.util.getAbsoluteURL(W(N)).replace(/^http/,"ws")
}function aw(){var ay=W(N);
return ay
}function H(az){V.transport="sse";
var ay=aw(N.url);
if(N.logLevel==="debug"){a.util.debug("Invoking executeSSE");
a.util.debug("Using URL: "+ay)
}if(N.enableProtocol&&az){var aB=a.util.now()-N.ctime;
N.lastTimestamp=Number(N.stime)+Number(aB)
}if(az&&!N.reconnect){if(n!=null){al()
}return
}try{n=new EventSource(ay,{withCredentials:N.withCredentials})
}catch(aA){ae(0,aA);
P("SSE failed. Downgrading to fallback transport and resending");
return
}if(N.connectTimeout>0){N.id=setTimeout(function(){if(!az){al()
}},N.connectTimeout)
}n.onopen=function(aC){x(N);
if(N.logLevel==="debug"){a.util.debug("SSE successfully opened")
}if(!N.enableProtocol){if(!az){M("opening","sse",N)
}else{M("re-opening","sse",N)
}}az=true;
if(N.method==="POST"){V.state="messageReceived";
n.send(N.data)
}};
n.onmessage=function(aD){x(N);
if(!N.enableXDR&&aD.origin&&aD.origin!==window.location.protocol+"//"+window.location.host){a.util.log(N.logLevel,["Origin was not "+window.location.protocol+"//"+window.location.host]);
return
}V.state="messageReceived";
V.status=200;
aD=aD.data;
var aC=w(aD,N,V);
if(n.URL){n.interval=100;
n.URL=aw(N.url)
}if(!aC){B();
V.responseBody="";
V.messages=[]
}};
n.onerror=function(aC){clearTimeout(N.id);
if(V.state==="closedByClient"){return
}ah(az);
al();
if(av){a.util.log(N.logLevel,["SSE closed normally"])
}else{if(!az){P("SSE failed. Downgrading to fallback transport and resending")
}else{if(N.reconnect&&(V.transport==="sse")){if(k++<N.maxReconnectOnClose){M("re-connecting",N.transport,N);
if(N.reconnectInterval>0){N.id=setTimeout(function(){H(true)
},N.reconnectInterval)
}else{H(true)
}V.responseBody="";
V.messages=[]
}else{a.util.log(N.logLevel,["SSE reconnect maximum try reached "+k]);
ae(0,"maxReconnectOnClose reached")
}}}}}
}function ai(az){V.transport="websocket";
if(N.enableProtocol&&az){var aA=a.util.now()-N.ctime;
N.lastTimestamp=Number(N.stime)+Number(aA)
}var ay=j(N.url);
if(N.logLevel==="debug"){a.util.debug("Invoking executeWebSocket");
a.util.debug("Using URL: "+ay)
}if(az&&!N.reconnect){if(Y!=null){al()
}return
}Y=i(ay);
if(N.webSocketBinaryType!=null){Y.binaryType=N.webSocketBinaryType
}if(N.connectTimeout>0){N.id=setTimeout(function(){if(!az){var aB={code:1002,reason:"",wasClean:false};
Y.onclose(aB);
try{al()
}catch(aC){}return
}},N.connectTimeout)
}Y.onopen=function(aB){x(N);
if(N.logLevel==="debug"){a.util.debug("Websocket successfully opened")
}if(!N.enableProtocol){if(!az){M("opening","websocket",N)
}else{M("re-opening","websocket",N)
}}az=true;
if(Y!=null){Y.webSocketOpened=az;
if(N.method==="POST"){V.state="messageReceived";
Y.send(N.data)
}}};
Y.onmessage=function(aD){x(N);
V.state="messageReceived";
V.status=200;
aD=aD.data;
var aB=typeof(aD)==="string";
if(aB){var aC=w(aD,N,V);
if(!aC){B();
V.responseBody="";
V.messages=[]
}}else{if(!t(N,aD)){return
}V.responseBody=aD;
B();
V.responseBody=null
}};
Y.onerror=function(aB){clearTimeout(N.id)
};
Y.onclose=function(aB){clearTimeout(N.id);
if(V.state==="closed"){return
}var aC=aB.reason;
if(aC===""){switch(aB.code){case 1000:aC="Normal closure; the connection successfully completed whatever purpose for which it was created.";
break;
case 1001:aC="The endpoint is going away, either because of a server failure or because the browser is navigating away from the page that opened the connection.";
break;
case 1002:aC="The endpoint is terminating the connection due to a protocol error.";
break;
case 1003:aC="The connection is being terminated because the endpoint received data of a type it cannot accept (for example, a text-only endpoint received binary data).";
break;
case 1004:aC="The endpoint is terminating the connection because a data frame was received that is too large.";
break;
case 1005:aC="Unknown: no status code was provided even though one was expected.";
break;
case 1006:aC="Connection was closed abnormally (that is, with no close frame being sent).";
break
}}if(N.logLevel==="warn"){a.util.warn("Websocket closed, reason: "+aC);
a.util.warn("Websocket closed, wasClean: "+aB.wasClean)
}if(V.state==="closedByClient"){return
}ah(az);
V.state="closed";
if(av){a.util.log(N.logLevel,["Websocket closed normally"])
}else{if(!az){P("Websocket failed. Downgrading to Comet and resending")
}else{if(N.reconnect&&V.transport==="websocket"){al();
if(k++<N.maxReconnectOnClose){M("re-connecting",N.transport,N);
if(N.reconnectInterval>0){N.id=setTimeout(function(){V.responseBody="";
V.messages=[];
ai(true)
},N.reconnectInterval)
}else{V.responseBody="";
V.messages=[];
ai(true)
}}else{a.util.log(N.logLevel,["Websocket reconnect maximum try reached "+N.requestCount]);
if(N.logLevel==="warn"){a.util.warn("Websocket error, reason: "+aB.reason)
}ae(0,"maxReconnectOnClose reached")
}}}}};
if(Y.url===undefined){Y.onclose({reason:"Android 4.1 does not support websockets.",wasClean:false})
}}function t(aB,aA){var ay=true;
if(a.util.trim(aA).length!==0&&aB.enableProtocol&&aB.firstMessage){aB.firstMessage=false;
var az=aA.split(aB.messageDelimiter);
var aC=az.length===2?0:1;
aB.uuid=a.util.trim(az[aC]);
aB.stime=a.util.trim(az[aC+1]);
ay=false;
if(aB.transport!=="long-polling"){an(aB)
}}else{if(aB.enableProtocol&&aB.firstMessage){ay=false
}else{an(aB)
}}return ay
}function x(ay){clearTimeout(ay.id);
if(ay.timeout>0&&ay.transport!=="polling"){ay.id=setTimeout(function(){q(ay);
C();
al()
},ay.timeout)
}}function q(ay){V.state="closedByClient";
V.responseBody="";
V.status=408;
V.messages=[];
B()
}function ae(ay,az){al();
clearTimeout(N.id);
V.state="error";
V.reasonPhrase=az;
V.responseBody="";
V.status=ay;
V.messages=[];
B()
}function w(aC,aB,ay){if(!t(N,aC)){return true
}if(aC.length===0){return true
}if(aB.trackMessageLength){aC=ay.partialMessage+aC;
var aA=[];
var az=aC.indexOf(aB.messageDelimiter);
while(az!==-1){var aE=a.util.trim(aC.substring(0,az));
var aD=+aE;
if(isNaN(aD)){throw new Error('message length "'+aE+'" is not a number')
}az+=aB.messageDelimiter.length;
if(az+aD>aC.length){az=-1
}else{aA.push(aC.substring(az,az+aD));
aC=aC.substring(az+aD,aC.length);
az=aC.indexOf(aB.messageDelimiter)
}}ay.partialMessage=aC;
if(aA.length!==0){ay.responseBody=aA.join(aB.messageDelimiter);
ay.messages=aA;
return false
}else{ay.responseBody="";
ay.messages=[];
return true
}}else{ay.responseBody=aC
}return false
}function P(ay){a.util.log(N.logLevel,[ay]);
if(typeof(N.onTransportFailure)!=="undefined"){N.onTransportFailure(ay,N)
}else{if(typeof(a.util.onTransportFailure)!=="undefined"){a.util.onTransportFailure(ay,N)
}}N.transport=N.fallbackTransport;
var az=N.connectTimeout===-1?0:N.connectTimeout;
if(N.reconnect&&N.transport!=="none"||N.transport==null){N.method=N.fallbackMethod;
V.transport=N.fallbackTransport;
N.fallbackTransport="none";
if(az>0){N.id=setTimeout(function(){s()
},az)
}else{s()
}}else{ae(500,"Unable to reconnect with fallback transport")
}}function W(aA,ay){var az=N;
if((aA!=null)&&(typeof(aA)!=="undefined")){az=aA
}if(ay==null){ay=az.url
}if(!az.attachHeadersAsQueryString){return ay
}if(ay.indexOf("X-Atmosphere-Framework")!==-1){return ay
}ay+=(ay.indexOf("?")!==-1)?"&":"?";
ay+="X-Atmosphere-tracking-id="+az.uuid;
ay+="&X-Atmosphere-Framework="+c;
ay+="&X-Atmosphere-Transport="+az.transport;
if(az.trackMessageLength){ay+="&X-Atmosphere-TrackMessageSize=true"
}if(az.lastTimestamp!=null){ay+="&X-Cache-Date="+az.lastTimestamp
}else{ay+="&X-Cache-Date="+0
}if(az.contentType!==""){ay+="&Content-Type="+az.contentType
}if(az.enableProtocol){ay+="&X-atmo-protocol=true"
}a.util.each(az.headers,function(aB,aD){var aC=a.util.isFunction(aD)?aD.call(this,az,aA,V):aD;
if(aC!=null){ay+="&"+encodeURIComponent(aB)+"="+encodeURIComponent(aC)
}});
return ay
}function an(ay){if(!ay.isOpen){ay.isOpen=true;
M("opening",ay.transport,ay)
}else{if(ay.isReopen){ay.isReopen=false;
M("re-opening",ay.transport,ay)
}}}function r(aA){var ay=N;
if((aA!=null)||(typeof(aA)!=="undefined")){ay=aA
}ay.lastIndex=0;
ay.readyState=0;
if((ay.transport==="jsonp")||((ay.enableXDR)&&(a.util.checkCORSSupport()))){y(ay);
return
}if(a.util.browser.msie&&a.util.browser.version<10){if((ay.transport==="streaming")){if(ay.enableXDR&&window.XDomainRequest){O(ay)
}else{au(ay)
}return
}if((ay.enableXDR)&&(window.XDomainRequest)){O(ay);
return
}}var aB=function(){ay.lastIndex=0;
if(ay.reconnect&&k++<ay.maxReconnectOnClose){M("re-connecting",aA.transport,aA);
Q(az,ay,aA.reconnectInterval)
}else{ae(0,"maxReconnectOnClose reached")
}};
if(ay.force||(ay.reconnect&&(ay.maxRequest===-1||ay.requestCount++<ay.maxRequest))){ay.force=false;
var az=a.util.xhr();
az.hasData=false;
g(az,ay,true);
if(ay.suspend){u=az
}if(ay.transport!=="polling"){V.transport=ay.transport;
az.onabort=function(){ah(true)
};
az.onerror=function(){V.error=true;
try{V.status=XMLHttpRequest.status
}catch(aD){V.status=500
}if(!V.status){V.status=500
}al();
if(!V.errorHandled){aB()
}}
}az.onreadystatechange=function(){if(av){return
}V.error=null;
var aE=false;
var aJ=false;
if(ay.transport==="streaming"&&ay.readyState>2&&az.readyState===4){al();
aB();
return
}ay.readyState=az.readyState;
if(ay.transport==="streaming"&&az.readyState>=3){aJ=true
}else{if(ay.transport==="long-polling"&&az.readyState===4){aJ=true
}}x(N);
if(ay.transport!=="polling"){if((!ay.enableProtocol||!aA.firstMessage)&&az.readyState===2){an(ay)
}var aD=200;
if(az.readyState>1){aD=az.status>1000?0:az.status
}if(aD>=300||aD===0){V.errorHandled=true;
al();
aB();
return
}}if(aJ){var aH=az.responseText;
if(a.util.trim(aH).length===0&&ay.transport==="long-polling"){if(!az.hasData){aB()
}else{az.hasData=false
}return
}az.hasData=true;
af(az,N);
if(ay.transport==="streaming"){if(!a.util.browser.opera){var aG=aH.substring(ay.lastIndex,aH.length);
aE=w(aG,ay,V);
ay.lastIndex=aH.length;
if(aE){return
}}else{a.util.iterate(function(){if(V.status!==500&&az.responseText.length>ay.lastIndex){try{V.status=az.status;
V.headers=a.util.parseHeaders(az.getAllResponseHeaders());
af(az,N)
}catch(aL){V.status=404
}x(N);
V.state="messageReceived";
var aK=az.responseText.substring(ay.lastIndex);
ay.lastIndex=az.responseText.length;
aE=w(aK,ay,V);
if(!aE){B()
}K(az,ay)
}else{if(V.status>400){ay.lastIndex=az.responseText.length;
return false
}}},0)
}}else{aE=w(aH,ay,V)
}try{V.status=az.status;
V.headers=a.util.parseHeaders(az.getAllResponseHeaders());
af(az,ay)
}catch(aI){V.status=404
}if(ay.suspend){V.state=V.status===0?"closed":"messageReceived"
}else{V.state="messagePublished"
}var aF=aA.transport!=="streaming";
if(aF&&!ay.executeCallbackBeforeReconnect){Q(az,ay,0)
}if(V.responseBody.length!==0&&!aE){B()
}if(aF&&ay.executeCallbackBeforeReconnect){Q(az,ay,0)
}K(az,ay)
}};
try{az.send(ay.data);
aj=true
}catch(aC){a.util.log(ay.logLevel,["Unable to connect to "+ay.url])
}}else{if(ay.logLevel==="debug"){a.util.log(ay.logLevel,["Max re-connection reached."])
}ae(0,"maxRequest reached")
}}function g(aA,aB,az){var ay=aB.url;
if(aB.dispatchUrl!=null&&aB.method==="POST"){ay+=aB.dispatchUrl
}ay=W(aB,ay);
ay=a.util.prepareURL(ay);
if(az){aA.open(aB.method,ay,aB.async);
if(aB.connectTimeout>0){aB.id=setTimeout(function(){if(aB.requestCount===0){al();
E("Connect timeout","closed",200,aB.transport)
}},aB.connectTimeout)
}}if(N.withCredentials){if("withCredentials" in aA){aA.withCredentials=true
}}if(!N.dropAtmosphereHeaders){aA.setRequestHeader("X-Atmosphere-Framework",a.util.version);
aA.setRequestHeader("X-Atmosphere-Transport",aB.transport);
if(aB.lastTimestamp!=null){aA.setRequestHeader("X-Cache-Date",aB.lastTimestamp)
}else{aA.setRequestHeader("X-Cache-Date",0)
}if(aB.trackMessageLength){aA.setRequestHeader("X-Atmosphere-TrackMessageSize","true")
}aA.setRequestHeader("X-Atmosphere-tracking-id",aB.uuid)
}if(aB.contentType!==""){aA.setRequestHeader("Content-Type",aB.contentType)
}a.util.each(aB.headers,function(aC,aE){var aD=a.util.isFunction(aE)?aE.call(this,aA,aB,az,V):aE;
if(aD!=null){aA.setRequestHeader(aC,aD)
}})
}function Q(az,aA,aB){if(aA.reconnect||(aA.suspend&&aj)){var ay=0;
if(az&&az.readyState!==0){ay=az.status>1000?0:az.status
}V.status=ay===0?204:ay;
V.reason=ay===0?"Server resumed the connection or down.":"OK";
clearTimeout(aA.id);
if(aB>0){aA.id=setTimeout(function(){r(aA)
},aB)
}else{r(aA)
}}}function ad(ay){ay.state="re-connecting";
aa(ay)
}function O(ay){if(ay.transport!=="polling"){D=U(ay);
D.open()
}else{U(ay).open()
}}function U(aA){var az=N;
if((aA!=null)&&(typeof(aA)!=="undefined")){az=aA
}var aF=az.transport;
var aE=0;
var ay=new window.XDomainRequest();
var aC=function(){if(az.transport==="long-polling"&&(az.reconnect&&(az.maxRequest===-1||az.requestCount++<az.maxRequest))){ay.status=200;
O(az)
}};
var aD=az.rewriteURL||function(aH){var aG=/(?:^|;\s*)(JSESSIONID|PHPSESSID)=([^;]*)/.exec(document.cookie);
switch(aG&&aG[1]){case"JSESSIONID":return aH.replace(/;jsessionid=[^\?]*|(\?)|$/,";jsessionid="+aG[2]+"$1");
case"PHPSESSID":return aH.replace(/\?PHPSESSID=[^&]*&?|\?|$/,"?PHPSESSID="+aG[2]+"&").replace(/&$/,"")
}return aH
};
ay.onprogress=function(){aB(ay)
};
ay.onerror=function(){if(az.transport!=="polling"){al();
if(k++<az.maxReconnectOnClose){if(az.reconnectInterval>0){az.id=setTimeout(function(){M("re-connecting",aA.transport,aA);
O(az)
},az.reconnectInterval)
}else{M("re-connecting",aA.transport,aA);
O(az)
}}else{ae(0,"maxReconnectOnClose reached")
}}};
ay.onload=function(){};
var aB=function(aG){clearTimeout(az.id);
var aI=aG.responseText;
aI=aI.substring(aE);
aE+=aI.length;
if(aF!=="polling"){x(az);
var aH=w(aI,az,V);
if(aF==="long-polling"&&a.util.trim(aI).length===0){return
}if(az.executeCallbackBeforeReconnect){aC()
}if(!aH){E(V.responseBody,"messageReceived",200,aF)
}if(!az.executeCallbackBeforeReconnect){aC()
}}};
return{open:function(){var aG=az.url;
if(az.dispatchUrl!=null){aG+=az.dispatchUrl
}aG=W(az,aG);
ay.open(az.method,aD(aG));
if(az.method==="GET"){ay.send()
}else{ay.send(az.data)
}if(az.connectTimeout>0){az.id=setTimeout(function(){if(az.requestCount===0){al();
E("Connect timeout","closed",200,az.transport)
}},az.connectTimeout)
}},close:function(){ay.abort()
}}
}function au(ay){D=v(ay);
D.open()
}function v(aB){var aA=N;
if((aB!=null)&&(typeof(aB)!=="undefined")){aA=aB
}var az;
var aC=new window.ActiveXObject("htmlfile");
aC.open();
aC.close();
var ay=aA.url;
if(aA.dispatchUrl!=null){ay+=aA.dispatchUrl
}if(aA.transport!=="polling"){V.transport=aA.transport
}return{open:function(){var aD=aC.createElement("iframe");
ay=W(aA);
if(aA.data!==""){ay+="&X-Atmosphere-Post-Body="+encodeURIComponent(aA.data)
}ay=a.util.prepareURL(ay);
aD.src=ay;
aC.body.appendChild(aD);
var aE=aD.contentDocument||aD.contentWindow.document;
az=a.util.iterate(function(){try{if(!aE.firstChild){return
}var aH=aE.body?aE.body.lastChild:aE;
var aJ=function(){var aL=aH.cloneNode(true);
aL.appendChild(aE.createTextNode("."));
var aK=aL.innerText;
aK=aK.substring(0,aK.length-1);
return aK
};
if(!aE.body||!aE.body.firstChild||aE.body.firstChild.nodeName.toLowerCase()!=="pre"){var aG=aE.head||aE.getElementsByTagName("head")[0]||aE.documentElement||aE;
var aF=aE.createElement("script");
aF.text="document.write('<plaintext>')";
aG.insertBefore(aF,aG.firstChild);
aG.removeChild(aF);
aH=aE.body.lastChild
}if(aA.closed){aA.isReopen=true
}az=a.util.iterate(function(){var aL=aJ();
if(aL.length>aA.lastIndex){x(N);
V.status=200;
V.error=null;
aH.innerText="";
var aK=w(aL,aA,V);
if(aK){return""
}E(V.responseBody,"messageReceived",200,aA.transport)
}aA.lastIndex=0;
if(aE.readyState==="complete"){ah(true);
M("re-connecting",aA.transport,aA);
if(aA.reconnectInterval>0){aA.id=setTimeout(function(){au(aA)
},aA.reconnectInterval)
}else{au(aA)
}return false
}},null);
return false
}catch(aI){V.error=true;
M("re-connecting",aA.transport,aA);
if(k++<aA.maxReconnectOnClose){if(aA.reconnectInterval>0){aA.id=setTimeout(function(){au(aA)
},aA.reconnectInterval)
}else{au(aA)
}}else{ae(0,"maxReconnectOnClose reached")
}aC.execCommand("Stop");
aC.close();
return false
}})
},close:function(){if(az){az()
}aC.execCommand("Stop");
ah(true)
}}
}function ak(ay){if(p!=null){l(ay)
}else{if(u!=null||n!=null){h(ay)
}else{if(D!=null){X(ay)
}else{if(F!=null){T(ay)
}else{if(Y!=null){G(ay)
}}}}}}function m(az,ay){if(!ay){ay=ao(az)
}ay.transport="polling";
ay.method="GET";
ay.async=false;
ay.reconnect=false;
ay.force=true;
ay.suspend=false;
r(ay)
}function l(ay){p.send(ay)
}function A(az){if(az.length===0){return
}try{if(p){p.localSend(az)
}else{if(ap){ap.signal("localMessage",a.util.stringifyJSON({id:I,event:az}))
}}}catch(ay){a.util.error(ay)
}}function h(az){var ay=ao(az);
r(ay)
}function X(az){if(N.enableXDR&&a.util.checkCORSSupport()){var ay=ao(az);
ay.reconnect=false;
y(ay)
}else{h(az)
}}function T(ay){h(ay)
}function S(ay){var az=ay;
if(typeof(az)==="object"){az=ay.data
}return az
}function ao(az){var aA=S(az);
var ay={connected:false,timeout:60000,method:"POST",url:N.url,contentType:N.contentType,headers:N.headers,reconnect:true,callback:null,data:aA,suspend:false,maxRequest:-1,logLevel:"info",requestCount:0,withCredentials:N.withCredentials,transport:"polling",isOpen:true,attachHeadersAsQueryString:true,enableXDR:N.enableXDR,uuid:N.uuid,dispatchUrl:N.dispatchUrl,enableProtocol:false,messageDelimiter:"|",maxReconnectOnClose:N.maxReconnectOnClose};
if(typeof(az)==="object"){ay=a.util.extend(ay,az)
}return ay
}function G(ay){var aB=S(ay);
var az;
try{if(N.dispatchUrl!=null){az=N.webSocketPathDelimiter+N.dispatchUrl+N.webSocketPathDelimiter+aB
}else{az=aB
}if(!Y.webSocketOpened){a.util.error("WebSocket not connected.");
return
}Y.send(az)
}catch(aA){Y.onclose=function(aC){};
al();
P("Websocket failed. Downgrading to Comet and resending "+ay);
h(ay)
}}function ab(az){var ay=a.util.parseJSON(az);
if(ay.id!==I){if(typeof(N.onLocalMessage)!=="undefined"){N.onLocalMessage(ay.event)
}else{if(typeof(a.util.onLocalMessage)!=="undefined"){a.util.onLocalMessage(ay.event)
}}}}function E(aB,ay,az,aA){V.responseBody=aB;
V.transport=aA;
V.status=az;
V.state=ay;
B()
}function af(ay,aB){if(!aB.readResponsesHeaders&&!aB.enableProtocol){aB.lastTimestamp=a.util.now();
aB.uuid=I;
return
}try{var aA=ay.getResponseHeader("X-Cache-Date");
if(aA&&aA!=null&&aA.length>0){aB.lastTimestamp=aA.split(" ").pop()
}var az=ay.getResponseHeader("X-Atmosphere-tracking-id");
if(az&&az!=null){aB.uuid=az.split(" ").pop()
}if(aB.headers){a.util.each(N.headers,function(aE){var aD=ay.getResponseHeader(aE);
if(aD){V.headers[aE]=aD
}})
}}catch(aC){}}function aa(ay){at(ay,N);
at(ay,a.util)
}function at(az,aA){switch(az.state){case"messageReceived":k=0;
if(typeof(aA.onMessage)!=="undefined"){aA.onMessage(az)
}break;
case"error":if(typeof(aA.onError)!=="undefined"){aA.onError(az)
}break;
case"opening":if(typeof(aA.onOpen)!=="undefined"){aA.onOpen(az)
}break;
case"messagePublished":if(typeof(aA.onMessagePublished)!=="undefined"){aA.onMessagePublished(az)
}break;
case"re-connecting":if(typeof(aA.onReconnect)!=="undefined"){aA.onReconnect(N,az)
}break;
case"closedByClient":if(typeof(aA.onClientTimeout)!=="undefined"){aA.onClientTimeout(N)
}break;
case"re-opening":if(typeof(aA.onReopen)!=="undefined"){aA.onReopen(N,az)
}break;
case"fail-to-reconnect":if(typeof(aA.onFailureToReconnect)!=="undefined"){aA.onFailureToReconnect(N,az)
}break;
case"unsubscribe":case"closed":var ay=typeof(N.closed)!=="undefined"?N.closed:false;
if(typeof(aA.onClose)!=="undefined"&&!ay){aA.onClose(az)
}N.closed=true;
break
}}function ah(ay){if(V.state!=="closed"){V.state="closed";
V.responseBody="";
V.messages=[];
V.status=!ay?501:200;
B()
}}function B(){var aA=function(aD,aE){aE(V)
};
if(p==null&&Z!=null){Z(V.responseBody)
}N.reconnect=N.mrequest;
var ay=typeof(V.responseBody)==="string";
var aB=(ay&&N.trackMessageLength)?(V.messages.length>0?V.messages:[""]):new Array(V.responseBody);
for(var az=0;
az<aB.length;
az++){if(aB.length>1&&aB[az].length===0){continue
}V.responseBody=(ay)?a.util.trim(aB[az]):aB[az];
if(p==null&&Z!=null){Z(V.responseBody)
}if(V.responseBody.length===0&&V.state==="messageReceived"){continue
}aa(V);
if(e.length>0){if(N.logLevel==="debug"){a.util.debug("Invoking "+e.length+" global callbacks: "+V.state)
}try{a.util.each(e,aA)
}catch(aC){a.util.log(N.logLevel,["Callback exception"+aC])
}}if(typeof(N.callback)==="function"){if(N.logLevel==="debug"){a.util.debug("Invoking request callbacks")
}try{N.callback(V)
}catch(aC){a.util.log(N.logLevel,["Callback exception"+aC])
}}}}this.subscribe=function(ay){ax(ay);
s()
};
this.execute=function(){s()
};
this.close=function(){am()
};
this.disconnect=function(){C()
};
this.getUrl=function(){return N.url
};
this.push=function(aA,az){if(az!=null){var ay=N.dispatchUrl;
N.dispatchUrl=az;
ak(aA);
N.dispatchUrl=ay
}else{ak(aA)
}};
this.getUUID=function(){return N.uuid
};
this.pushLocal=function(ay){A(ay)
};
this.enableProtocol=function(ay){return N.enableProtocol
};
this.request=N;
this.response=V
}};
a.subscribe=function(g,j,i){if(typeof(j)==="function"){a.addCallback(j)
}if(typeof(g)!=="string"){i=g
}else{i.url=g
}var h=new a.AtmosphereRequest(i);
h.execute();
f[f.length]=h;
return h
};
a.unsubscribe=function(){if(f.length>0){var g=[].concat(f);
for(var j=0;
j<g.length;
j++){var h=g[j];
h.close();
clearTimeout(h.response.request.id)
}}f=[];
e=[]
};
a.unsubscribeUrl=function(h){var g=-1;
if(f.length>0){for(var k=0;
k<f.length;
k++){var j=f[k];
if(j.getUrl()===h){j.close();
clearTimeout(j.response.request.id);
g=k;
break
}}}if(g>=0){f.splice(g,1)
}};
a.addCallback=function(g){if(a.util.inArray(g,e)===-1){e.push(g)
}};
a.removeCallback=function(h){var g=a.util.inArray(h,e);
if(g!==-1){e.splice(g,1)
}};
a.util={browser:{},parseHeaders:function(h){var g,j=/^(.*?):[ \t]*([^\r\n]*)\r?$/mg,i={};
while(g=j.exec(h)){i[g[1]]=g[2]
}return i
},now:function(){return new Date().getTime()
},isArray:function(g){return Object.prototype.toString.call(g)==="[object Array]"
},inArray:function(j,k){if(!Array.prototype.indexOf){var g=k.length;
for(var h=0;
h<g;
++h){if(k[h]===j){return h
}}return -1
}return k.indexOf(j)
},isBinary:function(h){var g=Object.prototype.toString.call(h);
return g==="[object Blob]"||g==="[object ArrayBuffer]"
},isFunction:function(g){return Object.prototype.toString.call(g)==="[object Function]"
},getAbsoluteURL:function(g){var h=document.createElement("div");
h.innerHTML='<a href="'+g+'"/>';
return encodeURI(decodeURI(h.firstChild.href))
},prepareURL:function(h){var i=a.util.now();
var g=h.replace(/([?&])_=[^&]*/,"$1_="+i);
return g+(g===h?(/\?/.test(h)?"&":"?")+"_="+i:"")
},trim:function(g){if(!String.prototype.trim){return g.toString().replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"").replace(/\s+/g," ")
}else{return g.toString().trim()
}},param:function(k){var i,g=[];
function j(l,m){m=a.util.isFunction(m)?m():(m==null?"":m);
g.push(encodeURIComponent(l)+"="+encodeURIComponent(m))
}function h(m,n){var l;
if(a.util.isArray(n)){a.util.each(n,function(p,o){if(/\[\]$/.test(m)){j(m,o)
}else{h(m+"["+(typeof o==="object"?p:"")+"]",o)
}})
}else{if(Object.prototype.toString.call(n)==="[object Object]"){for(l in n){h(m+"["+l+"]",n[l])
}}else{j(m,n)
}}}for(i in k){h(i,k[i])
}return g.join("&").replace(/%20/g,"+")
},storage:!!(window.localStorage&&window.StorageEvent),iterate:function(i,h){var j;
h=h||0;
(function g(){j=setTimeout(function(){if(i()===false){return
}g()
},h)
})();
return function(){clearTimeout(j)
}
},each:function(m,n,h){var l,j=0,k=m.length,g=a.util.isArray(m);
if(h){if(g){for(;
j<k;
j++){l=n.apply(m[j],h);
if(l===false){break
}}}else{for(j in m){l=n.apply(m[j],h);
if(l===false){break
}}}}else{if(g){for(;
j<k;
j++){l=n.call(m[j],j,m[j]);
if(l===false){break
}}}else{for(j in m){l=n.call(m[j],j,m[j]);
if(l===false){break
}}}}return m
},extend:function(k){var j,h,g;
for(j=1;
j<arguments.length;
j++){if((h=arguments[j])!=null){for(g in h){k[g]=h[g]
}}}return k
},on:function(i,h,g){if(i.addEventListener){i.addEventListener(h,g,false)
}else{if(i.attachEvent){i.attachEvent("on"+h,g)
}}},off:function(i,h,g){if(i.removeEventListener){i.removeEventListener(h,g,false)
}else{if(i.detachEvent){i.detachEvent("on"+h,g)
}}},log:function(i,h){if(window.console){var g=window.console[i];
if(typeof g==="function"){g.apply(window.console,h)
}}},warn:function(){a.util.log("warn",arguments)
},info:function(){a.util.log("info",arguments)
},debug:function(){a.util.log("debug",arguments)
},error:function(){a.util.log("error",arguments)
},xhr:function(){try{return new window.XMLHttpRequest()
}catch(h){try{return new window.ActiveXObject("Microsoft.XMLHTTP")
}catch(g){}}},parseJSON:function(g){return !g?null:window.JSON&&window.JSON.parse?window.JSON.parse(g):new Function("return "+g)()
},stringifyJSON:function(i){var l=/[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,j={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};
function g(m){return'"'+m.replace(l,function(n){var o=j[n];
return typeof o==="string"?o:"\\u"+("0000"+n.charCodeAt(0).toString(16)).slice(-4)
})+'"'
}function h(m){return m<10?"0"+m:m
}return window.JSON&&window.JSON.stringify?window.JSON.stringify(i):(function k(r,q){var p,o,m,n,t=q[r],s=typeof t;
if(t&&typeof t==="object"&&typeof t.toJSON==="function"){t=t.toJSON(r);
s=typeof t
}switch(s){case"string":return g(t);
case"number":return isFinite(t)?String(t):"null";
case"boolean":return String(t);
case"object":if(!t){return"null"
}switch(Object.prototype.toString.call(t)){case"[object Date]":return isFinite(t.valueOf())?'"'+t.getUTCFullYear()+"-"+h(t.getUTCMonth()+1)+"-"+h(t.getUTCDate())+"T"+h(t.getUTCHours())+":"+h(t.getUTCMinutes())+":"+h(t.getUTCSeconds())+'Z"':"null";
case"[object Array]":m=t.length;
n=[];
for(p=0;
p<m;
p++){n.push(k(p,t)||"null")
}return"["+n.join(",")+"]";
default:n=[];
for(p in t){if(b.call(t,p)){o=k(p,t);
if(o){n.push(g(p)+":"+o)
}}}return"{"+n.join(",")+"}"
}}})("",{"":i})
},checkCORSSupport:function(){if(a.util.browser.msie&&!window.XDomainRequest){return true
}else{if(a.util.browser.opera&&a.util.browser.version<12){return true
}}var g=navigator.userAgent.toLowerCase();
var h=g.indexOf("android")>-1;
if(h){return true
}return false
}};
d=a.util.now();
(function(){var h=navigator.userAgent.toLowerCase(),g=/(chrome)[ \/]([\w.]+)/.exec(h)||/(webkit)[ \/]([\w.]+)/.exec(h)||/(opera)(?:.*version|)[ \/]([\w.]+)/.exec(h)||/(msie) ([\w.]+)/.exec(h)||h.indexOf("compatible")<0&&/(mozilla)(?:.*? rv:([\w.]+)|)/.exec(h)||[];
a.util.browser[g[1]||""]=true;
a.util.browser.version=g[2]||"0";
if(a.util.browser.msie||(a.util.browser.mozilla&&a.util.browser.version.split(".")[0]==="1")){a.util.storage=false
}})();
a.util.on(window,"unload",function(g){a.unsubscribe()
});
a.util.on(window,"keypress",function(g){if(g.which===27){g.preventDefault()
}});
a.util.on(window,"offline",function(){a.unsubscribe()
});
window.atmosphere=a
})();