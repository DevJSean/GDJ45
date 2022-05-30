package com.goodee.ex13.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex13.domain.FreeBoardDTO;

@Mapper
public interface FreeBoardMapper {
	
	public int selectFreeBoardCount();
	public List<FreeBoardDTO> selectFreeBoardList(Map<String, Object> map);
	
	public int insertFreeBoard(FreeBoardDTO freeBoardDTO);
	
	public int updatePreviousReply(FreeBoardDTO freeBoardDTO);
	public int insertReply(FreeBoardDTO freeBoardDTO);
	
	public int deleteFreeBoard(Long freeBoardNo);
}