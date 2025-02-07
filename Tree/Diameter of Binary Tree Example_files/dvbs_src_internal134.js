
function dv_rolloutManager(handlersDefsArray, baseHandler) {
    this.handle = function () {
        var errorsArr = [];

        var handler = chooseEvaluationHandler(handlersDefsArray);
        if (handler) {
            var errorObj = handleSpecificHandler(handler);
            if (errorObj === null) {
                return errorsArr;
            }
            else {
                var debugInfo = handler.onFailure();
                if (debugInfo) {
                    for (var key in debugInfo) {
                        if (debugInfo.hasOwnProperty(key)) {
                            if (debugInfo[key] !== undefined || debugInfo[key] !== null) {
                                errorObj[key] = encodeURIComponent(debugInfo[key]);
                            }
                        }
                    }
                }
                errorsArr.push(errorObj);
            }
        }

        var errorObjHandler = handleSpecificHandler(baseHandler);
        if (errorObjHandler) {
            errorObjHandler['dvp_isLostImp'] = 1;
            errorsArr.push(errorObjHandler);
        }
        return errorsArr;
    };

    function handleSpecificHandler(handler) {
        var request;
        var errorObj = null;

        try {
            request = handler.createRequest();
            if (request && !request.isSev1) {
                var url = request.url || request;
                if (url) {
                    if (!handler.sendRequest(url)) {
                        errorObj = createAndGetError('sendRequest failed.',
                            url,
                            handler.getVersion(),
                            handler.getVersionParamName(),
                            handler.dv_script);
                    }
                } else {
                    errorObj = createAndGetError('createRequest failed.',
                        url,
                        handler.getVersion(),
                        handler.getVersionParamName(),
                        handler.dv_script,
                        handler.dvScripts,
                        handler.dvStep,
                        handler.dvOther
                    );
                }
            }
        }
        catch (e) {
            errorObj = createAndGetError(e.name + ': ' + e.message, request ? (request.url || request) : null, handler.getVersion(), handler.getVersionParamName(), (handler ? handler.dv_script : null));
        }

        return errorObj;
    }

    function createAndGetError(error, url, ver, versionParamName, dv_script, dvScripts, dvStep, dvOther) {
        var errorObj = {};
        errorObj[versionParamName] = ver;
        errorObj['dvp_jsErrMsg'] = encodeURIComponent(error);
        if (dv_script && dv_script.parentElement && dv_script.parentElement.tagName && dv_script.parentElement.tagName == 'HEAD') {
            errorObj['dvp_isOnHead'] = '1';
        }
        if (url) {
            errorObj['dvp_jsErrUrl'] = url;
        }
        if (dvScripts) {
            var dvScriptsResult = '';
            for (var id in dvScripts) {
                if (dvScripts[id] && dvScripts[id].src) {
                    dvScriptsResult += encodeURIComponent(dvScripts[id].src) + ":" + dvScripts[id].isContain + ",";
                }
            }
            
            
            
        }
        return errorObj;
    }

    function chooseEvaluationHandler(handlersArray) {
        var config = window._dv_win.dv_config;
        var index = 0;
        var isEvaluationVersionChosen = false;
        if (config.handlerVersionSpecific) {
            for (var i = 0; i < handlersArray.length; i++) {
                if (handlersArray[i].handler.getVersion() == config.handlerVersionSpecific) {
                    isEvaluationVersionChosen = true;
                    index = i;
                    break;
                }
            }
        }
        else if (config.handlerVersionByTimeIntervalMinutes) {
            var date = config.handlerVersionByTimeInputDate || new Date();
            var hour = date.getUTCHours();
            var minutes = date.getUTCMinutes();
            index = Math.floor(((hour * 60) + minutes) / config.handlerVersionByTimeIntervalMinutes) % (handlersArray.length + 1);
            if (index != handlersArray.length) { 
                isEvaluationVersionChosen = true;
            }
        }
        else {
            var rand = config.handlerVersionRandom || (Math.random() * 100);
            for (var i = 0; i < handlersArray.length; i++) {
                if (rand >= handlersArray[i].minRate && rand < handlersArray[i].maxRate) {
                    isEvaluationVersionChosen = true;
                    index = i;
                    break;
                }
            }
        }

        if (isEvaluationVersionChosen == true && handlersArray[index].handler.isApplicable()) {
            return handlersArray[index].handler;
        }
        else {
            return null;
        }
    }
}

function getCurrentTime() {
    "use strict";
    if (Date.now) {
        return Date.now();
    }
    return (new Date()).getTime();
}

function doesBrowserSupportHTML5Push() {
    "use strict";
    return typeof window.parent.postMessage === 'function' && window.JSON;
}

function dv_GetParam(url, name, checkFromStart) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regexS = (checkFromStart ? "(?:\\?|&|^)" : "[\\?&]") + name + "=([^&#]*)";
    var regex = new RegExp(regexS, 'i');
    var results = regex.exec(url);
    if (results == null)
        return null;
    else
        return results[1];
}

function dv_Contains(array, obj) {
    var i = array.length;
    while (i--) {
        if (array[i] === obj) {
            return true;
        }
    }
    return false;
}

function dv_GetDynamicParams(url, prefix) {
    try {
        prefix = (prefix != undefined && prefix != null) ? prefix : 'dvp';
        var regex = new RegExp("[\\?&](" + prefix + "_[^&]*=[^&#]*)", "gi");
        var dvParams = regex.exec(url);

        var results = [];
        while (dvParams != null) {
            results.push(dvParams[1]);
            dvParams = regex.exec(url);
        }
        return results;
    }
    catch (e) {
        return [];
    }
}

function dv_createIframe() {
    var iframe;
    if (document.createElement && (iframe = document.createElement('iframe'))) {
        iframe.name = iframe.id = 'iframe_' + Math.floor((Math.random() + "") * 1000000000000);
        iframe.width = 0;
        iframe.height = 0;
        iframe.style.display = 'none';
        iframe.src = 'about:blank';
    }

    return iframe;
}

function dv_GetRnd() {
    return ((new Date()).getTime() + "" + Math.floor(Math.random() * 1000000)).substr(0, 16);
}

function dv_SendErrorImp(serverUrl, errorsArr) {

    for (var j = 0; j < errorsArr.length; j++) {
        var errorObj = errorsArr[j];
        var errorImp =   dv_CreateAndGetErrorImp(serverUrl, errorObj);
        dv_sendImgImp(errorImp);
    }
}

function dv_CreateAndGetErrorImp(serverUrl, errorObj) {
    var errorQueryString = '';
    for (var key in errorObj) {
        if (errorObj.hasOwnProperty(key)) {
            if (key.indexOf('dvp_jsErrUrl') == -1) {
                errorQueryString += '&' + key + '=' + errorObj[key];
            }
            else {
                var params = ['ctx', 'cmp', 'plc', 'sid'];
                for (var i = 0; i < params.length; i++) {
                    var pvalue = dv_GetParam(errorObj[key], params[i]);
                    if (pvalue) {
                        errorQueryString += '&dvp_js' + params[i] + '=' + pvalue;
                    }
                }
            }
        }
    }

    var sslFlag = '&ssl=1';
    var errorImp = 'https://' + serverUrl + sslFlag + errorQueryString;

    return errorImp;
}

function dv_getDVUniqueKey(elm) {
    return elm && elm.getAttribute('data-uk');
}

function dv_getDVErrorGlobalScope(elm) {
    var uniqueKey = dv_getDVUniqueKey(elm);
    return uniqueKey && window._dv_win && window._dv_win[uniqueKey] && window._dv_win[uniqueKey].globalScopeVerifyErrorHandler || {};
}

function dv_onLoad(evt) {
    var elm = evt && evt.target || {};
    var globalScope = dv_getDVErrorGlobalScope(elm);
    if (globalScope) {
        var scriptSRC = dv_getScriptSRC(elm);
        if (!globalScope.isJSONPCalled) {
            setTimeout(function onTimeout(){
                globalScope.onTimeout(scriptSRC);
            }, globalScope.msTillJSONPCalled);
        }
    }
}

function dv_onResponse(evt) {
    var elm = evt && evt.target || {};
    var globalScope = dv_getDVErrorGlobalScope(elm);
    if (globalScope) {
        var scriptSRC = dv_getScriptSRC(elm);
        if (!globalScope.isJSONPCalled) {
            globalScope.onResponse(scriptSRC);
        }
    }
}

function dv_getScriptSRC(elm) {
    return elm && elm.src || '';
}
var IQPAParams = [
    "auprice", "ppid", "audeal", "auevent", "auadv", "aucmp", "aucrtv", "auorder", "ausite", "auplc", "auxch", "audvc", "aulitem",
    "auadid", "pltfrm", "aufilter1", "aufilter2", "autt", "auip", "aubndl", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9",
    "c10", "c11", "c12", "c13", "c14", "c15"
];

function dv_AppendIQPAParams(src) {
    var qs = [];
    var paramVal;
    IQPAParams.forEach(function forEachParam(paramName){
        paramVal = dv_GetParam(src, paramName);
        if (paramVal !== '' && paramVal !== null) {
            qs.push([paramName, paramVal].join('='));
        }
    });
    return qs.length && '&' + qs.join('&') || '';
}

function dv_onError(evt) {
    var elm = evt && evt.target || {};
    var globalScope = dv_getDVErrorGlobalScope(elm);
    if (globalScope) {
        globalScope.onError(dv_getScriptSRC(elm));
    }
}

function dv_getDVBSErrAddress(config) {
    return config && config.bsErrAddress || 'rtb0.doubleverify.com';
}

function dv_sendImgImp(url) {
    (new Image()).src = url;
}

