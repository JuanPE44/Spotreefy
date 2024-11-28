import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PlaylistPropias {
  private NodoPlaylistPropia primero;
  private final String rutaArchivo = "./archivos/playlists.ser";

  public PlaylistPropias() {
    this.primero = null;
  }

  public void insertarOrdenado(NodoPlaylistPropia playlist) {
    if (this.primero == null || this.primero.getNombre().compareTo(playlist.getNombre()) > 0) {
      playlist.setSiguiente(primero);
      this.primero = playlist;
    } else {
      // Si la lista no está vacía, recorremos hasta el último nodo
      NodoPlaylistPropia anterior = primero;
      NodoPlaylistPropia actual = primero.getSiguiente();

      while (actual != null && actual.getNombre().compareTo(playlist.getNombre()) < 0) {
        anterior = actual;
        actual = actual.getSiguiente();
      }
      playlist.setSiguiente(actual);
      anterior.setSiguiente(playlist);
    }
  }

  public void mostrar() {
    NodoPlaylistPropia actual = primero;
    while (actual != null) {
      System.out.println(actual.getNombre());
      actual = actual.getSiguiente();
    }
  }

  public void cargarCanciones(String usuario) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
      NodoPlaylistPropia actual;

      while ((actual = (NodoPlaylistPropia) ois.readObject()) != null) {
        if (actual.getUsuario().equals(usuario)) {
          insertarOrdenado(actual);
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("Archivo no encontrado. Creando archivo nuevo.");
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
      } catch (IOException exc) {
        exc.printStackTrace();
      }
    } catch (EOFException e) {
      System.out.println("Canciones cargados correctamente.");
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  public boolean existePlaylist(String nombre) {
    NodoPlaylistPropia actual = this.primero;
    while (actual != null) {
      if (actual.getNombre().equals(nombre)) {
        return true;
      }
      actual = actual.getSiguiente();
    }
    return false;
  }

  public void guardarEnArchivo() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
      NodoPlaylistPropia autorActual = this.primero;

      while (autorActual != null) {
        out.writeObject(autorActual);
        autorActual = autorActual.getSiguiente();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
