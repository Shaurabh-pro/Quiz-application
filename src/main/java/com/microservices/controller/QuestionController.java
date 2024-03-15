package com.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.model.Question;
import com.microservices.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

	    @Autowired
	    private QuestionService questionService;

	    @GetMapping("/allQuestions")
	    public ResponseEntity<List<Question>> getAllQuestions() {
	        return questionService.getAllQuestions();
	    }

	    @GetMapping("/category/{category}")
	    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {
	        return questionService.getAllQuestionsByCategory(category);
	    }

	    @PostMapping()
	    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
	        return questionService.addQuestion(question);
	    }

	    @DeleteMapping("/deleteQuestion/{id}")
	    public ResponseEntity<String> deleteQuestionById(@PathVariable int id) {
	        return questionService.deleteQuestionById(id);
	    }
	}