class NodoPlaylistPropias {
  String nombre;
  NodoPlaylistPropias canciones;
  SubListasCanciones siguiente;

  public NodoPlaylistPropias( String nombre) [
        this.nombre = nombre;
        
    ]

  public String getNombre() {
    return this.nombre;
  }
}