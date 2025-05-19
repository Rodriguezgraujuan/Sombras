$(document).ready(function () {
    $.getJSON('/clases', function (clases) {
        clases.forEach(function (clase) {
            const html = `
                       <div class="col-md-4 d-flex justify-content-center mb-4">
                            <div class="card">
                                <div class="card-body d-flex flex-column">
                                    <h3 class="card-title">${clase.nombre}</h3>
                                    <p style="font-size: 12px;">Puntos de golpe ${clase.pGolpe} <br> Rango ${clase.lanzador ? clase.lanzador : 'Cuerpo a Cuerpo'}</p>
                                    <button class="btn btn-pergamino btn-details">Más detalles</button>
                                </div>
                            </div>
                        </div>
                    `;
            $('#contenedor').append(html);
        });
    }).fail(function () {
        $('#contenedor').html('<p class="text-danger">No se pudieron cargar las razas.</p>');
    });


});