package com.study.server.ex2.controller;

import com.study.server.ex2.domain.GameResult;
import com.study.server.ex2.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping({"", "/index"})
    public String index() { return "game/index"; }

    @PostMapping("")
    public ModelAndView process(@RequestParam("input") int inputValue) {
        GameResult result = gameService.execGame(inputValue);

        // 응답 생성
        ModelAndView response = new ModelAndView("game/result");
        response.getModel().put("user", result.getUser());
        response.getModel().put("computer", result.getComputer());
        response.getModel().put("result", result.getResult());

        return response;
    }

    @GetMapping("/score")
    public ModelAndView getScoreBoard() {
        List<GameResult> results = gameService.getAllGameResult();

        ModelAndView response = new ModelAndView("game/score");

        // 1) 모델에 저장하는 방법
        response.getModel().put("result", results);

        // 2) 객체를 저장하는 방법
//        response.addObject(results);

        return response;
    }
}
