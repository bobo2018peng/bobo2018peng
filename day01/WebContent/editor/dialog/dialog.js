﻿var URLParams=new Object();var dz=document.location.search.substr(1).split('&');for(i=0;i<dz.length;i++){var dq=dz[i].split('=');URLParams[dq[0]]=dq[1];}var config;try{config=dialogArguments.config;}catch(e){try{config=opener.config;}catch(e){}}var lang;try{lang=dialogArguments.lang;}catch(e){try{lang=opener.lang;}catch(e){}}var myBrowser;try{myBrowser=dialogArguments.myBrowser;}catch(e){try{myBrowser=opener.myBrowser;}catch(e){}}function ec(ct,fx){return(URLParams[ct])?URLParams[ct]:fx;};function fO(url){document.write('<scr'+'ipt type="text/javascript" src="'+url+'" onerror="alert(\'Error loading \' + this.src);"><\/scr'+'ipt>');};function bs(H){lIdx=0;fZ=H.length;if(bs.arguments.length==2){ff=bs.arguments[1].toLowerCase();}else{ff="all";}for(var i=0;i<H.length;i++){mX=H.substring(lIdx,lIdx+1);mW=H.substring(fZ,fZ-1);if((ff=="all"||ff=="left")&&mX==" "){lIdx++;}if((ff=="all"||ff=="right")&&mW==" "){fZ--;}}H=H.slice(lIdx,fZ);return H;};function aF(km,mS){alert(mS);km.focus();km.select();return false;};function cr(color){var hc=color;if(hc=="")return true;if(hc.length!=7)return false;return(hc.search(/\#[a-fA-F0-9]{6}/)!= -1);};function aD(){return((event.keyCode>=48)&&(event.keyCode<=57));};function cq(af){var jW=document.all("d_"+af);var kQ=document.all("s_"+af);var url="selcolor.htm?color="+encodeURIComponent(jW.value);var P=showModalDialog(url,window,"dialogWidth:0px;dialogHeight:0px;help:no;scroll:no;status:no");if(P){jW.value=P;kQ.style.backgroundColor=P;}};function jK(){showModalDialog("backimage.htm?action=other",window,"dialogWidth:0px;dialogHeight:0px;help:no;scroll:no;status:no");};function eG(type,af){var el=document.all("d_"+af);var P=showModalDialog('browse.htm?return=input&type='+type,window,"dialogWidth:0px;dialogHeight:0px;help:no;scroll:no;status:no");if(P){el.value=P;}};function ap(gS,fx){for(var i=0;i<gS.length;i++){if(gS.options[i].value==fx){gS.selectedIndex=i;return true;}}return false;};function bn(H){H=bs(H);if(H!=""){var bH=parseFloat(H);if(isNaN(bH)){H="";}else{H=bH;}}return H;};function nH(url){var bH;var b=true;bH=url.substring(0,7);bH=bH.toUpperCase();if((bH!="HTTP://")||(url.length<10)){b=false;}return b;};function da(url,opt){var bH;var b=false;var s=opt.toUpperCase().split("|");for(var i=0;i<s.length;i++){bH=url.substr(url.length-s[i].length-1);bH=bH.toUpperCase();s[i]="."+s[i];if(s[i]==bH){b=true;break;}}return b;};function hB(url){if(url.substring(0,1)=="/"){return url;}if(url.indexOf("://")>=0){return url;}var gq=kM();while(url.substr(0,3)=="../"){url=url.substr(3);gq=gq.substring(0,gq.lastIndexOf("/"));}return gq+"/"+url;};function cG(url){switch(config.BaseUrl){case "0":url=hB(url);return lM(url);break;case "1":return hB(url);break;case "2":case "3":return fh()+hB(url);break;}};function lM(url){var baseHref=config.BaseHref;var b=true;while(b){var n1=url.indexOf("/");var n2=baseHref.indexOf("/");if((n1>=0)&&(n2>=0)){var u1=url.substring(0,n1+1);var u2=baseHref.substring(0,n2+1);if(u1==u2){url=url.substr(n1+1);baseHref=baseHref.substr(n2+1);}else{b=false;}}else{b=false;}}if(baseHref!=""){var a=baseHref.split("/");for(var i=1;i<a.length;i++){url="../"+url;}}return url;};function kM(){var url="/"+document.location.pathname;return url.substring(0,url.lastIndexOf("/dialog/"));};function fh(){var eD=document.location.protocol+"//"+document.location.host;if(eD.substr(eD.length-3)==":80"){eD=eD.substring(0,eD.length-3);}return eD;};function at(){var w=tabDialogSize.offsetWidth+6;var h=tabDialogSize.offsetHeight+25;if(myBrowser.IsSP2){h+=20;}window.dialogWidth=w+"px";window.dialogHeight=h+"px";window.dialogLeft=(screen.availWidth-w)/2;window.dialogTop=(screen.availHeight-h)/2;};function kR(el){if(!el["imageinitliazed"]){el["oncontextmenu"]=new Function("event.returnValue=false");el["onmouseout"]=new Function("jC(this)");el["onmousedown"]=new Function("jB(this)");el["unselectable"]="on";el["imageinitliazed"]=true;}el.className="kR";};function jC(el){el.className="jC";};function jB(el){el.className="jB";};function ez(gr){var fQ;switch(gr){case "image":fQ=config.AllowImageSize;break;case "flash":fQ=config.AllowFlashSize;break;case "media":fQ=config.AllowMediaSize;break;case "file":fQ=config.AllowFileSize;break;default:return "";}var mN=parseFloat(fQ)*1024;var html="<iframe name='myuploadformtarget' style='display:none;position:absolute;width:0px;height:0px' src='blank.htm'></iframe>"+"<form action='../"+config.ServerExt+"/upload."+config.ServerExt+"?action=save&type="+gr+"&style="+config.StyleName+"&cusdir="+config.CusDir+"&skey="+config.SKey+"' method=post name=myuploadform enctype='multipart/form-data' style='margin:0px;padding:0px;width:100%;border:0px' target='myuploadformtarget'>"+"<input type=hidden name='MAX_FILE_SIZE' value='"+mN+"'>"+"<input type=file name='uploadfile' size=1 style='width:100%' onchange=\"this.form.originalfile.value=this.value;try{bW();} catch(e){}\">"+"<input type=hidden name='originalfile' value=''>"+"</form>";return html;};function eA(aJ,bb,oj){var dF="";switch(aJ){case "ext":dF=lang["ErrUploadInvalidExt"]+":"+bb;break;case "size":dF=lang["ErrUploadSizeLimit"]+":"+oj+"KB";break;case "file":dF=lang["ErrUploadInvalidFile"];break;case "style":dF=lang["ErrInvalidStyle"];break;case "space":dF=lang["ErrUploadSpaceLimit"]+":"+config.SpaceSize+"MB";break;default:dF=aJ;break;}return dF;};function gs(name){var ke="";var search=name+"=";if(document.cookie.length>0){offset=document.cookie.indexOf(search);if(offset!= -1){offset+=search.length;end=document.cookie.indexOf(";",offset);if(end== -1){end=document.cookie.length;}ke=unescape(document.cookie.substring(offset,end));}}return ke;};function gn(name,value){var expire="";expire=new Date((new Date()).getTime()+24*365*3600000);expire=";expires="+expire.toGMTString();document.cookie=name+"="+escape(value)+expire;};var eWebEditorActiveX;function bY(hO){if(eWebEditorActiveX){eWebEditorActiveX=null;}var b=false;try{eWebEditorActiveX=new ActiveXObject("eWebSoft.eWebEditorActiveX");var hE=eWebEditorActiveX.Version;if(parseFloat(hE.replace(/[^0123456789]+/gi,""))>=config.jf){eWebEditorActiveX.Lang=lang.V;eWebEditorActiveX.SendUrl=config.SendUrl;eWebEditorActiveX.LocalSize=config.AllowLocalSize;eWebEditorActiveX.LocalExt=config.AllowLocalExt;b=true;}}catch(e){}if(!b&&hO){alert("真");var P=showModalDialog("installactivex.htm",dialogArguments,"dialogWidth:0px;dialogHeight:0px;help:no;scroll:no;status:no");}return b;};function cO(){var bR=eWebEditorActiveX.Error;if(bR!=""){var dG,dC;if(bR.indexOf(":")>=0){var a=bR.split(":");dG=a[0];dC=a[1];}else{dG=bR;dC="";}switch(dG){case "L":alert(lang["ErrLicense"]);break;case "InvalidFile":alert(lang["ErrInvalidFile"]+":"+dC);break;default:alert(bR);}return true;}return false;}