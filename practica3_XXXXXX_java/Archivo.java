
class Archivo extends Fichero {

    protected int tamanyo;

    Archivo(int _tamanyo, String _nombre) {
        super(_nombre);
        tamanyo = _tamanyo;
    }

    public int tamanyo(int nvl) {
        return tamanyo;
    }

    public void cambiarTamanyo(int numero) {
        tamanyo = numero;
    }

    public String who() {
        return "Archivo";
    }

}