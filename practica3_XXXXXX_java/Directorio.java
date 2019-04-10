
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
        if (elemento.nombre() == "") {
            // throw new ExcepcionNoExiste(elemento.nombre());
        } else {
            contenido.put(elemento.nombre(), elemento);
        }
    }

    public void eliminarElemento(Fichero elemento) {
        // EXCEPCION INSERTAR UN FICHERO ERRONEO
        if (elemento.nombre() == "") {

            // throw new ExcepcionFicheroErroneo();
        } else {
            contenido.remove(elemento.nombre(), elemento);
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
        return contenido.get(clave);
    }

    public String who() {
        return "Dir";
    }

}