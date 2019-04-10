
class probacion {
    public static void main(String[] args) {
        Directorio arbol = new Directorio("home");
        Directorio user = new Directorio("luisgg");
        arbol.anaydirElemento(user);
        Ruta laruta = new Ruta(arbol);
        laruta.ls();
        laruta.mkdir("saul");
        laruta.vim("deberes", 500);
        laruta.ls();
        laruta.cd("saul");
        laruta.vim("deberesd", 3500);
        laruta.stat("deberesd");
        laruta.mkdir("dani");
        laruta.ls();
        laruta.cd("/home/luisgg");
        laruta.vim("hola", 100);
        laruta.mkdir("pepe");
        System.out.println(laruta.pwd());
        laruta.ls();
        laruta.cd("..");
        System.out.println(laruta.pwd());
        laruta.cd("/");
        laruta.ln("saul/dani", "adios");
        laruta.stat("adios");
        laruta.cd("adios");
        System.out.println(laruta.pwd());
        laruta.ls();
        laruta.cd("..");
        System.out.println(laruta.pwd());
        laruta.rm("saul/dani");
        laruta.cd("/home/saul/dani");
        System.out.println(laruta.pwd());

    }
};