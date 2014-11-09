package ric.runeo.kaielth.rropa;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SignIn extends ActionBarActivity {

    Button btnSignUp;
    EditText txtName,txtAddress, txtPass,txtPass2;
    DatePicker dpiBirthDay;
    File prof;
    TextView txvPass,txvPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        txtName = (EditText)findViewById(R.id.txtName);
        txtAddress = (EditText)findViewById(R.id.txtAddress);
        txtPass = (EditText)findViewById(R.id.txtPassword);
        txtPass2 = (EditText)findViewById(R.id.txtPassword2);
        dpiBirthDay = (DatePicker)findViewById(R.id.dpiCumple);
        txvPass = (TextView)findViewById(R.id.txvPass);
        txvPass2 = (TextView)findViewById(R.id.txvPass2);
        prof = new File(this.getFilesDir(), "profile.txt");

    }

    public void singUp (View v){
        if (txtName.getText().toString().equals("")||txtAddress.getText().toString().equals("")){
            Toast.makeText(this,"Rellena los campos faltantes",Toast.LENGTH_SHORT).show();
        }else if(dpiBirthDay.getYear()<=1914||dpiBirthDay.getYear()>=2000){
            Toast.makeText(this,"Completa los campos correctamente",Toast.LENGTH_SHORT).show();
        }else{
            savePrf();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sing_in, menu);
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
    public void savePrf(){
        try{
            BufferedWriter escribir = new BufferedWriter(new FileWriter(prof));
            escribir.write(txtName.getText().toString());
            escribir.newLine();
            escribir.write(txtAddress.getText().toString());
            escribir.newLine();
            escribir.write(Integer.valueOf(dpiBirthDay.getDayOfMonth()).toString());
            escribir.newLine();
            escribir.write(Integer.valueOf(dpiBirthDay.getMonth()).toString());
            escribir.newLine();
            escribir.write(Integer.valueOf(dpiBirthDay.getYear()).toString());
            escribir.newLine();

            if(txtPass.getText().toString().equals("")){
                Toast.makeText(this,"Proporciona una contraseña",Toast.LENGTH_SHORT).show();
            }else if (txtPass.getText().toString().equals(txtPass2.getText().toString())){
                escribir.write(txtPass.getText().toString());
                txvPass2.setTextColor(Color.GREEN);
                txvPass.setTextColor(Color.GREEN);
                escribir.close();
                Intent i = new Intent(this,Splash.class);
                startActivity(i);
            }else{
                Toast.makeText(this,"La contraseña no coincide",Toast.LENGTH_SHORT).show();
                txvPass2.setTextColor(Color.RED);
            }
        }catch (IOException e){
            Toast.makeText(this,"Ha ocurrido un error :(",Toast.LENGTH_SHORT).show();
        }
    }
}
