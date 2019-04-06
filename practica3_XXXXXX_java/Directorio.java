
import java.util.HashMap;
import java.util.Map;

class Directorio extends Fichero {
    Map<String, Fichero> contenido;

    Directorio(String _nombre) {
        super(_nombre);
        contenido = new HashMap<String, Fichero>();
    }

    public void anaydirElemento(Fichero elemento) {
        // EXCEPCION INSERTAR UN FICHERO ERRONEO
        if(elemento.nombre()==""){
            throw new ExcepcionFicheroErroneo();
        }
        else{
            contenido.put(elemento.nombre(), elemento);
        }
        catch (ExcepcionFicheroErroneo e){
            system.out.println(e.toString());
        }
    }

    public int tamanyo() {
        int dato = 0;

        for (Map.Entry<String, Fichero> entry : contenido.entrySet()) {
            Fichero prueba = entry.getValue();
            dato += prueba.tamanyo();
        }

        return dato;
    }

    public void listar() {

        for (Map.Entry<String, Fichero> entry : contenido.entrySet()) {
            Fichero prueba = entry.getValue();
            System.out.println(prueba.nombre());
        }

    }

    public Fichero buscar(String clave) {
        // EXCEPCION BUSCAR ALGO QUE NO EXISTE
        try {
            return contenido.get(clave);
            throw new ExcepcionNoEncontradoDirectorios(clave);
        } catch (ExcepcionNoEncontradoDirectorios e) {
            system.out.println(e.toString());
        }
    }

}