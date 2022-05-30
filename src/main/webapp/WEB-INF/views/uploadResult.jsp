<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="false"%>

<script>

var result    = '${savedName}';
var delResult =  ${delResult};

if (result != '') {
	parent.addFilePath(result); //부모의 addFilePath 실행
}else if(delResult == -1){
	parent.addFilePath2(delResult); //부모의 addFilePath 실행
}




</script>

