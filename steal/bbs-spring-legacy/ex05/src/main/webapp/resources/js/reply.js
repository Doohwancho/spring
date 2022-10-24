
console.log("Reply Module...........");

var replyService = (function(){
	
	/*
	 * replyService.add(
			{reply:"JS TEST", replyer:"tester", bno:bnoValue}
			,
			function(result){
				alert("RESULT: "+ result);
			}
		);
		
			@PostMapping(value ="/new",
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE}
					)
			public ResponseEntity<String> create(@RequestBody ReplyVO vo){ //@RequestBody로 Json데이터 들어온걸 ReplyVO로 바꾼다.
				log.info("ReplyVO: "+vo);
				
				int insertCount = service.register(vo);
				
				log.info("Reply Insert Count: "+ insertCount);
				
				return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
										: new ResponseEntity<>("success", HttpStatus.INTERNAL_SERVER_ERROR);
			
	 * 
	 * 	ReplyMapper.xml
	 * 	<insert id="insert">

			insert into tbl_reply (rno, bno, reply, replyer)
			values (seq_reply.nextval, #{bno}, #{reply}, #{replyer})
		
		</insert>
		
		
		callback function
		
		function(result){
			alert("RESULT: "+ result);
		}
	 */
	
	function add(reply, callback, error){
		console.log("add reply................");
		
		$.ajax({
			type:'post',
			url: '/replies/new', 
			data : JSON.stringify(reply),
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
	
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1; //논리연산자는 피연산자중 하나를 반환. false || 4 === 4. 0 || 9 === 9
		
		$.getJSON("/replies/pages/"+bno+"/"+page+".json",
			function(data){
				if(callback){
					//callback(data); //댓글 목록만 가져오는 경우
					callback(data.replyCnt, data.list);
				}
			}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function remove(rno, replyer, callback, error){
		
		$.ajax({
			type:'delete',
			url: '/replies/'+rno, 
			data : JSON.stringify({rno:rno, replyer:replyer}),
			contentType: "application/json; charset=utf-8",
			
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	function update(reply, callback, error){
		
		$.ajax({
			type:'put',
			url:'/replies/'+reply.rno,
			data : JSON.stringify(reply),
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
	function get(rno, callback, error){
		$.get("/replies/"+rno+".json", function(result){
				if(callback){
					callback(result);
				}
			}).fail(
				function(xhr, status, er){
					if(error){
						error();
					}
				}
				);
			};

	return {
		add:add, 
		getList:getList,
		remove:remove,
		update:update,
		get:get
	};
})();