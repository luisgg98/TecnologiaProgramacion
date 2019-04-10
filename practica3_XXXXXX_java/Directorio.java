
import java.util.HashMap;
import java.util.Map;

class Directorio extends Fichero {
    Map<String, Fichero> contenido;

    Directorio(String _nombre) {
        super(_nombre);
        contenido = new HashMap<String, Fichero>();
    }

    public void anaydirElemento(Fichero elemento) throws ExcepcionYaExiste {
        // EXCEPCION INSERTAR UN FICHERO ERRONEO
        if(contenido.get(elemento.nombre()) == null){
            contenido.put(elemento.nombre(), elemento);
        }else{
            throw new ExcepcionYaExiste(elemento.nombre());
        }
    
    }

    public void eliminarElemento(Fichero elemento){
        // EXCEPCION INSERTAR UN FICHERO ERRONEO
        
        contenido.remove(elemento.nombre(), elemento);
        
    }

    public int tamanyo(int nvl) throws ExcepcionCiclo {
        if( nvl < 15 ){
            int dato = 0;

            for (Map.Entry<String, Fichero> entry : contenido.entrySet()) {
                Fichero prueba = entry.getValue();
                dato += prueba.tamanyo(nvl+1);
            }

            return dato;
        }else{
            throw new ExcepcionCiclo();
        }
    }

    public void listar() {

        for (Map.Entry<String, Fichero> entry : contenido.entrySet()) {
            Fichero prueba = entry.getValue();
            System.out.println(prueba.nombre());
        }

    }

    public Fichero buscar(String clave) throws ExcepcionNoEncontrado{
        // EXCEPCION BUSCAR ALGO QUE NO EXISTE
        try{
            return contenido.get(clave);
        }catch(NullPointerException e){
            throw new ExcepcionNoEncontrado(clave);
        }
        
    }

    public String who() {
        return "Dir";
    }

}