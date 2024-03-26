<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<style>

        #qnaMark{
            margin: auto;
            height: 150px;
            width: 1000px;
            text-align: center;
            line-height: 100px;
            font-size: 30px;
            font-weight: bold;
            text-shadow: 2px 2px rgba(128, 128, 128, 0.541);
            border-bottom: 1px solid  rgba(0,0,0,0.208);
        }
        #qnaBox{
            height: auto;
            width: 1000px;
            margin: auto;
            border-radius: 5px;
        }
        #titleBox{
            height: 50px;
            width: 100%;
            border-bottom: 1px solid rgba(0,0,0,0.208);
            text-align: center;
            font-size: 20px;
            font-weight: 800;
        }
        #titleBox>P{
            margin-top: 20px;
        }
        #dateBox{
            height: 100px;
            text-align: right;
            padding-right: 30px;
        }
        #dateBox>p{
            margin-top: 2px;
            font-size: 15px;
            font-weight: bold;
            color: rgb(70, 68, 68);
        }
        #downloadBox{
            height: 50px;
            width: 700px;
            margin: auto;
            text-align: left;
            font-size: 15px;
            font-weight: 1000;
            line-height: 40px;
        }
       #qnaText{
            width: 700px;
            height: 400px;
            margin: auto;
            padding-top: 10px;
            border: 1px solid rgb(231, 225, 225);
            word-wrap:break-word;
       }
       #buttonBox{
            margin: auto;
            text-align: center;
            width: 100%;
            height: 100px;
            line-height: 100px;
       }

       #replyBox{
            height: auto;
       }
       #replyText{
            width: 700px;
            height: 110px;
            margin: auto;
       }
       #replyText textarea{
            resize: none;
            width: 600px;
            height: 100%;
            box-sizing: border-box;
            border: 1px solid red;
            border-radius: 3px;
            font-size: 15px;
            font-weight: 800;
       }
       #replyText>button{
            width: 100px;
            height: 100%;
            float: right;
            background-color: red;
            border: none;
            color: white;
            font-size: 18px;
            font-weight: 1000;
            border-radius: 3px;
       }
       #reply:focus{
            outline: none;
       }
       #counter{
            font-size: 13px;
       }
       #replyLine{
            height: 30px;
            width: 700px;
            margin: auto;
            border-bottom: 0.5px solid rgba(0, 0, 0, 0.267);
       }
       #replySelect{
            width: 700px;
            height: auto;
            margin: auto;
       }
       .answer{
            width: 700px;
            height: 50px;
            margin: auto;
       }
       .answer a{
            position: relative;
            left: 90%;
            bottom: 50%;
            font-size: 12px;
       }
       .answerTextBox{
            margin: auto;
            width: 700px;
            height: 100px;
            line-height: 70px;
            font-size: 15px;
            border-bottom: 0.5px solid rgba(0, 0, 0, 0.267);            
       }
       .answerDate{
            position: relative;
            bottom: 5%;
            font-size: 13px;
       }
       .answer a:hover{
            color:red;
       }




</style>
</head>
<body>

	<%@ include file="../common/headerbar.jsp" %>
	
	        <div id="qnaMark">
                <p>Q & A</p>
            </div>
            <div id="qnaBox">

                <div id="titleBox">
                    <p>12월여행</p>
                </div>
                <div id="dateBox">
                    <label style="font-size: 12px;">작성일</label>
                    <p>22.04.06</p>
                </div>
                <div id="qnaText">
                    <p>12월 이벤트 일정입니다</p>
                </div>
                <div id="downloadBox">
                    <label>첨부파일 / </label><a download="/01_HTML_workspace/resources/image/1.jpg" href="">다운로드파일</a>
                </div>
                <div id="buttonBox">
                    <button class="btn btn-sm btn-secondary">목록</button>
                    <button class="btn btn-sm btn-danger">삭제</button>
                </div>
                
                
                <div id="replyBox">
                    
                    <div id="replyText">
                        <textarea name="reply" id="reply" cols="90" rows="8" name="replyContent"></textarea>
                        <button>작 성</button>
                        <div id="counter">(0 / 300)</div>
                    </div>

                    <div id="replyLine"></div>

                    <div id="replySelect">
                        <div class="answer">
                            <span class="answerDate"><label>작성자 </label>닉네임</span>
                            <div class="answerTextBox"><span class="answerText">안녕</span></div>
                            <a class="replyUpdate">수정</a>  <a class="replyDelete">삭제</a>
                        </div>
                        <div style="height: 75px;"></div>
                        
                    </div>
                </div>
                
            </div>
            
    <script>
		$('#reply').keyup(function (e){
	    let content = $(this).val();
	    $('#counter').html("("+ content.length +" / 300)");
	
	    if (content.length > 300){
	        alert("최대 300자까지 입력 가능합니다.");
	        $(this).val(content.substring(0, 300));
	    }
		});

	</script>

		<%@ include file="../common/footer.jsp" %>
</body>
</html>