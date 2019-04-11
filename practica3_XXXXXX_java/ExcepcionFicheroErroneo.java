class ExcepcionFicheroErroneo extends ExcepcionArbolFicheros {
    String fichero_nombre;

    ExcepcionFicheroErroneo() {
        super("El fichero proporcionado no tiene nombre o no existe.");
    }

    public String toString() {
        return nombre_excepcion;
    }

}