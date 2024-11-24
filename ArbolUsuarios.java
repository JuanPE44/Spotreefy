
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArbolUsuarios {
  private NodoUsuario raiz;
  private final String rutaArchivo = "./archivos/usuarios.ser";

  public ArbolUsuarios() {
    this.raiz = null;
    this.cargarUsuarios();
  }

  public void ingresar(NodoUsuario usuario) {
    raiz = ingresarRec(raiz, usuario);
  }

  private NodoUsuario ingresarRec(NodoUsuario actual, NodoUsuario usuario) {
    if (actual == null) {
      actual = new NodoUsuario(usuario.getNombre(), usuario.getContraseña());
    } else if (actual.getNombre().compareTo(usuario.getNombre()) < 0) {
      actual.menores = ingresarRec(actual.getMenores(), usuario);
    } else if (actual.getNombre().compareTo(usuario.getNombre()) > 0) {
      actual.mayores = ingresarRec(actual.getMayores(), usuario);
    }
    return actual;
  }

  public void cargarUsuarios() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
      NodoUsuario actual;

      while ((actual = (NodoUsuario) ois.readObject()) != null) {
        ingresar(actual);
      }

    } catch (FileNotFoundException e) {
      System.out.println("Archivo no encontrado. Creando archivo nuevo.");
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
      } catch (IOException exc) {
        exc.printStackTrace();
      }
    } catch (EOFException e) {
      System.out.println("Usuarios cargados correctamente.");
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

  private void guardarEnArchivoRec(NodoUsuario actual, ObjectOutputStream out) throws IOException {
    if (actual != null) {
      out.writeObject(actual);
      guardarEnArchivoRec(actual.getMenores(), out);
      guardarEnArchivoRec(actual.getMayores(), out);
    }
  }

  public void inOrder() {
    inOrderRec(raiz);
  }

  public void inOrderRec(NodoUsuario actual) {
    if (actual != null) {
      inOrderRec(actual.getMenores());
      System.out.println("" + actual.getNombre());
      inOrderRec(actual.getMayores());
    }
  }

  public boolean existe(String nombre) {
    boolean existe = existeRec(raiz, nombre);
    return existe;
  }

  public boolean existeRec(NodoUsuario actual, String nombre) {
    if (actual != null) {
      if (actual.getNombre().compareTo(nombre) == 0) {
        return true;
      }
      return existeRec(actual.getMenores(), nombre) || existeRec(actual.getMayores(), nombre);
    }
    return false;
  }

}
