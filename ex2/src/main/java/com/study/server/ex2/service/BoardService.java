package com.study.server.ex2.service;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
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
	
 
	public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }
	public Board findOneById(Integer boardId) {
		Board board = null;
		boardRepository.getOne(boardId);
		
		return board;
	}
	
	public Board updateBoard(Integer boardId, Board updateBoard) {
		//게시글 id에 맞는 데이터 조회
		Board saveBoard = boardRepository.getOne(boardId);
		
		//조회한 데이터를 수정 및 저장
		saveBoard.setTitle(updateBoard.getTitle());
		saveBoard.setPassword(updateBoard.getPassword());
		saveBoard.setContent(updateBoard.getContent());
		
		//수정된 데이터를응답
		return saveBoard;
	}
	
}
