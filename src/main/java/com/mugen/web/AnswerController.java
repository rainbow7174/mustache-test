package com.mugen.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mugen.domain.Answer;
import com.mugen.domain.AnswerRepository;
import com.mugen.domain.Question;
import com.mugen.domain.QuestionRepository;
import com.mugen.domain.User;
import com.mugen.utils.HttpSessionUtils;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private AnswerRepository answerRepo;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User writer = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepo.findOne(questionId);
		Answer answer = new Answer(writer, question, contents);
		answerRepo.save(answer);
		
		return String.format("redirect:/questions/%d", questionId);
	}
}
