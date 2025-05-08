document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formCompra");
    const numeroInput = document.getElementById("numero");
    const expInput = document.getElementById("exp");


    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const nombre = document.getElementById("nombre").value.trim();
        const numero = document.getElementById("numero").value.replace(/\s+/g, '');
        const exp = document.getElementById("exp").value.trim();
        const cvv = document.getElementById("cvv").value.trim();

        const tarjetaRegex = /^\d{16}$/;
        const fechaRegex = /^(0[1-9]|1[0-2])\/\d{2}$/;
        const cvvRegex = /^\d{3,4}$/;

        if (!nombre || !tarjetaRegex.test(numero) || !fechaRegex.test(exp) || !cvvRegex.test(cvv)) {
            alert("Por favor, completa todos los campos correctamente.");
            return;
        }

        alert("Pago procesado con éxito. ¡Gracias por tu compra!");
        const modal = bootstrap.Modal.getInstance(document.getElementById("modalCompra"));
        modal.hide();
        form.reset();
    });

    const expError = document.createElement("div");
    expError.classList.add("text-danger", "mt-1", "small");
    expInput.parentElement.appendChild(expError);

    numeroInput.addEventListener("input", (e) => {
        let value = e.target.value.replace(/\D/g, "").substring(0, 16);
        const parts = value.match(/.{1,4}/g);
        e.target.value = parts ? parts.join("-") : "";
    });

    expInput.addEventListener("input", (e) => {
        let value = e.target.value.replace(/\D/g, "").substring(0, 4);
        if (value.length >= 3) {
            e.target.value = value.substring(0, 2) + "/" + value.substring(2);
        } else {
            e.target.value = value;
        }

        validarFecha();
    });

    expInput.addEventListener("blur", validarFecha);

    function validarFecha() {
        const valor = expInput.value;
        const regex = /^(0[1-9]|1[0-2])\/\d{2}$/;

        if (!regex.test(valor)) {
            expError.textContent = "Formato inválido. Usa MM/AA.";
            return false;
        }

        const [mes, anio] = valor.split("/");
        const ahora = new Date();
        const mesActual = ahora.getMonth() + 1;
        const anioActual = ahora.getFullYear() % 100;

        if (parseInt(anio) < anioActual || (parseInt(anio) === anioActual && parseInt(mes) < mesActual)) {
            expError.textContent = "La tarjeta está expirada.";
            return false;
        }

        expError.textContent = "";
        return true;
    }
});
