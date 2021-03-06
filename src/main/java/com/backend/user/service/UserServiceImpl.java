package com.backend.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.board.domain.Board;
import com.backend.board.dto.BoardListResponseDto;
import com.backend.board.mapper.BoardMapper;
import com.backend.comment.domain.Comment;
import com.backend.comment.dto.CommentResponseDto;
import com.backend.comment.mapper.CommentMapper;
import com.backend.response.ResponseService;
import com.backend.response.result.CommonResult;
import com.backend.security.service.SecurityService;
import com.backend.user.domain.User;
import com.backend.user.dto.UserDetailsDto;
import com.backend.user.dto.UserLoginRequestDto;
import com.backend.user.dto.UserReissueDto;
import com.backend.user.dto.UserModifyRequestDto;
import com.backend.user.dto.UserResponseDto;
import com.backend.user.dto.UserSignupRequestDto;
import com.backend.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final BoardMapper boardMapper;
	private final CommentMapper commentMapper;
	private final ResponseService responseService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final SecurityService securityService;

	// 시큐리티 적용 후 비밀번호 인코딩/디코딩
	// private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public int register(UserSignupRequestDto userSignupRequestDto) {

		return userMapper.register(userSignupRequestDto.toEntity());
	}

	@Override
	@Transactional
	public UserResponseDto findByEmail(String email) {

		return new UserResponseDto(userMapper.findByEmail(email));
	}

	@Override
	@Transactional
	public UserResponseDto findByName(String name) {

		return new UserResponseDto(userMapper.findByName(name));
	}

	@Override
	@Transactional
	public UserDetailsDto findUserDetails(String email) {
		int boardCnt = boardMapper.findBoardListByUser(email).size();
		int commentCnt = commentMapper.findCommentByWriterCnt(email);
		User user = userMapper.findByEmail(email);
		
		
		UserDetailsDto userDetailsDto = new UserDetailsDto(user, boardCnt, commentCnt);
		return userDetailsDto;
	}

	@Override
	@Transactional
	public List<UserResponseDto> findByNickName(String nickName) {

		return userMapper.findByNickName(nickName).stream().map(u -> new UserResponseDto(u))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<UserResponseDto> findByLocation(String siName) {

		return userMapper.findByLocation(siName).stream().map(u -> new UserResponseDto(u)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<UserResponseDto> findAll() {

		return userMapper.findAll().stream().map(u -> new UserResponseDto(u)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public int updateUserInfo(UserModifyRequestDto userModifyRequestDto, String category) {

		switch (category) {
		case "nickName":

			return userMapper.isNickNameAvailable(userModifyRequestDto.getNickName()) == null
					? userMapper.updateUserNickName(userModifyRequestDto.toEntity())
					: -1;
		case "address":

			return userMapper.updateUserAddress(userModifyRequestDto.toEntity());
		case "profileImg":
			return userMapper.updateUserProfileImg(userModifyRequestDto.toEntity());
		case "point":
			return userMapper.updateUserPoint(userModifyRequestDto.toEntity());
		case "popularity":
			return userMapper.updateUserPopularity(userModifyRequestDto.toEntity());
		default:
			return 0;
		}
	}

	@Override
	@Transactional
	public int deleteUser(String email) {

		return userMapper.deleteUser(email);
	}

	@Override
	@Transactional
	public List<Long> getUserLikedBoardBId(String email) {
		log.debug("????????{}", userMapper.findUserLikedBoardBId(email));
		return userMapper.findUserLikedBoardBId(email);
	}

}
