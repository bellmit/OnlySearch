function urlconvert(oldurl){
  oldurl=trimString(oldurl);
  if (oldurl=='')
  {
	  return false;
  }
  if (oldurl.indexOf("thunder://")!=-1)
  {
      newurl=Thunderdecode(oldurl);
  }else if(oldurl.indexOf("Flashget://")!=-1){
      newurl=Flashgetdecode(oldurl);
  }else if(oldurl.indexOf("qqdl://")!=-1){
      newurl=qqdecode(oldurl);
  }else{
	   newurl=oldurl;
  }
  thunderurl=ThunderEncode(newurl);
  return thunderurl;
}

function ConvertURL2FG(url,fUrl,uid)
	{	
		try{
			FlashgetDown(url,uid);
		}catch(e){location.href = fUrl;}
}
function Flashget_SetHref(obj,uid){obj.href = obj.fg;}
 function   trimString(str)   
  {   
  var   re;   
  var   newstr;   
  re   =   new   RegExp("^(\\s)*");   
  re2   =   new   RegExp("(\\s)*$");   
  newstr   =   str.replace(re,"");   
  newstr   =   newstr.replace(re2,"");   
    
  return   newstr;   
}   
function qqencode(url){
   url='qqdl://'+encode64(url);
   return url;
}
function flashetencode(url){
   url='Flashget://'+encode64('[FLASHGET]'+url+'[FLASHGET]')+'&1926';
   return url;
}
 function ThunderEncode(t_url) {
	var thunderPrefix = "AA";
	var thunderPosix = "ZZ";
	var thunderTitle = "thunder://";
	var tem_t_url = t_url;
	var thunderUrl = thunderTitle + encode64(thunderPrefix + tem_t_url + thunderPosix);
	return thunderUrl;
}

function Thunderdecode(url) {
	 url=url.replace('thunder://','');
     thunderUrl=decode64(url);
	 thunderUrl=thunderUrl.substr(2,thunderUrl.length-4);
	 return thunderUrl;
}

function Flashgetdecode(url){
    url=url.replace('Flashget://','');
	if (url.indexOf('&')!=-1)
	{
		url=url.substr(0,url.indexOf('&'));	 
	}
	url=decode64(url);
	flashgeturl=url.replace('[FLASHGET]','');
	flashgeturl=flashgeturl.replace('[FLASHGET]','');
	 
	return flashgeturl;
}
function  qqdecode(url){
	url=url.replace('qqdl://','');
    qqurl=decode64(url);
    return qqurl;
}