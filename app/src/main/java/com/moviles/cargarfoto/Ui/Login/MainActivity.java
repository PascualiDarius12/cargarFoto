package com.moviles.cargarfoto.Ui.Login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;


import com.moviles.cargarfoto.Modelo.Usuario;
import com.moviles.cargarfoto.Ui.Registro.RegistroActivity;
import com.moviles.cargarfoto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel vm;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        pedirPermiso();

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario usuario = vm.loginObjeto(binding.etMail.getText().toString(), binding.etPassword.getText().toString());
                if (usuario != null){
                    Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                }


            }
        });
    }

    private void pedirPermiso() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // no hay permisos, solicitar permisos
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    100);
        } else {
            // Los permisos ya están concedidos, hacer algo?

        }
    }



}