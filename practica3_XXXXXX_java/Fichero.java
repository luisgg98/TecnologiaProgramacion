abstract class Fichero {

    protected String nombre;

    Fichero(String _nombre) {
        nombre = _nombre;
    }

    abstract public int tamanyo(int nvl) throws ExcepcionCiclo;

    public String nombre() {
        return nombre;
    }

    abstract public String who();
}