
import java.io.Serializable;

public class NodoUsuario implements Serializable {
  private static final long serialVersionUID = 1L;
  private String nombre;
  private String contraseña;
  private PlaylistPropias playlists;
  private PlaylistSeguidas playlistsSeguidas;
  public NodoUsuario menores;
  public NodoUsuario mayores;

  public NodoUsuario(String nombre, String contraseña) {
    this.nombre = nombre;
    this.contraseña = contraseña;
    this.menores = null;
    this.mayores = null;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getContraseña() {
    return this.contraseña;
  }

  public NodoUsuario getMenores() {
    return this.menores;
  }

  public NodoUsuario getMayores() {
    return this.mayores;
  }

  @Override
  public String toString() {
    return "NodoUsuario{nombre=" + nombre + ", contraseña=" + contraseña + ", playlists=" + playlists
        + ", playlistSeguidas=" + playlistsSeguidas + ", menores=" + menores + ", mayores=" + mayores + "}";
  }
}
