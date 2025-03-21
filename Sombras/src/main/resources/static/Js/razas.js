function mostrarInfo(raza) {
    let info = {
      humanos: `<h3>Humanos</h3><ul><li>+1 a todas las estadísticas</li><li>Versatilidad en habilidades</li><li>Buena reputación en la sociedad</li></ul>` ,
      elfos: `<h3>Elfos</h3><ul><li>+2 Destreza, +1 Inteligencia</li><li>Visión en la oscuridad</li><li>Resistencia a encantamientos</li></ul>` ,
      enanos: `<h3>Enanos</h3><ul><li>+2 Constitución, +1 Fuerza</li><li>Resistencia al veneno</li><li>Habilidad en herrería</li></ul>` 
    };
    
    let contenedor = document.getElementById('info-razas');
    contenedor.innerHTML = info[raza];
    contenedor.style.display = 'block';
  }