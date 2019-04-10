import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionArbolFicheros extends Exception {
    String nombre_excepcion;

    ExcepcionArbolFicheros(String str) {
        nombre_excepcion = str;
    }

    public String toString() {
        return nombre_excepcion;
    }
    // Puede que haya que hacer algo aqui
    // o no
}
