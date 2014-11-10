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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainShop extends ActionBarActivity {

    ImageView addShop01, addShop02, addShop03, addShop04, shop, category;
    ArrayList<TableRow> product00;
    TextView shopCounter;
    ArrayList<String> products;
    ArrayList<Producto> productos;
    Context context = this;
    private ViewGroup tabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);
        tabla = (ViewGroup) findViewById(R.id.contenedor);

        products = new ArrayList<String>();
        product00 = new ArrayList<TableRow>();

        shop = (ImageView) findViewById(R.id.shop);
        category = (ImageView) findViewById(R.id.category);

        ProductoDatabase pd = new ProductoDatabase(context);
        productos = pd.getProductos();
        agregarElemento(productos);

        shop = (ImageView) findViewById(R.id.shop);
        category = (ImageView) findViewById(R.id.category);

        shopCounter = (TextView) findViewById(R.id.shop_counter);
        shopCounter.setText("0");

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
                            for (int i = 0; i < product00.size(); i++){
                                product00.get(i).setVisibility(View.VISIBLE);
                            }
                        }else if(item == 1){
                            for (int i = 0; i < product00.size(); i++){
                                if(!productos.get(i).getCategoria().equals("Camiseta")){
                                    product00.get(i).setVisibility(View.GONE);
                                }else{
                                    product00.get(i).setVisibility(View.VISIBLE);
                                }
                            }
                        }else if(item == 2){
                            for (int i = 0; i < product00.size(); i++){
                                if(!productos.get(i).getCategoria().equals("Pantalon")){
                                    product00.get(i).setVisibility(View.GONE);
                                }else{
                                    product00.get(i).setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public static String nombreProducto;
    public void agregarElemento(ArrayList<Producto> x){
        for(int y = 0; y < x.size(); y++) {
            nombreProducto = x.get(y).getNombre();
            System.out.println("los productos a agregar son " + x.size());

            System.out.println("este es el producto " + y + " y su nombre es " + x.get(y).getNombre());

            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.elementos;
            TableRow product = (TableRow) inflater.inflate(id, null, false);

            int imagenID = context.getResources().getIdentifier(x.get(y).getImagen(), "drawable-xxhdpi", context.getPackageName());

            ImageView imagen = (ImageView) product.findViewById(R.id.imagenProducto);
            imagen.setImageResource(imagenID);

            TextView nombre = (TextView) product.findViewById(R.id.nombreProducto);
            nombre.setText(x.get(y).getNombre());

            TextView descripcion = (TextView) product.findViewById(R.id.descripcion);
            descripcion.setText(x.get(y).getDescripcion());

            TextView precio = (TextView) product.findViewById(R.id.precio);
            precio.setText(Double.valueOf(x.get(y).getPrecio()).toString());

            ImageView addShop01 = (ImageView) product.findViewById(R.id.addShopping01);
            addShop01.setOnClickListener(new View.OnClickListener() {
                String nombreProducto = MainShop.nombreProducto;

                @Override
                public void onClick(View view) {
                    shopCounter.setText(String.valueOf(Integer.parseInt(shopCounter.getText().toString())+1));
                    products.add(this.nombreProducto);
                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.bottomMargin = 20;
            product.setLayoutParams(params);
            product00.add(product);
            tabla.addView(product);
        }
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
