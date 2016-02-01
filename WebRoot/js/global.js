if (window.Event) 
document.captureEvents(Event.MOUSEUP); 
function nocontextmenu(){ 
 event.cancelBubble = true; 
 event.returnValue = false; 
 return false; 
} 
function norightclick(e){ 
 if (window.Event){ 
  if (e.which == 2 || e.which == 3) 
  return false; 
 } 
 else 
  if (event.button == 2 || event.button == 3){ 
   event.cancelBubble = true; 
   event.returnValue = false; 
   return false; 
  } 
} 
 function noctrl (){
	  // event.keyCode = 0;
	   //event.returnvalue = false;
 if ((event.ctrlKey)&&(event.keyCode==67)) //фа╠н Ctrl+c  
  {  
   alert("ctrl + C");  
   event.returnValue=false;  
  }  
  if ((event.ctrlKey)&&(event.keyCode==88)) //фа╠н Ctrl+x  
  {  
   alert("ctrl + x");  
   event.returnValue=false;  
  }  

 }

document.oncontextmenu = nocontextmenu; // for IE5+ 
document.onmousedown = norightclick; // for all others 
document.onkeydown =noctrl;

