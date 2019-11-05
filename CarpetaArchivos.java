package cl.juliogodoymunoz.EventoArchivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CarpetaArchivos implements Runnable,Serializable{
	
	private String ruta;
	private File[] contenido;
	private String[] listado;
	private String[] listadoAntiguo;
	private Carpeta interfaceCarpeta;
	private final String nombreArchivo;
	private String nombrePrueba="hola";
	
	CarpetaArchivos(String ruta) throws IOException{
    this.ruta=ruta;
    String aux[]=ruta.split("\\\\");
    nombreArchivo=aux[aux.length-1]+".txt";
    File carpeta=new File(ruta);
	contenido=carpeta.listFiles();
	
	File lista=new File(nombreArchivo);

	guardarLista(lista);
	
	
	}
    public void guardarLista(File lista) throws IOException {
    
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
    public void guardarLista() throws IOException {
        File lista=new File(nombreArchivo);
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
       // setNombrePrueba("juanito perez");
		//persistencia(this);
		while(true) {
      	    	while(contenido.length==listadoAntiguo.length){
      	    	  File archivo=new File(ruta);
		          contenido=archivo.listFiles();
		         }
		        persistenciaEntrada().carpetaPerformer();
		
		
		        try {
			          guardarLista();
		            } catch (IOException e) {
			               e.printStackTrace();
		            }
	           }
	}
	
	
	/*public void addCarpetaListener(Object o) throws IOException {
		interfaceCarpeta=(Carpeta)o;
		while(true) {
		run();
		interfaceCarpeta.carpetaPerformer();
		guardarLista();
		}
		
	}*/
	public Carpeta getInterface() {
		return interfaceCarpeta;
	}
	public void setInterface(Carpeta carpeta) {
	  interfaceCarpeta=carpeta;
	 }
	
	public void setNombrePrueba(String nombre) {
		nombrePrueba=nombre;
	}
	public void persistencia(Object objeto) {
		ObjectOutputStream salida;
		try {
			salida = new ObjectOutputStream(new FileOutputStream("carpetaArchivo.obj"));
			salida.writeObject(objeto);
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	 
	}
	public Carpeta persistenciaEntrada() {
		
		ObjectInputStream entrada;
		 Carpeta entregaObjeto=null;
		try {
			 entrada = new ObjectInputStream(new FileInputStream("carpetaArchivo.obj"));
			 entregaObjeto=(Carpeta)entrada.readObject();
			 entrada.close();
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
	    }
		
		return entregaObjeto;
	}
}
