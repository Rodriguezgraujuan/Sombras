$(document).ready(function () {
    $.get("/userInfo", function (data) {
        console.log(data);
        renderUserInfo(data);

        $("#historialPersonajes").empty();
        console.log(data.personajes.length)
        if (!data.personajes || data.personajes.length === 0) {
            $("#contenido").html(`
            <div style="text-align: center; margin-top: 50px;" id="historialPersonajes">
                <a style="font-size: 2.5rem; color: #333; text-decoration: none" href="/myPersonajes.html">No tienes personajes</a>
            </div>
        `);
            $("#historialPersonajes").append(`
                <li class="list-group-item">
                    <a>${personaje.nombre} - ${personaje.public ? 'Público' : 'Privado'}</a>
                </li>
            `);
        } else {
            data.personajes.forEach(personaje => {
                $("#historialPersonajes").append(`
                <li class="list-group-item">
                    <a>${personaje.nombre} - ${personaje.public ? 'Público' : 'Privado'}</a>
                </li>
            `);
            });
        }

    }).fail(function (error) {
        alert(`Error: ${error.statusText}`);
    });

    // Abrir modal al hacer clic en "Editar Perfil"
    $("#editProfileBtn").on("click", function () {
        $("#editUsernameModal").modal("show");
    });

    // Guardar el nuevo nombre de usuario
    $("#saveUsernameBtn").on("click", function () {
        const newUsername = $("#newUsernameInput").val().trim();
        if (!newUsername) {
            alert("Por favor ingresa un nombre válido");
            return;
        }

        $.ajax({
            url: "/updateUsername",
            type: "PUT",
            data: { newUsername },
            success: function (response) {
                alert("Nombre actualizado correctamente");
                $("#editUsernameModal").modal("hide");
                $("#username").text(newUsername);
            },
            error: function (xhr) {
                alert("Error: " + xhr.responseText);
            }
        });
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


