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

$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	console.log('클릭됨');
	
	var queryString = $(".answer-write").serialize();
	console.log(queryString);
	
	var url = $(".answer-write").attr("action");
	console.log(url);
	$.ajax({
		type: 'post',
		url: url,
		data: queryString,
		dataType: 'json',
		error: onError,
		success: onSuccess
	});
}

function onError() {
	console.log('error');
}

function onSuccess(data, status) {
	console.log(data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.userid, data.formattedCreateDate, data.contents, data.answerId, data.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
	$(".answer-write textarea[name=contents]").val("");
}
