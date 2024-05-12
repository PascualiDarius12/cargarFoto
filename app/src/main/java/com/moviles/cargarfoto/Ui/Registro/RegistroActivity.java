package com.moviles.cargarfoto.Ui.Registro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.moviles.cargarfoto.Modelo.Usuario;
import com.moviles.cargarfoto.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private RegistroActivityViewModel vm;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        vm.getmUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etDni.setText(usuario.getDni().toString());
                binding.etEmail2.setText(usuario.getMail());
                binding.etPass2.setText(usuario.getPassword().toString());
            }
        });

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("entro", "si");
                Usuario usuario = new Usuario(binding.etNombre.getText().toString(),binding.etApellido.getText().toString(),
                        Long.parseLong(binding.etDni.getText().toString()),binding.etEmail2.getText().toString(),binding.etPass2.getText().toString());

                vm.guardarObjeto(usuario);
            }
        });

        Intent intent = getIntent();
        if(intent.hasExtra("usuario")){
            Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
            vm.cargarUsuario(usuario);

        }

        vm.getUriMutable().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivFoto.setImageURI(uri);
            }
        });


        binding.btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });
    }


    private void abrirGaleria(){


        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


        arl=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                vm.recibirFoto(result);


            }
        });


    }
}