$(document).ready(function () {
    $.getJSON('/razas', function (razas) {
        razas.forEach(function (raza) {
            const html = `
        <div class="col-md-4 d-flex justify-content-center mb-4">
          <div class="card">
            <div class="card-body d-flex flex-column">
              <h3 class="card-title">${raza.name}</h3>
              <p style="font-size: 12px;">Altura ${raza.tall} <br> Velocidad ${raza.velocity}</p>
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
