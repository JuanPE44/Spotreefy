import java.io.Serializable;

public class NodoPlaylistPropia implements Serializable {
  private String nombre;
  private String usuario;
  private NodoPlaylistPropia siguiente;
  private SubListaCanciones canciones;

  public NodoPlaylistPropia(String nombre, String usuario) {
    this.usuario = usuario;
    this.nombre = nombre;
    this.siguiente = null;
  }

  public String getNombre() {
    return nombre;
  }

  public String getUsuario() {
    return usuario;
  }

  public SubListaCanciones getCanciones() {
    return canciones;
  }

  public void setCanciones(SubListaCanciones canciones) {
    this.canciones = canciones;
  }

  public NodoPlaylistPropia getSiguiente() {
    return siguiente;
  }

  public void setSiguiente(NodoPlaylistPropia siguiente) {
    this.siguiente = siguiente;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return "NodoPlaylistPropia{nombre=" + nombre + ", usuario=" + usuario + ", siguiente=" + siguiente
        + ", canciones=" + canciones + "}";
  }
}
