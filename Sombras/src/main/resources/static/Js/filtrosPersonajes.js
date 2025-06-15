$(document).ready(function () {
    $('#filtroNombre').on('input', function () {
        filtrarPersonajes();
    });

    $('#filtroClase').on('change', function () {
        filtrarPersonajes();
    });

    $('#filtroRaza').on('change', function () {
        filtrarPersonajes();
    });

    function filtrarPersonajes() {
        const nombre = $('#filtroNombre').val().toLowerCase();
        const clase = $('#filtroClase').val();
        const raza = $('#filtroRaza').val();

        $('.personajeCard').each(function () {
            const card = $(this);
            const nombreCard = card.find('.card-title').text().toLowerCase();
            const claseCard = card.find('.card-text').text().toLowerCase();
            const razaCard = card.data('raza')?.toLowerCase() || '';

            const coincideNombre = nombre === '' || nombreCard.includes(nombre);
            const coincideClase = clase === '' || claseCard.includes(clase.toLowerCase());
            const coincideRaza = raza === '' || razaCard.includes(raza.toLowerCase());

            if (coincideNombre && coincideClase && coincideRaza) {
                card.show();
            } else {
                card.hide();
            }
        });
    }
});
