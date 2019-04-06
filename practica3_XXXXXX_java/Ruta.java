
import java.util.LinkedList;
import java.util.ListIterator;

class Ruta {

    LinkedList<Directorio> contenido;
    // ListIterator<Directorio> iterador;

    Ruta(Directorio _directorio) {
        // SALTA LA EXCEPCION SI NO EXISTE EL DIRECTORIO
        contenido = new LinkedList<Directorio>();
        contenido.addFirst(_directorio);
        // iterador = contenido.listIterator();
    }

    public String pwd() {
        String listado = "";

        for (ListIterator<Directorio> it = contenido.listIterator(); it.hasNext();) {
            Fichero ar = it.next();
            listado += "/";
            listado += ar.nombre();
        }

        return listado;

    }

    public void ls() {

        Directorio prueba = contenido.getLast();
        prueba.listar();

    }

    public void cd(String path) {

        if (path.equals(".")) {
            // Este caso podria ser una excepcion ya que no hace nada

        } else if (path.equals("..")) {
            contenido.pop();
            // Revisa si existe un elemento anterior
            // y en el caso de no haber salta una excepcion
            // Si existe anterior retrocede

        } else if (path.equals("/")) {

            Directorio raiz = contenido.getFirst();
            contenido.clear();
            contenido.addFirst(raiz);

        } else if (path.contains("/")) {
            // Tendriamos este formato
            // /home/deberes/aoc
            // agregar los directorios entre / / mientras
            // exista '/'
            // Comprobar la existencia de los directorios empezando por el raiz.
            // Excepción, algun elemento no es un directorio o enlace a directorio

        } else {
            // ¿Tiene que ser igual al siguiente?
            // Se puede emplementar en una lista o
            // utilizar otra estructura de datos
            // Directorio nuevo = iterador.next();
            // obtengo el next
            // Directorio incluido = nuevo.buscar(path);
            // busco en el next el diretorio
            // contenido.addLast(incluido);
            // lo añado al final de la ruta
            // iterador.hasNext();
            // me posiciono en ese directorio

        }

    }

    public void stat(String elem) {
        // EXCEPCION NO ENCONTRADO EL FICHERO DEL QUE MOSTRAR STATS
        // Fichero dato = contenido.getLast().buscar(elem);
        try {
            System.out.println(contenido.getLast().buscar(elem).tamanyo());

        } catch (ExcepcionNoEncontradoDirectorios e) {
            system.out.println(e.toString());
        }
    }

    public void vim(String file, int size) {
        // EXCEPCION SE INTENTA MODIFICAR UN FILE QUE NO EXISTE

    }

    public void mkdir(String dir) {

        Directorio direct = new Directorio(dir);
        // Crear un directorio que ya existe EXCEPCION
        contenido.getLast().anaydirElemento(direct);

    }

    public void ln(String orig, String dest) {
        // EXCEPCION Crear un enlace un directorio que no existe

    }

    public void rm(String e) {
        // EXCEPCION eliminar un directorio que no existe

    }

}