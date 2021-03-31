
    // 미리 작성된 영역 - 수정하지 않으셔도 됩니다.
    // 사용자가 내용을 올바르게 입력하였는지 확인합니다.
    function isValidContents(contents) {
    if (contents == '') {
    alert('내용을 입력해주세요');
    return false;
}
    if (contents.trim().length > 140) {
    alert('공백 포함 140자 이하로 입력해주세요');
    return false;
}
    return true;
}

    // 수정 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
    // 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.
    function editPost(id) {
    showEdits(id);
    let content = $(`#${id}-content`).text().trim();
    $(`#${id}-textarea`).val(content);
}

    function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-content`).hide();
    $(`#${id}-edit`).hide();
}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 여기서부터 코드를 작성해주시면 됩니다.

    $(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getstories();
})

    // 메모를 불러와서 보여줍니다.
    function getstories() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#table-body').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
    type: 'GET',
    url: '/api/stories',
    success: function (response) {
        let cnt = response.length;
        for (let i = 0; i < response.length; i++) {

        let story = response[i];
        let id = story['id'];
        let title = story['title'];
        let writer = story['writer'];
        let content = story['content'];
        let modifiedAt = story['modifiedAt'].split('T');
        addHTML(cnt, id, title, writer, content, modifiedAt[0]);
        cnt -= 1;
        }
    }
})
}
    function myPost() {
        // 1. 기존 메모 내용을 지웁니다.
        $('#table-body').empty();
        // 2. 메모 목록을 불러와서 HTML로 붙입니다.
        $.ajax({
            type: 'GET',
            url: '/api/writerstory',
            success: function (response) {

                let cnt = response.length;
                for (let i = 0; i < response.length; i++) {
                    let story = response[i];
                    let id = story['id'];
                    let title = story['title'];
                    let writer = story['writer'];
                    let content = story['content'];
                    let modifiedAt = story['modifiedAt'].split('T');
                    addHTML(cnt,id, title, writer, content, modifiedAt[0]);
                    cnt -= 1;
                }
            }
        })
    }

    // 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
    function addHTML(cnt, id, title, writer, content, modifiedAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml =
    `<tr>
                <td>${cnt}</td>
                <td onclick="popstory(${id})" id="${id}-title">${title}</td>
                <td id="${id}-writer">${writer}</td>
                <td id="${id}-modifiedAT">${modifiedAt}</td>
            </tr>`;

    // 2. #cards-box 에 HTML을 붙인다.
    $('#table-body').append(tempHtml);
}


    function popstory(id) {
    $('#story-content').empty();
    $.ajax({
    type: 'GET',
    url: `/api/pop/${id}`,
    success: function (response) {
    let story = response;
    let id = story['id'];
    let title = story['title'];
    let writer = story['writer'];
    let content = story['content'];
    let modifiedAt = story['modifiedAt'].split('T');
    let time = modifiedAt[0]
    let time_back = modifiedAt[1].split('.')
    let hour = time_back[0]
    let tempHtml = ` <div class="field">
                            <div class="form-group">
                                <div class="modal-main-header">
                                <h4 class="modal-title" id="${id}-title" style="text-decoration: underline" >
                                ${title}
                                </h4>
                                </div>
                            </div>
                        </div>
                              <h7 class="modal-sub-header" >
                              <span style="color:#0080FF">${writer}</span>님이 작성한 메모입니다. &nbsp&nbsp 작성시간: ${time} ${hour}
                              </h7>



                                <div class="modal-body" id="${id}-content">
                                <h5>${content}</h5>
                                </div>

                                <div id="${id}-editarea" class="edit">
                                <textarea id="${id}-textarea" class="form-control" rows="3" ></textarea>
                                </div>


                        <div class="modal-footer">
                            <button id="${id}-edit" class="icon-start-edit" onclick="editPost('${id}')"> 수정하기 </button>
                            <button id="${id}-delete" class="icon-delete"   onclick="deleteOne('${id}')"> 삭제하기 </button>
                            <button id="${id}-submit" style ="display:none" class="icon-end-edit" onclick="submitEdit('${id}')"> 저장하기 </button>
                            <div class="field">
                            <div class="form-group">
                                <textarea class="form-control" id="comment" rows="1" placeholder="당신의 느낌점을 적어주세요."></textarea> 
                                <button onclick="writeComment('${id}')" type="button" class="btn btn-light float-right">등록하기</button>
                            </div>
                            <div class = "comments">
                            
                            </div>
                            </div>
                        </div>`;
    $('#story-content').append(tempHtml);
    getComment('${id}');
    $("#story-modal").addClass("is-active");

}
})
}

    function getComment(id) {
        // 1. 기존 메모 내용을 지웁니다.
        $('#comments').empty();
        // 2. 메모 목록을 불러와서 HTML로 붙입니다.
        $.ajax({
            type: 'GET',
            url: `/api/comment/${id}`,
            success: function (response) {
                let cnt = response.length;
                for (let i = 0; i < response.length; i++) {

                    let comment = response[i];
                    let id = comment['id'];
                    let writer = comment['writer'];
                    let content = comment['content'];
                    let modifiedAt = comment['modifiedAt'].split('T');
                    addComment(cnt, id, writer, content, modifiedAt[0]);
                    cnt -= 1;
                }
            }
        })
    }

    function addComment(cnt, id, writer, content, modifiedAt) {
        // 1. HTML 태그를 만듭니다.
        let tempHtml =
            `<tr>
                <td>${cnt}</td>
                <td id="${id}-comment">${content}</td>
                <td id="${id}-writer">${writer}</td>
                <td id="${id}-modifiedAT">${modifiedAt}</td>
            </tr>`;

        // 2. #cards-box 에 HTML을 붙인다.
        $('#comments').append(tempHtml);
    }




