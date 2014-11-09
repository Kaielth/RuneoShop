package ric.runeo.kaielth.rropa;

/**
 * Created by user on 8/11/14.
 */
public class Producto {
    private String nombre;
    private double precio;
    private String imagen;
    private String descripcion;

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    public String getImagen(){
        return imagen;
    }
    public String getDescripcion(){
        return descripcion;
    }

}
