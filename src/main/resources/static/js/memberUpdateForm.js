const form = document.getElementById('update-form');

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    let nickname = e.target.nickname.value;
    const currentNickname = e.target.nickname.dataset.nickname;
    const isChanged = nickname !== currentNickname;
    const isExists = await fetch(`/api/oauth/nickname/${nickname}`)
        .then((response) => response.json());
    if (!nickname) {
        return alert('닉네임을 입력해주세요.');
    }
    if (isExists && isChanged) {
        return alert("이미 사용중인 닉네임입니다.");
    }

    window.onbeforeunload = null;
    form.submit();
});

const profileImage = document.getElementById("profileImage");
const profileImageDeleteBtn = document.getElementById('profileImageDeleteBtn');
const isDeleted = document.getElementById('isDeleted');
const file = document.getElementById('file');

file.addEventListener('change', (e) => {
    const input = e.target;
    if(input.files && input.files[0]) {
        const reader = new FileReader();
        // 이미지가 로드가 된 경우
        reader.onload = (e) => {
            profileImage.style.backgroundImage = 'url(' + e.target.result + ')';
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0]);
    } else {
        profileImage.style.backgroundImage = 'url(' + profileImage.dataset.src + ')';
    }
    isDeleted.value = 'false';
    profileImageDeleteBtn.removeAttribute('disabled');
});

profileImageDeleteBtn.addEventListener('click', () => {
    file.value = '';
    isDeleted.value = 'true';
    profileImageDeleteBtn.setAttribute('disabled', '');
    profileImage.style.backgroundImage = 'url(https://groupirum-bucket.s3.ap-northeast-2.amazonaws.com/profile_image_default.png)';
});

window.onbeforeunload = function(e) {
    let nickname = document.getElementById('nickname');
    if((nickname.value !== nickname.dataset.nickname) || (file.files && file.files[0])) {
        e.returnValue = "변경사항이 저장되지 않을 수 있습니다.";
    } else {
        return;
    }
};
