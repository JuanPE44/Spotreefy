
public class NodoCancion {
  private String titulo;
  public NodoCancion menores;
  public NodoCancion mayores;
  public NodoCancion siguiente;

  public NodoCancion(String titulo) {
    this.titulo = titulo;
    this.menores = null;
    this.mayores = null;
    this.siguiente = null;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public NodoCancion getMenores() {
    return this.menores;
  }

  public NodoCancion getMayores() {
    return this.mayores;
  }
}
