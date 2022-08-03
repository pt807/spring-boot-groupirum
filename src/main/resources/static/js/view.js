const div = document.querySelector('#replyDiv');
const recruitId = document.getElementById('recruitId').value;

//댓글 작성 로직
document.getElementById('reply-form').addEventListener('submit', async (e) => {
    e.preventDefault(); //새로 실생되는것을 막아줌 submit은 작동됨
    const content = e.target.content.value;
    const memberId = e.target.memberId.value;
    if (!content) {
        return alert('댓글을 입력해주세요');
    }
    await fetch(`/reply`, {
        method: 'post',
        headers: {
            'content-type': 'application/json',
        },
        body: JSON.stringify({
            memberId: memberId,
            recruitId: recruitId,
            content: content,
        }),
    })
        .then(() => {
            getReplies(recruitId);
        })
        .catch((err) => {
            console.log(err);
        });
    e.target.content.value = '';
});

//댓글 수정 버튼 로직
const editById = (id, e) => {
    e.preventDefault();
    document.getElementById(`updateReplyBtn${id}`).style.display = "none";
    document.getElementById(`deleteReplyBtn${id}`).style.display = "none";
    document.getElementById(`replyBtn${id}`).style.display = "none";
    document.getElementById(`content${id}`).style.display = "none";
    document.getElementById(`updateContentDiv${id}`).style.display = "block";
    document.getElementById(`updateContentBtn${id}`).style.display = "block";
    document.getElementById(`cancelReplyBtn${id}`).addEventListener("click", (e) => {
        e.preventDefault();
        document.getElementById(`updateReplyBtn${id}`).style.display = "block";
        document.getElementById(`deleteReplyBtn${id}`).style.display = "block";
        document.getElementById(`replyBtn${id}`).style.display = "block";
        document.getElementById(`content${id}`).style.display = "block";
        let content = document.getElementById(`content${id}`).innerText;
        document.getElementById(`updateContent${id}`).value = content;
        document.getElementById(`updateContentDiv${id}`).style.display = "none";
        document.getElementById(`updateContentBtn${id}`).style.display = "none";
    })
}

//댓글 수정 로직
function updateReply(id, e) {
    e.preventDefault();
    const updateContent = document.getElementById(`updateContent${id}`).value;
    fetch(`/reply/${id}`, {
        method: 'put',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            content: updateContent
        }),
    })
        .then(() => {
            alert('댓글이 수정되었습니다.');
            getReplies(recruitId);
        })
        .catch((err) => {
            console.log(err);
        });
}

//댓글 삭제 로직
function deleteReply(id, e) {
    e.preventDefault();
    const deleteConfirm = confirm('삭제 하시겠습니까?');
    if (!deleteConfirm) {
        return;
    }
    fetch(`/reply/${id}`, {
        method: 'delete',
    })
        .then(() => {
            alert('댓글이 삭제되었습니다.');
            getReplies(recruitId);
        })
        .catch(() => {
            alert('ajax 실패');
        });
}

//댓글 답글 로직
const Reply = (id, e) => {
    e.preventDefault();
    if (e.target.dataset.auth == 'false') {
        return;
    }
    document.getElementById(`replyForm${id}`).style.display = "block";
    document.getElementById(`replyCancel${id}`).addEventListener("click", (e) => {
        e.preventDefault();
        document.getElementById(`replyForm${id}`).style.display = "none";
        document.getElementById(`replyContent${id}`).value = '';
    })
}

//답글 저장 로직
function replySave(id, e) {
    e.preventDefault();
    const content = document.getElementById(`replyContent${id}`).value;
    const memberId = document.getElementById('authMember').dataset.id;
    if (!content) {
        return alert('답글을 입력해주세요');
    }
    fetch(`/reply`, {
        method: 'post',
        headers: {
            'content-type': 'application/json',
        },
        body: JSON.stringify({
            recruitId: recruitId,
            content: content,
            parent: id,
            memberId: memberId,
        }),
    })
        .then(() => {
            getReplies(recruitId);
        })
        .catch((err) => {
            console.log(err);
        });
}


//댓글 리스트 가져오기
function getReplies(id) {
    fetch(`/reply/${id}`, {
        method: 'get',
    })
        .then((res) => res.text())
        .then((text) => {
            document.getElementById('replyList').innerHTML = text;
            $("#replyCount").load(window.location.href + " #replyCount");
        })
        .catch((err) => {
            console.log('실패', err)
        });
}

//답글 버튼 권한 확인
document.querySelector('body').addEventListener('click', (e) => {
    let target = e.target;
    if (!target.dataset.auth) {
        return;
    }
    if (target.dataset.auth === 'false') {
        document.getElementById('content').readOnly = true;
        if (confirm("로그인 하신 후 이용해 주시기 바랍니다.") === true) {
            location.href = "/oauth/login";
        } else {
            return;
        }
    }
})

