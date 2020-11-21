package com.study.server.ex2.controller;

import com.study.server.ex2.domain.Board;
import com.study.server.ex2.repository.BoardRepository;
import com.study.server.ex2.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardRepository repository;
    @Autowired
    private BoardService service;

    @GetMapping("")
    public ModelAndView getHomeForModel() {
    	System.out.println("###############G E T###################");
        List<Board> boardList = repository.findAll();

        ModelAndView result = new ModelAndView("board/index");
        result.addObject(boardList);

        return result;
    }

    // Redirect를 통해 다른 페이지로 안보내면 문제점
    // > 새로고침하면 데이터가 계속 저장될 수 있음
    // 설명 필요
    /*@PostMapping("/params")
    public String postBoardWithParams(@RequestParam("title") String title,
                            @RequestParam("password") String password,
                            @RequestParam("content") String content) {
        // 각 파라미터들을 1개의 데이터로 묶음
        // 묶은 데이터를 repository를 이용하여 저장
        // 저장된 게시글을 볼 수 있도록 페이지 이동

        return "";
    }*/

    @PostMapping("")
    public String postBoardWithParams(@ModelAttribute Board board) {
        Board savedBoard = repository.save(board);
        // Redirect item page with board number

        String redirectUrl = "redirect:/board/" + savedBoard.getId();

        return redirectUrl;
    }

    @GetMapping("/{id}")
    public ModelAndView getBoardItem(@PathVariable("id") Integer boardId) {
    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    	// Get item of boards
        Board board = repository.getOne(boardId);

        ModelAndView result = new ModelAndView("board/item");
        result.getModel().put("boardItem", board);
        return result;
    }
    
    /*
     	1. 글목록에서 해당 글을 선택하고 불러옴
		2. 사용자가  DELETE 버튼을 클릭시 mapping된 서블릿에서 boardId값을 불러옴
		3. 불러온 boardId 값을 boardController에서 
		@postMapping(“/board/{id}”) 
		public String delete(){
		} 
		를 추가
		4. BoardService 클래스 생성 후 delete() 메소드 생성 
		5. BoardService 추가된 delete()를 controller에 호출 
		6. controller에 호출된 delete()를 통해 /board/{id}에 Delete로 요청 오는 것을 처리함
		7. domain에 있는 Board 클래스의 board table안의 데이터를 삭제함
		8. 글목록에서 해당 데이터가 삭제됨
     */
    
    @PostMapping("/{id}")
    public String deleteBoardItem(@PathVariable("id") Integer boardId) {
    	System.out.println("D E L E T E");
        String redirectUrl = "redirect:/board";
//        BoardService boardService = new BoardService();
        
        service.deleteBoard(boardId);
        
        
        return redirectUrl;
    	
    }
    /*
     * 1. 글목록에서 수정버튼을 누르면 GET요청을 함
     * 2. BoardService 클래스에 update() 메소드 작성
     * 3. 작성된 update()를 controller에서 호출
     * 4. controller에 호출된 update()를 통해 /{id}에 replace로 요청오는것을 처리함
     * 5. domain에 있는 table의 데이터를 수정
     * 6. 글목록에서 수정
     */
    @GetMapping("/{id}/update")
    public ModelAndView getUpdate(@PathVariable("id") Integer boardId) {
    	
    	Board saveBoard = service.findOneById(boardId);
    	
    	//board의 updateForm을 보여줌
    	ModelAndView response = new ModelAndView("board/updateForm");
    	//이름 update로 연결
    	response.getModel().put("update", saveBoard);
    	//응답
    	return response;
    }
    
    @PostMapping("/{id}/updateForm")
    public String postUpdateBoard(@PathVariable("id") Integer boardId, @ModelAttribute Board updateBoard) {
    	service.updateBoard(boardId, updateBoard);
    	
    	//방법1) 수정된 데이터를 새로 출력해서 응답하는 방법
//    	ModelAndView response = new ModelAndView(viewName: "board/index");
//    	response.getModel().put("board", resultBoard);
//    	return response;
    	
    	//방법2) 기존에 게시글을 조회하는 페이지로 이동하는 방법
    	return "redirect:/board/"+boardId;
    }
    
}
