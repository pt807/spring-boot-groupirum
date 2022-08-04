// let lastId = 0;
// const TABLE_ELE = document.querySelector("table");
//
// const drawList = (DATA) => {
//     let recruitHtml = "";
//     DATA.forEach((item, index) => {
//         const { id, data1, data2, data3, data4, data5, data6, data7 } = item;
//         const TR_ELE = document.createElement('tr');
//         recruitHtml = `<td>${data1}</td>
//                     <td>${data2}</td>
//                     <td>${data3}</td>
//                     <td>${data4}</td>
//                     <td>${data5}</td>
//                     <td>${data6}</td>
//                     <td>${data7}</td>`;
//
//         if (index === DATA.length - 1) lastId = id; // 마지막건 ID 저장
//
//         TR_ELE.innerHtml = recruitHtml;
//         TABLE_ELE.appendChild(TR_ELE);
//     });
// };
let isFetching = false;
let page = 1;
function getCard() {
    isFetching = true;
    fetch(`/recruitCard?page=${page}`, {
        method: "get",
    })
        .then((res) => res.text())
        .then((text) => {
            if (text != '') {
                $('#recruitCards').append(text);
                isFetching = false;
            }
        });
}

window.addEventListener("scroll", function () {
    const scrolled_height = window.scrollY; //스크롤된 높이
    const window_height = window.innerHeight; //클라이언트에게 보여지는 화면의 높이
    const doc_total_height = document.body.offsetHeight; //전체 높이
    const is_bottom = (window_height + scrolled_height > doc_total_height - 500);

    if (is_bottom && !isFetching) {
        getCard();
        page++;
    }
});