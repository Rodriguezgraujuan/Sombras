$('#contenedor').on('click', '.btn-details', function () {
    const card = $(this).closest('.card-body');
    const tipo = card.data('tipo');

    if (tipo === "raza") {
        const raza = card.data('raza'); // objeto raza

        let detallesHtml = `
            <p><strong>Nombre:</strong> ${raza.name}</p>
            <p><strong>Altura:</strong> ${raza.tall}</p>
            <p><strong>Velocidad:</strong> ${raza.velocity}</p>
            <p><strong>Descripción</strong> ${raza.descripcion}</p>
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

    } else if (tipo === "clase") {
        const clase = card.data('clase'); // objeto clase

        let detallesHtml = `
        <p><strong>Nombre:</strong> ${clase.nombre}</p>
        <p><strong>P. de Golpe:</strong> ${clase.pGolpe}</p>
        <p><strong>Lanzador:</strong> ${clase.lanzador}</p>
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

        // Cargar equipamientos
        $.getJSON('/claseEquipamientos', { id: clase.id })
            .done(function (equipamientos) {
                console.log(equipamientos)
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
    }
});
