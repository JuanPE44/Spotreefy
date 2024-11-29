
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ArbolCanciones {
  private NodoCancion raiz;
  private final String rutaArchivo = "./archivos/canciones.ser";

  public ArbolCanciones(ListaAutores autores) {
    this.raiz = null;
    this.cargarCanciones(autores);
  }

  public void ingresar(NodoCancion cancion) {
    if (existeTitulo(cancion.getTitulo()))
      return;

    raiz = ingresarRec(raiz, cancion);
  }

  public boolean existeTitulo(String titulo) {
    return existeTituloRec(raiz, titulo);
  }

  private boolean existeTituloRec(NodoCancion actual, String titulo) {
    if (actual != null) {
      if (actual.getTitulo().equals(titulo)) {
        return true;
      } else {
        return existeTituloRec(actual.getMayores(), titulo) || existeTituloRec(actual.getMenores(), titulo);
      }
    }
    return false;
  }

  public NodoCancion BuscarCancion(String titulo) {
       return BuscarCancionRec(raiz, titulo);
    
  }

  private NodoCancion BuscarCancionRec(NodoCancion actual, String titulo){
    if  (actual != null) {
      if (actual.getTitulo().equals(titulo)) {
        return actual;
      } else {
        BuscarCancionRec(actual.getMenores());
        BuscarCancionRec(actual.getMayores());  
      }
    }
    return null;
  }

  private NodoCancion ingresarRec(NodoCancion actual, NodoCancion cancion) {
    if (actual == null) {
      actual = new NodoCancion(cancion.getTitulo());
    } else if (actual.getTitulo().compareTo(cancion.getTitulo()) < 0) {
      actual.menores = ingresarRec(actual.getMenores(), cancion);
    } else if (actual.getTitulo().compareTo(cancion.getTitulo()) > 0) {
      actual.mayores = ingresarRec(actual.getMayores(), cancion);
    }
    return actual;
  }

  public void inOrder() {
    inOrderRec(raiz);
  }

  public void inOrderRec(NodoCancion actual) {
    if (actual != null) {
      inOrderRec(actual.getMenores());
      System.out.print(" - " + actual.getTitulo());
      inOrderRec(actual.getMayores());
    }
  }

  public void cargarCanciones(ListaAutores autores) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
      CancionArch actual;

      while ((actual = (CancionArch) ois.readObject()) != null) {
        NodoCancion cancion = new NodoCancion(actual.getTitulo());
        String autor = actual.getAutor();
        if (autores.existeAutor(autor)) {
          NodoCancion primero = autores.buscarPrimero(autor);
          autores.insertarListaCircular(primero, cancion);
        } else {
          autores.insertarOrdenado(autor, cancion);
          autores.insertarListaCircular(null, cancion);
        }
        ingresar(cancion);
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

}
