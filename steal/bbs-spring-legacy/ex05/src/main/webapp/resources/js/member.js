
console.log("Member Module...........");

var memberService = (function(){
	
	function update(vo, callback, error){
		
		$.ajax({
			type:'post', 
			url:'/member/memberUpdate',
			data : JSON.stringify(vo), //이걸 안하면 다음의 에러 발생. HttpMessageNotReadableException: Could not read document: Unrecognized token 'url': was expecting ('true', 'false' or 'null')
			contentType : "application/json; charset=utf-8", 
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}

	function remove(vo, callback, error){
		
		$.ajax({
			type:'post',
			url: '/member/memberDelete', 
			data : JSON.stringify(vo),
			contentType: "application/json; charset=utf-8",
			
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}

	return {
		remove:remove,
		update:update
	};
})();