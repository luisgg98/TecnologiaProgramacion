//Similar a los includes de C++, traemos aquellas clases que nos seran utiles
import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

//En Java, no existe el concepto de pre-declaracion ni de funcion. Java es un 
//lenguaje puramente orientado a objetos, por lo que todo son clases.
//
//El uso de programación paramétrica no requiere ninguna palabra clave. Directamente ponemos
//el tipo parámetro entre corchetes.
//Para hacer uso de iteradores, implementamos el interfaz Iterable<T>. Esto es un mecanismo
//básico de la herencia en Java que es inevitable en este caso y sobre el que hablaremos
//más adelante en clase de teoría.
public class Agrupacion<T> implements Iterable<T>
{
	private class Nodo{
		protected T dato;
		protected Nodo siguiente;
		protected Nodo anterior;

		public Nodo(){};

		public Nodo (Nodo sig, Nodo ant,T dato)
		{
			siguiente = sig;
			anterior = ant;
			this.dato = dato;

		}
	}

	private Nodo ultimo;
	private Nodo primero;

	//El constructor de Java llama a los constructores de los parámetros dentro del bloque de código.
	@SuppressWarnings("unchecked")
	public Agrupacion()
	{
		ultimo = null;
		primero = null;
	}

	public boolean anyadir(T t) 
	{
		//TODO: Rellena el código de este método para que anyada un elemento a la agrupacion
		//y devuelva si ha sido posible (o no) meterlo.

		Nodo nuevo;
		if(primero == null)
		{
			nuevo = new Nodo(null,null,t);
			primero = nuevo;
		}
		else
		{
			nuevo = new Nodo(ultimo,null,t);
		}
		ultimo = nuevo;

		return true;
	}

	public boolean borrarUltimo()
	{
		//TODO: Rellena el código de este método para que borre el último elemento de la agrupación
		//y devuelva si ha sido posible (o no) borrarlo.
		boolean sePuede = ultimo!=null;
		if( sePuede )
		{
			Nodo aux = ultimo;
			ultimo = ultimo.ant;
			if(ultimo!=nullptr) ultimo.sig = null;
			else primero = nullptr;

			//delete aux; //COMO SE BORRA UN PUNTERO EN JAVA
		}

		return sePuede; 
	}

	//Esta clase representa a un iterador sobre la agrupación. De nuevo, por el comportamiento estándar de los
	//iteradores en Java, deberemos utilizar la herencia.
	private class IteradorAgrupacion<T> implements Iterator<T> 
	{
		//Aquí declaramos los atributos
		Agrupacion<T> ag;
		Nodo i;

		//Este es el constructor del iterador.
		private IteradorAgrupacion(Agrupacion<T> ag) 
		{
			this.ag = ag;
			i       = ag.ultimo;
		}

		//Todos los iteradores deben de implementar un método que devuelva
		//si hay siguiente elemento (o no).
		public boolean hasNext()
		{
			//TODO: Devuelve si hay siguiente elemento o no.
			return i.siguiente != null;
		}

		//Todos los iteradores deben de implementar un método que devuelva el elemento
		//en la posción actual y avance el iterador. Por construcción, este método debe
		//lanzar una excepción si no existe el siguiente elemento.
		//Recuerda que se recorren desde el último al primero.
		public T next() throws NoSuchElementException
		{
			//Aquí lanzamos la excepción
			if (!hasNext()) throw new NoSuchElementException();
			else {
				//TODO: Devuelve el elemento apuntado por el iterador, y avanza el iterador.
				T t = i.dato;
				i = i.anterior;
				return t;
			}
		}

		//Los iteradores en Java tienen que tener este método, también. Para no implementarlo (no lo vamos
		//a usar y nuestra definición de agrupación no lo incluye) simplemente lanzamos una excepción.
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}
	}

	//Este método de la estructura de datos simplemente devuelve un nuevo iterador con el que recorrerse la estructura de datos.
	public Iterator<T> iterator()
	{
		return new IteradorAgrupacion<T>(this);
	}
}
