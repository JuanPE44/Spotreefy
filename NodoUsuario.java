public class NodoUsuario {
  private String nombre;
  private String contraseña;
  private PlaylistPropias playlists;
  private PlaylistSeguidas playlistsSeguidas;
  private NodoUsuario menores;
  private NodoUsuario mayores;

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
}
