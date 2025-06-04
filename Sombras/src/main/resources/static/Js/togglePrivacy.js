$('#zonaPersonajes').on('click', '.toggle-privacidad', function (e) {
    e.stopPropagation();

    const badge = $(this);
    const personajeId = badge.data('id');
    const estadoActual = badge.data('estado') === true || badge.data('estado') === "true";

    const nuevoEstado = !estadoActual;

    $.ajax({
        url: '/editPrivacy',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            id: personajeId,
            publico: nuevoEstado
        }),
        success: function () {
            badge
                .removeClass(estadoActual ? 'bg-success' : 'bg-secondary')
                .addClass(nuevoEstado ? 'bg-success' : 'bg-secondary')
                .text(nuevoEstado ? 'Público' : 'Privado')
                .data('estado', nuevoEstado);

            console.log("Visibilidad actualizada.");
        },
        error: function (xhr) {
            console.error("Error al cambiar visibilidad:", xhr.responseText);
            alert("No se pudo cambiar la visibilidad.");
        }
    });
});