class Enlace extends Fichero {

    Fichero contenido;

    Enlace(Fichero _contenido, String _nombre) {
        super(_nombre);
        contenido = _contenido;
    }

    public int tamanyo() {
        return contenido.tamanyo();
    }

    public String who() {
        return "Link";
    }

    public String type() {
        return contenido.who();
    }
}