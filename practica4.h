#ifndef OBJETO_H
#define OBJETO_H

#include <string>
#include <iostream>
#include <type_traits>

using namespace std;

class Objeto{

    protected:
        double capacidad;

    public:

        Objeto( double cap ) : capacidad(cap){};

        double capacidad_(){
            return capacidad;
        }

};

class Necesario :public Objeto{

    public:

       Necesario( double cap ) : Objeto(cap){};

};

class Generico : public Necesario {

    public:

        Generico( double cap ) : Necesario(cap){};

};

class ProductoG : public Necesario {

    string Nombre;

    public:

        ProductoG( double cap, string name ) : Necesario(cap), Nombre(name) {};

};

class Toxico : public ProductoG {

    public:

        Toxico( double cap, string name ) : ProductoG(cap, name) {};

};

class SerVivo : public ProductoG {

    public:

        SerVivo( double cap, string name ) :  ProductoG(cap, name) {};

};


class Producto : public ProductoG, public Generico {

    public:

        Producto( double cap, string name ) : ProductoG(cap, name), Generico(cap) {};

};

template <typename T>
class Carga : public Objeto {

    public:

        Carga( double cap ) : Objeto(cap) {};

        virtual bool guardar(T element){
            
            if ( Objeto::capacidad - element.capacidad_() < 0 ){
                return false;
            }
            else
            {   
                Objeto::capacidad -= element.capacidad_();
                return true;
            }

        };

};

template <typename T>
class Contenedor : public Generico, public Carga<T> {

    public:

        Contenedor( double cap ) : Generico(cap), Carga<T>(cap) {
            static_assert(std::is_base_of<Necesario,T>::value,"Tipo de dato no correcto");
        };

};

class Camion : public Carga<Generico> {

    public:

        Camion( double cap ) : Carga<Generico>(cap) {};

};

#endif