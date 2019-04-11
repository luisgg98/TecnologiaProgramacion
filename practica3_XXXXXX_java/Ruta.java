import java.util.LinkedList;
import java.util.ListIterator;

class Ruta {

    LinkedList<Directorio> contenido;
    // ListIterator<Directorio> iterador;

    Ruta(Directorio _directorio) throws ExcepcionAccionInvalida{
        // SALTA LA EXCEPCION SI NO EXISTE EL DIRECTORIO
        if(_directorio == null){
            throw new ExcepcionAccionInvalida("Elemento es null");
        }else{
            contenido = new LinkedList<Directorio>();
            contenido.addFirst(_directorio);
        }
        // iterador = contenido.listIterator();
    }

    public String pwd() {
        String listado = "";

        for (ListIterator<Directorio> it = contenido.listIterator(1); it.hasNext();) {
            Fichero ar = it.next();
            listado += "/";
            listado += ar.nombre();
        }

        if(listado.equals("")){
            return "/";
        }else{
            return listado;
        }
    }

    public void ls() {

        Directorio prueba = contenido.getLast();
        prueba.listar();

    }

    public void cd(String path) throws ExcepcionNoEncontrado, ExcepcionAccionInvalida, ExcepcionFicheroRoot {
        LinkedList<Directorio> aux = new LinkedList<Directorio>();
        aux = (LinkedList<Directorio>) contenido.clone();

        LinkedList<String> a = localizar(path);

        cd_rec(a, aux);
        contenido = aux;
    }

    private void cd_rec(LinkedList<String> path, LinkedList<Directorio> aux) throws ExcepcionNoEncontrado, ExcepcionAccionInvalida, ExcepcionFicheroRoot {
        if (!path.isEmpty()) {
            String elemento = path.removeFirst();
            if (elemento.equals("/")) {
                aux.clear();
                aux.add(contenido.getFirst());
            } else if (elemento.equals("..")) {
                if(aux.size() == 1){
                    throw new ExcepcionFicheroRoot("No hay ningun directorio anterior al actual");
                }else{
                    aux.removeLast();
                }
            } else if (elemento.equals(".") || elemento.equals(aux.getLast().nombre)) {

            } else {
                Fichero f = aux.getLast().buscar(elemento);
                if (f != null) {
                    if (f.who().equals("Dir")) {
                        aux.add((Directorio) f);
                    } else if (f.who().equals("Link")) {
                        Enlace e = (Enlace) f;
                        aux.add((Directorio) zelda(e));
                    }else {
                        throw new ExcepcionAccionInvalida("El elemento "+ elemento + " no es un directorio");
                    }
                } else {
                    throw new ExcepcionNoEncontrado(elemento);
                }
            }
            cd_rec(path, aux);
        }
    }

    private Fichero zelda(Enlace e){
        if ( !e.type().equals("Link")) {
            return e.contenido;
        }else{
            return zelda((Enlace)e.contenido);
        }
    }

    public void stat(String elem) throws ExcepcionNoEncontrado ,ExcepcionCiclo, ExcepcionFicheroRoot, ExcepcionAccionInvalida {

        if (elem.contains("/")) {
            LinkedList<Directorio> aux = new LinkedList<Directorio>();
            aux = (LinkedList<Directorio>) contenido.clone();

            LinkedList<String> a = localizar(elem);

            elem = a.removeLast();

            cd_rec(a, aux);
           
            System.out.println(seleccion(aux, elem).tamanyo(0));

        }else{
            System.out.println(seleccion(contenido, elem).tamanyo(0));
        }
        

    }

