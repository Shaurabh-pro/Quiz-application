package com.microservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.Dao.QuestionDao;
import com.microservices.Dao.QuizDao;
import com.microservices.model.Question;
import com.microservices.model.QuestionWraper;
import com.microservices.model.Quiz;
import com.microservices.model.Response;

@Service
public class QuizService {
	
	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
		
		
		
	}

	public ResponseEntity<List<QuestionWraper>> getQuizQuestion(int id) {
	    Optional<Quiz> quizOptional = quizDao.findById(id);
	    
	    if (quizOptional.isPresent()) {
	        Quiz quiz = quizOptional.get();
	        List<Question> questionsFromDB = quiz.getQuestions();
	        List<QuestionWraper> questionsFromUser = new ArrayList<>();
	        
	        for (Question q : questionsFromDB) {
	            QuestionWraper qWrapper = new QuestionWraper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
	            questionsFromUser.add(qWrapper);
	        }
	        
	        return new ResponseEntity<>(questionsFromUser, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Quiz with the given id not found
	    }
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for(Response response : responses ) {
			if(response.getResponses().equals(questions.get(i).getRightAnswer()));
				right++;
				
				i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

		
	
}
