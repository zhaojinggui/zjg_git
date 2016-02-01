 
  //����Ӧ���߶�
  //percent������ڸ߶ȵİٷֱ�(��С����ʽ���֣���0.3���30%)

function hightfit(percent) {
    var bodyHeight = document.body.clientHeight;
    var fithight =bodyHeight * percent;
    return fithight;
}

Date.prototype.format = function(format){ 
		var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
		} 
		if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		} 
		for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
		format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
			} 
		} 
		return format; 
	}

function toDate(obj,format){
	   if(obj!=null)
		   {
		   	 var date = new Date(obj.time);
		   	 return date.format(format); 
		   }
	}
String.prototype.toEgNumber = function(){
	var resultStr = "";
	var arr=new Array("zero","one","two","three","four","five","six","seven","eight","nine");
	for(var i=0;i<this.length;i++){
		resultStr +=arr[this.charAt(i)];
	}
	return resultStr;
};

function uuid() { 
    var s = []; 
    var hexDigits = "0123456789abcdef"; 
    for (var i = 0; i < 32; i++) { 
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1); 
    } 
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010 
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01 
    s[8] = s[13] = s[18] = s[23] = "-"; 
  
    var uuid = s.join(""); 
    return uuid; 
} 
