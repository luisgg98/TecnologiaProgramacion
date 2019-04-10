import java.lang.Exception;
import java.lang.Throwable;

class ExcepcionCiclo extends ExcepcionArbolFicheros {
    ExcepcionCiclo() {
        super("Se ha creado un ciclo, no se puede calcular el tamanyo");
    }

    public String toString(){
        return nombre_excepcion;
    }
}