import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionElementoInvalido extends ExcepcionArbolFicheros {
    ExcepcionElementoInvalido(String str) {
        super(str);
    }

    public String toString(){
        return nombre_excepcion;
    }
}