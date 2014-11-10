package ric.runeo.kaielth.rropa;

/**
 * Created by Kaielth on 09/11/2014.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ProductoDatabase extends SQLiteOpenHelper{
    //Versión de la Base de datos
    private static final int DATABASE_VERSION = 2;
    //Nombre de la base de datos
    private static final String DATABASE_NAME = "rropa";
    // Tablas
    // Tabla : producto
    private static final String TABLE_PRODUCTO = "producto";
    // Columnas de la tabla : producto
    private static final String KEY_PRODUCTO_ID = "id";
    private static final String KEY_PRODUCTO_NOMBRE = "nombre";
    private static final String KEY_PRODUCTO_PRECIO = "precio";
    private static final String KEY_PRODUCTO_IMAGEN = "imagen";
    private static final String KEY_PRODUCTO_DESCRIPCION = "descripcion";
    private static final String KEY_PRODUCTO_CATEGORIA = "categoria";

    private static final String[] PRODUCTO_COLUMNS = {KEY_PRODUCTO_ID, KEY_PRODUCTO_NOMBRE,
            KEY_PRODUCTO_PRECIO, KEY_PRODUCTO_IMAGEN, KEY_PRODUCTO_DESCRIPCION, KEY_PRODUCTO_CATEGORIA};

    public ProductoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Consulta para crear la tabla PRODUCTO
        String CREATE_PRODUCTO_TABLE = "CREATE TABLE producto ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "precio REAL, " +
                "imagen TEXT, " +
                "descripcion TEXT, " +
                "categoria TEXT)";

        //Creamos la tabla PRODUCTO
        sqLiteDatabase.execSQL(CREATE_PRODUCTO_TABLE);
        //Agregamos algunos productos base
        sqLiteDatabase.execSQL("INSERT INTO producto(nombre, precio, imagen, descripcion, categoria) VALUES('Easy Rib', 175.0, 'tshirt', 'Cómoda camisa en negro con estampado de detalle y bolsillos..', 'Camiseta')");
        sqLiteDatabase.execSQL("INSERT INTO producto(nombre, precio, imagen, descripcion, categoria) VALUES('Long Sleeve', 260.0, 'tshirt2', 'Dobladillo 69cm/27.25, Pecho 49cm/19.25, Manga 68cm/26.75','Camiseta')");
        sqLiteDatabase.execSQL("INSERT INTO producto(nombre, precio, imagen, descripcion, categoria) VALUES('Crew Neck', 130.0, 'tshirt3', 'Dobladillo 71cm/28, Pecho 51cm/20, Manga 19.5cm/7.5.','Camiseta')");
        sqLiteDatabase.execSQL("INSERT INTO producto(nombre, precio, imagen, descripcion, categoria) VALUES('Slim Jeans', 350.0, 'pants', 'Abotonado y cremallera oculta, cinco bolsillos.','Pantalon')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Quitamos la tabla antigua
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS producto");
        //Refrescamos la tabla de Productos
        this.onCreate(sqLiteDatabase);
    }

    public ArrayList<Producto> getProductos(){
        ArrayList<Producto> productos = new ArrayList<Producto>();

        //Consulta
        String query = "SELECT * FROM " + TABLE_PRODUCTO;

        //Referencia a la BD
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //Recorremos el resultado
        Producto producto = null;
        if(cursor.moveToFirst()){
            do {
                producto = new Producto();
                producto.setNombre(cursor.getString(1));
                producto.setPrecio(cursor.getDouble(2));
                producto.setImagen(cursor.getString(3));
                producto.setDescripcion(cursor.getString(4));
                producto.setCategoria(cursor.getString(5));

                //Agregamos el producto
                productos.add(producto);
            } while(cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return productos;
    }
}
