import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionYaExiste extends ExcepcionArbolFicheros {
    ExcepcionYaExiste(String str) {
        super(str);
    }

    public String toString(){
        return "El elemento " + nombre_excepcion + " ya existe";
    }
}