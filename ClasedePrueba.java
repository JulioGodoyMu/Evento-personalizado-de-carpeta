package cl.juliogodoymunoz.EventoArchivo;

import java.io.IOException;

public class ClasedePrueba implements Carpeta{
    
	ClasedePrueba(){
		
		try {
			ArchivoRunnable archivoRunnable=new ArchivoRunnable("C:\\Users\\julio\\Desktop\\prueba");
			archivoRunnable.addCarpetaListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void carpetaPerformer() {
		System.out.println("Se guardo o se elimino un archivo");
		
	}
}
