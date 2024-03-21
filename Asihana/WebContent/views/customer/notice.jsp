<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList , com.kh.semi.customer.model.vo.Notice , com.kh.semi.pageInfo.model.vo.PageInfo" %>    

<%
	PageInfo pi = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<Notice> noticeList = (ArrayList<Notice>)request.getAttribute("noticeList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        #noticetext{
            margin: auto;
            width: 1000px;
            text-align: center;
            font-size: 30px;
            font-weight: bold;
            margin-top: 50px;
            border-bottom: 1px solid rgba(75, 70, 70, 0.692);
        }
        #noticetext>p{
            margin: 0;
            margin-bottom: 20px;
        }


        #search{
            margin: auto;
            width: 800px;
            height: 100px;
            text-align: center;
            margin-top: 50px;
            background-color: rgb(247, 240, 233);
            border-radius: 15px;
        }
        #search>form{
            position: relative;
            top: 33%;
            height: 32px;
        }
        #select{
            height: 100%;
            width: 60px;
            text-align: center;
            border: 1px solid rgb(180, 108, 108);
            border-radius: 5px;
        }
        #select:focus{outline: none;}
        #searchText{
            height: 100%;
            width: 300px;
            border: 1px solid rgb(180, 108, 108);
            border-radius: 5px;
            box-sizing: border-box;
        }
        #searchText:focus{outline: none;}
        #search button{
            position: relative;
            top: 2px;
            right: 5px;
            height: 100%;
            width: 50px;
            border-radius: 5px;
            border: none;
            background-color: red;
            color: white;
            font-weight: bold;
        }
        #search button:active{
            box-shadow: 3px 3px 3px rgba(204, 96, 96, 0.5);
            position: relative;
            top:2px
        }

        
        .table{
            width: 1000px;
            margin: auto;
            text-align: center;
        }
        .noticeNo{
            width: 30%;
            border-left: none;
            border-right: none;
        }
        .title{
            width: 50%;
            border-left: none;
            border-right: none;
            padding-right: 120px;
        }
        .date{
            width: 20%;
            border-left: none;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th{
            border-bottom: 1px solid #b182827a;
            padding: 20px;
            background-color: rgb(247, 240, 233);
            border-radius: 5px;
            font-size: 15px;
        }
        td{
            border-bottom: 1px solid #b18282c4;
            padding: 15px;
            background-color: rgba(247, 240, 233, 0.253);
            font-size: 15px;
        }
	    #buttonBox{
            width: 1000px;
            text-align: center;
            margin: auto;
            margin-top: 50px;
            margin-bottom: 50px;
        }
        #backButton, #nextButton{
            width: 45px;
            height: 30px;
            background-color: red;
            border: none;
            border-radius: 5px;
            color: white;
            font-weight: bold;
        }
        .numButton{
            width: 30px;
            height: 30px;
            background-color: rgba(250, 160, 160, 0.685);
            border: none;
            border-radius: 5px;
            font-weight: bold;
            font-size: 15px;
        }

</style>
</head>
<body>

	<%@ include file="../common/headerbar.jsp" %>
	
	<div id="noticetext">
        <p>공지사항</p>
    </div>
    <div id="search">
        <form action="" method="get">
            <select name="select" id="select">
            <option value="title">제목</option>
            <option value="content">내용</option>
            </select>
            <input type="text" id="searchText">
            <button>검색</button>
        </form>
    </div>

    <div style="height: 70px;"></div>

    <table class="table">
        <thead>
            <tr>
                <th class="th noticeNo">번호</th>
                <th class="th title">제목</th>
                <th class="th date">등록일</th>
            </tr>
        </thead>
        <tbody>
        <% if(noticeList.isEmpty()) { %>
        	<tr>
               <td colspan="3" style="color: #ff52a0;">조회된 게시글이 없습니다.</td>
            </tr>
        <% } else { %>
	        <% for(Notice notice : noticeList){ %>
	            <tr>
	            	<% if(notice.getNoticeHold().equals("Y")) {%>
	                	<td class="td noticeNo" style="color:red;">[공지]</td>
	                <% } else { %>
	                	<td class="td noticeNo"><%= notice.getNoticeNo() %></td>
	                <% } %>	
	                <td class="td title"><%= notice.getNoticeTitle() %></td>
	                <td class="td date"><%= notice.getCreateDate() %></td>
	            </tr>
	        <% } %>   
        <% } %>
        </tbody>
    </table>
	
	<div id="buttonBox">
		<% if(pi.getCurrentPage() > 1){ %>
        	<button id="backButton" onclick="location.href='<%=contextPath%>/notice.customer?currentPage=<%= pi.getCurrentPage() - 1%>'">이전</button>
        <% } %>
        <% for(int i = pi.getStartPage(); i <= pi.getEndPage(); i++) { %>
        	<% if( pi.getCurrentPage() != i) { %>
        		<button class="numButton" onclick="location.href='<%=contextPath%>/notice.customer?currentPage=<%=i%>'"><%= i %></button>
        	<% } else{ %>
        		<button class="numButton"><%= i %></button>
        	<% } %>
        <% } %>
        <% if(pi.getCurrentPage() != pi.getMaxPage()) {%>
       	 	<button id="nextButton" onclick="location.href='<%=contextPath%>/notice.customer?currentPage=<%= pi.getCurrentPage() + 1%>'">다음</button>
        <% } %>
    </div>
	
	
	
	<%@ include file="../common/footer.jsp" %>




</body>
</html>