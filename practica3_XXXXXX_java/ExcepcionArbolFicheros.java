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

class ExcepcionNoExiste extends ExcepcionArbolFicheros {

    ExcepcionNoExiste(String str) {
        super("No existe " + str + " en el directorio.");
    }
}

class ExcepcionNoEncontrado extends ExcepcionArbolFicheros {

    ExcepcionNoEncontrado(String str) {
        super("El fichero buscado" + str + "no ha sido encontrado.");
    }
}

class ExcepcionNombreInvalido extends ExcepcionArbolFicheros {
    ExcepcionNombreInvalido(String str) {
        super("El nombre" + str + "cuenta con carácteres inválidos.");
    }
}

class ExcepcionYaExiste extends ExcepcionArbolFicheros {
    ExcepcionYaExiste(String str) {
        super("Ya existe el fichero" + str);
    }
}