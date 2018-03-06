package com.mugen.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mugen.domain.Answer;
import com.mugen.domain.AnswerRepository;
import com.mugen.domain.Question;
import com.mugen.domain.QuestionRepository;
import com.mugen.domain.User;
import com.mugen.utils.HttpSessionUtils;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private AnswerRepository answerRepo;
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		
		User writer = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepo.findOne(questionId);
		Answer answer = new Answer(writer, question, contents);
		return answerRepo.save(answer);
	}
}
