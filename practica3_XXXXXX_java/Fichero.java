abstract class Fichero {

    protected String nombre;

    Fichero(String _nombre) {
        nombre = _nombre;
    }

    abstract public int tamanyo();

    public String nombre() {
        return nombre;
    }
}