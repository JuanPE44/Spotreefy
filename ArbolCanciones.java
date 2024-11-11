
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArbolCanciones {
  private NodoCancion raiz;
  private final String rutaArchivo = "./archivos/canciones.ser";

  public void ingresar(NodoCancion usuario) {
    raiz = ingresarRec(raiz, usuario);
    this.cargarCanciones();
  }

  private NodoCancion ingresarRec(NodoCancion actual, NodoCancion usuario) {
    if (actual == null) {
      actual = new NodoCancion(usuario.getTitulo());
    } else if (actual.getTitulo().compareTo(usuario.getTitulo()) < 0) {
      actual.menores = ingresarRec(actual.getMenores(), usuario);
    } else if (actual.getTitulo().compareTo(usuario.getTitulo()) > 0) {
      actual.mayores = ingresarRec(actual.getMayores(), usuario);
    }
    return actual;
  }

  public void cargarCanciones() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
      NodoCancion actual;

      while ((actual = (NodoCancion) ois.readObject()) != null) {
        ingresar(actual);
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

  public void guardarEnArchivo() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
      guardarEnArchivoRec(raiz, out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void guardarEnArchivoRec(NodoCancion actual, ObjectOutputStream out) throws IOException {
    if (actual != null) {
      out.writeObject(actual);
      guardarEnArchivoRec(actual.getMenores(), out);
      guardarEnArchivoRec(actual.getMayores(), out);
    }
  }

  public void inOrder() {
    inOrderRec(raiz);
  }

  public void inOrderRec(NodoCancion actual) {
    if (actual != null) {
      inOrderRec(actual.getMenores());
      System.out.println("" + actual.getTitulo());
      inOrderRec(actual.getMayores());
    }
  }

}
