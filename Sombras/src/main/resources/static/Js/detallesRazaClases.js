$('#contenedor').on('click', '.btn-details', function () {
    const card = $(this).closest('.card-body');
    const nombre = card.find('.card-title').text();
    const extraHtml = `
    <p><strong>Nombre:</strong> ${nombre}</p>
    <p><strong>Descripción:</strong> Información detallada aquí...</p>
    <p><strong>Otras habilidades:</strong> ...</p>
  `;
    $('#modal-body-content').html(extraHtml);
    const modal = new bootstrap.Modal(document.getElementById('detalleModal'));
    modal.show();
});
