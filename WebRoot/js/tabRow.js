$(function(){
	    $('tbody>tr').addClass('in_rightno');
		
		$('tbody>tr').click(function() {
			var hasSelected=$(this).hasClass('in_right');
			$(this)[hasSelected?"removeClass":"addClass"]('in_right')
				.find(':checkbox').attr('checked',!hasSelected);
		});
		$('tbody>tr:has(:checked)').addClass('in_right');
		
		$('tbody>tr').mouseover(function(){
			$(this).addClass("mouseOver");
		});
		$('tbody>tr').mouseout(function(){
			var isChecked=$(this).find(':checkbox').attr('checked');
			if(isChecked==false){
			  $(this).removeClass("mouseOver");
			}
		});
    })
    
    $(function(){
     $("#checkallid").click(function(){
			$('[name=onecheckid]:checkbox').attr("checked", this.checked );
			if(this.checked==true){
			  $('tbody>tr').addClass('in_right');
			}else{
				  $('tbody>tr').removeClass('in_right');
				  $('tbody>tr').addClass('in_rightno');
			}
	 });
	 $('[name=onecheckid]:checkbox').click(function(){
				var $tmp=$('[name=onecheckid]:checkbox');
				$('#compId').attr('checked',$tmp.length==$tmp.filter(':checked').length);
	 });

})
