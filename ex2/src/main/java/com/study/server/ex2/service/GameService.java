package com.study.server.ex2.service;

import com.study.server.ex2.domain.GameResult;
import com.study.server.ex2.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public GameResult execGame(int userValue) {
        String user = "";
        String computer = "";
        String result = "";

        int computerValue = (int) ((Math.random() * 3) / 1 + 1);

        // User
        switch (userValue) {
            case 1:
                user = "가위";
                break;
            case 2:
                user = "바위";
                break;
            case 3:
                user = "보";
                break;
        }
        // Computer
        switch (computerValue) {
            case 1:
                computer = "가위";
                break;
            case 2:
                computer = "바위";
                break;
            case 3:
                computer = "보";
                break;
        }

        // 게임 결과 판단
        if (userValue == computerValue) {
            result = "비김";
        } else if ((computerValue == 1 && userValue == 2) ||
                (computerValue == 2 && userValue == 3) ||
                (computerValue == 3 && userValue == 1)) {
            result = "승리";
        } else {
            result = "패배";
        }

        GameResult gameResult = saveGameResult(user, computer, result);

        return gameResult;
    }

    public GameResult saveGameResult(String user,
                                     String computer,
                                     String result) {
        // 게임 결과 값 생성
        GameResult gameResult = new GameResult();
        gameResult.setUser(user);
        gameResult.setComputer(computer);
        gameResult.setResult(result);

        // DB에 저장
        GameResult savedResult = gameRepository.save(gameResult);

        return savedResult;
    }

    public List<GameResult> getAllGameResult() {
        List<GameResult> results = gameRepository.findAll();

        return results;
    }

}
