package com.moviles.cargarfoto.Ui.Login;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.moviles.cargarfoto.Modelo.Usuario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivityViewModel extends AndroidViewModel {
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public Usuario loginObjeto(String email, String password) {
        File archivo = new File(getApplication().getFilesDir(), "datos.dat");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) {
                Usuario usuario = (Usuario) ois.readObject();
                // trim() para eliminar espacios en blanco, por q eso me daba error
                if (usuario.getMail().trim().equals(email.trim()) && usuario.getPassword().trim().equals(password.trim())) {
                    return usuario;
                }
            }
        } catch (EOFException e) {
            // Se alcanzó el final del archivo, usuario no encontrado
            Toast.makeText(getApplication(), "Usuario no encontrado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(), "Archivo no encontrado", Toast.LENGTH_LONG).show();
            Log.e("salida", "Archivo no encontrado: " + e.getMessage(), e);
        } catch (IOException | ClassNotFoundException e) {
            Toast.makeText(getApplication(), "Error de E/S", Toast.LENGTH_LONG).show();
            Log.e("salida", "Error de E/S: " + e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}
