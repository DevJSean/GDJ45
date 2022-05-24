package com.goodee.ex10.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex10.domain.NoticeDTO;
import com.goodee.ex10.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> findNotices() {
		return noticeMapper.selectNoticeList();
	}

	@Override
	public NoticeDTO findNoticeByNo(HttpServletRequest request) {
		
		String requestURI = request.getRequestURI(); // "/ex10/notice/detail"
		String[] arr = requestURI.split("/");        // { "", "ex10", "notice", "detail"}
		
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		
		// 이렇게 하는 이유는, 수정할 때는 조회수가 늘어나지 않게 하기 위함
		if(arr[arr.length - 1].equals("detail")) {          // 상세보기 요청이면(부르는 매핑 값의 마지막 부분이 detail),
			noticeMapper.updateHit(noticeNo);	        // 조회수를 늘리고, 
		}
		return noticeMapper.selectNoticeByNo(noticeNo); // 정보를 조회한다.
	}

	@Override
	public int save(HttpServletRequest request) {
		
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		
		return noticeMapper.insertNotice(notice);
	}

	@Override
	public int change(HttpServletRequest request) {
		
		NoticeDTO notice = new NoticeDTO();
		notice.setNoticeNo(Long.parseLong(request.getParameter("noticeNo")));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public int removeOne(HttpServletRequest request) {
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		return noticeMapper.deleteNotice(noticeNo);
	}
	
	@Override
	public int removeList(HttpServletRequest request) {
		// 하나씩 여러 번 지우기
		// DELETE FROM NOTICE WHERE NOTICE_NO = 1
		// DELETE FROM NOTICE WHERE NOTICE_NO = 4
		String[] noticeNoList = request.getParameterValues("noticeNoList"); // 파라미터가 여러 개의 값 가지고 올 때
		Long res = 0L; 														// {"1", "4"}
		for(int i = 0; i < noticeNoList.length; i++) {
			Long noticeNo = Long.parseLong(noticeNoList[i]);                // Long.parseLong("1") -> Long.parseLong("4")
			res += noticeMapper.deleteNotice(noticeNo);                 // 삭제한 횟수 만큼 누적
		}
		return (res == noticeNoList.length) ? 1 : 0; 						// 모두 삭제를 성공하면 1, 아니면 0 반환
	}
	// 하나라도 삭제를 실패하면 transaction처리 때문에 아예 삭제되지 않는다.
	
	@Override
	public int removeList2(HttpServletRequest request) {
		// 한 번에 여러 개 지우기
		// DELETE FROM NOTICE WHERE NOTICE_NO IN(1, 4)
		String[] noticeNoList = request.getParameterValues("noticeNoList"); // 파라미터가 여러 개의 값 가지고 올 때
		List<Long> list = new ArrayList<>();
		for(int i = 0; i < noticeNoList.length; i++) {
			list.add(Long.parseLong(noticeNoList[i]));                      // list.add(1) -> list.add(4)
		}
		return noticeMapper.deleteNoticeList(list);
	} 
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 트랜잭션
	public void transaction() {
		// 성공하는 데이터
		noticeMapper.insertNotice(new NoticeDTO(null, "테스트", "테스트", null, null, null));
		// 실패하는 데이터
		noticeMapper.insertNotice(new NoticeDTO());
	}
}
