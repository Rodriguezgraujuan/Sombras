$('#contenedor').on('click', '.btn-details', function () {
    const tipo = $(this).data('tipo');

    if (tipo === "clase") {
        const clase = $(this).data('clase');

        let detallesHtml = `
            <p><strong>Nombre:</strong> ${clase.nombre}</p>
            <p><strong>P. de Golpe:</strong> ${clase.pGolpe}</p>
            <p><strong>Lanzador:</strong> ${clase.lanzador ? clase.lanzador : 'No es lanzador'}</p>
            <p><strong>Descripción:</strong> ${clase.descripcion}</p>
            <p><strong>Conjuros:</strong></p>
            <ul id="lista-conjuros">
                <li>Cargando conjuros...</li>
            </ul>
            <p><strong>Equipamientos:</strong></p>
            <ul id="lista-equipamientos">
                <li>Cargando equipamientos...</li>
            </ul>
        `;

        $('#modal-body-content').html(detallesHtml);

        const modal = new bootstrap.Modal(document.getElementById('detalleModal'));
        modal.show();

        $.getJSON('/claseConjuros', { id: clase.id })
            .done(function (conjuros) {
                if (conjuros.length === 0) {
                    $('#lista-conjuros').html('<li>Este personaje no dispone de conjuros.</li>');
                } else {
                    let conjurosHtml = '';
                    conjuros.forEach(conjuro => {
                        conjurosHtml += `<li>${conjuro.nombre}: ${conjuro.descripcion}</li>`;
                    });
                    $('#lista-conjuros').html(conjurosHtml);
                }
            })
            .fail(function () {
                $('#lista-conjuros').html('<li>Error al cargar conjuros.</li>');
            });

        $.getJSON('/claseEquipamientos', { id: clase.id })
            .done(function (equipamientos) {
                if (equipamientos.length === 0) {
                    $('#lista-equipamientos').html('<li>No hay equipamientos asignados.</li>');
                } else {
                    let equipamientosHtml = '';
                    equipamientos.forEach(eq => {
                        equipamientosHtml += `<li>${eq.name}</li>`;
                    });
                    $('#lista-equipamientos').html(equipamientosHtml);
                }
            })
            .fail(function () {
                $('#lista-equipamientos').html('<li>Error al cargar equipamientos.</li>');
            });
    } else if (tipo === "raza") {
        const raza = $(this).data('raza');

        let detallesHtml = `
        <p><strong>Nombre:</strong> ${raza.name}</p>
        <p><strong>Altura:</strong> ${raza.tall}</p>
        <p><strong>Velocidad:</strong> ${raza.velocity}</p>
        <p><strong>Descripción:</strong> ${raza.descripcion}</p>
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
                if (habilidades.length === 0) {
                    $('#lista-habilidades').html('<li>Esta raza no tiene habilidades asignadas.</li>');
                } else {
                    console.log(habilidades)
                    let habilidadesHtml = '';
                    habilidades.forEach(h => {
                        habilidadesHtml += `<li>${h.habilidad.name}: ${h.habilidad.description}</li>`;
                    });
                    $('#lista-habilidades').html(habilidadesHtml);
                }
            })
            .fail(function () {
                $('#lista-habilidades').html('<li>Error al cargar habilidades.</li>');
            });
    }
});
