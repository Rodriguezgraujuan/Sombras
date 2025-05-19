$(document).ready(function () {
    console.log("Cargadow")
    $('#zonaPersonajes').on('click', '.eliminarCharacter', function (e) {
        console.log('Entering deleting process')
        e.stopPropagation()
        const personajeId = $(this).data('id');
        const personajeNombre = $(this).data('nombre');

        $('#confirmarEliminar').data('id', personajeId);
        $('#confirmarEliminar').data('nombre', personajeNombre);

        $('#eliminarPersonajeModal').modal('show');
    });

    $('#confirmarEliminar').on('click', function () {
        const id = $(this).data('id');
        const nombre = $(this).data('nombre');

        console.log("Eliminando personaje:", id, nombre);

        $.ajax({
            url: '/deleteCharacter',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({id: id}),
            success: function () {
                $('#eliminarPersonajeModal').modal('hide');
                console.log("Personaje eliminado correctamente")
                location.reload();
            },
            error: function (xhr) {
                console.error("Error al eliminar personaje:", xhr.responseText);
            }
        });
    });
    document.addEventListener('click', function (e) {
        if (e.target.closest('.eliminarCharacter')) {
            console.log('✅ Click detectado en botón eliminar');
        }
    });
    $(document).on('click', '.eliminarCharacter', function () {
        console.log('✅ Click detectado');
    });

});
