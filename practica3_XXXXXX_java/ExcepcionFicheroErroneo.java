class ExcepcionFicheroErroneo extends ExceptionArbolFicheros {
    String fichero_nombre;

    ExcepcionFicheroErroneo() {
    }

    public String toString() {
        return ("El fichero proporcionado no tiene nombre o no existe.");
    }

}