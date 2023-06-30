




import

    {
        addModalBtnEvent, addModalCloseEvent
    }

    from "../common/modal-handler.js";

// 게시글 쓰기
(()=>

    {
        console.log("daaa");
        addModalBtnEvent();
        addModalCloseEvent();
    })();


//document.getElementById("search").addEventListener("click", handleSearchClick);
//
//document.getElementById("category-btn").addEventListener("click", function (event) {
//  // 클릭 이벤트 처리 코드 작성
//  console.log("Button clicked:", event.target.id);
//});

//document.getElementById("freedom-btn").addEventListener("click", function () {
//  // 클릭 이벤트 처리 코드 작성
//  console.log("Freedom button clicked!");
//});
//
//document.getElementById("accuse-btn").addEventListener("click", function () {
//  // 클릭 이벤트 처리 코드 작성
//  console.log("Accuse button clicked!");
//});
document.getElementById("search").

    addEventListener("click",function() {

  const baseUrl = 'http://localhost:8282';
  const page = 1; // 페이지 번호
  const keyword = document.querySelector('#search')
  const boardCategory = document.querySelector('.selected-category').innerText;

  console.log("keyword::::::::::::", keyword)
  console.log("boardCategory", boardCategory)
  const encodedUrl = encodeURI("게시글");
  const test = decodeURI(encodedUrl);

   const url = `/api/v1/boards/${page}/"게시글 첫 글"/FREE`;

        fetch(url)
        .then(response => {
            if (response.ok) {
                console.log("======아무말===========", response);
            return response.json();
            } else {
                throw new Error('게시글 목록을 가져오지 못했습니다');
            }
        })
                 .then(boardList => {
                console.log("======아무말대잔치==================");
                 const data = boardList;

                 const boardListWrapper = document.createElement("div");
                 boardListWrapper.className = "board-list-wrapper";

                 const boardListContainer = document.createElement("ul");
                 boardListContainer.className = "board-list-container";

                 data.forEach(function(item) {
                   const anchor = document.createElement("a");
                   anchor.href = "#";



                   const recommendBox = document.createElement("div");
                   recommendBox.className = "board-recommend-box";

                   const bodyBox = document.createElement("div");
                   bodyBox.className = "board-body-box";

                   const title = document.createElement("div");
                   title.className = "boardTitle";
                   title.textContent = item.boardTitle;

                   const info = document.createElement("div");
                   info.className = "board-info";
                   info.innerHTML = "<span class='boardCategory'>" + item.boardCategory + "</span>|" +
                       "<span class='board-writer'>" + item.account +"</span>|" +
                       "<span class='writtenDate'>" + item.writtenDate + "</span>";

                   const li = document.createElement("li");
                   li.className = "board-card";


                   const recommend = document.createElement("div")
                   recommend.className ="board-recommend";

                    var img = document.createElement('img');
                    img.src = '/assets/img/community/upgray.png';
                    img.alt = '추천수';




                   bodyBox.appendChild(title);
                   bodyBox.appendChild(info);
                   recommendBox.appendChild(img);
                   recommendBox.appendChild(recommend);

                   li.appendChild(recommendBox);
                   li.appendChild(bodyBox);

                   anchor.appendChild(li);
                   boardListContainer.appendChild(anchor);
                 });

                 boardListWrapper.appendChild(boardListContainer);

                 // 이전에 생성된 게시글 목록을 제거
                 const existingBoardList = document.querySelector('.board-list-wrapper');
                 if (existingBoardList) {
                   existingBoardList.remove();
                 }

                 // 랜더링된 요소를 body에 추가합니다.
                 document.body.appendChild(boardListWrapper);

                 console.log(data); // 가져온 게시글 목록 출력
               })


        })



