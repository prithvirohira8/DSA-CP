/*! v6940 c385a96c */
var __webpack_exports__={};(function(){function n(){try{return(new Date).getTime()}catch(n){return 0}}var t=n(),e={InjectTagError:{id:1,message:"InjectTagError"},LoadTagError:{id:2,message:"LoadTagError"},UnexpectedError:{id:128,message:"UnexpectedError"}},r=function(){},o=b(),i="undefined"==typeof window||"function"!=typeof window.addEventListener,c=function(){var n,t;if(!i){try{O(n=S("about:blank")),t=!D(n)}catch(n){t=!0}try{n&&("function"==typeof n.remove&&n.remove(),n=null)}catch(n){}}return t}(),u=-1,a=function(){try{if(i)return!1;if("object"==typeof omidVerificationProperties&&"web"===omidVerificationProperties.injectionSource)return!0;for(var n=!1,t=window;!1===n&&t;)n=null!==t.document.querySelector("script[src*=omweb-v1]"),t=t===window.top?null:t.parent,u++;return n}catch(n){return!1}}(),d=-1,f=function(){try{if(i||a)return!1;var n=!1,t=window;for(;!1===n&&t;)n=(e=t.omid3p)&&"function"==typeof e.registerSessionObserver&&"function"==typeof e.addEventListener,t=t===window.top?null:t.parent,d++;return n}catch(n){return!1}var e}(),v=!(i||!document.currentScript),p=function(){try{if(!i&&document){let n=document.currentScript;return n.dvvisit=1,n}}catch(n){H(n,19,!1)}}();function m(n){const t=new URL(n),{hostname:e}=t;return e}function s(n){return n&&["*cdn*.doubleverify.com","*cdn*.dv.tech","localhost","127.0.0.1"].some((t=>l(t,n)))}function w(n){return n.indexOf("/dvtp_src.js")>-1}function l(n,t){const e=n.replace(/[.+^${}()|[\]\\]/g,"\\$&");return new RegExp(`^${e.replace(/\*/g,".*").replace(/\?/g,".")}$`,"i").test(t)}var y=!i&&x(),_=6940,g=y?y.substr(0,y.indexOf("/dvtp_src.js")+1):"https://cdn.doubleverify.com/",R="https://tps.doubleverify.com/",E="visit.jpg",h={loadScript:function n(t,e,o){e=e||r,o=o||r,"object"==typeof omidNative&&("function"==typeof omidNative.downloadJavaScriptResource?omidNative.downloadJavaScriptResource(t,(function n(t){try{dvWindow=M($dv),eval(t),e()}catch(n){var r=void 0!==t&&t&&t.length||0;o("OnEvalError_ResLen_"+r)}}),(function(){o("OnFailedToDownload")})):o("DownloadJavaScriptResourceNotExist"))},fire:function(n,t,e){t=t||r,e=e||r,"object"==typeof omidNative&&"function"==typeof omidNative.sendUrl&&omidNative.sendUrl(n,t,e)}},I={loadScript:function(n,t,e,r){if(dvWindow=window,c||r||a)return P(n,t,e);A(n)},fire:function(n,t,e){var o=new Image(1,1);o.onerror=e||r,o.onload=t||r,o.src=n}},L=i?h:I,U,j;function $(){for(var n=1,t=Math.floor(10*Math.random())+1,e=0;e<t;++e)n*=Math.random();return n}function b(){return(n()*$()).toString(10)}function C(){return p&&p.src}function T(){return v&&document.currentScript.src}function x(){return T()||C()}function M(n){var t={location:{protocol:"https:"}},e={getElementById:r,createElement:r,body:{insertBefore:r},querySelectorAll:function(){return[]}};return t.$dv=n,t.parent=t,t.top=t,t.document=e,t}function N(n,t){var e=q(),r=D(n),o='<html><head><script type="text/javascript">('+function(){window.$dv=window.parent.$dv,window.$dv.isFrameSupported=!0,window.$frmId=Math.random().toString(36)+Math.random().toString(36)}.toString()+')();<\/script></head><body><script type="text/javascript">('+function n(t,e){var r;function o(){"function"==typeof window.clearTimeout&&RetrayLoader.timerRef&&window.clearTimeout(RetrayLoader.timerRef)}window.RetrayLoader||(window.RetrayLoader={MAX_NUM_OF_TRIES:3,TIMEOUT:400,numOfTries:1,timerRef:null}),t&&((r=document.createElement("script")).src=t,r.onload=function(){window.isDVMLoaded=!0,o()},r.onerror=function(){if(!window.isDVMLoaded){if(r&&"function"==typeof r.remove&&(r.remove(),r=null),"function"!=typeof window.setTimeout||RetrayLoader.MAX_NUM_OF_TRIES===RetrayLoader.numOfTries)return i=e,void(new Image(1,1).src=i);var i;++RetrayLoader.numOfTries,o(),RetrayLoader.timerRef=window.setTimeout((function(){n(t,e)}),RetrayLoader.TIMEOUT)}},document.body.appendChild(r))}.toString()+')("'+t+'", "'+e+'");<\/script></body></html>';if(r)r.open(),r.write(o),r.close();else{var i=encodeURIComponent(o.replace(/'/g,"\\'").replace(/\n|\r\n|\r/g,""));n.src='javascript: (function(){document.open();document.domain="'+window.document.domain+"\";document.write('"+i+"');})()"}}function S(n){var t=Math.floor(1e12*(Math.random()+"")),e=document.createElement("iframe");return e.name=e.id="iframe_"+t,e.setAttribute("data-dv-frm",t),e.width="0",e.height="0",e.style.display="none",e.src=n,e}function O(n){if(document.currentScript){var t=document.currentScript.parentNode;t&&"function"==typeof t.insertBefore?t.insertBefore(n,document.currentScript):document.currentScript.appendChild(n)}else if(document.body&&"function"==typeof document.body.insertBefore)document.body.firstChild?document.body.insertBefore(n,document.body.firstChild):document.body.appendChild(n);else{var e=document.getElementsByTagName("head")[0];e&&"function"==typeof document.body.insertBefore&&e.firstChild?e.insertBefore(n,e.firstChild):document.documentElement.appendChild(n)}}function D(n){return n&&(n.contentDocument||n.contentWindow&&n.contentWindow.document||frames&&frames[n.name]&&frames[n.name].document)}function P(n,t,e){var i=document.createElement("script");i.onload=t||r,i.onerror=e||r,i.src=n,i.type="application/javascript",O(i),W().tagData[o].scriptInjectionMode="injectedAsSibling"}function A(n){var t=S("about:blank");O(t),N(t,n),W().tagData[o].scriptInjectionMode="injectedInIframe"}var k=R+E+"?";function F(n,t,e){var r=n&&n.message||"error undefined",o=[k,"ctx=818052&cmp=1619415&dvtagver=6.1.src","&napr=",r,"&cerrt=",t,"&tgjsver=",_,"&jsver=",_,"&flvr=","0"];e&&o.push("&dvp_isLostImp=1");var u=0;return i&&(u|=4),c&&(u|=8),u>0&&o.push("&tstype="+u),y&&o.push("&ee_dp_dvtpurl="+encodeURIComponent(y)),o.join("")}function H(n,t,e){var r=F(n,t,e),o=encodeURI(r);L&&L.fire(o)}try{j=function(n){var t,e,r,o={},i={};return(t=n)&&(e=function(n){var t=n.indexOf("#"),e=n.indexOf("?"),r="";r=t>-1&&e>-1?e<t?"?":"#":-1==e?"#":"?";var o="",i=n.trim().split(r);return 2===i.length&&(o=i[1]),o}(t),e&&(r={},e.split(/[&?#]+/).forEach((function(n){2===(n=n.trim().split("=")).length&&(r[n[0].trim()]=n[1].trim())})),i=function(n){var t={};function e(e){return function(r){n.hasOwnProperty(r)&&(t[e+r]=n[r])}}return["gdpr","gdpr_consent"].forEach(e("dvp_")),["ctx","cmp","sid","plc"].forEach(e("ee_dp_")),t}(o=r))),{toQueryString:function(n){var t,e="";for(t in n)n.hasOwnProperty(t)&&(e&&(e+="&"),e=[e,t,"=",n[t]].join(""));return e},getQueryStringParams:function(){return o},getQueryStringClientParams:function(){return i}}}(y),U=function(){var n,t="",e="__ERR_MSG_PLACEHOLDER__",r="__CLIENT_ERROR_TYPE_PLACEHOLDER__",o="__ERR_TRACE_PLACEHOLDER__",u={ctx:818052,cmp:1619415,dvtagver:"6.1.src",tgjsver:_,jsver:_,napr:e,cerrt:r,flvr:"0",ee_dp_dvtptrace:o},a=0;function d(n){return n&&n.trim().replace(/(\t|\n|\r|\|)/g,"")}function f(n,i,c){i=(i=i&&d(i))&&encodeURIComponent(i)||"";let u=n.trace&&d(n.trace);u=u&&encodeURIComponent(u)||"";var a=t.replace(new RegExp(e,"g"),[n.message,i].join("__"));return a=(a=a.replace(new RegExp(r,"g"),n.id)).replace(new RegExp(o,"g"),u),c&&(a+="&dvp_isLostImp=1"),a}return i&&(a|=4),c&&(a|=8),a>0&&(u.tstype=a),y&&(u.ee_dp_dvtpurl=encodeURIComponent(y)),n=[j.toQueryString(u),j.toQueryString(j.getQueryStringClientParams())].join("&"),t=[k,n].join(""),{report:function(n,t,e){L.fire(f(n,t,e))},getReportUrl:f}}()}catch(n){H(n,e.UnexpectedError,!1)}function Q(n,t){for(var e=5;n[t]&&--e;)t=b();if(n[t])throw new Error("failed to create tagUniqueKey");return t}function V(e,r,o,c,v,m){e.tagData=e.tagData||{},r=Q(e.tagData,r);var s={};s.dvtpScriptVersion=o,s.dvtpScriptUrl=c,s.restrictedAccess=v,s.$frmId=m,s.tagScriptElem=p,s.tagLoadedMS=t,s.isOmidForWeb=a,s.isOmid3p=f,s.omidWebHopCounter=u,s.omid3pHopCounter=d,s.tagExecTimeMs=n()-t,s.flvr="0",s.authorizedDomain=G(i,c),i||(s.tagReadyState=document.readyState),s.staticPrefix=g,s.serverPrefix=R,e.tagData[r]=s}function q(n){n=n||"NotFound";var t=e.LoadTagError;t.trace="TagUrl="+encodeURIComponent(y)+";DvmUrl="+encodeURIComponent(X)+";CurrentUrl="+encodeURIComponent(document.location.href);var r="failedToLoadDVM__"+n;return U?U.getReportUrl(t,r,!0):F(t.id,t.message+"__"+r,!0)}function B(n){var t=q(n);L.fire(t)}function W(){return $dv="undefined"!=typeof $dv&&$dv||{tags:{},tagsCounter:0},$dv}function G(n,t){return n||J(t)}function J(n){return s(m(n))}try{var K=i||w(y);if(!K)throw new Error("Invalid tag URL");$dv=W(),$frmId=b(),$dv.tagsCounter++,$dv.isDomlessEnvironment=i,$dv.sharedUniqueKey=$dv.sharedUniqueKey||b(),$dv.restrictedAccess=c,k=R+E+"?";var X=[g,"dv-measurements6940",".js"].join("");V($dv,o,_,y,c,$frmId),L.loadScript(X,r,B)}catch(n){var Y=e.InjectTagError;U?U.report(Y,n.message,!0):H(n,Y,!0)}})();