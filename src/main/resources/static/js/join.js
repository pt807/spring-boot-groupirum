const form = document.getElementById('join-form');
form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const nickname = e.target.nickname.value;
    if (!nickname) {
        return alert('닉네임을 입력해주세요.');
    }
    const isExists = await fetch(`/api/oauth/nickname/${nickname}`)
        .then((response) => response.json());
    if (isExists === true) {
        return alert("이미 사용중인 닉네임입니다.");
    }
    form.submit();
});
