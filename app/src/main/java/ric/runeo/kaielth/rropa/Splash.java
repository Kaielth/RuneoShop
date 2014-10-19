package ric.runeo.kaielth.rropa;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class Splash extends ActionBarActivity {

    ImageView imagen;
    ProgressBar progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imagen = (ImageView) findViewById(R.id.imagen);
        progreso = (ProgressBar) findViewById(R.id.progreso);

        Actualiza anima = new Actualiza(this);
        anima.execute();

    }

    public class Actualiza extends AsyncTask<Void, Integer, Void> {
        Context contexto;
        public Actualiza(Context c){
            contexto = c;
        }

        int trans = 255;
        @Override
        protected Void doInBackground(Void... voids) {
            int imagen = 0;
            boolean cambio = false;
            try{
                while(trans >= 0 && imagen < 4){
                    if(cambio){
                        trans = trans + 12;
                        if(trans > 255){
                            trans = 255;
                            cambio = false;
                        }
                    }else{
                        trans = trans - 12;
                        if (trans <= 0){
                            imagen++;
                            trans = 0;
                            cambio = true;
                        }
                    }
                    publishProgress(imagen, trans);
                    Thread.sleep(50);
                }
                /*publishProgress(0);
                Thread.sleep(1500);
                publishProgress(1);
                Thread.sleep(1500);
                publishProgress(2);
                Thread.sleep(1500);
                publishProgress(3);
                Thread.sleep(1500);*/
            }catch (InterruptedException e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intento = new Intent();
            intento.setClass(contexto, MainShop.class);
            startActivity(intento);
            finish();
        }

        /* @Override
        protected void  throws Throwable {
            super.finalize();
            Intent intento = new Intent();
            intento.setClass(contexto, paso2.class);
            startActivity(intento);
        }*/

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]){
                case 0:
                    imagen.setImageResource(R.drawable.teni9);
                    imagen.getDrawable().setAlpha(trans);
                    progreso.setProgress(0);
                    break;
                case 1:
                    imagen.setImageResource(R.drawable.falda9);
                    imagen.getDrawable().setAlpha(trans);
                    progreso.setProgress(1);
                    break;
                case 2:
                    imagen.setImageResource(R.drawable.camisa9);
                    imagen.getDrawable().setAlpha(trans);
                    progreso.setProgress(2);
                    break;
                case 3:
                    imagen.setImageResource(R.drawable.cachucha9);
                    imagen.getDrawable().setAlpha(trans);
                    progreso.setProgress(3);
                    break;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
}
