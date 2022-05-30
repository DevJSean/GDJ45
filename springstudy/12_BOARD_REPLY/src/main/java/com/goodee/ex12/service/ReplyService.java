package com.goodee.ex12.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ReplyService {

	// AJAX처리는 값을 반환한다. Map으로 보내는 게 제일 편하다.
	
	public Map<String, Object> findReplies(Long boardNo);
	
	public Map<String, Object> saveReply(HttpServletRequest request);
	
	public Map<String, Object> removeReply(Long replyNo);
}
