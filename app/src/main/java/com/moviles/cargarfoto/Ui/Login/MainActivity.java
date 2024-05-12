package com.moviles.cargarfoto.Ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
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
}