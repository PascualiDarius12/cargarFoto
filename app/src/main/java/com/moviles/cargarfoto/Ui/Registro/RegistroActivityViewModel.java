package com.moviles.cargarfoto.Ui.Registro;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.moviles.cargarfoto.Modelo.Usuario;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RegistroActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Usuario> mUsuario;
    private MutableLiveData<Uri> uriMutableLiveData;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getmUsuario() {
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public LiveData<Uri> getUriMutable(){

        if(uriMutableLiveData==null){
            uriMutableLiveData=new MutableLiveData<>();
        }
        return uriMutableLiveData;
    }


    public void cargarUsuario(Usuario usuario){


            mUsuario.setValue(usuario);

    }

    public void guardarObjeto(Usuario usuario) {

        File archivo = new File(getApplication().getFilesDir(), "datos.dat");


        try {
            FileOutputStream fos = new FileOutputStream(archivo);//sin true para q se sobreescriba y pueda editar
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(usuario);


            bos.flush();
            fos.close();


            Toast.makeText(getApplication(), "Dato guardado", Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(), "Error al acceder al archivo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplication(), "Error al acceder al archivo", Toast.LENGTH_LONG).show();
        }
    }

    public void recibirFoto(ActivityResult result) {
        if(result.getResultCode() == RESULT_OK){
            Intent data=result.getData();
            Uri uri=data.getData();
            uriMutableLiveData.setValue(uri);


        }
    }



}
