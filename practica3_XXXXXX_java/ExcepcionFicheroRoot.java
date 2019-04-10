import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionFicheroRoot extends ExcepcionArbolFicheros {
    ExcepcionFicheroRoot(String str) {
        super(str);
    }

    public String toString(){
        return nombre_excepcion;
    }
}