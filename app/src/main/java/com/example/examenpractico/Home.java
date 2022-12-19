package com.example.examenpractico;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private CheckBox tarea1, tarea2, tarea3, tarea4;
    private Button siguiente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tarea1 = findViewById(R.id.boxRespuesta1);
        tarea2 = findViewById(R.id.boxRespuesta2);
        tarea3 = findViewById(R.id.boxFalso1);
        tarea4 = findViewById(R.id.boxFalso2);
        siguiente = findViewById(R.id.btnSiguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tarea1.isChecked() && tarea2.isChecked() && tarea3.isChecked() && tarea4.isChecked()){
                    Intent i = new Intent(Home.this, Final.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(),"Completa TODAS LAS TAREAS!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void cerrarSesion(View l) {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Abrir MainActivity con SigIn button
               
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                Home.this.finish();   
            }
    }
    
    public void iniciarServicio(View m){
        //Intent irService = new Intent(this, MyService.class);
        //startService(irService);
        if(UtilsNetwork.isOnline(this)){
            Toast.makeText(getApplicationContext(),"Tiene internet ", Toast.LENGTH_SHORT).show();
        }
    }
    
}
