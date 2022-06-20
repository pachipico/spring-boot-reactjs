package com.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@AllArgsConstructor
public class BoardLikeDto {
	private Long bId;
	private String email;
}
