$(function(){
	$.select({
		url:'/region/city/' +cityCode+ '/json' , 
		selectId:'#region'
	}) ;
	
	 var picker = new Pikaday(
			    {
			        field: document.getElementById('rentStartTime'),
			        firstDay: 1,
			        minDate: new Date('2000-01-01'),
			        maxDate: new Date('2020-12-31'),
			        yearRange: [2000,2020]
			    });
}) ;


var House = {
		showDetail : function(id) {
			ajaxGet('/house/'+id +'/json/',  
					function(data){
				if(data != null) {
					for(key in data) {
						var element = $("#" + key ) ;
						if(element!=null){
							element.val(data[key]) ;
						}
					}
					showLayer("#showone") ;
				}
				
			}) ;
		} 
} ;