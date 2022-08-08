let page = 0;
let gameName = '';
let isFetching = false;

document.addEventListener('DOMContentLoaded', () => {
    getCard();
    page++;

});

document.getElementById(`all`).addEventListener('click', async (e) => {
    isFetching = true;
    e.preventDefault();
    gameName = '';
    page = 0;
    document.getElementById(`recruitCards`).innerHTML = '';
    await getCard();
})

document.getElementById(`overwatch`).addEventListener('click', async (e) => {
    isFetching = true;
    e.preventDefault();
    gameName = '오버워치';
    page = 0;
    document.getElementById(`recruitCards`).innerHTML = '';
    await getCard();
})

document.getElementById(`League of Legends`).addEventListener('click', async (e) => {
    isFetching = true;
    e.preventDefault();
    gameName = '리그오브레전드';
    page = 0;
    document.getElementById(`recruitCards`).innerHTML = '';
    await getCard();
})

document.getElementById(`battle ground`).addEventListener('click', async (e) => {
    isFetching = true;
    e.preventDefault();
    gameName = '배틀그라운드';
    page = 0;
    document.getElementById(`recruitCards`).innerHTML = '';
    await getCard();
})


async function getCard() {
    isFetching = true;
    await fetch(`/recruitCard?page=${page}&gameName=${gameName}`)
        .then((res) => res.text())
        .then((text) => {
            if (text != '') {
                $('#recruitCards').append(text);
                isFetching = false;
            }
        });
}

window.addEventListener("scroll", async function () {
    const scrolled_height = window.scrollY; //스크롤된 높이
    const window_height = window.innerHeight; //클라이언트에게 보여지는 화면의 높이
    const doc_total_height = document.body.offsetHeight; //전체 높이
    const is_bottom = (window_height + scrolled_height > doc_total_height - 500);

    if (is_bottom && !isFetching) {
        page++;
        await getCard();
    }
});