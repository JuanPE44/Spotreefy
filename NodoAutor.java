
class NodoAutor {
    private String nombre;
    private NodoAutor siguiente;
    private NodoCancion cancion;

    public NodoAutor(String nombre, NodoCancion cancion) {
        this.nombre = nombre;
        this.cancion = cancion;
        this.siguiente = null;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoAutor getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoAutor siguiente) {
        this.siguiente = siguiente;
    }

    public NodoCancion getCancion() {
        return this.cancion;
    }

}