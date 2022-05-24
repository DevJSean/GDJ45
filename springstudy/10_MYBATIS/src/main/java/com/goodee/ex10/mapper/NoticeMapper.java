package com.goodee.ex10.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex10.domain.NoticeDTO;

@Mapper   // mapper 선언, 여기의 메소드를 호출하면 notice.xml의 쿼리문이 실행된다.
public interface NoticeMapper {

	// notice.xml에 등록된 쿼리문의 id를 추상메소드로 작성한다.
	// mybatis가 제공하는 방식, 메소드의 이름과 같은 매퍼의 쿼리문 id를 호출
	public List<NoticeDTO> selectNoticeList();
	public int insertNotice(NoticeDTO notice);
	public NoticeDTO selectNoticeByNo(Long noticeNo);
	public int updateHit(Long noticeNo);
	public int updateNotice(NoticeDTO notice);
	public int deleteNotice(Long noticeNo);
	public int deleteNoticeList(List<Long> list);
	
}