    public void vim(String file, int size) throws ExcepcionNoEncontrado, ExcepcionYaExiste, ExcepcionNombreInvalido  {
        // EXCEPCION SE INTENTA MODIFICAR UN FILE QUE NO EXISTE
        Fichero f = contenido.getLast().buscar(file);
        if (f != null) {
            if(f.who().equals("Link")){ 
                f = zelda((Enlace)f);
            }
            if(f.who().equals("Archivo")){
                Archivo a = (Archivo) f;
                a.cambiarTamanyo(size);
            }
        } else if(file.equals(".") || file.equals("..") || file.contains("/") || file.contains(" ")) {
            throw new ExcepcionNombreInvalido(file);
        } else{
            Archivo nuevo = new Archivo(size, file);
            contenido.getLast().anaydirElemento(nuevo);
        }
    }

    public void mkdir(String dir) throws ExcepcionYaExiste, ExcepcionNombreInvalido {

        if(dir.equals(".") || dir.equals("..") || dir.contains("/") || dir.contains(" ")){
            throw new ExcepcionNombreInvalido(dir);
        }else{
            Directorio direct = new Directorio(dir);
            contenido.getLast().anaydirElemento(direct);
        }
        

    }

    public void ln(String orig, String dest) throws ExcepcionNoEncontrado, ExcepcionYaExiste, ExcepcionAccionInvalida, ExcepcionFicheroRoot, ExcepcionNombreInvalido  {
        // EXCEPCION Crear un enlace un directorio que no existe
        if(dest.equals(".") || dest.equals("..") || dest.contains("/") || dest.contains(" ")){
            throw new ExcepcionNombreInvalido(dest);
        }else{

            Enlace e;
            if (orig.contains("/")) {
                LinkedList<Directorio> aux = new LinkedList<Directorio>();
                aux = (LinkedList<Directorio>) contenido.clone();
    
                LinkedList<String> a = localizar(orig);
    
                orig = a.removeLast();
    
                cd_rec(a, aux);
               
                e = new Enlace(seleccion(aux,orig),dest);
    
            }else{
                e = new Enlace(seleccion(contenido,orig),dest);
            }
    
            contenido.getLast().anaydirElemento(e);
    
        }
    }

    private Fichero seleccion(LinkedList<Directorio> aux, String orig) throws ExcepcionNoEncontrado, ExcepcionFicheroRoot {
        if(orig.equals(".")){
            return aux.getLast();
        }
        else if(orig.equals("..")){

            if(aux.size() == 1){
                throw new ExcepcionFicheroRoot("No hay ningun directorio anterior al actual");
            }else{
                return aux.get(aux.size()-2);
            }
            
        }
        else if(orig.equals("/")){
            return aux.getFirst();
        }
        else{
            if(aux.getLast().buscar(orig) != null){
                return aux.getLast().buscar(orig);
            }else{
                throw new ExcepcionNoEncontrado(orig);
            }
        }
    }
    
    public void rm(String e) throws ExcepcionNoEncontrado, ExcepcionAccionInvalida, ExcepcionFicheroRoot {
        if (e.contains("/")) {
            LinkedList<Directorio> aux = new LinkedList<Directorio>();
            aux = (LinkedList<Directorio>) contenido.clone();

            LinkedList<String> a = localizar(e);

            e = a.removeLast();

            cd_rec(a, aux);

            if (aux.getLast().buscar(e) != null ){
                aux.getLast().eliminarElemento(aux.getLast().buscar(e));
            }
            else{
                throw new ExcepcionNoEncontrado(e);
            }

        } else {
            
            if (contenido.getLast().buscar(e) != null ){
                contenido.getLast().eliminarElemento(contenido.getLast().buscar(e));
            }
            else{
                throw new ExcepcionNoEncontrado(e);
            }

        }

    }

    private LinkedList<String> localizar(String path) {
        LinkedList<String> a = new LinkedList<String>();

        String[] camino = path.split("/");

        for (String i : camino) {
            a.add(i);
        }   

        if (path.charAt(0) == '/') {

            if(a.size() == 0){
                a.add("/");
            }
            else{
                a.set(0, "/");
            }
        
        }
      
        return a;
    }

}