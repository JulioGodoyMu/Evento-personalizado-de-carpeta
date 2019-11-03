package cl.juliogodoymunoz.EventoArchivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CarpetaArchivos implements Runnable{
	
	private String ruta;
	private File[] contenido;
	private String[] listado;
	private String[] listadoAntiguo;
	private Carpeta interfaceCarpeta;
	private Object objeto;
	CarpetaArchivos(String ruta) throws IOException{
    this.ruta=ruta;
	File carpeta=new File(ruta);
	contenido=carpeta.listFiles();
	if(listadoAntiguo==null) {
		File lista=new File("listado.txt");
		guardarLista(lista);
	}
	
	}
    public void guardarLista(File lista) throws IOException {
    
    	listadoAntiguo=new String[contenido.length];
       
        FileWriter escritura=new FileWriter(lista,true);
    	for(int i=0;i<contenido.length;i++) {
    			if(i==0){
    			   escritura.write(contenido[i].getName());
    			}else {    				
				   escritura.write("\n"+contenido[i].getName());
    			}
			    listadoAntiguo[i]=contenido[i].getName();
    	}
    	escritura.close();
    }
    public void guardarLista() throws IOException {
        File lista=new File("listado.txt");
    	listadoAntiguo=new String[contenido.length];
       
        FileWriter escritura=new FileWriter(lista,false);
    	for(int i=0;i<contenido.length;i++) {
    			if(i==0){
    			   escritura.write(contenido[i].getName());
    			}else {    				
				   escritura.write("\n"+contenido[i].getName());
    			}
			    listadoAntiguo[i]=contenido[i].getName();
    	}
    	escritura.close();
    }
    public void llenarListado(){
       listado=new String[contenido.length];
       for(int i=0;i<listado.length;i++) {
    	   listado[i]=contenido[i].getName();
       }
    
    }
    public String[] getListadoAntiguo() {
    	return listadoAntiguo;
    }
    public String[] listado() {
    	llenarListado();
    	return listado;
    }
	@Override
	public void run() {
		while(contenido.length==listadoAntiguo.length){
		File archivo=new File(ruta);
		contenido=archivo.listFiles();
		}
		
	}
	
	
	public void addCarpetaListener(Object o) throws IOException {
		interfaceCarpeta=(Carpeta)o;
		while(true) {
		run();
		interfaceCarpeta.carpetaPerformer();
		guardarLista();
		}
		
	}
	public Object getInterface() {
		return interfaceCarpeta;
	}
}
