import java.util.LinkedList;
import java.util.ListIterator;

class Ruta {

    LinkedList<Directorio> contenido;
    // ListIterator<Directorio> iterador;

    Ruta(Directorio _directorio) throws ExcepcionElementoInvalido {
        // SALTA LA EXCEPCION SI NO EXISTE EL DIRECTORIO
        if (_directorio == null) {
            throw new ExcepcionElementoInvalido("Elemento es null");
        } else {
            contenido = new LinkedList<Directorio>();
            contenido.addFirst(_directorio);
        }
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

    public void cd(String path) throws ExcepcionNoEncontrado, ExcepcionElementoInvalido, ExcepcionFicheroRoot {
        LinkedList<Directorio> aux = new LinkedList<Directorio>();
        aux = (LinkedList<Directorio>) contenido.clone();

        LinkedList<String> a = localizar(path);

        cd_rec(a, aux);
        contenido = aux;
    }

    private void cd_rec(LinkedList<String> path, LinkedList<Directorio> aux)
            throws ExcepcionNoEncontrado, ExcepcionElementoInvalido, ExcepcionFicheroRoot {
        if (!path.isEmpty()) {
            String elemento = path.removeFirst();
            if (elemento.equals("/" + contenido.getFirst().nombre())) {
                aux.clear();
                aux.add(contenido.getFirst());
            } else if (elemento.equals("..")) {
                if (aux.size() == 1) {
                    throw new ExcepcionFicheroRoot("No hay ningun directorio anterior al actual");
                } else {
                    aux.removeLast();
                }
            } else if (elemento.equals(".")) {

            } else {
                Fichero f = aux.getLast().buscar(elemento);
                if (f != null) {
                    if (f.who().equals("Dir")) {
                        aux.add((Directorio) f);
                    } else if (f.who().equals("Link")) {
                        Enlace e = (Enlace) f;
                        if (e.type().equals("Dir")) {
                            aux.add((Directorio) e.contenido);
                        }
                    } else {
                        throw new ExcepcionElementoInvalido("El elemento " + elemento + " no es un directorio");
                    }
                } else {
                    throw new ExcepcionNoEncontrado(elemento);
                }
            }
            cd_rec(path, aux);
        }
    }

    public void stat(String elem) throws ExcepcionNoEncontrado, ExcepcionCiclo {

        if (elem.equals(".")) {
            System.out.println(contenido.getLast().tamanyo(0));
        } else {
            System.out.println(contenido.getLast().buscar(elem).tamanyo(0));
        }
    }

    public void vim(String file, int size) throws ExcepcionNoEncontrado, ExcepcionYaExiste {
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

    public void mkdir(String dir) throws ExcepcionYaExiste {

        Directorio direct = new Directorio(dir);
        // Crear un directorio que ya existe EXCEPCION
        contenido.getLast().anaydirElemento(direct);

    }

    public void ln(String orig, String dest)
            throws ExcepcionNoEncontrado, ExcepcionYaExiste, ExcepcionElementoInvalido, ExcepcionFicheroRoot {
        // EXCEPCION Crear un enlace un directorio que no existe
        Enlace e;
        if (orig.contains("/")) {
            LinkedList<Directorio> aux = new LinkedList<Directorio>();
            aux = (LinkedList<Directorio>) contenido.clone();

            LinkedList<String> a = localizar(orig);

            orig = a.removeLast();

            cd_rec(a, aux);

            if (orig.equals(".")) {
                e = new Enlace(aux.getLast(), dest);
            } else {
                e = new Enlace(aux.getLast().buscar(orig), dest);
            }
        } else {
            if (orig.equals(".")) {
                e = new Enlace(contenido.getLast(), dest);
            } else {
                e = new Enlace(contenido.getLast().buscar(orig), dest);
            }
        }

        contenido.getLast().anaydirElemento(e);

    }

    public void rm(String e)
            throws ExcepcionNoEncontrado, ExcepcionElementoInvalido, ExcepcionFicheroRoot, ExcepcionFicheroErroneo {
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

        for (String i : camino) {
            a.add(i);
        }

        if (path.charAt(0) == '/') {

            if (a.size() >= 1) {

                a.removeFirst();
                a.set(0, "/" + a.getFirst());

            } else if (a.size() == 0) {
                a.add(0, "/" + contenido.getFirst().nombre());
            }

        }

        return a;
    }

}