function dv_sendScriptRequest(url, onLoad, onError, uniqueKey) {
    var emptyFunction = function(){};
    onLoad = onLoad || emptyFunction;
    onError = onError || emptyFunction;
    document.write('<scr' + 'ipt data-uk="' + uniqueKey + '" onerror="(' + onError + ')({target:this});" onload="(' + onLoad + ')({target:this});" type="text/javascript" src="' + url + '"></scr' + 'ipt>');
}

function dv_getPropSafe(obj, propName) {
    try {
        if (obj)
            return obj[propName];
    } catch (e) {
    }
}

function dvBsType() {
    var that = this;
    var eventsForDispatch = {};

    this.getEventsForDispatch = function getEventsForDispatch () {
        return eventsForDispatch;
    };

    var messageEventListener = function (event) {
        try {
            var timeCalled = getCurrentTime();
            var data = window.JSON.parse(event.data);
            if (!data.action) {
                data = window.JSON.parse(data);
            }
            if (data.timeStampCollection) {
                data.timeStampCollection.push({messageEventListenerCalled: timeCalled});
            }
            var myUID;
            var visitJSHasBeenCalledForThisTag = false;
            if ($dvbs.tags) {
                for (var uid in $dvbs.tags) {
                    if ($dvbs.tags.hasOwnProperty(uid) && $dvbs.tags[uid] && $dvbs.tags[uid].t2tIframeId === data.iFrameId) {
                        myUID = uid;
                        visitJSHasBeenCalledForThisTag = true;
                        break;
                    }
                }
            }

        } catch (e) {
            try {
                dv_SendErrorImp(window._dv_win.dv_config.tpsErrAddress + '/visit.jpg?flvr=0&ctx=818052&cmp=1619415&dvtagver=6.1.src&jsver=0&dvp_ist2tListener=1', {cemsg: encodeURIComponent(e)});
            } catch (ex) {
            }
        }
    };

    if (window.addEventListener)
        addEventListener("message", messageEventListener, false);
    else if (window.attachEvent)
        window.attachEvent("onmessage", messageEventListener);

    this.pubSub = new function () {

        var subscribers = [];

        this.subscribe = function (eventName, uid, actionName, func) {
            if (!subscribers[eventName + uid])
                subscribers[eventName + uid] = [];
            subscribers[eventName + uid].push({Func: func, ActionName: actionName});
        };

        this.publish = function (eventName, uid) {
            var actionsResults = [];
            if (eventName && uid && subscribers[eventName + uid] instanceof Array)
                for (var i = 0; i < subscribers[eventName + uid].length; i++) {
                    var funcObject = subscribers[eventName + uid][i];
                    if (funcObject && funcObject.Func && typeof funcObject.Func == "function" && funcObject.ActionName) {
                        var isSucceeded = runSafely(function () {
                            return funcObject.Func(uid);
                        });
                        actionsResults.push(encodeURIComponent(funcObject.ActionName) + '=' + (isSucceeded ? '1' : '0'));
                    }
                }
            return actionsResults.join('&');
        };
    };

    this.domUtilities = new function () {

        this.addImage = function (url, parentElement, trackingPixelCompleteCallbackName) {
            url = appendCacheBuster(url);
            if (typeof(navigator.sendBeacon) === 'function') {
                var isSuccessfullyQueuedDataForTransfer = navigator.sendBeacon(url);
                if (isSuccessfullyQueuedDataForTransfer && typeof(window[trackingPixelCompleteCallbackName]) === 'function') {
                    window[trackingPixelCompleteCallbackName]();
                }
                return;
            }

            var image = new Image();
            if (typeof(window[trackingPixelCompleteCallbackName]) === 'function') {
                image.addEventListener('load', window[trackingPixelCompleteCallbackName]);
            }
            image.src = url;
        };

        this.addScriptResource = function (url, parentElement, onLoad, onError, uniqueKey) {
            var emptyFunction = function(){};
            onLoad = onLoad || emptyFunction;
            onError = onError || emptyFunction;
            uniqueKey = uniqueKey || '';
            if (parentElement) {
                var scriptElem = parentElement.ownerDocument.createElement("script");
                scriptElem.onerror = onError;
                scriptElem.onload = onLoad;
                if (scriptElem && typeof(scriptElem.setAttribute) === 'function') {
                    scriptElem.setAttribute('data-uk', uniqueKey);
                }
                scriptElem.type = 'text/javascript';
                scriptElem.src = appendCacheBuster(url);
                parentElement.insertBefore(scriptElem, parentElement.firstChild);
            }
            else {
                addScriptResourceFallBack(url, onLoad, onError, uniqueKey);
            }
        };

        function addScriptResourceFallBack(url, onLoad, onError, uniqueKey) {
            var emptyFunction = function(){};
            onLoad = onLoad || emptyFunction;
            onError = onError || emptyFunction;
            uniqueKey = uniqueKey || '';
            var scriptElem = document.createElement('script');
            scriptElem.onerror = onError;
            scriptElem.onload = onLoad;
            if (scriptElem && typeof(scriptElem.setAttribute) === 'function') {
                scriptElem.setAttribute('data-uk', uniqueKey);
            }
            scriptElem.type = "text/javascript";
            scriptElem.src = appendCacheBuster(url);
            var firstScript = document.getElementsByTagName('script')[0];
            firstScript.parentNode.insertBefore(scriptElem, firstScript);
        }

        this.addScriptCode = function (srcCode, parentElement) {
            var scriptElem = parentElement.ownerDocument.createElement("script");
            scriptElem.type = 'text/javascript';
            scriptElem.innerHTML = srcCode;
            parentElement.insertBefore(scriptElem, parentElement.firstChild);
        };

        this.addHtml = function (srcHtml, parentElement) {
            var divElem = parentElement.ownerDocument.createElement("div");
            divElem.style = "display: inline";
            divElem.innerHTML = srcHtml;
            parentElement.insertBefore(divElem, parentElement.firstChild);
        };
    };

    this.resolveMacros = function (str, tag) {
        var viewabilityData = tag.getViewabilityData();
        var viewabilityBuckets = viewabilityData && viewabilityData.buckets ? viewabilityData.buckets : {};
        var upperCaseObj = objectsToUpperCase(tag, viewabilityData, viewabilityBuckets);
        var newStr = str.replace('[DV_PROTOCOL]', upperCaseObj.DV_PROTOCOL);
        newStr = newStr.replace('[PROTOCOL]', upperCaseObj.PROTOCOL);
        newStr = newStr.replace(/\[(.*?)\]/g, function (match, p1) {
            var value = upperCaseObj[p1];
            if (value === undefined || value === null)
                value = '[' + p1 + ']';
            return encodeURIComponent(value);
        });
        return newStr;
    };

    this.settings = new function () {
    };

    this.tagsType = function () {
    };

    this.tagsPrototype = function () {
        this.add = function (tagKey, obj) {
            if (!that.tags[tagKey])
                that.tags[tagKey] = new that.tag();
            for (var key in obj)
                that.tags[tagKey][key] = obj[key];
        };
    };

    this.tagsType.prototype = new this.tagsPrototype();
    this.tagsType.prototype.constructor = this.tags;
    this.tags = new this.tagsType();

    this.tag = function () {
    };
    this.tagPrototype = function () {
        this.set = function (obj) {
            for (var key in obj)
                this[key] = obj[key];
        };

        this.getViewabilityData = function () {
        };
    };

    this.tag.prototype = new this.tagPrototype();
    this.tag.prototype.constructor = this.tag;

    this.getTagObjectByService = function (serviceName) {

        for (var impressionId in this.tags) {
            if (typeof this.tags[impressionId] === 'object'
                && this.tags[impressionId].services
                && this.tags[impressionId].services[serviceName]
                && !this.tags[impressionId].services[serviceName].isProcessed) {
                this.tags[impressionId].services[serviceName].isProcessed = true;
                return this.tags[impressionId];
            }
        }


        return null;
    };

    this.addService = function (impressionId, serviceName, paramsObject) {

        if (!impressionId || !serviceName)
            return;

        if (!this.tags[impressionId])
            return;
        else {
            if (!this.tags[impressionId].services)
                this.tags[impressionId].services = {};

            this.tags[impressionId].services[serviceName] = {
                params: paramsObject,
                isProcessed: false
            };
        }
    };

    this.Enums = {
        BrowserId: {Others: 0, IE: 1, Firefox: 2, Chrome: 3, Opera: 4, Safari: 5},
        TrafficScenario: {OnPage: 1, SameDomain: 2, CrossDomain: 128}
    };

    this.CommonData = {};

    var runSafely = function (action) {
        try {
            var ret = action();
            return ret !== undefined ? ret : true;
        } catch (e) {
            return false;
        }
    };

    var objectsToUpperCase = function () {
        var upperCaseObj = {};
        for (var i = 0; i < arguments.length; i++) {
            var obj = arguments[i];
            for (var key in obj) {
                if (obj.hasOwnProperty(key)) {
                    upperCaseObj[key.toUpperCase()] = obj[key];
                }
            }
        }
        return upperCaseObj;
    };

    var appendCacheBuster = function (url) {
        if (url !== undefined && url !== null && url.match("^http") == "http") {
            if (url.indexOf('?') !== -1) {
                if (url.slice(-1) == '&')
                    url += 'cbust=' + dv_GetRnd();
                else
                    url += '&cbust=' + dv_GetRnd();
            }
            else
                url += '?cbust=' + dv_GetRnd();
        }
        return url;
    };

    
    var messagesClass = function () {
        var waitingMessages = [];

        this.registerMsg = function(dvFrame, data) {
            if (!waitingMessages[dvFrame.$frmId]) {
                waitingMessages[dvFrame.$frmId] = [];
            }

            waitingMessages[dvFrame.$frmId].push(data);

            if (dvFrame.$uid) {
                sendWaitingEventsForFrame(dvFrame, dvFrame.$uid);
            }
        };

        this.startSendingEvents = function(dvFrame, impID) {
            sendWaitingEventsForFrame(dvFrame, impID);
            
        };

        function sendWaitingEventsForFrame(dvFrame, impID) {
            if (waitingMessages[dvFrame.$frmId]) {
                var eventObject = {};
                for (var i = 0; i < waitingMessages[dvFrame.$frmId].length; i++) {
                    var obj = waitingMessages[dvFrame.$frmId].pop();
                    for (var key in obj) {
                        if (typeof obj[key] !== 'function' && obj.hasOwnProperty(key)) {
                            eventObject[key] = obj[key];
                        }
                    }
                }
                that.registerEventCall(impID, eventObject);
            }
        }

        function startMessageManager() {
            for (var frm in waitingMessages) {
                if (frm && frm.$uid) {
                    sendWaitingEventsForFrame(frm, frm.$uid);
                }
            }
            setTimeout(startMessageManager, 10);
        }
    };
    this.messages = new messagesClass();

    this.dispatchRegisteredEventsFromAllTags = function () {
        for (var impressionId in this.tags) {
            if (typeof this.tags[impressionId] !== 'function' && typeof this.tags[impressionId] !== 'undefined')
                dispatchEventCalls(impressionId, this);
        }
    };

    var dispatchEventCalls = function (impressionId, dvObj) {
        var tag = dvObj.tags[impressionId];
        var eventObj = eventsForDispatch[impressionId];
        if (typeof eventObj !== 'undefined' && eventObj != null) {
            var url = 'https://' + tag.ServerPublicDns + "/bsevent.gif?flvr=0&impid=" + impressionId + '&' + createQueryStringParams(eventObj);
            dvObj.domUtilities.addImage(url, tag.tagElement.parentElement);
            eventsForDispatch[impressionId] = null;
        }
    };

    this.registerEventCall = function (impressionId, eventObject, timeoutMs) {
        addEventCallForDispatch(impressionId, eventObject);

        if (typeof timeoutMs === 'undefined' || timeoutMs == 0 || isNaN(timeoutMs))
            dispatchEventCallsNow(this, impressionId, eventObject);
        else {
            if (timeoutMs > 2000)
                timeoutMs = 2000;

            var dvObj = this;
            setTimeout(function () {
                dispatchEventCalls(impressionId, dvObj);
            }, timeoutMs);
        }
    };

    this.createEventCallUrl = function(impId, eventObject) {
        var tag = this.tags && this.tags[impId];
        if (tag && typeof eventObject !== 'undefined' && eventObject !== null) {
            return ['https://', tag.ServerPublicDns, '/bsevent.gif?flvr=0&impid=', impId, '&', createQueryStringParams(eventObject)].join('');
        }
    }

    var dispatchEventCallsNow = function (dvObj, impressionId, eventObject) {
        addEventCallForDispatch(impressionId, eventObject);
        dispatchEventCalls(impressionId, dvObj);
    };

    var addEventCallForDispatch = function (impressionId, eventObject) {
        for (var key in eventObject) {
            if (typeof eventObject[key] !== 'function' && eventObject.hasOwnProperty(key)) {
                if (!eventsForDispatch[impressionId])
                    eventsForDispatch[impressionId] = {};
                eventsForDispatch[impressionId][key] = eventObject[key];
            }
        }
    };

    if (window.addEventListener) {
        window.addEventListener('unload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
        window.addEventListener('beforeunload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
    }
    else if (window.attachEvent) {
        window.attachEvent('onunload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
        window.attachEvent('onbeforeunload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
    }
    else {
        window.document.body.onunload = function () {
            that.dispatchRegisteredEventsFromAllTags();
        };
        window.document.body.onbeforeunload = function () {
            that.dispatchRegisteredEventsFromAllTags();
        };
    }

    var createQueryStringParams = function (values) {
        var params = '';
        for (var key in values) {
            if (typeof values[key] !== 'function') {
                var value = encodeURIComponent(values[key]);
                if (params === '')
                    params += key + '=' + value;
                else
                    params += '&' + key + '=' + value;
            }
        }

        return params;
    };
}


var $jscomp=$jscomp||{};$jscomp.scope={};$jscomp.ASSUME_ES5=!1;$jscomp.ASSUME_NO_NATIVE_MAP=!1;$jscomp.ASSUME_NO_NATIVE_SET=!1;$jscomp.defineProperty=$jscomp.ASSUME_ES5||"function"==typeof Object.defineProperties?Object.defineProperty:function(g,t,v){g!=Array.prototype&&g!=Object.prototype&&(g[t]=v.value)};$jscomp.getGlobal=function(g){return"undefined"!=typeof window&&window===g?g:"undefined"!=typeof global&&null!=global?global:g};$jscomp.global=$jscomp.getGlobal(this);$jscomp.SYMBOL_PREFIX="jscomp_symbol_";
$jscomp.initSymbol=function(){$jscomp.initSymbol=function(){};$jscomp.global.Symbol||($jscomp.global.Symbol=$jscomp.Symbol)};$jscomp.Symbol=function(){var g=0;return function(t){return $jscomp.SYMBOL_PREFIX+(t||"")+g++}}();
$jscomp.initSymbolIterator=function(){$jscomp.initSymbol();var g=$jscomp.global.Symbol.iterator;g||(g=$jscomp.global.Symbol.iterator=$jscomp.global.Symbol("iterator"));"function"!=typeof Array.prototype[g]&&$jscomp.defineProperty(Array.prototype,g,{configurable:!0,writable:!0,value:function(){return $jscomp.arrayIterator(this)}});$jscomp.initSymbolIterator=function(){}};$jscomp.arrayIterator=function(g){var t=0;return $jscomp.iteratorPrototype(function(){return t<g.length?{done:!1,value:g[t++]}:{done:!0}})};
$jscomp.iteratorPrototype=function(g){$jscomp.initSymbolIterator();g={next:g};g[$jscomp.global.Symbol.iterator]=function(){return this};return g};$jscomp.iteratorFromArray=function(g,t){$jscomp.initSymbolIterator();g instanceof String&&(g+="");var v=0,w={next:function(){if(v<g.length){var B=v++;return{value:t(B,g[B]),done:!1}}w.next=function(){return{done:!0,value:void 0}};return w.next()}};w[Symbol.iterator]=function(){return w};return w};
$jscomp.polyfill=function(g,t,v,w){if(t){v=$jscomp.global;g=g.split(".");for(w=0;w<g.length-1;w++){var B=g[w];B in v||(v[B]={});v=v[B]}g=g[g.length-1];w=v[g];t=t(w);t!=w&&null!=t&&$jscomp.defineProperty(v,g,{configurable:!0,writable:!0,value:t})}};$jscomp.polyfill("Array.prototype.keys",function(g){return g?g:function(){return $jscomp.iteratorFromArray(this,function(g){return g})}},"es6","es3");
function dv_baseHandler(){function g(a){var b=window._dv_win,e=0;try{for(;10>e;){if(b[a]&&"object"===typeof b[a])return!0;if(b==b.parent)break;e++;b=b.parent}}catch(d){}return!1}function t(a){var b=0,e;for(e in a)a.hasOwnProperty(e)&&++b;return b}function v(a,b){a:{var e={};try{if(a&&a.performance&&a.performance.getEntries){var d=a.performance.getEntries();for(a=0;a<d.length;a++){var c=d[a],n=c.name.match(/.*\/(.+?)\./);if(n&&n[1]){var f=n[1].replace(/\d+$/,""),h=b[f];if(h){for(var g=0;g<h.stats.length;g++){var r=
h.stats[g];e[h.prefix+r.prefix]=Math.round(c[r.name])}delete b[f];if(!t(b))break}}}}var l=e;break a}catch(D){}l=void 0}if(l&&t(l))return l}function w(a,b){function e(a){var c=r,b;for(b in a)a.hasOwnProperty(b)&&(c+=["&"+b,"="+a[b]].join(""));return c}function d(){return Date.now?Date.now():(new Date).getTime()}function c(c){if(!t){t=!0;var b={};b[q.MONITORING_INIT]=1;b[q.DVM_INJECT_FLOW]=c;c=e(b);$dvbs.domUtilities.addImage(c,document.body);try{c="https://cdn.doubleverify.com/dvtp_src.js#tagtype=video";
b="ctx cmp plc sid adsrv adid crt advid prr dup turl iframe ad vssd apifw vstvr tvcp ppid auip pltfrm gdpr gdpr_consent adu invs litm ord sadv scrt vidreg seltag splc spos sup unit dvtagver msrapi vfwctx auprice audeal auevent auadv aucmp aucrtv auorder ausite auplc auxch audvc aulitem auadid autt c1 c2 c3 c4 c5 c6 c7 c8 c9 c10 c11 c12 c13 c14 c15 aufilter1 aufilter2 ppid".split(" ");for(var f=0;f<b.length;f++){var n=b[f],l=h(a,n);void 0!==l&&(c+=["&",n,"=",encodeURIComponent(l)].join(""))}c+="&gmnpo="+
("1"==a.gmnpo?"1":"0");c+="&dvp_dvtpts="+d();c+="&bsimpid="+g;void 0!==a.dvp_aubndl&&(c+="&aubndl="+encodeURIComponent(a.dvp_aubndl));for(var m in a)a.hasOwnProperty(m)&&m.match(/^(dvp|dvpx|tde)_/i)&&a[m]&&(c+=["&",m.toLocaleLowerCase(),"=",encodeURIComponent(a[m])].join(""));$dvbs.domUtilities.addScriptResource(c,document.body)}catch(P){n=e({cerrt:y.INJECT_SCRIPT_ERROR,cemsg:P}),$dvbs.domUtilities.addImage(n,document.body)}}}function n(a){var c={};c[a]=d();a=e(c);$dvbs.domUtilities.addImage(a,document.body)}
function f(a,c){-1<x.indexOf(a)?n(c):k.subscribe(function(){n(c)},a)}function h(a,c){c=c.toLowerCase();for(var b in a)if(b.toLowerCase()===c)return a[b];return null}var g=a.impressionId,r=window._dv_win.dv_config.bsEventJSURL?window._dv_win.dv_config.bsEventJSURL:"https://"+a.ServerPublicDns+"/bsevent.gif";r+="?flvr=0&impid="+encodeURIComponent(g);var l={};l[q.VPAID_FLOW_INIT]=1;var D=e(l),u="responseReceived_"+g,m=a.DVP_DCB||a.DVP_DECISION_CALLBACK,p=h(a,"adid"),E=function(a){var c=a;switch(a){case 5:c=
1;break;case 6:c=2}return c}(b.ResultID),t=!1;$dvbs.domUtilities.addImage(D,document.body);if("function"===typeof window[m]){var w=!1;setTimeout(function(){var a={};a[q.WAS_CALLBACK_CALLED]=w;a=e(a);$dvbs.domUtilities.addImage(a,document.body)},1E3);window[u]=function(a,b,f,n,l,h,m){w=!0;try{if(m){var k={};k[q.CB_STATE]=m;var g=e(k);$dvbs.domUtilities.addImage(g,document.body)}else{k={};k[q.PARTNER_CB_CALLED]=d();g=e(k);$dvbs.domUtilities.addImage(g,document.body);k={};k[q.C_START_TIMESTAMP]=f;k[q.C_END_TIMESTAMP]=
n;k[q.D_RECEIVED_TIMESTAMP]=l;k[q.WAS_AD_PLAYED]=a;f=k;n="";for(var p in f)f.hasOwnProperty(p)&&(n+=["&"+p,"="+f[p]].join(""));var r=n;p=E;if(!b)switch(p=2,E){case 1:var u=21;break;case 2:u=20;break;case 3:u=22;break;case 4:u=23}p={bres:p};p[q.BLOCKING_DECISION_USED]=b?"1":"0";u&&(p.breason=u);g=e(p)+r;$dvbs.domUtilities.addImage(g,document.body,h);a&&!K()&&c("adplayed")}}catch(Q){a=e({cerrt:y.INNOVID_CALLBACK_INIT_EXCEPTION,cemsg:Q}),$dvbs.domUtilities.addImage(a,document.body)}};try{l={};l[q.VPAID_CB_CALLED]=
d();var v=e(l);$dvbs.domUtilities.addImage(v,document.body);window[m](E,u)}catch(F){b=e({cerrt:y.INNOVID_CALLBACK_EX_ERR,cemsg:F}),$dvbs.domUtilities.addImage(b,document.body)}}else b=e({cerrt:y.INNOVID_CALLBACK_MISSING}),$dvbs.domUtilities.addImage(b,document.body);try{var k=window[p]();if(k.getPreviousEvents&&"function"===typeof k.getPreviousEvents){l={};l[q.SUBSCRIBED_TO_ADSTART]=d();v=e(l);$dvbs.domUtilities.addImage(v,document.body);var x=k.getPreviousEvents(),C=0;-1<x.indexOf("AdStarted")?(C=
1,c("ad_started_triggered")):k.subscribe(function(){return c("ad_started_subscribe")},"AdStarted");l={};l[q.AD_STARTED]=C;l[q.PREVIOUS_EVENTS]=x;v=e(l);$dvbs.domUtilities.addImage(v,document.body);f("AdError",q.AD_ERROR);f("AdStopped",q.AD_STOPPED);f("AdVideoStart",q.AD_VIDEO_START);f("AdImpression",q.AD_IMPRESSION)}else C=e({cerrt:y.INNOVID_CALLBACK_NOT_A_FUNCTION,cemsg:"vpaidWrapper.getPreviousEvents not a function"}),$dvbs.domUtilities.addImage(C,document.body)}catch(F){l={cerrt:y.INNOVID_CALLBACK_EXCEPTION,
cemsg:F},l[q.AD_ID]=p,l[q.AD_ID_TYPE]=typeof window[p],C=e(l),$dvbs.domUtilities.addImage(C,document.body)}}function B(a,b){try{$dvbs.registerEventCall(a,{dvp_te_exec:R.RTN}),Object.keys(b).length&&b.forEach(function(b){b.actions&&b.actions.length?b.actions.forEach(function(d){$dvbs.pubSub.subscribe(b.eventName,a,"RTN_"+b.eventName,function(){d.url&&d.actionType&&("image"===d.actionType?navigator.sendBeacon(d.url):"javascript"===d.actionType&&$dvbs.domUtilities.addScriptResource(d.url,document.body))})}):
$dvbs.registerEventCall(a,{dvp_rtnError:1,dvp_errMsg:"Malformed or empty RTN object"})})}catch(e){$dvbs.registerEventCall(a,{dvp_rtnError:1,dvp_errMsg:encodeURIComponent(e.message)})}}function S(a){var b,e=window._dv_win.document.visibilityState;window[a.tagObjectCallbackName]=function(d){var c=window._dv_win.$dvbs;c&&(b=d.ImpressionID,c.tags.add(d.ImpressionID,a),c.tags[d.ImpressionID].set({tagElement:a.script,impressionId:d.ImpressionID,dv_protocol:a.protocol,protocol:"https:",uid:a.uid,serverPublicDns:d.ServerPublicDns,
ServerPublicDns:d.ServerPublicDns}),a.script&&a.script.dvFrmWin&&(a.script.dvFrmWin.$uid=d.ImpressionID,c.messages&&c.messages.startSendingEvents&&c.messages.startSendingEvents(a.script.dvFrmWin,d.ImpressionID)),function(){function a(){"prerender"===e&&"prerender"!==c&&"unloaded"!==c&&(e=c,window._dv_win.$dvbs.registerEventCall(d.ImpressionID,{prndr:0}),window._dv_win.document.removeEventListener(b,a))}var c=window._dv_win.document.visibilityState;if("prerender"===e)if("prerender"!==window._dv_win.document.visibilityState&&
"unloaded"!==c)window._dv_win.$dvbs.registerEventCall(d.ImpressionID,{prndr:0});else{var b;"undefined"!==typeof window._dv_win.document.hidden?b="visibilitychange":"undefined"!==typeof window._dv_win.document.mozHidden?b="mozvisibilitychange":"undefined"!==typeof window._dv_win.document.msHidden?b="msvisibilitychange":"undefined"!==typeof window._dv_win.document.webkitHidden&&(b="webkitvisibilitychange");window._dv_win.document.addEventListener(b,a,!1)}}());if("1"!=a.foie)try{var n=v(window,{verify:{prefix:"vf",
stats:[{name:"duration",prefix:"dur"}]}});n&&window._dv_win.$dvbs.registerEventCall(d.ImpressionID,n)}catch(f){}};window[a.callbackName]=function(d){z.setIsJSONPCalled(!0);var c=window._dv_win.$dvbs&&"object"==typeof window._dv_win.$dvbs.tags[b]?window._dv_win.$dvbs.tags[b]:a;var e=window._dv_win.dv_config.bs_renderingMethod||function(a){document.write(a)};"2"!=c.tagformat||void 0===c.DVP_DCB&&void 0===c.DVP_DECISION_CALLBACK||w(c,d);switch(d.ResultID){case 1:c.tagPassback?e(c.tagPassback):d.Passback?
e(decodeURIComponent(d.Passback)):d.AdWidth&&d.AdHeight&&e(decodeURIComponent("%3Cdiv%20style%3D%22display%3A%20flex%3B%20align-items%3A%20center%3B%20justify-content%3A%20center%3B%20width%3A%20"+d.AdWidth+"px%3B%20height%3A%20"+d.AdHeight+"px%3B%20outline-offset%3A%20-1px%3B%20background%3A%20url('data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAADoAAAA6CAYAAAGWvHq%2BAAAABmJLR0QA%2FwD%2FAP%2BgvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH5AQBECEbFuFN7gAAAB1pVFh0Q29tbWVudAAAAAAAQ3JlYXRlZCB3aXRoIEdJTVBkLmUHAAAFBklEQVRo3uUby3arOEzxAbxIyKLt%2F%2F9gIQtIF4aFZ5ERVxhJyIbcnjmjTZLast4vQ%2BG762OMMX53fQzTFIfxGenfKvgXvj4%2FoOsfy3eECgBgmmcYhnFZ6PoHeO%2F%2FbBiGEQAAxufPghlC%2BLOBYqa%2FHezAJcYYOUz87QAA7vd2g4lMAsrLfQJ%2BQeUM43PZsMJEwN8L58gMfgIAAMVKv6syX4bxGVF9qTiuvV2Byouf7e0Kl%2B%2Buj6kJU8aktV07aFClTkThfm9hGMbNGu53dCNd%2FPr8gBCm5TsnAivz%2BPwBqkHvPaDiVvpAW6Nh0FBhmpagSdfQV0Q7oVySPrz3LyO3t%2BvCKrJIHTtdG58%2FvLycZk%2Bzr1uFkgFWuYHKZHHNEMIr4lMb0pO5v7e3qyyj983KATYydv1jswFZneZ5wzaKVaEMVnNgjsw2B8pcbMdLmKbY1PVG5dTl0rVpnsGlSDReOcfo%2Bgc0df3SagrTPC8m4aDrH1ClaR4AgHKRmgN%2FL9HBbeI4wdKVitXUtYpLGXPSgpUg1lBaPzWCWW6wJ4lkB9aFUL1pQkXOvW9WBDltULNM8wwhTEtIcQn88t31kdpEU7FmOwsemqiiqtPsQvufXMCmCulUSKy9XaG9XYGrLhbv1iSlWU0NGdyQqlPKBHQfh0vxVkQ1abSQybX3oQ7nUPWUpEQ1oaokLVAnSfG4cy8xxpjrEFyVtuCJNt3rETDgu%2F6xiT9zRqKSci0DxzHdZ5E0zXabjGTtwSxr9FyqjazSJkmTi%2Bckb01BS5HaGnems%2BZWzdb62qQTfQdwDDl2Wj0RuKnYpX1sDrJljcvHTqow4%2FNn5SBNXYuzPD0Y8agDsRlpr3NIg1vyYGnSS%2BPUURVIcRhC2A0ZyYPxTKqNyuo8IYRlpMSGLYRJDRdOYyEEqEpDIIfY5qYhhLBrL0s%2BLS7imqq995tijYVdCxlx0EMnaW9XlvD93m4aZ0s4cZ3gqspYOjppRKcMcXipGZyU7Ju63iXIhVOKx53trCWqtMpwZzor8n%2BqynBnnlJlNGa5M51VSmlksBSDlOHlKk%2FzUq0KcVVEYgidytz3coS19lPrFh1y2fUP1Xu1HKsRxHWakao9hLNglZHeESaal3vvocKx3zKP7BXnLJtaxgNkjKY1Wp1y7inYUVG7Akg79vSeKefKwHJ1kEtTikBxJrYkmpIBr1TgPdgbrZ1WkPbuz84UEiNZG1ZLhdydE0sqeqlytGG2pEt4%2B0Ccc9H8zs4kS1Br0542F0fqR0lesOCwyehoIioZq86gqcWq6XbZwrTGqMSAhmOhKWVpjp74PObIsLt3R3g0g1oETs8R32woFbLEHUuEs9CiZa6SslZJmpcuf%2F4GcNc0tDf9lYcxvwGVrI3mkDVeY0NjbumOui9XCtkYlZJIbjt3pF8tzQ0czZTvTXnJSdlHSstRXAlPUpQ4vRy1TK4nnNEwaDTd2ZNE6fQSQiieevBiprjXLamjpco5Mv1YSuH%2Fpry4o%2BMPN70cgZI4tYyG7h3J4evzI1tJ%2BIynBLTHMdnlpXQKsTQCkoAaPakZEctL%2BpbK0Y7FMkloCnrXHMsKileMpS0ZR3zvveez2kDJG6szRiSuJqaulfbOaQJ5KfcYH5wnLK82v2uMCmHaPDz%2BDVj%2BfSNNBGdZmIu9v6EIKWbVZHTmVYrl9clSRVsS0urOKDdlW1J%2B6SubFoH3SiF13X8A3uobUgsAG3MAAAAASUVORK5CYII%3D')%20repeat%3B%20outline%3A%20solid%201px%20%23969696%3B%22%3E%3C%2Fdiv%3E"));
break;case 2:case 3:c.tagAdtag&&e(c.tagAdtag);break;case 4:d.AdWidth&&d.AdHeight&&e(decodeURIComponent("%3Cstyle%3E%0A.dvbs_container%20%7B%0A%09border%3A%201px%20solid%20%233b599e%3B%0A%09overflow%3A%20hidden%3B%0A%09filter%3A%20progid%3ADXImageTransform.Microsoft.gradient(startColorstr%3D%27%23315d8c%27%2C%20endColorstr%3D%27%2384aace%27)%3B%0A%7D%0A%3C%2Fstyle%3E%0A%3Cdiv%20class%3D%22dvbs_container%22%20style%3D%22width%3A%20"+d.AdWidth+"px%3B%20height%3A%20"+d.AdHeight+"px%3B%22%3E%09%0A%3C%2Fdiv%3E"))}d.ServerContext&&
d.ServerContext.rtn&&d.ServerContext.rtn.events&&B(b,d.ServerContext.rtn.events)}}function T(a){var b=null,e=null,d=function(a){var c=dv_GetParam(a,"cmp");a=dv_GetParam(a,"ctx");return"919838"==a&&"7951767"==c||"919839"==a&&"7939985"==c||"971108"==a&&"7900229"==c||"971108"==a&&"7951940"==c?"</scr'+'ipt>":/<\/scr\+ipt>/g}(a.src);"function"!==typeof String.prototype.trim&&(String.prototype.trim=function(){return this.replace(/^\s+|\s+$/g,"")});var c=function(a){!(a=a.previousSibling)||"#text"!=a.nodeName||
null!=a.nodeValue&&void 0!=a.nodeValue&&0!=a.nodeValue.trim().length||(a=a.previousSibling);if(a&&"SCRIPT"==a.tagName&&a.getAttribute("type")&&("text/adtag"==a.getAttribute("type").toLowerCase()||"text/passback"==a.getAttribute("type").toLowerCase())&&""!=a.innerHTML.trim()){if("text/adtag"==a.getAttribute("type").toLowerCase())return b=a.innerHTML.replace(d,"\x3c/script>"),{isBadImp:!1,hasPassback:!1,tagAdTag:b,tagPassback:e};if(null!=e)return{isBadImp:!0,hasPassback:!1,tagAdTag:b,tagPassback:e};
e=a.innerHTML.replace(d,"\x3c/script>");a=c(a);a.hasPassback=!0;return a}return{isBadImp:!0,hasPassback:!1,tagAdTag:b,tagPassback:e}};return c(a)}function K(){try{if("object"==typeof window.$ovv||"object"==typeof window.parent.$ovv)return 1}catch(a){}return 0}function L(a,b,e,d,c,n,f,h,J,r,l,D){b.dvregion=0;var u=dv_GetParam(k,"useragent");k=window._dv_win.$dvbs.CommonData;if(void 0!=k.BrowserId&&void 0!=k.BrowserVersion&&void 0!=k.BrowserIdFromUserAgent)var m={ID:k.BrowserId,version:k.BrowserVersion,
ID_UA:k.BrowserIdFromUserAgent};else m=U(u?decodeURIComponent(u):navigator.userAgent),k.BrowserId=m.ID,k.BrowserVersion=m.version,k.BrowserIdFromUserAgent=m.ID_UA;var p="";void 0!=b.aUrl&&(p="&aUrl="+b.aUrl);var t="";try{d.depth=V(d);var q=W(d,e,m);if(q&&q.duration){var v="&dvp_strhd="+q.duration;v+="&dvpx_strhd="+q.duration}q&&q.url||(q=X(d));q&&q.url&&(p="&aUrl="+encodeURIComponent(q.url),t="&aUrlD="+q.depth);var w=d.depth+c;n&&d.depth--}catch(C){v=t=p=w=d.depth=""}c=K();n=function(){function a(c){b++;
var d=c.parent==c;return c.mraid||d?c.mraid:20>=b&&a(c.parent)}var c=window._dv_win||window,b=0;try{return a(c)}catch(ia){}}();var k=b.script.src;c="&ctx="+(dv_GetParam(k,"ctx")||"")+"&cmp="+(dv_GetParam(k,"cmp")||"")+"&plc="+(dv_GetParam(k,"plc")||"")+"&sid="+(dv_GetParam(k,"sid")||"")+"&advid="+(dv_GetParam(k,"advid")||"")+"&adsrv="+(dv_GetParam(k,"adsrv")||"")+"&unit="+(dv_GetParam(k,"unit")||"")+"&isdvvid="+(dv_GetParam(k,"isdvvid")||"")+"&uid="+b.uid+"&tagtype="+(dv_GetParam(k,"tagtype")||"")+
"&adID="+(dv_GetParam(k,"adID")||"")+"&app="+(dv_GetParam(k,"app")||"")+"&sup="+(dv_GetParam(k,"sup")||"")+"&isovv="+c+"&gmnpo="+(dv_GetParam(k,"gmnpo")||"")+"&crt="+(dv_GetParam(k,"crt")||"");c+="&dvp_rdyst="+document.readyState+"&dvp_intrst=1&dvp_ttp="+(dv_GetParam(k,"dvp_ttp")||"");"1"==dv_GetParam(k,"foie")&&(c+="&foie=1");n&&(c+="&ismraid=1");(n=dv_GetParam(k,"xff"))&&(c+="&xff="+n);(n=dv_GetParam(k,"vssd"))&&(c+="&vssd="+n);(n=dv_GetParam(k,"apifw"))&&(c+="&apifw="+n);(n=dv_GetParam(k,"vstvr"))&&
(c+="&vstvr="+n);(n=dv_GetParam(k,"tvcp"))&&(c+="&tvcp="+n);l&&(c+="&urlsrc=sf");D&&(c+="&sfe=1");navigator&&navigator.maxTouchPoints&&5==navigator.maxTouchPoints&&(c+="&touch=1");navigator&&navigator.platform&&(c+="&nav_pltfrm="+navigator.platform);v&&(c+=v);u&&(c+="&useragent="+u);m&&(c+="&brid="+m.ID+"&brver="+m.version+"&bridua="+m.ID_UA);c+="&dup="+dv_GetParam(k,"dup");try{c+=dv_AppendIQPAParams(k)}catch(C){}(l=dv_GetParam(k,"turl"))&&(c+="&turl="+l);(l=dv_GetParam(k,"tagformat"))&&(c+="&tagformat="+
l);"video"===dv_GetParam(k,"tagtype")&&(c+="&DVP_BYPASS219=1");c+=Y();r=r?"&dvf=0":"";l=g("maple")?"&dvf=1":"";f=(window._dv_win.dv_config.verifyJSURL||"https://"+(window._dv_win.dv_config.bsAddress||"rtb"+b.dvregion+".doubleverify.com")+"/verify.js")+"?flvr=0&jsCallback="+b.callbackName+"&jsTagObjCallback="+b.tagObjectCallbackName+"&num=6"+c+"&srcurlD="+d.depth+"&ssl="+b.ssl+r+l+"&refD="+w+b.tagIntegrityFlag+b.tagHasPassbackFlag+"&htmlmsging="+(f?"1":"0")+"&tstype="+M(window._dv_win);(w=dv_GetDynamicParams(k,
"dvp").join("&"))&&(f+="&"+w);(w=dv_GetDynamicParams(k,"dvpx").join("&"))&&(f+="&"+w);if(!1===h||J)f=f+("&dvp_isBodyExistOnLoad="+(h?"1":"0"))+("&dvp_isOnHead="+(J?"1":"0"));e="srcurl="+encodeURIComponent(e);(h=Z())&&(e+="&ancChain="+encodeURIComponent(h));(d=aa(d))&&(e+="&canurl"+encodeURIComponent(d));d=4E3;/MSIE (\d+\.\d+);/.test(navigator.userAgent)&&7>=new Number(RegExp.$1)&&(d=2E3);if(h=dv_GetParam(k,"referrer"))h="&referrer="+h,f.length+h.length<=d&&(f+=h);(h=dv_GetParam(k,"prr"))&&(f+="&prr="+
h);(h=dv_GetParam(k,"iframe"))&&(f+="&iframe="+h);(h=dv_GetParam(k,"gdpr"))&&(f+="&gdpr="+h);(h=dv_GetParam(k,"gdpr_consent"))&&(f+="&gdpr_consent="+h);p.length+t.length+f.length<=d&&(f+=t,e+=p);(p=ba())&&(f+="&m1="+p);(p=ca())&&0<p.f&&(f+="&bsig="+p.f,f+="&usig="+p.s);p=da();0<p&&(f+="&hdsig="+p);navigator&&navigator.hardwareConcurrency&&(f+="&noc="+navigator.hardwareConcurrency);f+=ea();p=fa();f+="&vavbkt="+p.vdcd;f+="&lvvn="+p.vdcv;""!=p.err&&(f+="&dvp_idcerr="+encodeURIComponent(p.err));"prerender"===
window._dv_win.document.visibilityState&&(f+="&prndr=1");(k=dv_GetParam(k,"wrapperurl"))&&1E3>=k.length&&f.length+k.length<=d&&(f+="&wrapperurl="+k);f+="&"+a.getVersionParamName()+"="+a.getVersion();a="&eparams="+encodeURIComponent(G(e));f=f.length+a.length<=d?f+a:f+"&dvf=3";window.performance&&window.performance.mark&&window.performance.measure&&window.performance.getEntriesByName&&(window.performance.mark("dv_create_req_end"),window.performance.measure("dv_creqte_req","dv_create_req_start","dv_create_req_end"),
(a=window.performance.getEntriesByName("dv_creqte_req"))&&0<a.length&&(f+="&dvp_exetime="+a[0].duration.toFixed(2)));for(var x in b)b.hasOwnProperty(x)&&void 0!==b[x]&&-1<["number","string"].indexOf(typeof b[x])&&-1===["protocol","callbackName","dvregion"].indexOf(x.toLowerCase())&&!x.match(/^tag[A-Z]/)&&!(new RegExp("(\\?|&)"+x+"=","gi")).test(f)&&(f+=["&",x,"=",encodeURIComponent(b[x])].join(""));return{isSev1:!1,url:f}}function Y(){var a="";try{var b=window._dv_win.parent;a+="&chro="+(void 0===
b.chrome?"0":"1");a+="&hist="+(b.history?b.history.length:"");a+="&winh="+b.innerHeight;a+="&winw="+b.innerWidth;a+="&wouh="+b.outerHeight;a+="&wouw="+b.outerWidth;b.screen&&(a+="&scah="+b.screen.availHeight,a+="&scaw="+b.screen.availWidth)}catch(e){}return a}function fa(){var a=[],b=function(a){d(a,null!=a.AZSD,9);d(a,a.location.hostname!=a.encodeURIComponent(a.location.hostname),10);d(a,null!=a.cascadeWindowInfo,11);d(a,null!=a._rvz,32);d(a,null!=a.FO_DOMAIN,34);d(a,null!=a.va_subid,36);d(a,a._GPL&&
a._GPL.baseCDN,40);d(a,e(a,"__twb__")&&e(a,"__twb_cb_"),43);d(a,null!=a.landingUrl&&null!=a.seList&&null!=a.parkingPPCTitleElements&&null!=a.allocation,45);d(a,e(a,"_rvz",function(a){return null!=a.publisher_subid}),46);d(a,null!=a.cacildsFunc&&null!=a.n_storesFromFs,47);d(a,a._pcg&&a._pcg.GN_UniqueId,54);d(a,e(a,"__ad_rns_")&&e(a,"_$_"),64);d(a,null!=a.APP_LABEL_NAME_FULL_UC,71);d(a,null!=a._priam_adblock,81);d(a,a.supp_ads_host&&a.supp_ads_host_overridden,82);d(a,a.uti_xdmsg_manager&&a.uti_xdmsg_manager.cb,
87);d(a,a.LogBundleData&&a.addIframe,91);d(a,a.xAdsXMLHelperId||a.xYKAffSubIdTag,95);d(a,a.__pmetag&&a.__pmetag.uid,98);d(a,a.CustomWLAdServer&&/(n\d{1,4}adserv)|(1ads|cccpmo|epommarket|epmads|adshost1)/.test(a.supp_ads_host_overridden),100)},e=function(a,b,d){for(var c in a)if(-1<c.indexOf(b)&&(!d||d(a[c])))return!0;return!1},d=function(b,d,e){d&&-1==a.indexOf(e)&&a.push((b==window.top?-1:1)*e)};try{return function(){for(var a=window,d=0;10>d&&(b(a),a!=window.top);d++)try{a.parent.document&&(a=a.parent)}catch(f){break}}(),
{vdcv:28,vdcd:a.join(","),err:void 0}}catch(c){return{vdcv:28,vdcd:"-999",err:c.message||"unknown"}}}function V(a){for(var b=0;10>b&&a!=window._dv_win.top;)b++,a=a.parent;return b}function M(a){if(a==window._dv_win.top)return $dvbs.Enums.TrafficScenario.OnPage;try{for(var b=0;window._dv_win.top!=a&&10>=b;){var e=a.parent;if(!e.document)break;a=e;b++}}catch(d){}return a==window._dv_win.top?$dvbs.Enums.TrafficScenario.SameDomain:$dvbs.Enums.TrafficScenario.CrossDomain}function W(a,b,e){try{if(e.ID==
$dvbs.Enums.BrowserId.IE||M(a)!=$dvbs.Enums.TrafficScenario.CrossDomain)return null;a.performance&&a.performance.mark&&a.performance.mark("dv_str_html_start");if(b){var d=b.toString().match(/^(?:https?:\/\/)?[\w\-\.]+\/[a-zA-Z0-9]/gi);if(d&&0<d.length)return null}var c=a.document;if(c&&c.referrer){var n=c.referrer.replace(/\//g,"\\/").replace(/\./g,"\\."),f=new RegExp('(?:w{0,4}=")?'+n+"[^&\"; %,'\\$\\\\\\|]+","gi");b=/banner|adprefs|safeframe|sandbox|sf\.html/gi;e=/^\w{0,4}="/gi;var h=N(c,"script",
"src",f,b,e);if(!h){var g=c.referrer;d="";var r=c.getElementsByTagName("script");if(r)for(n=0;!d&&n<r.length;){var l=r[n].innerHTML;if(l&&-1!=l.indexOf(g)){var D=l.match(f);d=O(D,b,e)}n++}(h=d)||(h=N(c,"a","href",f,b,e))}var u=h;a:{if(a.performance&&a.performance.mark&&a.performance.measure&&a.performance.getEntriesByName){a.performance.mark("dv_str_html_end");a.performance.measure("dv_str_html","dv_str_html_start","dv_str_html_end");var m=a.performance.getEntriesByName("dv_str_html");if(m&&0<m.length){var p=
m[0].duration.toFixed(2);break a}}p=null}return{url:u,depth:-1,duration:p}}}catch(E){}return null}function O(a,b,e){var d="";if(a&&0<a.length)for(var c=0;c<a.length;c++){var g=a[c];g.length>d.length&&null==g.match(b)&&0!=g.indexOf('src="')&&0!=g.indexOf('turl="')&&(d=g.replace(e,""))}return d}function N(a,b,e,d,c,g){a=a.querySelectorAll(b+"["+e+'*="'+a.referrer+'"]');var f="";if(a)for(b=0;!f&&b<a.length;)f=a[b][e].match(d),f=O(f,c,g),b++;return f}function X(a){try{if(1>=a.depth)return{url:"",depth:""};
var b=[];b.push({win:window._dv_win.top,depth:0});for(var e,d=1,c=0;0<d&&100>c;){try{if(c++,e=b.shift(),d--,0<e.win.location.toString().length&&e.win!=a)return 0==e.win.document.referrer.length||0==e.depth?{url:e.win.location,depth:e.depth}:{url:e.win.document.referrer,depth:e.depth-1}}catch(h){}var g=e.win.frames.length;for(var f=0;f<g;f++)b.push({win:e.win.frames[f],depth:e.depth+1}),d++}return{url:"",depth:""}}catch(h){return{url:"",depth:""}}}function Z(){var a=window._dv_win[G("=@42E:@?")][G("2?46DE@C~C:8:?D")];
if(a&&0<a.length){var b=[];b[0]=window._dv_win.location.protocol+"//"+window._dv_win.location.hostname;for(var e=0;e<a.length;e++)b[e+1]=a[e];return b.reverse().join(",")}return null}function aa(a){return(a=a.document.querySelector("link[rel=canonical]"))?a.href:null}function G(a){new String;var b=new String,e;for(e=0;e<a.length;e++){var d=a.charAt(e);var c="!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".indexOf(d);0<=c&&(d="!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".charAt((c+
47)%94));b+=d}return b}function H(){return Math.floor(1E12*(Math.random()+""))}function U(a){for(var b=[{id:4,brRegex:"OPR|Opera",verRegex:"(OPR/|Version/)"},{id:1,brRegex:"MSIE|Trident/7.*rv:11|rv:11.*Trident/7|Edge/|Edg/",verRegex:"(MSIE |rv:| Edge/|Edg/)"},{id:2,brRegex:"Firefox",verRegex:"Firefox/"},{id:0,brRegex:"Mozilla.*Android.*AppleWebKit(?!.*Chrome.*)|Linux.*Android.*AppleWebKit.* Version/.*Chrome",verRegex:null},{id:0,brRegex:"AOL/.*AOLBuild/|AOLBuild/.*AOL/|Puffin|Maxthon|Valve|Silk|PLAYSTATION|PlayStation|Nintendo|wOSBrowser",
verRegex:null},{id:3,brRegex:"Chrome",verRegex:"Chrome/"},{id:5,brRegex:"Safari|(OS |OS X )[0-9].*AppleWebKit",verRegex:"Version/"}],e=0,d="",c=0;c<b.length;c++)if(null!=a.match(new RegExp(b[c].brRegex))){e=b[c].id;if(null==b[c].verRegex)break;a=a.match(new RegExp(b[c].verRegex+"[0-9]*"));null!=a&&(d=a[0].match(new RegExp(b[c].verRegex)),d=a[0].replace(d[0],""));break}b=ha();4==e&&(b=e);return{ID:b,version:b===e?d:"",ID_UA:e}}function ha(){try{if(null!=window._phantom||null!=window.callPhantom)return 99;
if(document.documentElement.hasAttribute&&document.documentElement.hasAttribute("webdriver")||null!=window.domAutomation||null!=window.domAutomationController||null!=window._WEBDRIVER_ELEM_CACHE)return 98;if(void 0!=window.opera&&void 0!=window.history.navigationMode||void 0!=window.opr&&void 0!=window.opr.addons&&"function"==typeof window.opr.addons.installExtension)return 4;if(void 0!=document.uniqueID&&"string"==typeof document.uniqueID&&(void 0!=document.documentMode&&0<=document.documentMode||
void 0!=document.all&&"object"==typeof document.all||void 0!=window.ActiveXObject&&"function"==typeof window.ActiveXObject)||window.document&&window.document.updateSettings&&"function"==typeof window.document.updateSettings||Object.values&&navigator&&Object.values(navigator.plugins).some(function(a){return-1!=a.name.indexOf("Edge PDF")}))return 1;if(void 0!=window.chrome&&"function"==typeof window.chrome.csi&&"function"==typeof window.chrome.loadTimes&&void 0!=document.webkitHidden&&(1==document.webkitHidden||
0==document.webkitHidden))return 3;if(void 0!=window.mozInnerScreenY&&"number"==typeof window.mozInnerScreenY&&void 0!=window.mozPaintCount&&0<=window.mozPaintCount&&void 0!=window.InstallTrigger&&void 0!=window.InstallTrigger.install)return 2;var a=!1;try{var b=document.createElement("p");b.innerText=".";b.style="text-shadow: rgb(99, 116, 171) 20px -12px 2px";a=void 0!=b.style.textShadow}catch(e){}return(0<Object.prototype.toString.call(window.HTMLElement).indexOf("Constructor")||window.webkitAudioPannerNode&&
window.webkitConvertPointFromNodeToPage)&&a&&void 0!=window.innerWidth&&void 0!=window.innerHeight?5:0}catch(e){return 0}}function ba(){try{var a=0,b=function(b,c){c&&32>b&&(a=(a|1<<b)>>>0)},e=function(a,b){return function(){return a.apply(b,arguments)}},d="svg"===document.documentElement.nodeName.toLowerCase(),c=function(){return"function"!==typeof document.createElement?document.createElement(arguments[0]):d?document.createElementNS.call(document,"http://www.w3.org/2000/svg",arguments[0]):document.createElement.apply(document,
arguments)},g=["Moz","O","ms","Webkit"],f=["moz","o","ms","webkit"],h={style:c("modernizr").style},q=function(a,b){function d(a){return a.replace(/([a-z])-([a-z])/g,function(a,b,c){return b+c.toUpperCase()}).replace(/^-/,"")}function e(){l&&(delete h.style,delete h.modElem)}var f;for(f=["modernizr","tspan","samp"];!h.style&&f.length;){var l=!0;h.modElem=c(f.shift());h.style=h.modElem.style}var g=a.length;for(f=0;f<g;f++){var n=a[f];~(""+n).indexOf("-")&&(n=d(n));if(void 0!==h.style[n])return e(),
"pfx"==b?n:!0}e();return!1},r=function(a,b,c){var d=a.charAt(0).toUpperCase()+a.slice(1),l=(a+" "+g.join(d+" ")+d).split(" ");if("string"===typeof b||"undefined"===typeof b)return q(l,b);l=(a+" "+f.join(d+" ")+d).split(" ");for(var h in l)if(l[h]in b){if(!1===c)return l[h];a=b[l[h]];return"function"===typeof a?e(a,c||b):a}return!1};b(0,!0);b(1,r("requestFileSystem",window));b(2,window.CSS?"function"==typeof window.CSS.escape:!1);b(3,r("shapeOutside","content-box",!0));return a}catch(l){return 0}}
function I(){var a=window,b=0;try{for(;a.parent&&a!=a.parent&&a.parent.document&&!(a=a.parent,10<b++););}catch(e){}return a}function ca(){try{var a=I(),b=0,e=0,d=function(a,c,d){d&&(b+=Math.pow(2,a),e+=Math.pow(2,c))},c=a.document;d(14,0,a.playerInstance&&c.querySelector('script[src*="ads-player.com"]'));d(14,1,(a.CustomWLAdServer||a.DbcbdConfig)&&function(){var a=c.querySelector('p[class="footerCopyright"]');return a&&a.textContent.match(/ MangaLife 2016/)}());d(15,2,a.zpz&&a.zpz.createPlayer);d(15,
3,a.vdApp&&a.vdApp.createPlayer);d(15,4,c.querySelector('body>div[class="z-z-z"]'));d(16,5,a.xy_checksum&&a.place_player&&(a.logjwonready&&a.logContentPauseRequested||a.jwplayer));d(17,6,function(){var b=c.querySelector('body>object[id="player"]');return a==a.top&&""==c.title?b&&b.data&&1<b.data.indexOf("jwplayer")&&"visibility: visible;"==b.getAttribute("style"):!1}());d(17,7,c.querySelector('script[src*="sitewebvideo.com"]'));d(17,8,a.InitPage&&a.cef&&a.InitAd);d(17,9,function(){var b=c.querySelector("body>#player");
return a==a.top&&""==c.title?null!=b&&null!=(null!=b.querySelector('div[id*="opti-ad"]')||b.querySelector('iframe[src="about:blank"]')):!1}());d(17,10,function(){var b=c.querySelector('body>div[id="kt_player"]');return a==a.top&&""==c.title&&a.InitAdPlayer?null!=b&&null!=b.querySelector('div[class="flash-blocker"]'):!1}());d(17,11,null!=a.clickplayer&&null!=a.checkRdy2);d(19,12,a.instance&&a.inject&&c.querySelector('path[id="cp-search-0"]'));d(20,13,function(){try{if(a.top==a&&0<a.document.getElementsByClassName("asu").length)for(var b=
a.document.styleSheets,c=0;c<b.length;c++)for(var d=a.document.styleSheets[c].cssRules,e=0;e<d.length;e++)if("div.kk"==d[e].selectorText||"div.asu"==d[e].selectorText)return!0}catch(p){}}());a:{try{var g=null!=c.querySelector('div[id="kt_player"][hiegth]');break a}catch(l){}g=void 0}d(21,14,g);a:{try{var f=a.top==a&&null!=a.document.querySelector('div[id="asu"][class="kk"]');break a}catch(l){}f=void 0}d(22,15,f);a:{try{var h=c.querySelector('object[data*="/VPAIDFlash.swf"]')&&c.querySelector('object[id*="vpaid_video_flash_tester_el"]')&&
c.querySelector('div[id*="desktopSubModal"]');break a}catch(l){}h=void 0}d(25,16,h);var q=navigator.userAgent;if(q&&-1<q.indexOf("Android")&&-1<q.indexOf(" wv)")&&a==window.top){var r=c.querySelector('img[src*="dealsneartome.com"]')||(a.__cads__?!0:!1)||0<c.querySelectorAll('img[src*="/tracker?tag="]').length;d(28,17,r||!1)}return{f:b,s:e}}catch(l){return null}}function da(){try{var a=I(),b=0,e=a.document;a==window.top&&""==e.title&&!e.querySelector("meta[charset]")&&e.querySelector('div[style*="background-image: url("]')&&
(e.querySelector('script[src*="j.pubcdn.net"]')||e.querySelector('span[class="ad-close"]'))&&(b+=Math.pow(2,6));return b}catch(d){return null}}function ea(){try{var a="&fcifrms="+window.top.length;window.history&&(a+="&brh="+window.history.length);var b=I(),e=b.document;if(b==window.top){a+="&fwc="+((b.FB?1:0)+(b.twttr?2:0)+(b.outbrain?4:0)+(b._taboola?8:0));try{e.cookie&&(a+="&fcl="+e.cookie.length)}catch(d){}b.performance&&b.performance.timing&&0<b.performance.timing.domainLookupStart&&0<b.performance.timing.domainLookupEnd&&
(a+="&flt="+(b.performance.timing.domainLookupEnd-b.performance.timing.domainLookupStart));e.querySelectorAll&&(a+="&fec="+e.querySelectorAll("*").length)}return a}catch(d){return""}}var A=this,q={VPAID_FLOW_INIT:"ee_dp_vfinit",VPAID_CB_CALLED:"ee_dp_vcbc",PARTNER_CB_CALLED:"ee_dp_pcbc",SUBSCRIBED_TO_ADSTART:"ee_dp_subadstrt",MONITORING_INIT:"ee_dp_moninit",AD_STARTED:"ee_dp_adstrt",CB_STATE:"ee_dp_cbst",WAS_CALLBACK_CALLED:"ee_dp_wcb",C_START_TIMESTAMP:"ee_dp_cst",C_END_TIMESTAMP:"ee_dp_cet",D_RECEIVED_TIMESTAMP:"ee_dp_drt",
WAS_AD_PLAYED:"ee_dp_wap",AD_ID:"ee_dp_adid",AD_ID_TYPE:"ee_dp_adidt",AD_ERROR:"ee_dp_ader",AD_STOPPED:"ee_dp_adstp",AD_VIDEO_START:"ee_dp_avse",AD_IMPRESSION:"ee_dp_aie",BLOCKING_DECISION_USED:"ee_dp_bdu",PREVIOUS_EVENTS:"ee_dp_prvevnts",DVM_INJECT_FLOW:"ee_dp_dvm_inj"},y={VERIFY_LOAD_JSONP_CALLBACK_FAILED:1,VERIFY_FAILED_TO_LOAD:2,INNOVID_CALLBACK_INIT_EXCEPTION:4,INNOVID_CALLBACK_EXCEPTION:8,INNOVID_CALLBACK_NOT_A_FUNCTION:16,INNOVID_CALLBACK_EX_ERR:32,INNOVID_CALLBACK_MISSING:64,INJECT_SCRIPT_ERROR:128,
INIT_SCRIPT_ERROR:256,AD_RENDERED_UPON_VERIFY_FAILURE:512,SEND_VERIFY_REQUEST_FAILURE:1024,VIDEO_BLOCKING_CALLBACK_ERROR:4096,BLOCKING_MAIN_ERROR:8192},z=function(){function a(a,b,e){var f=[];e&&g.forEach(function(a){var b=dv_GetParam(e,a);""!==b&&null!==b&&f.push(["dvp_"+a,b].join("="))});var h=window&&window._dv_win||{};h=h.dv_config=h.dv_config||{};h=dv_getDVBSErrAddress(h);var l=[c,d].join("=");a=["cerrt",a].join("=");b=["cemsg",b].join("=");h+=["/verify.js?flvr=0&ctx=818052&cmp=1619415&dvp_isLostImp=1&ssl=1",
l,a,b,f.join("&")].join("&");(new Image(1,1)).src="https://"+h}function b(b,c){var d=window._dv_win.dv_config.bs_renderingMethod||function(a){document.write(a)};c="AdRenderedUponVerifyFailure__"+(c||"");if(A&&A.tagParamsObj&&A.tagParamsObj.tagAdtag)try{d(A.tagParamsObj.tagAdtag)}catch(D){c+="__RenderingMethodFailed"}else A?A.tagParamsObj?A.tagParamsObj.tagAdtag||(c+="__HandlerTagParamsObjTagAdtag__Undefined"):c+="__HandlerTagParamsObj__Undefined":c+="__Handler__Undefined";a(y.AD_RENDERED_UPON_VERIFY_FAILURE,
c,b)}var e=!1,d,c,g=["ctx","cmp","plc","sid"],f=[A.constructor&&A.constructor.name||"UKDV","__",H()].join(""),h={onResponse:function(c){e||(a(y.VERIFY_LOAD_JSONP_CALLBACK_FAILED,"VerifyCallbackFailed",c),b(c,"VCF"))},onError:function(c){a(y.VERIFY_FAILED_TO_LOAD,"VerifyFailedToLoad",c);b(c,"VFTL")}};h.reportError=a;h.isJSONPCalled=e;window._dv_win[f]={globalScopeVerifyErrorHandler:h};return{setVersionData:function(a,b){c=a;d=b},setIsJSONPCalled:function(a){e=a},getIsJSONPCalled:function(){return e},
onLoad:dv_onResponse,onError:dv_onError,uniqueKey:f}}();this.createRequest=function(){window.performance&&window.performance.mark&&window.performance.mark("dv_create_req_start");var a=!1,b=window._dv_win,e=0,d=!1,c;try{for(c=0;10>=c;c++)if(null!=b.parent&&b.parent!=b)if(0<b.parent.location.toString().length)b=b.parent,e++,a=!0;else{a=!1;break}else{0==c&&(a=!0);break}}catch(p){a=!1}a:{try{var g=b.$sf;break a}catch(p){}g=void 0}var f=(c=b.location&&b.location.ancestorOrigins)&&c[c.length-1];if(0==b.document.referrer.length)a=
b.location;else if(a)a=b.location;else{a=b.document.referrer;a:{try{var h=b.$sf&&b.$sf.ext&&b.$sf.ext.hostURL&&b.$sf.ext.hostURL();break a}catch(p){}h=void 0}if(h&&(!c||0==h.indexOf(f))){a=h;var q=!0}d=!0}if(!window._dv_win.dvbsScriptsInternal||!window._dv_win.dvbsProcessed||0==window._dv_win.dvbsScriptsInternal.length)return{isSev1:!1,url:null};c=window._dv_win.dv_config&&window._dv_win.dv_config.isUT?window._dv_win.dvbsScriptsInternal[window._dv_win.dvbsScriptsInternal.length-1]:window._dv_win.dvbsScriptsInternal.pop();
h=c.script;this.dv_script_obj=c;this.dv_script=h;window._dv_win.dvbsProcessed.push(c);window._dv_win._dvScripts.push(h);f=h.src;this.dvOther=0;this.dvStep=1;var r=window._dv_win.dv_config?window._dv_win.dv_config.dv_GetRnd?window._dv_win.dv_config.dv_GetRnd():H():H();c=window.parent.postMessage&&window.JSON;var l={};try{for(var t=/[\?&]([^&]*)=([^&#]*)/gi,u=t.exec(f);null!=u;)"eparams"!==u[1]&&(l[u[1]]=u[2]),u=t.exec(f);var m=l}catch(p){m=l}this.tagParamsObj=m;m.perf=this.perf;m.uid=r;m.script=this.dv_script;
m.callbackName="__verify_callback_"+m.uid;m.tagObjectCallbackName="__tagObject_callback_"+m.uid;m.tagAdtag=null;m.tagPassback=null;m.tagIntegrityFlag="";m.tagHasPassbackFlag="";0==(null!=m.tagformat&&"2"==m.tagformat)&&(u=T(m.script),m.tagAdtag=u.tagAdTag,m.tagPassback=u.tagPassback,u.isBadImp?m.tagIntegrityFlag="&isbadimp=1":u.hasPassback&&(m.tagHasPassbackFlag="&tagpb=1"));u=(/iPhone|iPad|iPod|\(Apple TV|iOS|Coremedia|CFNetwork\/.*Darwin/i.test(navigator.userAgent)||navigator.vendor&&"apple, inc."===
navigator.vendor.toLowerCase())&&!window.MSStream;m.protocol="https:";m.ssl="1";f=m;(r=window._dv_win.dvRecoveryObj)?("2"!=f.tagformat&&(r=r[f.ctx]?r[f.ctx].RecoveryTagID:r._fallback_?r._fallback_.RecoveryTagID:1,1===r&&f.tagAdtag?document.write(f.tagAdtag):2===r&&f.tagPassback&&document.write(f.tagPassback)),f=!0):f=!1;if(f)return{isSev1:!0};this.dvStep=2;S(m);h=h&&h.parentElement&&h.parentElement.tagName&&"HEAD"===h.parentElement.tagName;this.dvStep=3;return L(this,m,a,b,e,d,c,!0,h,u,q,g)};this.sendRequest=
function(a){var b=dv_GetParam(a,"tagformat");if(z)try{z.setVersionData(this.getVersionParamName(),this.getVersion()),b&&"2"==b?$dvbs.domUtilities.addScriptResource(a,document.body,z.onLoad,z.onError,z.uniqueKey):dv_sendScriptRequest(a,z.onLoad,z.onError,z.uniqueKey)}catch(e){b&&"2"==b?$dvbs.domUtilities.addScriptResource(a,document.body):dv_sendScriptRequest(a)}else b&&"2"==b?$dvbs.domUtilities.addScriptResource(a,document.body):dv_sendScriptRequest(a);return!0};this.isApplicable=function(){return!0};
this.onFailure=function(){};var R={RTN:1};window.debugScript&&(window.CreateUrl=L);this.getVersionParamName=function(){return"ver"};this.getVersion=function(){return"180"}};


function dvbs_src_main(dvbs_baseHandlerIns, dvbs_handlersDefs) {

    this.bs_baseHandlerIns = dvbs_baseHandlerIns;
    this.bs_handlersDefs = dvbs_handlersDefs;

    this.exec = function () {
        try {
            window._dv_win = (window._dv_win || window);
            window._dv_win.$dvbs = (window._dv_win.$dvbs || new dvBsType());

            window._dv_win.dv_config = window._dv_win.dv_config || {};
            window._dv_win.dv_config.bsErrAddress = window._dv_win.dv_config.bsAddress || 'rtb0.doubleverify.com';

            var errorsArr = (new dv_rolloutManager(this.bs_handlersDefs, this.bs_baseHandlerIns)).handle();
            if (errorsArr && errorsArr.length > 0)
                dv_SendErrorImp(window._dv_win.dv_config.bsErrAddress + '/verify.js?flvr=0&ctx=818052&cmp=1619415&num=6', errorsArr);
        }
        catch (e) {
            try {
                dv_SendErrorImp(window._dv_win.dv_config.bsErrAddress + '/verify.js?flvr=0&ctx=818052&cmp=1619415&num=6&dvp_isLostImp=1', {cemsg: encodeURIComponent(e)});
            } catch (e) {
            }
        }
    };
};


try {
    window._dv_win = window._dv_win || window;
    var dv_baseHandlerIns = new dv_baseHandler();
	

    var dv_handlersDefs = [];
    (new dvbs_src_main(dv_baseHandlerIns, dv_handlersDefs)).exec();
} catch (e) { }