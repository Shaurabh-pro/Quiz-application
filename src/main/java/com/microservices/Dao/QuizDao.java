package com.microservices.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
