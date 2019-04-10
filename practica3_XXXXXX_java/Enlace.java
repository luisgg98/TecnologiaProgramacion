class Enlace extends Fichero {

    Fichero contenido;

    Enlace(Fichero _contenido, String _nombre) {
        super(_nombre);
        contenido = _contenido;
    }

    public int tamanyo(int nvl) throws ExcepcionCiclo {
        if(nvl < 15){
            return contenido.tamanyo(nvl+1);
        }
        else{
            throw new ExcepcionCiclo();
        }
    }

    public String who() {
        return "Link";
    }

    public String type() {
        return contenido.who();
    }
}