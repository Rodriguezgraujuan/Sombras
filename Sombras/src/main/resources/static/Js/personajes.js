$(document).ready(function () {
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

    $(document).on('click', '.eliminarPersonaje', function () {
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
                location.reload();
            },
            error: function (xhr) {
                console.error("Error al eliminar personaje:", xhr.responseText);
            }
        });
    });

    $("#formCrearPersonaje").submit(function (event) {
        event.preventDefault();

        $('.error-msg').text('');

        const nombre = $('#nombrePersonaje').val().trim();
        const apellido = $('#apellidoPersonaje').val().trim();
        const nivel = parseInt($('#level').val(), 10);
        const clase = $('#clasePersonaje').val();
        const raza = $('#razaPersonaje').val();

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
            nombre: $("#nombrePersonaje").val(),
            apellido: $("#apellidoPersonaje").val(),
            nivel: parseInt($("#level").val()),
            clase: {id: parseInt($("#clasePersonaje").val())},
            raza: {id: parseInt($("#razaPersonaje").val())},
            descripcion: $("#description").val()
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
                        id: p[0], nombre: p[1], clase: p[2], imagen: p[3]
                    });
                });


                const zona = document.getElementById("zonaPersonajes");
                zona.innerHTML = "";
                personajesTuyos.forEach(p => {
                    zona.innerHTML += `
      <div class="col personajeCard">
        <div class="card card-personaje h-100">
          <img src="${p.imagen}" class="card-img-top" alt="${p.nombre}">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">${p.nombre}</h5>
            <div class="d-flex justify-content-between align-items-center mt-2">
              <p class="card-text mb-0">${p.clase}</p>
              <button class="btn btn-sm btn-danger eliminarPersonaje" data-id="${p.id}">
                <i class="bi bi-trash3"> ELIMINAR</i>
              </button>
            </div>
          </div>
        </div>
            <div class="nivel-detalle mt-2" style="display:none;"></div>
      </div>
    `
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

        const tipo = $(this).data('tipo');

        // Cambiar pestaña activa visualmente
        $('.showChareacters').removeClass('active');
        $(this).addClass('active');

        // Cargar personajes según tipo
        mostrarPersonajes(tipo);
    });

    function cargarOtrosPersonajes() {
        const personajesOtros = [];
        $.ajax({
            url: '/showOtherCharacter', type: 'GET', success: function (data) {
                console.log("Personajes recibidos:", data);
                data.forEach(p => {
                    personajesOtros.push({
                        id: p[0], nombre: p[1], clase: p[2], imagen: p[3]
                    });
                });


                const zona = document.getElementById("zonaPersonajes");
                zona.innerHTML = "";
                personajesOtros.forEach(p => {
                    zona.innerHTML += `
      <div class="col">
        <div class="card card-personaje h-100">
          <img src="${p.imagen}" class="card-img-top" alt="${p.nombre}">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">${p.nombre}</h5>
            <div class="d-flex justify-content-between align-items-center mt-2">
              <p class="card-text mb-0">${p.clase}</p>
            </div>
          </div>
        </div>
      </div>
    `
                });

            }, error: function (xhr, status, error) {
                console.error("Error al obtener personajes:", error);
            }
        });
    }

    cargarMisPersonajes()

    $('.card-personaje').on('click', function () {
        const card = $(this);
        const personajeId = card.data('id');
        const detalle = card.closest('.personajeCard').find('.nivel-detalle');

        if (detalle.is(':visible')) {
            detalle.slideUp();
        } else {
            $.ajax({
                url: `/personajeData`, type: 'GET', data: {personajeId: personajeId}, success: function (personaje) {
                    let html = `
  <form class="form-niveles" data-id="${personaje.id}">
    <ul>
      <li>Inteligencia: <input type="number" name="inteligencia" value="${personaje.inteligencia}" /></li>
      <li>Fuerza: <input type="number" name="fuerza" value="${personaje.fuerza}" /></li>
      <li>Destreza: <input type="number" name="destreza" value="${personaje.destreza}" /></li>
      <li>Constitución: <input type="number" name="constitucion" value="${personaje.constitucion}" /></li>
      <li>Sabiduría: <input type="number" name="sabiduria" value="${personaje.sabiduria}" /></li>
    </ul>
    <strong>Nivel total: ${personaje.nivel}</strong><br>
    <button type="submit" class="btn btn-sm btn-success mt-2">Guardar Cambios</button>
  </form>
`;

                    detalle.html(html).slideDown();
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
                                alert('Niveles actualizados correctamente');
                                detalle.slideUp();
                            },
                            error: function () {
                                alert('Error al actualizar los niveles');
                            }
                        });
                    });

                }, error: function () {
                    detalle.html('<p>Error al obtener niveles.</p>').slideDown();
                }
            });
        }
    });


});

