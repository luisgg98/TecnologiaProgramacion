import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionNoEncontrado extends ExcepcionArbolFicheros {
    ExcepcionNoEncontrado(String str) {
        super(str);
    }

    public String toString(){
        return "El fichero " + nombre_excepcion + " no existe";
    }
}