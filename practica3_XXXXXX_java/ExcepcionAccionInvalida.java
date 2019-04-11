import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionAccionInvalida extends ExcepcionArbolFicheros {
    ExcepcionAccionInvalida(String str) {
        super(str);
    }

    public String toString(){
        return nombre_excepcion;
    }
}