import java.util.Scanner;

class App {
  private static ArbolUsuarios usuarios = new ArbolUsuarios();
  private static ArbolCanciones canciones = new ArbolCanciones();

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    MostrarInicio(input);
  }

  public static void MostrarInicio(Scanner input) {
    int opcion = 0;
    while (opcion != 4) {
      System.out.println("╔══════════════════════════════╗");
      System.out.println("║           Spotreefy          ║");
      System.out.println("╠══════════════════════════════╣");
      System.out.println("║ 1. Iniciar sesion            ║");
      System.out.println("║ 2. Registrarse               ║");
      System.out.println("║ 3. Ver usuarios              ║");
      System.out.println("║ 4. Salir                     ║");
      System.out.println("╚══════════════════════════════╝");
      System.out.print("Selecione una opción: ");
      opcion = input.nextInt();
      input.nextLine();

      switch (opcion) {
        case 1:
          // Iniciar sesión
          String nombreLog = Verificaciones.validarNombreLog(input, usuarios);
          String contraseñaLog = Verificaciones.validarContraseña(input);
          MostrarMenu2(input);
          // Falta verificar si existe o no
          break;
        case 2:
          // Crear usuario
          String nombreReg = Verificaciones.validarNombreReg(input, usuarios);
          String contraseñaReg = Verificaciones.validarContraseña(input);
          NodoUsuario usuario = new NodoUsuario(nombreReg, contraseñaReg);
          usuarios.ingresar(usuario);
          usuarios.guardarEnArchivo();
          MostrarMenu2(input);
          break;
        case 3:
          // Ver lista de usuarios
          System.out.println("Lista de usuarios");
          usuarios.inOrder();

          System.out.print("Ingrese s para salir: ");
          input.nextLine();
          break;
        case 4:
          // Salir

          break;
        default:
          // codigo

          System.out.println("Opción no válida, seleccione una opción del 1 al 4");
          break;
      }
    }
  }

  public static void MostrarMenu2(Scanner input) {
    int opcion = 0;
    while (opcion != 7) {
      System.out.println("Bienvenido a Spotreefy!. Si no tenes cuenta, create una");
      System.out.println("╔══════════════════════════════╗");
      System.out.println("║           Spotreefy          ║");
      System.out.println("╠══════════════════════════════╣");
      System.out.println("║ 1. Agregar cancion           ║");
      System.out.println("║ 2. Crear Playlist            ║");
      System.out.println("║ 3. Agregar cancion (titulo)  ║");
      System.out.println("║ 4. Agregar cancion (autor)   ║");
      System.out.println("║ 5. Eliminar Playlist         ║");
      System.out.println("║ 6. Agregar Playlist (usuario)║");
      System.out.println("║ 7. Cerrar sesion             ║");
      System.out.println("╚══════════════════════════════╝");
      System.out.print("Selecione una opción: ");
      opcion = input.nextInt();
      input.nextLine();

      switch (opcion) {
        case 1:
          break;
        case 2:
          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          break;
        default:
          // codigo
          System.out.println("Opción no válida, seleccione una opción del 1 al 7");
          break;
      }
    }
  }

  private static void borrarTablero() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

}