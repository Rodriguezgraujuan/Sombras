function mostrarInfo(clase) {
    let info = {
      guerrero: `<h3>Guerrero</h3><ul><li>+1 a todas las estadísticas</li><li>Versatilidad en habilidades</li><li>Buena reputación en la sociedad</li></ul>` ,
      mago: `<h3>Mago</h3><ul><li>+2 Destreza, +1 Inteligencia</li><li>Visión en la oscuridad</li><li>Resistencia a encantamientos</li></ul>` ,
      druida: `<h3>Druida</h3><ul><li>+2 Constitución, +1 Fuerza</li><li>Resistencia al veneno</li><li>Habilidad en herrería</li></ul>` 
    };
    
    let contenedor = document.getElementById('info-clases');
    contenedor.innerHTML = info[clase];
    contenedor.style.display = 'block';
  }