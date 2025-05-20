$('#contenedor').on('click', '.btn-details', function () {
    const card = $(this).closest('.card-body');
    const tipo = card.data('tipo');

    if (tipo === "raza") {
        const raza = card.data('raza'); // objeto raza

        // Primero mostrar datos básicos
        let detallesHtml = `
            <p><strong>Nombre:</strong> ${raza.name}</p>
            <p><strong>Altura:</strong> ${raza.tall}</p>
            <p><strong>Velocidad:</strong> ${raza.velocity}</p>
            <p><strong>Habilidades:</strong></p>
            <ul id="lista-habilidades">
                <li>Cargando habilidades...</li>
            </ul>
        `;

        $('#modal-body-content').html(detallesHtml);

        const modal = new bootstrap.Modal(document.getElementById('detalleModal'));
        modal.show();

        $.getJSON('/razaHabilidades', { id: raza.id })
            .done(function (habilidades) {
                console.log(habilidades)
                if (habilidades.length === 0) {
                    $('#lista-habilidades').html('<li>No hay habilidades asignadas.</li>');
                } else {
                    let habilidadesHtml = '';
                    habilidades.forEach(hab => {
                        habilidadesHtml += `<li>${hab.habilidad.name}</li>`;
                    });
                    $('#lista-habilidades').html(habilidadesHtml);
                }
            })
            .fail(function () {
                $('#lista-habilidades').html('<li>Error al cargar habilidades.</li>');
            });

    } else if (tipo === "personaje") {
        console.log("Personaje clicado, sin acción.");
    }
});
