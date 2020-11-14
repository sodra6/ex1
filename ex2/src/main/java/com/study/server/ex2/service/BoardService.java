package com.study.server.ex2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.study.server.ex2.domain.Board;
import com.study.server.ex2.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
 
	public void deletePost(Integer id) {
        boardRepository.deleteById(id);
    }
	
	public ModelAndView update(Integer id) {
		ModelAndView modelAndView =null;
		
		boardRepository.getOne(id);
		modelAndView.getModel().replace("boardItem", board);
		
		return modelAndView;
	}
	
}
