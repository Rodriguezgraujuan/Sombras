$(document).ready(function () {
    $.get("/userInfo", function (data) {
        console.log(data);
        renderUserInfo(data);
        $("#historialPersonajes").empty();
        data.personajes.forEach(personaje =>{
            $("#historialPersonajes").append(`<li class="list-group-item"><a>${personaje.nombre}-${personaje.public ? 'Público' : 'Privado'}</a></li>`);
        })

    }).fail(function (error) {
        alert(`Error: ${error.statusText}`);
    });
});

function renderUserInfo(data) {
    $('#username').text(`${data.username}`)
    $('#email').html(`<i class="bi bi-envelope"></i> ${data.email}`);
    $('#startD').html(`<i class="bi bi-calendar-check"></i> Miembro desde: ${data.startDate}`)
    if (data.imagen) {
        document.getElementById('userImage').src = `/imagen`;
    }
}
