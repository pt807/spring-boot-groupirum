function submit(e) {
    e.preventDefault();
    if (confirm("모집글을 삭제하시겠습니까?") == true) {
        document.getElementById('form').submit();
    } else {
        return;
    }
}
