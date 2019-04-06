import java.lang.Exception;

class ExcepcionNoEncontradoDirectorios extends ExceptionArbolFicheros {
    String Directorio;

    ExcepcionNoEncontradoDirectorios(String str) {
        Directorio = str;
    }

    public String toString() {
        return ("El fichero buscado" + Directorio + "no ha sido encontrado.");
    }

}