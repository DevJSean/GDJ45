<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    
<script>

	if('${kind}' == 'insert'){  // if('insert') == 'insert'){ 
		if('${res}' == '1') {
			alert('공지사항이 등록되었습니다.');
			location.href='${contextPath}/notice/list'; // javascript로 페이지 이동(redirect)
		} else {
			alert('공지사항이 등록되지 않았습니다.');
			history.back();
		}
	}
	if('${kind}' == 'update'){  // if('update') == 'update'){ 
		if('${res}' == '1') {
			alert('공지사항이 수정되었습니다.');
			location.href='${contextPath}/notice/list'; // javascript로 페이지 이동(redirect)
		} else {
			alert('공지사항이 수정되지 않았습니다.');
			history.back();
		}
	}
	if('${kind}' == 'deleteOne'){  // if('deleteOne') == 'deleteOne'){ 
		if('${res}' == '1') {
			alert('공지사항이 삭제되었습니다.');
			location.href='${contextPath}/notice/list'; // javascript로 페이지 이동(redirect)
		} else {
			alert('공지사항이 삭제되지 않았습니다.');
			history.back();
		}
	}
	if('${kind}' == 'deleteList'){  // if('deleteList') == 'deleteList'){ 
		//if(${res} > 0) {          // ${res} 없이 처리된다면 if( > 0) 컴파일 오류 발생
		if('${res}' > '0') {        // ${res} 없이 처리된다면 if( '' > '0') 컴파일 오류 없이 실패로 진행,  문자열 '0'보다 문자열'1'이 더 크다. (아스키 코드 값)
			alert('선택한 공지사항이 모두 삭제되었습니다.');
			location.href='${contextPath}/notice/list'; // javascript로 페이지 이동(redirect)
		} else {
			alert('선택한 공지사항이 삭제되지 않았습니다.');
			history.back();
		}
	}

</script>