public class NodoSubLista {
  private NodoSubLista siguiente;
  private NodoCancion cancion;

  public NodoSubLista(NodoCancion cancion) {
    this.cancion = cancion;
    this.siguiente = null;
  }

  public NodoSubLista getSiguiente() {
    return siguiente;
  }

  public NodoCancion getCancion() {
    return this.cancion;
  }

  public void setSiguiente(NodoSubLista siguiente) {
    this.siguiente = siguiente;
  }
}
