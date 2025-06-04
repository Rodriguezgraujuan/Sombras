$(document).ready(function () {
    $.getJSON('/clases', function (clases) {
        clases.forEach(function (clase) {
            const imagenFondo = `../images/${clase.nombre}.webp`;

            const html = `
    <div class="col-md-4 d-flex flex-column align-items-center mb-5">
        <div class="card sin-bordes position-relative mb-2" 
             style="background-image: url(${imagenFondo}); background-size: contain; background-repeat: no-repeat; background-position: center;">
        </div>
        <button class="btn btn-pergamino btn-details" 
                data-tipo="clase" 
                data-clase='${JSON.stringify(clase)}'>Más detalles</button>
    </div>
`;

            $('#contenedor').append(html);
        });
    }).fail(function () {
        $('#contenedor').html('<p class="text-danger">No se pudieron cargar las clases.</p>');
    });
});