function writeComment(id){
    let comment = $('#comment').val();
    let writer = $('#writer').text().trim();
    let storyId = id;
    let data = {'storyId':storyId, 'writer':writer, 'content':comment};
    console.log(data);

    $.ajax({
        type: "POST",
        url: "/api/comment",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });

}




    // 메모를 생성합니다.
    function writePost() {
    // 1. 작성한 메모를 불러옵니다.
    let content = $('#content').val();
    if (isValidContents(content) == false) {
            return;
    }

    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(content) == false) {
    return;
    }
    let writer = $('#writer').text().trim();
    let title = $('#title').val();
    let data = {'title': title, 'writer': writer, 'content': content};

    // 5. POST /api/memos 에 data를 전달합니다.
    $.ajax({
    type: "POST",
    url: "/api/stories",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (response) {
    alert('메시지가 성공적으로 작성되었습니다.');
    window.location.reload();
    }
    });
    }

    // 메모를 수정합니다.
    function submitEdit(id) {
    // 1. 작성 대상 메모의 username과 contents 를 확인합니다.
    let writer = $(`#${id}-writer`).text().trim();
    let content = $(`#${id}-textarea`).val().trim();
    let title = $(`#${id}-title`).text().trim();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(content) == false) {
    return;
}

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'title': title, 'writer': writer, 'content': content};

    // 4. PUT /api/memos/{id} 에 data를 전달합니다.
    $.ajax({
    type: "PUT",
    url: `/api/stories/${id}`,
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (response) {
    alert('메시지 변경에 성공하였습니다.');
    window.location.reload();
}
});
}

    // 메모를 삭제합니다.
    function deleteOne(id) {
    // 1. DELETE /api/memos/{id} 에 요청해서 메모를 삭제합니다.
    $.ajax({
        type: "DELETE",
        url: `/api/stories/${id}`,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}

function logout(){
    setTimeout(function(){
        location.href = "/user/logout";
    },200);
}
