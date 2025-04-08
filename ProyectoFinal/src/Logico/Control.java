package Logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class Control implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private ArrayList<User> usuarios;
    private static volatile Control instancia;
    private static User usuarioLogueado;
    
    private Control() {
        usuarios = new ArrayList<>();
    }
    
    public static Control getInstance() {
        if (instancia == null) {
            synchronized (Control.class) { // Sincronizar el bloque para garantizar la concurrencia segura
                if (instancia == null) {
                    instancia = new Control();
                }
            }
        }
        return instancia;
    }
    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public static User getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(User usuarioLogueado) {
        Control.usuarioLogueado = usuarioLogueado;
    }

    public void registrarUsuario(User user) {
        usuarios.add(user);
    }

    public Optional<User> confirmarLogin(String nombreUsuario, String contrasena) {
        Optional<User> usuarioEncontrado = usuarios.stream()
                                                .filter(user -> user.getUserName().equals(nombreUsuario) && user.getPass().equals(contrasena))
                                                .findFirst();
        usuarioEncontrado.ifPresent(user -> usuarioLogueado = user);
        return usuarioEncontrado;
    }
    
    public void cargarUsuariosDesdeArchivo() {
        File archivoUsuarios = new File("usuarios.dat");
        if (!archivoUsuarios.exists()) {
            try {
                archivoUsuarios.createNewFile();
                System.out.println("Se ha creado un nuevo archivo 'usuarios.dat'");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo 'usuarios.dat'");
                e.printStackTrace();
                return; // Terminar el m�todo si ocurre un error al crear el archivo
            }
        }

        if (archivoUsuarios.length() == 0) {
            System.out.println("El archivo 'usuarios.dat' est� vac�o.");
            return; // Terminar el m�todo si el archivo est� vac�o
        }

        try (FileInputStream fileIn = new FileInputStream(archivoUsuarios);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            ArrayList<User> usuarios = (ArrayList<User>) objectIn.readObject();
            Control.getInstance().setUsuarios(usuarios);
        } catch (FileNotFoundException e) {
            // Manejar la excepci�n si el archivo no existe
            System.out.println("El archivo 'usuarios.dat' no se encontr�.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los usuarios desde el archivo 'usuarios.dat'");
            e.printStackTrace();
        }
    }
}
