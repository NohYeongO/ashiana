<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<style>
		#content{
            width: 1000px;
            margin: auto;
            text-align: center;
            padding-top: 80px;
            font-size: 30px;
        }
        b{
            border-bottom: 1px solid rgb(0, 0, 0);
            color: rgb(10, 10, 10);
            padding-bottom: 5px;
            text-shadow: 4px 4px rgb(226, 183, 183);
        }
        #title{
            padding-bottom: 60px;
        }
        .q{
            margin: auto;
            background-color :rgba(247, 113, 113, 0.384);
            width : 600px;
            height : 25px;
            line-height : 25px;
            text-align: left;
            border : 1px solid beige;
            border-radius : 15px;
            color : black;
            font-weight : bold;
            font-size: 15px;
            padding : 13px;
            cursor : pointer;
            box-shadow: 3px 1px rgb(196, 140, 140);
        }
        .a{
            padding : 12px;
            width : 600px;
            margin: auto;
            height: 15px;
            border : 1px solid lightpink;
            background-color : rgba(250, 55, 55, 0.089);
            color : black;
            font-weight: bold;
            font-size: 15px;
            text-align: left;
            line-height : 10px;
            border-radius : 15px;
            margin-bottom: 10px;
            display: none;
            box-shadow: 2px 3px rgb(196, 140, 140);
        }
        .line{
            margin: auto;
            width: 600px;
            height: 5px;
        }
	</style>

</head>
<body>

	<%@ include file="../common/headerbar.jsp" %>
	
	<div id="content">
        <div id="title"><b>자주 찾는 질문</b></div>
        
        <div class="q">Q. [여행플랜] 비공개 설정 가능한가요??</div>
        <p class="a">A. 네 가능합니다.</p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
        <div class="q"></div>
        <p class="a"></p>
        <div class="line"></div>
    </div>

    <script>
        $(function(){

            $('.q').click(function(){

                const $p = $(this).next();

                if($p.css('display') == 'none'){

                    $p.siblings('.a').slideUp(500);

                    $p.slideDown(500);
                }
                else{
                    $p.slideUp(500);
                }
            });

        });
    </script>


	<%@ include file="../common/footer.jsp" %>
</body>
</html>