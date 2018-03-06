// 스크립틀릿 포맷
String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

// 답변하기 눌렀을 때
$(".answer-write input[type=submit]").click(function (e) {
	e.preventDefault(); // 이벤트를 막음
//	console.log('클릭됨');
	
	var queryString = $(".answer-write").serialize();
//	console.log(queryString);
	
	var url = $(".answer-write").attr("action");
//	console.log(url);
	$.ajax({
		type: 'post',
		url: url,
		data: queryString,
		dataType: 'json',
		success: function(data, status) {
//			console.log(data);
			var answerTemplate = $("#answerTemplate").html();
//			console.log("questionId : "+data.question.id);
//			console.log("answerId : "+data.id);
			var template = answerTemplate.format(data.writer.userid, data.formattedCreateDate, data.contents, data.question.id, data.id);
			$(".qna-comment-slipp-articles").prepend(template);
			$(".answer-write textarea[name=contents]").val("");
		},
		error: function(xhr, status) {
		},
	});
});

// 답변-삭제하기 눌렀을 때
$(".link-delete-article").click(function (e) {
	e.preventDefault();
//	console.log('삭제하기 클릭됨');
	
	var btnDelete = $(this);
	var url = btnDelete.attr("href");
//	console.log("url : "+url);
	$.ajax({
		type: 'delete',
		url: url,
		dataType: 'json',
		success: function(data, status) {
//			console.log("valid: "+data.valid+", errorMsg: "+data.errorMessage);
			if(data.valid) {
				btnDelete.closest("article").remove();
			} else {
				alert(data.errorMessage);
			}
		},
		error: function(xhr, status) {
		}
	});
});
