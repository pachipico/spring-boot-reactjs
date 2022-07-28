package com.backend.board.dto;

import com.backend.board.domain.Board;
import com.backend.user.domain.User;
import com.backend.user.dto.UserResponseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardListResponseDto {
    private Long bId;
    private String siName;
    private String title;
    private UserResponseDto writer;

    private String regAt;
    private String modAt;
    private int hit;
    private String category;
    private int likeCnt;
    private int commentCnt;

    public BoardListResponseDto(Board board, User user) {
        this.bId = board.getBId();
        this.siName = board.getSiName();
        this.title = board.getTitle();
        this.writer = new UserResponseDto(user);

        this.regAt = board.getRegAt();
        this.modAt = board.getModAt();
        this.hit = board.getHit();
        this.category = board.getCategory();
        this.likeCnt = board.getLikeCnt();
        this.commentCnt = board.getCommentCnt();
    }

    public BoardListResponseDto(Board board, User user, int commentCnt) {
        this.bId = board.getBId();
        this.siName = board.getSiName();
        this.title = board.getTitle();
        this.writer = new UserResponseDto(user);

        this.regAt = board.getRegAt();
        this.modAt = board.getModAt();
        this.hit = board.getHit();
        this.category = board.getCategory();
        this.likeCnt = board.getLikeCnt();
        this.commentCnt = commentCnt;
    }

}
