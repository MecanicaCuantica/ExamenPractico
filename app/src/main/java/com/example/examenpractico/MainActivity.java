package com.example.examenpractico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

     public void Ingresar(){
        Intent ir = new Intent(this,Home.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        String vEmail = ed1.getText().toString();
        String vPassword = ed2.getText().toString();

        if(ed1.getText().toString().matches("") || ed2.getText().toString().matches("")){
            Toast.makeText(this, "Revise los campos",Toast.LENGTH_LONG).show();
        }else {
            for(int i=0; i<usuarios.length;i++){
                if(usuarios[i].equals(vEmail) && password[i].equals(vPassword)) {
                    Bundle datos = new Bundle();
                    datos.putString("name", ed1.getText().toString());
                    ir.putExtras(datos);
                    startActivity(ir);

                }
            }
        }
    }


    public  void dataVolley(View l){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://run.mocky.io/v3/044ad539-431c-4305-b752-f67beb23078b";
        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("usuarios");
                            for (int i = 0; i < response.getInt("count"); i++) {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                usuarios[i] = jsonObject2.getString("email");
                                password[i] = jsonObject2.getString("contraseña");
                                nombres[i] = jsonObject2.getString("nombre");
                            }
                            Ingresar();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        queue.add(req);
    }

}
