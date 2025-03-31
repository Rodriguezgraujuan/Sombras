$(document).ready(function () {
    $.get("/userInfo", function (data) {
        console.log(data);
        renderUserInfo(data);
    }).fail(function (error) {
        alert(`Error: ${error.statusText}`);
    });
});

function renderUserInfo(data) {
    $('#name').html(`<i class="bi bi-envelope"></i> ${data.email}`);
}
