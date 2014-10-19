package ric.runeo.kaielth.rropa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainShop extends ActionBarActivity {

    ImageView addShop01, addShop02, addShop03, addShop04, shop, category;
    TableRow product01, product02, product03, product04;
    TextView shopCounter;
    ArrayList<String> products;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);

        products = new ArrayList<String>();

        product01 = (TableRow) findViewById(R.id.product01);
        product02 = (TableRow) findViewById(R.id.product02);
        product03 = (TableRow) findViewById(R.id.product03);
        product04 = (TableRow) findViewById(R.id.product04);

        addShop01 = (ImageView)findViewById(R.id.addShopping01);
        addShop02 = (ImageView)findViewById(R.id.addShopping02);
        addShop03 = (ImageView)findViewById(R.id.addShopping03);
        addShop04 = (ImageView)findViewById(R.id.addShopping04);
        shop = (ImageView) findViewById(R.id.shop);
        category = (ImageView) findViewById(R.id.category);

        shopCounter = (TextView) findViewById(R.id.shop_counter);
        shopCounter.setText("0");

        addShop01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopCounter.setText(String.valueOf(Integer.parseInt(shopCounter.getText().toString())+1));
                products.add("Easy Rib");
            }
        });

        addShop02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopCounter.setText(String.valueOf(Integer.parseInt(shopCounter.getText().toString())+1));
                products.add("Long Sleeve");
            }
        });

        addShop03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopCounter.setText(String.valueOf(Integer.parseInt(shopCounter.getText().toString())+1));
                products.add("Crew Neck");
            }
        });

        addShop04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopCounter.setText(String.valueOf(Integer.parseInt(shopCounter.getText().toString())+1));
                products.add("Jeans");
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String compra = "Estos son los productos que has agregado:\n";
                for(int i = 0; i < products.size(); i++){
                    compra += products.get(i) + "\n";
                }
                showAlertDialog("Compra", compra);
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"Todos", "Camisetas", "Pantalones"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Elige tu categoría:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if(item == 0){
                            product01.setVisibility(View.VISIBLE);
                            product02.setVisibility(View.VISIBLE);
                            product03.setVisibility(View.VISIBLE);
                            product04.setVisibility(View.VISIBLE);
                        }else if(item == 1){
                            product01.setVisibility(View.VISIBLE);
                            product02.setVisibility(View.VISIBLE);
                            product03.setVisibility(View.VISIBLE);
                            product04.setVisibility(View.GONE);
                        }else if(item == 2){
                            product01.setVisibility(View.GONE);
                            product02.setVisibility(View.GONE);
                            product03.setVisibility(View.GONE);
                            product04.setVisibility(View.VISIBLE);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_shop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch(id){
            case R.id.menu_mision:
                showAlertDialog("Misión", getString(R.string.menu_mision_description));
                break;
            case R.id.menu_vision:
                showAlertDialog("Visión", getString(R.string.menu_vision_description));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialog(String title, String content){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
