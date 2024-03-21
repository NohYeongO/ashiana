<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <style>

        .main{
            width : 1000px;
            margin : auto;
            margin-bottom : 80px;
        }

        .searchbar{
            width: 100%;
            height: 50px;
        }

        #searchform{
            margin-top: 20px;
            width: 100%;
            text-align: center;
        }

        #searchtext{
            border : none;
            border-radius: 50px;
            background: none;
            outline : none;
            width: 500px;
            height: 50px;
            background-color: rgba(239, 239, 238, 0.575);
            text-align: center;
        }

        #searchbtn{
            display: none;
        }
        
        .tag{
            margin-top: 20px;
            width: 100%;
            height: 30px;
            text-align: center;
        }

        .tagbtn{
            font-size: small;
            margin-right: 3px;
            padding : 2px 7px 2px 7px;
            width: auto;
            display : inline-block;
            background-color: #ff595e;
            border-radius: 10px;
            color : white;
        }

        .storyBoardView{
            position : relative;
            z-index : 0;
            margin-top: 40px;
            width: 100%;
            height: 500px;
            border : 1px solid red;
        }

        .storyBoardView>img{
            position : absolute;
            z-index : 1;
            width: 100%;
            height: 100%;
        }

        #title{
            position : absolute;
            z-index : 2;
            font-size : large;
            font-weight: 700;
            color : white;
            display: inline-block;
            width: auto;
            margin-left: 20px;
        }

        #boardTitle{
            position : absolute;
            z-index: 5;
            width: 1000px;
            color : wheat;
            text-align: center;
            font-weight: 800;
            font-size: x-larger;
            top : 25%;
        }

        #detail{
            width: 100%;
            position: absolute;
            z-index: 4;
            left : 90%;
            bottom : 10%;
        }

        #storypage{
            width: auto;
            background-color: rgba(50, 50, 50, 0.308);
            text-decoration: none;
            color : white;
            padding : 2px 10px 2px 10px;
        }

        .cityView{
            width: 100%;
            background-color: #faf3dd;
            padding-left : 30px;
        }
        .city{
            background-color: white;
            margin : 20px 0px 20px 0px;
            width: 225px;
            height: 300px;
            display: inline-block;
            margin-right: 7px;
            box-shadow: 1px 1px 5px 2px gray ;
        }

        .cityPhoto{
            margin-top: 5px;
            margin-left: 5px;
            width: 100%;
            height: 70%;
        }

        .cityPhoto>img{
            width: 95%;
            height: 95%;
        }

        .cityName{
            width: 100%;
            height: 20%;
            background-color: white;
        }

        .cityName>h5{
            line-height: 30px;
            text-align: center;
            font-weight: 800;
            margin : 0px;
        }
        .cityName>h6{
            line-height: 40px;
            text-align: center;
            font-weight: 800;
        }


    </style>
<body>

<%@ include file="adminbar.jsp" %>

    <div class="outer">
        <div class="main">

            <div class="searchbar">
                <form action="#" name="search" id="searchform">
                    <input type="text" name="query" id="searchtext" placeholder="검색어를 입력하세요">
                    <button type="submit" id="searchbtn"></button>
                </form>
            </div>

            <div class="tag">
                <div class="tagbtn">#아이와함께</div>
                <div class="tagbtn">#태그2</div>
                <div class="tagbtn">#태그3</div>
                <div class="tagbtn">#태그4</div>
                <div class="tagbtn">#태그5</div>
            </div>

            <div class="storyBoardView">
                <div id="title"> S T O R Y </div>
                <div id="boardTitle">여행스토리의 제목</div>
                <img src="https://content.skyscnr.com/m/64f1189bc1e2bf0e/original/UK-ST3-165-TRAVELTRENDS-Q323-Article2-1-2.jpg?crop=1224px:647px&position=attention" alt="">
                <div id="detail"><a href="#" id="storypage">상세보기 ></a></div>
            </div>

            <br><br><br>
            <div>
                <h4>추천여행지</h4>
            </div>
            <br>
            <div class="cityView">
                <div class="city">
                    <div class="cityPhoto">
                        <img src="https://tourimage.interpark.com//Spot/187/15599/202112/6377508663314465670.jpg" alt="">
                    </div>
                    <div class="cityName">
                        <h5>피렌체</h5>
                        <h6>이탈리아</h6>
                    </div>
                </div>
                <div class="city">
                    <div class="cityPhoto">
                        <img src=https://ak-d.tripcdn.com/images/1i6192224syebo5lcFD0D_W_400_0_R5_Q90.jpg?proc=source/trip" alt="">
                    </div>
                    <div class="cityName">
                        <h5>도쿄</h5>
                        <h6>일본</h6>
                    </div>
                </div>
                <div class="city">
                    <div class="cityPhoto">
                        <img src="https://mblogthumb-phinf.pstatic.net/MjAxNjEyMDZfMjc2/MDAxNDgwOTUzMzQ2NDMy.XZTVP0zjye6zsCzTNFiRM72yprWmzNRuF_yrZvYRD8cg.r-VzTAWxS9qAxdpTcXcNdWIUPoc0MlwQdMFswLi1O5Qg.JPEG.minzsnap/IMG_3725.JPG?type=w420" alt="">
                    </div>
                    <div class="cityName">
                        <h5>어디지</h5>
                        <h6>스위스</h6>
                    </div>
                </div>
                <div class="city">
                    <div class="cityPhoto">
                        <img src="https://st3.depositphotos.com/22201120/37041/i/1600/depositphotos_370410358-stock-photo-jinbi-square-golden-horse-jade.jpg" alt="">
                    </div>
                    <div class="cityName">
                        <h5>아마</h5>
                        <h6>중국</h6>
                    </div>
                </div>   
            </div>
        </div>
    </div>
    
</body>
</html>