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
        LinkedList<Directorio> aux = new LinkedList<Directorio>();
        aux = (LinkedList<Directorio>) contenido.clone();

        LinkedList<String> a = localizar(path);

        cd_rec(a, aux);
        contenido = aux;
    }

    private void cd_rec(LinkedList<String> path, LinkedList<Directorio> aux) {
        if (!path.isEmpty()) {
            String elemento = path.removeFirst();
            if (elemento == "/") {
                aux.clear();
                aux.add(contenido.getFirst());
            } else if (elemento == "..") {
                aux.removeLast();
            } else if (elemento == ".") {

            } else {
                Fichero f = aux.getLast().buscar(elemento);
                if (f != null) {
                    if (f.who() == "Dir") {
                        aux.add((Directorio) f);
                    } else if (f.who() == "Link") {
                        Enlace e = (Enlace) f;
                        if (e.type() == "Dir") {
                            aux.add((Directorio) e.contenido);
                        }
                    }
                } else {
                    // throw exception;
                }
            }
            cd_rec(path, aux);
        }
    }

    public void stat(String elem) {
        // EXCEPCION NO ENCONTRADO EL FICHERO DEL QUE MOSTRAR STATS
        // Fichero dato = contenido.getLast().buscar(elem);
        System.out.println(contenido.getLast().buscar(elem).tamanyo());

    }

    public void vim(String file, int size) {
        // EXCEPCION SE INTENTA MODIFICAR UN FILE QUE NO EXISTE
        Fichero f = contenido.getLast().buscar(file);
        if (f != null && f.who() == "Archivo") {
            Archivo a = (Archivo) f;
            a.cambiarTamanyo(size);
        } else {
            Archivo nuevo = new Archivo(size, file);
            contenido.getLast().anaydirElemento(nuevo);
            // throw exception;
        }
    }

    public void mkdir(String dir) {

        Directorio direct = new Directorio(dir);
        // Crear un directorio que ya existe EXCEPCION
        contenido.getLast().anaydirElemento(direct);

    }

    public void ln(String orig, String dest) {
        // EXCEPCION Crear un enlace un directorio que no existe
        if (orig.contains("/")) {
            LinkedList<Directorio> aux = new LinkedList<Directorio>();
            aux = (LinkedList<Directorio>) contenido.clone();

            LinkedList<String> a = localizar(orig);

            orig = a.removeLast();

            cd_rec(a, aux);

            Enlace e = new Enlace(aux.getLast().buscar(orig), dest);
            contenido.getLast().anaydirElemento(e);

        } else {
            Enlace e = new Enlace(contenido.getLast().buscar(orig), dest);
            contenido.getLast().anaydirElemento(e);
        }
    }

    public void rm(String e) {
        if (e.contains("/")) {
            LinkedList<Directorio> aux = new LinkedList<Directorio>();
            aux = (LinkedList<Directorio>) contenido.clone();

            LinkedList<String> a = localizar(e);

            e = a.removeLast();

            cd_rec(a, aux);

            aux.getLast().eliminarElemento(aux.getLast().buscar(e));

        } else {
            contenido.getLast().eliminarElemento(contenido.getLast().buscar(e));
        }

    }

    private LinkedList<String> localizar(String path) {
        LinkedList<String> a = new LinkedList<String>();

        String[] camino = path.split("/");

        if (path.charAt(0) == '/') {
            a.add("/");
        }
        for (String i : camino) {
            a.add(i);
        }

        return a;
    }

}