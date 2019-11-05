package cl.juliogodoymunoz.EventoArchivo;

import java.io.IOException;
import java.io.Serializable;

public class ClasedePrueba implements Carpeta,Serializable{
    
	ClasedePrueba(){
		
		try {
			ArchivoRunnable archivoRunnable=new ArchivoRunnable("C:\\Users\\julio\\Desktop\\prueba2");
			archivoRunnable.addCarpetaListener(this);
			ArchivoRunnable archivo2=new ArchivoRunnable("C:\\Users\\julio\\Desktop\\anita");
			archivo2.addCarpetaListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void carpetaPerformer() {
		System.out.println("Se guardo o se elimino un archivo");
		
	}
}
