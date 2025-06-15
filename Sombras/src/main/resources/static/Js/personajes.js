$(document).ready(function () {

    let tipoPersonajeActual = 'tuyos';
    $('#crearPersonaje').on('click', function () {

        $.get("/razas")
            .done(function (data) {
                data.forEach(function (raza) {
                    $("#razaPersonaje").append(`<option value="${raza.id}">${raza.name}</option>`);
                });
            })
            .fail(function (error) {
                console.error("Error al cargar razas:", error);
            });

        $.get("/clases")
            .done(function (data) {
                data.forEach(function (clase) {
                    $("#clasePersonaje").append(`<option value="${clase.id}">${clase.nombre}</option>`);
                });
            }).fail(function (data, error) {
            console.log(data)
            console.error("Error al cargar clases:", error);
        });
        $('#crearPersonajeModal').modal('show');
    });





    $("#formCrearPersonaje").submit(function (event) {
        event.preventDefault();

        $('.error-msg').text('');

        const nombre = $('#nombrePersonaje').val().trim();
        const apellido = $('#apellidoPersonaje').val().trim();
        const nivel = parseInt($('#level').val(), 10);
        const clase = $('#clasePersonaje').val();
        const raza = $('#razaPersonaje').val();
        const publico = $("#visibilidadPersonaje").val() === "true";

        let isValid = true;

        if (!nombre) {
            $('#error-nombre').text('El nombre es obligatorio');
            isValid = false;
        }

        if (!apellido) {
            $('#error-apellido').text('El apellido es obligatorio');
            isValid = false;
        }

        if (!nivel || nivel < 1 || nivel > 10) {
            $('#error-nivel').text('El nivel debe estar entre 1 y 10');
            isValid = false;
        }

        if (!clase) {
            $('#error-clase').text('Selecciona una clase');
            isValid = false;
        }

        if (!raza) {
            $('#error-raza').text('Selecciona una raza');
            isValid = false;
        }

        if (!isValid) return;

        const personaje = {
            nombre: nombre,
            apellido: apellido,
            nivel: nivel,
            clase: clase,
            raza: raza,
            descripcion: $("#description").val(),
            publico: publico
        };

        $.ajax({
            url: '/createCharacter',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(personaje),
            success: function () {
                console.log("Personaje creado exitosamente");
                $('#crearPersonajeModal').modal('hide');
                location.reload();
            },
            error: function (xhr) {
                console.log("Error al crear el personaje: " + xhr.responseText);
            }
        });
    });

    function cargarMisPersonajes() {
        const personajesTuyos = [];
        $.ajax({
            url: '/showCharacter', type: 'GET', success: function (data) {
                console.log("Personajes recibidos:", data);
                data.forEach(p => {
                    personajesTuyos.push({
                        id: p[0],
                        nombre: p[1],
                        clase: p[2],
                        imagen: p[3],
                        visibilidad: p[4] === true || p[4] === 'true' || p[4] === 1,
                        raza: p[5]
                    });
                });



                const zona = document.getElementById("zonaPersonajes");
                zona.innerHTML = "";
                personajesTuyos.forEach(p => {
                    zona.innerHTML += `
  <div class="col personajeCard" data-raza="${p.raza}">
    <div class="card card-personaje imgCard" data-id="${p.id}"style="background-image: url('${p.imagen}')">
      <div class="card-body d-flex flex-column">
        <h5 class="card-title d-flex justify-content-between align-items-center mt-auto">
          ${p.nombre}
          <span 
  class="badge bg-${p.visibilidad ? 'success' : 'secondary'} rounded-pill small toggle-privacidad" 
  data-id="${p.id}" 
  data-estado="${p.visibilidad}">
  ${p.visibilidad ? 'Público' : 'Privado'}
</span>
        </h5>
        <div class="d-flex justify-content-between align-items-center mt-2">
          <p class="card-text mb-0">${p.clase}</p>
          <button class="btn btn-sm btn-danger eliminarCharacter" data-id="${p.id}" data-nombre="${p.nombre}">
            <i class="bi bi-trash3"> ELIMINAR</i>
          </button>
        </div>
      </div>
      <div class="nivel-detalle mt-2" style="display:none;"></div>
    </div>
  </div>
`;
                });

            }, error: function (xhr, status, error) {
                console.error("Error al obtener personajes:", error);
            }
        });
    }


    function mostrarPersonajes(tipo) {
        if (tipo === 'tuyos') {
            cargarMisPersonajes();
        } else {
            cargarOtrosPersonajes();
        }
    }

    $('#personajesTabs').on('click', '.showChareacters', function (e) {
        e.preventDefault();

        tipoPersonajeActual = $(this).data('tipo');

        $('.showChareacters').removeClass('active');
        $(this).addClass('active');

        mostrarPersonajes(tipoPersonajeActual);
    });

    function cargarOtrosPersonajes() {
        const personajesOtros = [];
        $.ajax({
            url: '/showOtherCharacter', type: 'GET', success: function (data) {
                console.log("Personajes recibidos:", data);
                data.forEach(p => {
                    personajesOtros.push({
                        id: p[0], nombre: p[1], clase: p[2], imagen: p[3], raza: p[4]
                    });
                });


                const zona = document.getElementById("zonaPersonajes");
                zona.innerHTML = "";
                personajesOtros.forEach(p => {
                    zona.innerHTML += `
  <div class="col personajeCard" data-raza="${p.raza}">
    <div class="card card-personaje" data-id="${p.id}">
      <div class="card-body d-flex flex-column imgCard" style="background-image: url('${p.imagen}')">
        <h5 class="card-title">${p.nombre}</h5>
        <div class="d-flex justify-content-between align-items-center mt-2">
          <p class="card-text mb-0">${p.clase}</p>
        </div>
      </div>
      <div class="nivel-detalle mt-2" style="display:none;"></div>
    </div>
  </div>
`;
                });

            }, error: function (xhr, status, error) {
                console.error("Error al obtener personajes:", error);
            }
        });
    }

    cargarMisPersonajes()

    $('#zonaPersonajes').on('click', '.card-personaje', function (e) {
        e.stopPropagation()
        if ($(e.target).closest('button').length > 0) return;
        const card = $(this);
        const personajeId = card.data('id');
        const detalle = card.find('.nivel-detalle');

        if (detalle.is(':visible')) {
            //detalle.slideUp();
        } else {
            $.ajax({
                url: `/personajeData`,
                type: 'GET',
                data: {personajeId: personajeId},
                success: function (personaje) {
                    const valoresBase = {
                        inteligencia: 0,
                        fuerza: 0,
                        destreza: 0,
                        constitucion: 0,
                        sabiduria: 0
                    };

                    const reglasPorRaza  = {
                        "Humano": { bonificaciones:{ inteligencia: 1, fuerza: 1, destreza: 1, constitucion: 1, sabiduria: 1}, libres: 1},
                        "Semielfo": { bonificaciones:{inteligencia: 0, fuerza: 0, destreza: 2, constitucion: 0, sabiduria: 0}, libres: 1 },
                        "Gnomo": { bonificaciones: {inteligencia: 2, fuerza: 0, destreza: 1, constitucion: 1, sabiduria: 0}, libres:0},
                        "Semiorco": {bonificaciones: {inteligencia: 0, fuerza: 2, destreza: 0, constitucion: 1, sabiduria: 0}, libres:0},
                        "Enano": {bonificaciones: {inteligencia: 0, fuerza: 1, destreza: 0, constitucion: 2, sabiduria: 0}, libres:0},
                        "Elfo": { bonificaciones: {inteligencia: 1, fuerza: 0, destreza: 2, constitucion: 1, sabiduria: 1}, libres:0},
                    };

                    const reglas = reglasPorRaza[personaje.raza.name] || { bonificaciones: {}, libres: 0 };
                    const bonificaciones = reglas.bonificaciones;
                    const puntosLibres = reglas.libres;

                    for (let stat in valoresBase) {
                        valoresBase[stat] = bonificaciones[stat] || 0;
                    }



                    const puntosAsignables = personaje.nivel * 2;
                    const sumaBase = Object.values(valoresBase).reduce((a, b) => a + b, 0);
                    let puntosDisponibles = puntosAsignables + puntosLibres - sumaBase;

                    let html = `
    <form class="form-niveles p-3 rounded" data-id="${personaje.id}">
    `;

                    for (let stat in valoresBase) {
                        const valor = (personaje[stat] !== undefined) ? personaje[stat] : valoresBase[stat];
                        html += `
        <div class="nivel-campo">
            <label for="${stat}-${personaje.id}">${stat.charAt(0).toUpperCase() + stat.slice(1)}:</label>
            <input id="${stat}-${personaje.id}" type="number" name="${stat}" 
                   value="${valor}" min="${valoresBase[stat]}" ${tipoPersonajeActual === 'otros' ? 'disabled' : ''}/>
        </div>
        `;
                    }

                    html += `
${tipoPersonajeActual === 'tuyos'
                        ? `<p class="mt-2 puntos-restantes"><strong>Puntos disponibles:</strong> (Nivel: ${personaje.nivel}, Extra: ${puntosLibres})</p>`
                        : `<p class="mt-2 puntos-restantes">Nivel: ${personaje.nivel}</p>`}
${tipoPersonajeActual === 'tuyos' ? `<button type="submit" class="btn btn-success btn-sm mt-2">Guardar</button>` : ''}
    </form>`;

                    detalle.html(html).slideDown();
                    actualizarPuntos(detalle, valoresBase, puntosAsignables, puntosLibres, sumaBase);
                    actualizarPuntos(detalle, valoresBase, puntosAsignables, puntosLibres, sumaBase);
                    detalle.on('input', 'input[type="number"]', function () {
                        actualizarPuntos(detalle, valoresBase, puntosAsignables, puntosLibres, sumaBase);
                    });

                    detalle.off('submit').on('submit', '.form-niveles', function (e) {
                        e.preventDefault();
                        const form = $(this);
                        const personajeId = form.data('id');

                        const updatedData = {
                            id: personajeId,
                            inteligencia: form.find('[name="inteligencia"]').val(),
                            fuerza: form.find('[name="fuerza"]').val(),
                            destreza: form.find('[name="destreza"]').val(),
                            constitucion: form.find('[name="constitucion"]').val(),
                            sabiduria: form.find('[name="sabiduria"]').val()
                        };

                        $.ajax({
                            url: '/editLevels',
                            type: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify(updatedData),
                            success: function () {
                                console.log('Niveles actualizados correctamente');
                                detalle.slideUp();
                            },
                            error: function () {
                                alert('Error al actualizar los niveles');
                            }
                        });
                    });

                },
                error: function () {
                    detalle.html('<p>Error al obtener niveles.</p>').slideDown();
                }
            });
        }
        $(document).on('click', function (e) {
            if (!$(e.target).closest('.card-personaje').length) {
                $('.nivel-detalle').slideUp();
            }
        });
    });

    function actualizarPuntos(detalle, valoresBase, puntosAsignables, puntosLibres, sumaBase) {
        const inputs = detalle.find('input[type="number"]');
        let suma = 0;

        inputs.each(function () {
            suma += parseInt($(this).val(), 10) || 0;
        });

        let usados = suma - sumaBase;
        let disponibles = puntosAsignables + puntosLibres - usados;
        console.log(puntosAsignables, puntosLibres, usados, disponibles)

        detalle.find('.puntos-restantes strong').text(`Puntos disponibles: ${Math.max(0, disponibles)}`);

        inputs.each(function () {
            const name = $(this).attr('name');
            const base = valoresBase[name];
            const actual = parseInt($(this).val(), 10) || 0;
            const maxPermitido = base + disponibles + (actual - base);
            $(this).attr('max', maxPermitido);
            if (actual < base) {
                $(this).val(base);
            }
        });
    }
});

