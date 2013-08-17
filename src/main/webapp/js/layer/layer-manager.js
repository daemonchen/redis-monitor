function showLayer(selector , opt) {
	var option = {
		    type : 1,
		    title : '详细信息',
		    offset:['50%' , ''],
		    border : false,
		    area : ['503px','395px'],
		    page : {dom : selector }
		} ;
	if(typeof opt != 'undefined')
		$.extend(option , opt) ;
	
	var _layer = $.layer(option);
	
	return _layer ;
	
}

function closeLayer(id){
	layer.close(id) ;
}
