import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ListaAutores {
  private NodoAutor primero;
  private final String rutaArchivo = "./archivos/canciones.ser";

  public ListaAutores() {
    this.primero = null;
  }

  public void insertarOrdenado(String nombreAutor, NodoCancion cancion) {
    NodoAutor autor = new NodoAutor(nombreAutor, cancion);
    if (this.primero == null || this.primero.getNombre().compareTo(autor.getNombre()) > 0) {
      autor.setSiguiente(primero);
      this.primero = autor;
    } else {
      // Si la lista no está vacía, recorremos hasta el último nodo
      NodoAutor anterior = primero;
      NodoAutor actual = primero.getSiguiente();

      while (actual != null && actual.getNombre().compareTo(autor.getNombre()) < 0) {
        anterior = actual;
        actual = actual.getSiguiente();
      }
      autor.setSiguiente(actual);
      anterior.setSiguiente(autor);
    }
  }

  public void insertarListaCircular(NodoCancion primero, NodoCancion nuevo) {

    if (primero == null) {
      // La lista está vacía
      nuevo.setSiguiente(nuevo); // Apunta a sí mismo para ser circular
    } else {
      // Insertar al final de la lista
      NodoCancion actual = primero;
      while (actual.siguiente != primero) { // Buscar el último nodo
        actual = actual.getSiguiente();
      }
      actual.setSiguiente(nuevo); // Conectar el último nodo al nuevo
      nuevo.setSiguiente(primero); // Conectar el nuevo nodo al primero
    }
  }

  public void mostrar() {
    NodoAutor actual = this.primero;
    while (actual != null) {
      System.out.print(" - " + actual.getNombre());
      actual = actual.getSiguiente();
    }
  }

  public boolean existeAutor(String autor) {
    NodoAutor actual = primero;
    while (actual != null) {
      if (actual.getNombre().equals(autor)) {
        return true;
      }
      actual = actual.getSiguiente();
    }
    return false;
  }

  public NodoCancion buscarPrimero(String nombreAutor) {
    if (nombreAutor == null) {
      return null; // Evitar comparar contra null
    }
    NodoAutor actual = this.primero;
    while (actual != null) {
      if (nombreAutor.equals(actual.getNombre())) {
        return actual.getCancion();
      }
      actual = actual.getSiguiente();
    }
    return null; // Retornar null si no se encuentra el autor
  }

  public void guardarEnArchivo() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
      NodoAutor autorActual = this.primero;

      while (autorActual != null) {
        System.out.println("AUTOR: " + autorActual.getNombre());

        NodoCancion primeraCancion = autorActual.getCancion();

        if (primeraCancion == null) {
          System.out.println("El autor " + autorActual.getNombre() + " no tiene canciones.");
        } else {
          NodoCancion actual = primeraCancion;

          do {
            System.out.println("CANCION: " + actual.getTitulo());

            // Crear objeto CancionArch con los datos actuales
            CancionArch cancion = new CancionArch(actual.getTitulo(), autorActual.getNombre());
            out.writeObject(cancion); // Escribir la canción al archivo

            actual = actual.getSiguiente();
          } while (actual != primeraCancion); // Volvemos al inicio de la lista de canciones
        }

        // Pasar al siguiente autor
        autorActual = autorActual.getSiguiente();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
