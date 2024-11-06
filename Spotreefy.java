import java.util.Scanner;

class Spotreefy {
  private static ArbolUsuarios usuarios = new ArbolUsuarios();

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    MostrarInicio(input);
  }

  public static void MostrarInicio(Scanner input) {
    int opcion = 0;
    while (opcion != 4) {
      System.out.println("Bienvenido a Spotreefy!. Si no tenes cuenta, create una");
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
          String nombreLog = Verificaciones.nombreLogin(input, "Ingrese el nombre del usuario: ", usuarios);
          String contraseñaLog = Verificaciones.ingresarContraseña(input, 6);
          MostrarMenu2(input);
          // Falta verificar si existe o no
          break;
        case 2:
          // Crear usuario
          String nombreReg = Verificaciones.nombreRegistro(input, "Ingrese el nombre del usuario: ", usuarios);
          String contraseñaReg = Verificaciones.ingresarContraseña(input, 6);
          MostrarMenu2(input);
          break;
        case 3:
          // Ver lista de usuarios
          usuarios.inOrder();
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