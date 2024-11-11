
class NodoAutor {
    String nombre;
    NodoAutor siguiente;

    public NodoAutor(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }

    public String getNombre() {
        return this.nombre;
    }

}