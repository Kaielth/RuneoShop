package ric.runeo.kaielth.rropa;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class LogIn extends ActionBarActivity {

    Button btnLogIn, btnRegister;
    EditText txtNombre,txtPassword;
    File prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        btnLogIn = (Button)findViewById(R.id.btnLogIn);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

        prof = new File(this.getFilesDir(), "profile.txt");

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprueba()){
                    Intent i = new Intent(LogIn.this,Splash.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LogIn.this,"Datos erroneos",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this,SignIn.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean comprueba() {
        boolean tu = false;
        try {
            BufferedReader leer = new BufferedReader(new FileReader(prof));
            String nombre = leer.readLine();
            leer.readLine();
            leer.readLine();
            leer.readLine();
            leer.readLine();
            String cont = leer.readLine();

            if (nombre.equals(txtNombre.getText().toString()) && cont.equals(txtPassword.getText().toString())) {
                tu = true;
            } else {
                tu = false;
            }
        } catch (IOException e) {
            Toast.makeText(LogIn.this, "Error", Toast.LENGTH_SHORT).show();
        }
        return tu;
    }

}
