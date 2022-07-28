package com.backend.board.service;

import com.backend.board.domain.Board;
import com.backend.board.dto.*;
import com.backend.board.mapper.BoardMapper;
import com.backend.comment.mapper.CommentMapper;
import com.backend.common.dto.PageableWithEmail;
import com.backend.user.domain.User;
import com.backend.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {


    private final BoardMapper boardMapper;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    @Transactional
    @Override
    public void registerBoard(BoardRegisterRequestDto boardRegisterRequestDto) {
        boardMapper.register(boardRegisterRequestDto.toEntity());
    }

    @Transactional
    @Override
    public List<BoardListResponseDto> findBoardListByQuery(BoardQuery boardQuery) {
        log.debug("findBoardListByQuery >>>> {}", boardQuery);
        return boardMapper.findBoardListByQuery(boardQuery).stream().map(v -> {
            BoardListResponseDto boardListResponseDto = new BoardListResponseDto(v, userMapper.findByEmail(v.getWriter()));
            boardListResponseDto.setLikeCnt(boardMapper.findLikeCntByBId(v.getBId()));
            return boardListResponseDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public int findBoardCntByQuery(BoardQuery boardQuery) {

        return boardMapper.findBoardCntByQuery(boardQuery);
    }

    @Transactional
    @Override
    public List<BoardListResponseDto> findUserWroteList(PageableWithEmail pageableWithEmail) {

        return boardMapper.findUserWroteList(pageableWithEmail).stream().map(v -> new BoardListResponseDto(v, userMapper.findByEmail(v.getWriter()))).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public int findUserWroteListCnt(String email) {
        return boardMapper.findUserWroteListCnt(email);
    }

    @Transactional
    @Override
    public List<BoardListResponseDto> findUserLikedList(PageableWithEmail pageableWithEmail) {

        return boardMapper.findUserLikedList(pageableWithEmail).stream().map(v -> new BoardListResponseDto(v, userMapper.findByEmail(v.getWriter()))).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public int findUserLikedListCnt(String email) {

        return boardMapper.findUserLikedListCnt(email);
    }

    @Transactional
    @Override
    public List<BoardListResponseDto> findPopularBoardList(String siName, String category) {

        return boardMapper.findPopularBoardList(siName, category).stream().map(v -> new BoardListResponseDto(v, userMapper.findByEmail(v.getWriter()))).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BoardDetailResponseDto findBoardByBId(Long bId) {
        boardMapper.updateBoardHit(bId);
        Board board = boardMapper.findBoardDetail(bId);
        User user = userMapper.findByEmail(board.getWriter());
        BoardDetailResponseDto boardDetailResponseDto = new BoardDetailResponseDto(board, user);
        boardDetailResponseDto.setLikeCnt(boardMapper.findLikeCntByBId(bId));
        return boardDetailResponseDto;
    }

    @Transactional
    @Override
    public Long findNextBId(Long bId, String siName) {

        return boardMapper.findNextBId(bId, siName);
    }

    @Transactional
    @Override
    public Long findPrevBId(Long bId, String siName) {

        return boardMapper.findPrevBId(bId, siName);
    }

    @Transactional
    @Override
    public void modifyBoard(BoardModifyRequestDto boardModifyRequestDto) {
        boardMapper.updateBoard(boardModifyRequestDto.toEntity());
    }

    @Transactional
    @Override
    public void deleteBoard(Long bId) {
        boardMapper.deleteBoard(bId);
    }

    @Override
    @Transactional
    public void deleteBoardList(List<Long> list) {
        boardMapper.deleteBoardList(list);
    }

    @Transactional
    @Override
    public void likeBoard(BoardLikeDto boardLikeDto) {
        boardMapper.likeBoard(boardLikeDto.getBId(), boardLikeDto.getEmail());
    }

    @Transactional
    @Override
    public void unlikeBoard(BoardLikeDto boardLikeDto) {
        boardMapper.unlikeBoard(boardLikeDto.getBId(), boardLikeDto.getEmail());
    }

    @Override
    public List<Si> findAllSi() {

        return boardMapper.findAllSi();
    }

    @Override
    @Transactional
    public void unlikeBoardList(String email, List<Long> list) {
        boardMapper.unlikeBoardList(email, list);
    }

    @Override
    public List<BoardListResponseDto> findMostLikedList() {
        return boardMapper.findMostLikedList().stream().map(v -> new BoardListResponseDto(v, userMapper.findByEmail(v.getWriter()), commentMapper.findCommentCntByBId(v.getBId()))).collect(Collectors.toList());
    }

    @Override
    public List<BoardListResponseDto> findMostViewedList() {
        return boardMapper.findMostViewedList().stream().map(v -> new BoardListResponseDto(v, userMapper.findByEmail(v.getWriter()), commentMapper.findCommentCntByBId(v.getBId()))).collect(Collectors.toList());
    }

    @Override
    public List<BoardListResponseDto> findMostCommentedList() {
        return boardMapper.findMostCommentedList().stream().map(v -> new BoardListResponseDto(v, userMapper.findByEmail(v.getWriter()), commentMapper.findCommentCntByBId(v.getBId()))).collect(Collectors.toList());
    }
}
