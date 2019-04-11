import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionNombreInvalido extends ExcepcionArbolFicheros {
    ExcepcionNombreInvalido(String str) {
        super(str);
    }

    public String toString(){
        return (nombre_excepcion + " no es un nombre valido");
    }
}