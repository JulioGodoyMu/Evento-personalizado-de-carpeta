package cl.juliogodoymunoz.EventoArchivo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ArchivoRunnable extends CarpetaArchivos{
    static int contador=0;
    private Thread[] hilo=new Thread[20];
    
    
	ArchivoRunnable(String ruta) throws IOException{
		super(ruta);
		CarpetaArchivos r = new CarpetaArchivos(ruta);
		hilo[contador]= new Thread(r);	
	}
	
	public void addCarpetaListener(Object o) {
	super.setInterface((Carpeta)o);
	
	Object aux=(Object)o;
	super.persistencia(aux);
	hilo[contador].start();
	contador++;
	}
	
}
