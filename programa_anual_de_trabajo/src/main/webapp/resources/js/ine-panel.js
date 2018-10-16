$(document).ready(function(){
	$(".ine-panel .panel-header .title").click(function(event){

  	panel=$(event.target).parent().parent();
  	if(panel.hasClass("active")){
    	panel.removeClass("active");
    }else{
    	panel.addClass("active");
    }
  });
});