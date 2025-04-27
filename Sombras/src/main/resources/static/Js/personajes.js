const personajesTuyos = [
    {nombre: "Elandor", clase: "Mago", imagen: "img/elandor.jpg"},
    {nombre: "Kaela", clase: "Ladrona", imagen: "img/kaela.jpg"},
];

const personajesOtros = [
    {nombre: "Thorgar", clase: "Guerrero", imagen: "img/thorgar.jpg"},
];

function mostrarPersonajes(tipo) {
    const zona = document.getElementById("zonaPersonajes");
    zona.innerHTML = "";
    const personajes = tipo === "tuyos" ? personajesTuyos : personajesOtros;
    if (tipo==="tuyos"){
    personajes.forEach(p => {
        zona.innerHTML += `
      <div class="col">
        <div class="card card-personaje h-100">
          <img src="${p.imagen}" class="card-img-top" alt="${p.nombre}">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">${p.nombre}</h5>
            <div class="d-flex justify-content-between align-items-center mt-2">
              <p class="card-text mb-0">${p.clase}</p>
              <button class="btn btn-sm btn-danger eliminarPersonaje">
                <i class="bi bi-trash3"> ELIMINAR</i>
              </button>
            </div>
          </div>
        </div>
      </div>
    `;
    });}else {
        personajes.forEach(p => {
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
    `;
        });
    }


    // Actualiza pestañas
    const tabs = document.querySelectorAll('#personajesTabs .nav-link');
    tabs.forEach(tab => tab.classList.remove('active'));
    tabs[tipo === "tuyos" ? 0 : 1].classList.add('active');
}

$(document).ready(function () {
    $('#crearPersonaje').on('click', function () {

        $.get("/razas")
            .done(function (data) {
                data.forEach(function (raza) {
                    $("#razaPersonaje").append(
                        `<option value="${raza.id}">${raza.name}</option>`
                    );
                });
            })
            .fail(function (error) {
                console.error("Error al cargar razas:", error);
            });

        $.get("/clases")
            .done(function (data) {
                data.forEach(function (clase) {
                    $("#clasePersonaje").append(
                        `<option value="${clase.id}">${clase.nombre}</option>`
                    );
                });
            }).fail(function (data, error) {
            console.log(data)
            console.error("Error al cargar clases:", error);
        });
        $('#crearPersonajeModal').modal('show');
    });

    $('#eliminarPersonaje').on('click', function () {
        $('#eliminarPersonajeModal').modal('show');
    });

    $('#confirmarEliminar').on('click', function () {
        console.log('Personaje eliminado');
        $('#eliminarPersonajeModal').modal('hide');
        //Falta eliminar en el server
    });

    /*$('#crear').on('', () => {

    })*/

    $("#razaPersonaje")
});

// Mostrar por defecto los tuyos
window.onload = () => mostrarPersonajes("tuyos");
