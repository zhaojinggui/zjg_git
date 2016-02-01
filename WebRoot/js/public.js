var index=0;
var lastClick=0;



function clickRow()
{
	lastClick=index;
	index=this.rowIndex;
	if(lastClick>0)
	{
		if(this.parentElement.rows[lastClick])
		this.parentElement.rows[lastClick].className="mouseOut"+lastClick%2;
	}
	this.className="mouseClick";	 
}

function cancelClickRow()
{
	if(index!=0)
	{
		this.parentElement.rows[index].className="mouseOut"+index%2;
		index=0;
	}
}

function bind(id) {
	var length = id.rows.length;
	if (length > 1)
		id.rows[0].onclick = cancelClickRow;
	for ( var i = 1; i < length; i++) {
		var obj = id.rows[i];
		obj.onclick = clickRow;
		obj.onmouseover = mouseOver;
		obj.onmouseout = mouseOut;
		obj.ondblclick = dblclickRow;
		obj.className = "mouseOut" + i % 2;
	}
}

function mouseOver()
{
  if(this.className=="mouseClick")
  {
	  return;
  }
  else
  {
      this.className="mouseOver";
  }
}

function mouseOut()
{
 
  if(this.className=="mouseClick")
  {
	  return;
  }
  else
  {
      this.className="mouseOut"+this.rowIndex%2;
  }
}

function add(id)
{ 
    var len=id.rows.length;
    if(len>10)
	  return false;
	var row=id.insertRow();
	row.onclick=test(1);
	row.onmouseover=mouseOver;
	row.onmouseout=mouseOut;
	row.className="mouseOut";
	row.ondblclick=dblclickRow;
	if(id.rows[1])
	{
	var obj=id.rows[1];
	var length=obj.cells.length;
	for(var i=0;i<length;i++)
	{
	 row.insertCell(i);
	 row.cells[i].innerHTML=obj.cells[i].innerHTML;
	}	
	}
}

function del(id)
{  
    var len=id.rows.length;
    if(len<3)
	  return false;
	id.deleteRow(len-1);
}

function dblclickRow()
{
  index=this.rowIndex;
}

function setKeyByName(pkName)
{	
	
	var obj=document.all(pkName);	
	if(obj.length!=null)
	{
		return obj[index-1].value;
	}
	else
		return obj.value;
}

function link(s,w,h)
{ 
 window.open(s,"","height="+h+",width="+w+"resizable=no,scrollbars=yes,center=yes,status=no,top="+(screen.availHeight/2-h/2)+", left="+(screen.availWidth/2-w/2)+",toolbar=no,menubar=no,location=no");
}

if(typeof(listtb)!='undefined')
{
  try
   {
    bind(listtb);
   }catch(e)
   {
   }
}

