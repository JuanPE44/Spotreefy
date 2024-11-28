public class SubListaCanciones {
  private NodoSubLista primero;

  public SubListaCanciones() {
    this.primero = null;
  }

  public void insertar(NodoCancion cancion) {
    NodoSubLista nuevo = new NodoSubLista(cancion);
    nuevo.setSiguiente(primero);
    primero = nuevo;
  }

  public boolean existeCancion(String titulo) {
    NodoSubLista actual = this.primero;
    while (actual != null) {
      if (actual.getCancion().getTitulo().equals(titulo)) {
        return true;
      }
      actual = actual.getSiguiente();
    }
    return false;
  }
}
