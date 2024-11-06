import java.util.Scanner;

public class Verificaciones {
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";

  public void contraseña() {

  }

  public static String nombreLogin(Scanner input, String texto, ArbolUsuarios usuarios) {
    boolean datoValido = false;
    String dato = "";

    while (!datoValido) {
      try {
        System.out.print(texto);
        dato = input.nextLine();

        if (!usuarios.existe(dato))
          throw new Exception("El usuarios no existe");

        // verificacion
        if (dato == "")
          throw new Exception("No puede ingresar un dato vacio");

        if (esEntero(dato))
          throw new Exception("No puede ingresar un numero");

        datoValido = true;

      } catch (Exception exc) {
        System.out.println(ANSI_RED + exc.getMessage() + ANSI_RED);
        System.out.println(ANSI_RESET);

      }
    }

    return dato;
  }

  public static String nombreRegistro(Scanner input, String texto, ArbolUsuarios usuarios) {
    boolean datoValido = false;
    String dato = "";

    while (!datoValido) {
      try {
        System.out.print(texto);
        dato = input.nextLine();

        if (usuarios.existe(dato))
          throw new Exception("El usuarios ya existe");

        // verificacion
        if (dato == "")
          throw new Exception("No puede ingresar un dato vacio");

        if (esEntero(dato))
          throw new Exception("No puede ingresar un numero");

        datoValido = true;

      } catch (Exception exc) {
        System.out.println(ANSI_RED + exc.getMessage() + ANSI_RED);
        System.out.println(ANSI_RESET);

      }
    }

    return dato;
  }

  public static String ingresarContraseña(Scanner input, int min) {
    boolean datoValido = false;
    String dato = "";

    while (!datoValido) {
      try {
        System.out.print("Ingrese la contraseña: ");
        dato = input.nextLine();

        // verificacion
        if (dato == "")
          throw new Exception("No puede ingresar un dato vacio");

        if (dato.length() < min)
          throw new Exception("La contraseña debe tener minimo 6 caracteres");

        datoValido = true;

      } catch (Exception exc) {
        System.out.println(ANSI_RED + exc.getMessage() + ANSI_RED);
        System.out.println(ANSI_RESET);

      }
    }

    return dato;
  }

  public static boolean esEntero(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
