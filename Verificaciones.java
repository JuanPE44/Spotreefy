
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verificaciones {
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";

  public static String validarNombreReg(Scanner input, ArbolUsuarios usuarios) {
    String patron = "^.+$";
    return validarCadena(input, "Ingrese el nombre del usuario: ", patron, true, usuarios);
  }

  public static String validarNombreLog(Scanner input, ArbolUsuarios usuarios) {
    String patron = "^.+$";
    return validarCadena(input, "Ingrese el nombre del usuario: ", patron, false, usuarios);
  }

  public static String validarNombrePlaylist(Scanner input) {
    String patron = "^.+$";
    return validarCadena(input, "Ingrese el nombre de la playlist: ", patron, false, null);
  }

  public static String validarTitulo(Scanner input) {
    String patron = "^.{0,30}$";
    return validarCadena(input, "Ingrese el titulo: ", patron, false, null);
  }

  public static String validarAutor(Scanner input) {
    String patron = "^.{0,8}$";
    return validarCadena(input, "Ingrese el autor: ", patron, false, null);
  }

  public static String validarContraseña(Scanner input) {
    String patron = "^.{6,}$";
    return validarCadena(input, "Ingrese la contraseña: ", patron, false, null);
  }

  private static String validarCadena(Scanner input, String texto, String patron, boolean existe,
      ArbolUsuarios usuarios) {
    boolean datoValido = false;
    String dato = "";

    while (!datoValido) {
      try {
        System.out.print(texto);
        dato = input.nextLine();

        if (usuarios != null) {
          if (existe) {
            if (usuarios.existe(dato))
              throw new Exception("El usuarios ya existe");
          } else {
            if (!usuarios.existe(dato))
              throw new Exception("El usuarios no existe");
          }
        }

        if (dato == "")
          throw new Exception("No puede ingresar un dato vacio");

        if (!validarTexto(dato, patron))
          throw new Exception("Ingrese un dato valido");

        datoValido = true;

      } catch (Exception exc) {
        System.out.println(ANSI_RED + exc.getMessage() + ANSI_RED);
        System.out.println(ANSI_RESET);

      }
    }

    return dato;
  }

  public static boolean validarTexto(String texto, String patron) {
    Pattern pattern = Pattern.compile(patron);
    Matcher matcher = pattern.matcher(texto);
    return matcher.matches();
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
