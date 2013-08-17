(function(kendo){
	kendo.message = function(newMessage , option) {
		var $ = kendo.jQuery ;
		var _kendoMessage = kendo['_kendoMessage'] ;
		var _kendoMessageDom = kendo['_kendoMessageDom'] ;
		var defaultOption = {
								width: "400px",
								height: "150px" ,
								title: "消息",
								actions: [
									"Close"
								],
								modal:true ,
								button:{text:'&nbsp&nbsp确定&nbsp&nbsp',callback:function(kendoMessage){kendoMessage.close();}}
							} ;
		$.extend(defaultOption , option) ;
		
		if(_kendoMessage){
			_kendoMessageDom.children("#msg").html(newMessage) ;
			_kendoMessage.center();
			_kendoMessage.open() ;
		} else {
			
							
			var html = '<div><div id="msg" style="height: 60%;margin:8px;overflow:hidden;" >' + newMessage +'</div>' +
				'<div class="k-edit-buttons k-state-default k-window-action" style="text-align:center;margin: 8px;">'+
				'<a href="#" class="k-button">'+defaultOption.button.text+'</a></div></div>' ;
		
			
			_kendoMessageDom = $(html) ;
			_kendoMessageDom.kendoWindow(defaultOption) ;
			_kendoMessage = _kendoMessageDom.data("kendoWindow");
			_kendoMessage.center() ;
			_kendoMessage.open() ;
			_kendoMessageDom.find("a").click(function(){defaultOption.button.callback(_kendoMessage);}) ;
			kendo['_kendoMessage'] = _kendoMessage ;
			kendo['_kendoMessageDom'] = _kendoMessageDom ;
		}
	} ,
	kendo.confirm = function(newMessage , option) {
		var $ = kendo.jQuery ;
		var _kendoConfirm = kendo['_kendoConfirm'] ;
		var _kendoConfirmDom = kendo['_kendoConfirmDom'] ;
		var defaultOption = {
								width: "400px",
								height: "150px" ,
								title: "消息",
								resizable:false ,
								actions: [
									"Close"
								],
								modal:true ,
								ok:{text:'&nbsp&nbsp确定&nbsp&nbsp',callback:function(kendoConfirm){kendoConfirm.close();}} ,
								cancel: {text:'&nbsp&nbsp取消&nbsp&nbsp',callback:function(kendoConfirm){kendoConfirm.close();}}
							} ;
		$.extend(defaultOption , option) ;
		
		if(_kendoConfirm){
			_kendoConfirmDom.children("#msg").html(newMessage) ;
			_kendoConfirm.center();
			_kendoConfirm.open() ;
		} else {
			
							
			var html = '<div><div id="msg" style="height: 60%;margin:8px;overflow:hidden;" >' + newMessage +'</div>' +
				'<div class="k-edit-buttons k-state-default k-window-action" style="text-align:center;margin: 8px;">'+
				'<a href="#" class="k-button" id="confirmOk" style="margin-right:8px;">'+defaultOption.ok.text+'</a>'+
				'<a href="#" class="k-button" id="confirmCancel">'+defaultOption.cancel.text+'</a></div></div>' ;
		
			
			_kendoConfirmDom = $(html) ;
			_kendoConfirmDom.kendoWindow(defaultOption) ;
			_kendoConfirm = _kendoConfirmDom.data("kendoWindow");
			_kendoConfirm.center() ;
			_kendoConfirm.open() ;
			_kendoConfirmDom.find("#confirmOk").click(function(){defaultOption.ok.callback(_kendoConfirm);}) ;
			_kendoConfirmDom.find("#confirmCancel").click(function(){defaultOption.cancel.callback(_kendoConfirm);}) ;
			kendo['_kendoConfirm'] = _kendoConfirm ;
			kendo['_kendoConfirmDom'] = _kendoConfirmDom ;
		}
	}
})(kendo) ;