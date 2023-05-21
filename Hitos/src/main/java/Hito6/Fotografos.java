package Hito6;
import java.util.ArrayList;

public class Fotografos {
    String name;
    Conexion c;
    private ArrayList<String> matriz = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Conexion getC() {
        return c;
    }

    public ArrayList<String> getMatriz() {
        return matriz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setC(Conexion c) {
        this.c = c;
    }

    public void setMatriz(ArrayList<String> matriz) {
        this.matriz = matriz;
    }

    public Fotografos() {
        c = new Conexion();
    }

    public void tenerNombres(){
        this.matriz = c.tenerFotografos();
    }

}