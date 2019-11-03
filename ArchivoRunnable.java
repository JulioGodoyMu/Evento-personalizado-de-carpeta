package cl.juliogodoymunoz.EventoArchivo;

import java.io.IOException;

public class ArchivoRunnable extends CarpetaArchivos {
     
	ArchivoRunnable(String ruta) throws IOException{
		super(ruta);
		Runnable runnable=new CarpetaArchivos(ruta);
		Thread hilo=new Thread(runnable);
		
		hilo.start();
		
		
	}
	
	
	
}
