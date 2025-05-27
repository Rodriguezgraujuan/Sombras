package container.Sombras.Dto;

public class PersonajeDto {

    private String nombre;
    private String apellido;
    private String descripcion;
    private boolean publico;
    private String imagen;
    private int nivel;
    private int inteligencia;
    private int fuerza;
    private int destreza;
    private int constitucion;
    private int sabiduria;
    private Long clase;
    private Long raza;

    public PersonajeDto() {}

    public PersonajeDto(String nombre, String apellido, String descripcion, boolean publico, String imagen, int nivel, int inteligencia, int fuerza, int destreza, int constitucion, int sabiduria, Long clase, Long raza) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.publico = publico;
        this.imagen = imagen;
        this.nivel = nivel;
        this.inteligencia = inteligencia;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.constitucion = constitucion;
        this.sabiduria = sabiduria;
        this.clase = clase;
        this.raza = raza;
    }

    public boolean isPublico() {
        return publico;
    }

    public Long getClase() {
        return clase;
    }

    public int getConstitucion() {
        return constitucion;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getNivel() {
        return nivel;
    }

    public int getSabiduria() {
        return sabiduria;
    }

    public Long getRaza() {
        return raza;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setClase(Long clase) {
        this.clase = clase;
    }

    public void setConstitucion(int constitucion) {
        this.constitucion = constitucion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public void setRaza(Long raza) {
        this.raza = raza;
    }

    public void setSabiduria(int sabiduria) {
        this.sabiduria = sabiduria;
    }

    @Override
    public String toString() {
        return "PersonajeDto{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", publico=" + publico +
                ", imagen='" + imagen + '\'' +
                ", nivel=" + nivel +
                ", inteligencia=" + inteligencia +
                ", fuerza=" + fuerza +
                ", destreza=" + destreza +
                ", constitucion=" + constitucion +
                ", sabiduria=" + sabiduria +
                ", clase=" + clase +
                ", raza=" + raza +
                '}';
    }
}
