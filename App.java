import java.util.Scanner;

class App {
  private static ListaAutores autores = new ListaAutores();
  private static ArbolUsuarios usuarios = new ArbolUsuarios();
  private static ArbolCanciones canciones = new ArbolCanciones(autores);
  private static PlaylistPropias playlistPropias = new PlaylistPropias();
  // private static PlaylistSeguidas playlistSeguidas = new PlaylistSeguidas();
  private static String nombreUsuario = "";

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
          nombreUsuario = Verificaciones.validarNombreLog(input, usuarios);
          String contraseñaLog = Verificaciones.validarContraseña(input);
          MostrarMenu2(input);
          break;
        case 2:
          nombreUsuario = Verificaciones.validarNombreReg(input, usuarios);
          String contraseñaReg = Verificaciones.validarContraseña(input);
          NodoUsuario usuario = new NodoUsuario(nombreUsuario, contraseñaReg);
          usuarios.ingresar(usuario);
          usuarios.guardarEnArchivo();
          MostrarMenu2(input);
          break;
        case 3:
          System.out.println("Lista de usuarios");
          usuarios.inOrder();
          System.out.print("Ingrese s para salir: ");
          input.nextLine();
          break;
        case 4:
          break;
        default:
          System.out.println("Opción no válida, seleccione una opción del 1 al 4");
          break;
      }
    }
  }

  public static void MostrarMenu2(Scanner input) {
    int opcion = 0;
    playlistPropias.cargarCanciones(nombreUsuario);
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
      System.out.println("Usuario: " + nombreUsuario);
      System.out.println("LISTA CANCIONES: ");
      canciones.inOrder();
      System.out.println();
      System.out.println("LISTA AUTORES: ");
      autores.mostrar();
      System.out.println();

      System.out.println("Playlist propias: ");
      playlistPropias.mostrar();
      System.out.print("Selecione una opción: ");
      opcion = input.nextInt();
      input.nextLine();

      switch (opcion) {
        case 1:
          String titulo = Verificaciones.validarTitulo(input);
          String autor = Verificaciones.validarAutor(input);
          NodoCancion cancion = new NodoCancion(titulo);
          if (autores.existeAutor(autor)) {
            NodoCancion primero = autores.buscarPrimero(autor);
            autores.insertarListaCircular(primero, cancion);
          } else {
            autores.insertarOrdenado(autor, cancion);
            autores.insertarListaCircular(null, cancion);
          }
          canciones.ingresar(cancion);
          System.out.print("Ingrese s para salir: ");
          input.nextLine();
          break;
        case 2:
          System.out.println("Crear Playlist");
          String nombrePlaylist = Verificaciones.validarNombrePlaylist(input);
          NodoPlaylistPropia playlist = new NodoPlaylistPropia(nombrePlaylist,
              nombreUsuario);
          System.out.println("1");
          playlistPropias.insertarOrdenado(playlist);
          System.out.println("2");
          playlistPropias.guardarEnArchivo();
          System.out.println("3");
          System.out.print("Ingrese s para salir: ");
          input.nextLine();
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
          autores.guardarEnArchivo();
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
