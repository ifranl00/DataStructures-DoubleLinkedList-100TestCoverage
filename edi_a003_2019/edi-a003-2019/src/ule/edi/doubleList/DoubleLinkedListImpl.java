package ule.edi.doubleList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<T> implements DoubleLinkedList<T> {
	/**
	 * Nodo doblemente enlazado.
	 * 
	 * Como es estÃ¡tica, no tiene en Ã¡mbito el parÃ¡metro 'T' de la
	 * clase que la contiene. El parÃ¡metro 'D' serÃ¡ sustituÃ­do por
	 * un tipo particular cuando se use el nodo, por ejemplo:
	 * 
	 * 		DoubleNode<T> cab;
	 * 
	 * en la lista.
	 *
	 * @param <D>
	 */
	static class DoubleNode<D> {

		DoubleNode(D element) {
			this.content = element;
			this.previous = null;
			this.next = null;
		}
		
		//	dato a almacenar en el nodo
		D content;
		
		DoubleNode<D> previous;
		
		DoubleNode<D> next;
	}

	/**
	 * Apunta al nodo cabecera; siempre habrÃ¡ un nodo vacÃ­o (sin elemento) que actua de cabecera
	 *  OJO!!! ESTE NODO CABECERA DEBERÃ� CREARSE EN CADA CONSTRUCTOR DE LA LISTA
	 */
	private DoubleNode<T> cab;
	
	
	
	//////////////////////
	/////  CONSTRUCTORES
	//////////////////////
	
	
	/**
	 * Construye una lista vacÃ­a.
	 */
	public DoubleLinkedListImpl() {
		// DeberÃ¡ crear el nodo cabecera vacÃ­o
		cab = new DoubleNode<T>(null);
		cab.previous = cab;
		cab.next = cab;
	
	}
	
	/**
	 * Construye una lista con los elementos dados.
	 * 
	 * Java crearÃ¡ un array 'elements' con los dados en la
	 * llamada al constructor; por ejemplo:
	 * 
	 * 	x = new DoubleLinkedList<String>("A", "B", "C");
	 * 
	 * ejecuta este mÃ©todo con un array [A, B, C] en 
	 * 'elements'.
	 * 
	 * @param elements
	 */
	public DoubleLinkedListImpl(T ... elements) {
		
		
		cab = new DoubleNode<T>(null);
		cab.previous = cab;
		cab.next = cab;
		
		for( T element : elements) {
			
			if(this.isEmpty() == true) {
				
				addFirst(element);
			}else {
				
				addLast(element);
			}
		}
	
	}
	
	/**
	 * Construye una lista a partir de otra.
	 * 
	 * Las listas tienen nodos independientes, con los mismos
	 * contenidos.
	 */
	public DoubleLinkedListImpl(DoubleLinkedList<T> other) {
	
		
		cab = new DoubleNode<T>(null);
		cab.previous = cab;
		cab.next = cab;
		
		for( T element : other) {
			
			if(this.isEmpty() == true) {
				
				addFirst(new DoubleNode<T>(element).content);
				
			}else {
				
				addLast(new DoubleNode<T>(element).content);
			}
		}
		
	}
	

	
	//////////////////////
	/////  ITERADORES
	//////////////////////
	
	private class ForwardIterator implements Iterator<T> {

		private DoubleNode<T> at = cab.next ;
		
		@Override
		public boolean hasNext(){
			
			boolean hasNext = false;
				
			if(at != cab) {
				
				hasNext = true;
			}
			return hasNext;
			
			
		}

		@Override
		public T next() {
			
			T element = null;
			
			if(hasNext() == false) {
				
				throw new NoSuchElementException();
			}else {
				
				element = at.content;
				at = at.next;
			}
		
			return element;
		}
		
		@Override
		public void remove() {
	
			throw new UnsupportedOperationException();
		}
	};
	
	
	private class reverseIterator implements Iterator<T> {

		private DoubleNode<T> at = cab.previous;
		
		@Override
		public boolean hasNext() {
			
			boolean hasNext = false;
			
			if(at != cab) {
				
				hasNext = true;
			}
			return hasNext;
		}

		@Override
		public T next() {
			
			T element = null;
			
			if(hasNext() == false) {
				
				throw new NoSuchElementException();
				
			}else {
				
				element = at.content;
				at = at.previous;
			}
			
			return element;
		}
		
		@Override
		public void remove() {
			
			throw new UnsupportedOperationException();
		}
	};

		private class OddAndEvenIterator implements Iterator<T> {

		// Definir los atributos necesarios para implementar el iterador
		 private DoubleNode<T> iter;
		 private int nRound;
		
		
		public OddAndEvenIterator(){
			
			iter = cab.next;
			if(isEmpty() == true) {
				
				nRound = 1;
			}else if(size() == 1){
				
				nRound = 1;
			}
		}
		
		
		@Override
		public boolean hasNext() {
			
			boolean hasNext = false;
			
			
			if((iter == cab && nRound == 1) || (nRound == 2)) {
				
				hasNext = false;
				
			}else {
				
				hasNext = true;
			}
			
			return hasNext;
		}
		
		
		@Override
		public T next() {
			
			T element = null;
			
			if(hasNext() == false) {
				
				throw new NoSuchElementException();
			}else {
				
				if(nRound == 0 && iter != cab ) { //La vuelta 0 es la que devuelve los pares
					
					element = iter.next.content; 
					
					if(iter.next != cab) {
					
						iter = iter.next.next; //avanza de dos en dos
						
					}else {
						
						iter = cab.next; //posicionamos el iterador
						nRound = 1; //ya acabo la vuelta de los pares y empieza la de los impares
					}
				
				}
				if(nRound == 1 && iter != cab) { //vuelta 1 de los impares
					
					element = iter.content;
					
					if(iter.next != cab) {
						
						iter = iter.next.next; //avanza de dos en dos
					}else {
						
						nRound = 2; //ya se han finalizado las vueltas
					}
				}
				
			}
			
			
			return element;
		}
		
		@Override
		public void remove() {
		
			throw new UnsupportedOperationException();
		}
	};
	
		
	
	
	////// FIN DE ITERADORES ///////
	////////////////////////////////
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		
		boolean isEmpty = false;
		
		if(size() == 0) {
			
			isEmpty = true;
		}
		
		return isEmpty;

	}

	@Override
	public int size() {
		int size = 0;
		
		DoubleNode<T> aux = cab.next;
		while(aux != cab) {
			
			size++;
			aux = aux.next;
		}
		
		return size;
	}
	
	@Override
	public void addFirst(T element) {
		
		DoubleNode<T> n = new DoubleNode<T>(element);
		
		if(isEmpty() == true) {
			
			cab.next = n;
			n.next = cab;
			n.previous = cab;
			cab.previous = n;
			
		}else {
			
			n.next = cab.next;
			cab.next.previous = n;
			n.previous = cab;
			cab.next = n;
			
		}
		
	}

	@Override
	public void addLast(T element) {
		
		DoubleNode<T> n = new DoubleNode<T>(element);
		
		if(isEmpty() == true) {
			
			cab.next = n;
			n.next = cab;
			n.previous = cab;
			cab.previous = n;
			
		}else {
			
			n.next = cab;
			n.previous = cab.previous;
			cab.previous.next = n;
			cab.previous = n;
			
		}
		
		
	}

	@Override
	public void addAtPos(T element, int p) {
		
		if(p < 1) {
			
			throw new IllegalArgumentException();
			
		}else {
		
			DoubleNode<T> aux = cab.next;
			int i = 0;
		
			if(p > size()) { 
			
				addLast(element);
			
			}else {
			
				DoubleNode<T> n = new DoubleNode<T>(element);
			
				while(i + 1 < p) {
				
					aux = aux.next; //para en el nodo en posicion p
					i++;
				}
			
				n.previous = aux.previous;
				n.next = aux;
				aux.previous.next = n;
			}
		}
		
	}

	@Override
	public void addNTimes(T element, int n) {
		
		for(int i = 0; i < n; i++) {
			
			addLast(element);
		}
	}

	@Override
	public T getElem(int p) {
		
		if(p < 1) {
			
			throw new IllegalArgumentException();
			
		}else if(size() < p){
			
			throw new IndexOutOfBoundsException();
			
		}else {
			
			DoubleNode<T> aux = cab.next;
			int i = 0;
			
			while(i + 1 < p) {
				
				aux = aux.next; //para en el nodo en posicion p
				i++;
			}
			
			return aux.content;
		}
	}

	@Override
	public void setElem(T elem, int p) {
		
		if(size() < p) {
			
			throw new IndexOutOfBoundsException();
			
		}else {
			
			DoubleNode<T> aux = cab.next;
			int i = 0;
			
			while(i + 1 < p) {
				
				aux = aux.next; //para en el nodo en posicion p
				i++;
			}
			
			aux.content = elem;
		}
	}

	@Override
	public int indexOf(T elem) {
		
		Iterator<T> iter = iterator();
		int i = 1;
		int pos = 0;
		
		while(iter.hasNext() && pos == 0) {
			
			if(iter.next().equals(elem)) {
				
				pos = i;
						
			}else {
				i++;
			}
				
		}
		if(pos == 0) { //se ha llegado al final de la lista
			
			throw new NoSuchElementException();
		}
		return pos;
	}

	@Override
	public int indexOf(T elem, int p) {
		
		int pos = 0;
		
		if(size() < p) {
			
			throw new IndexOutOfBoundsException();
			
		}else {
			
			DoubleNode<T> aux = cab.next;
			int i = 1;
			
			while(i < p && aux.next != cab) {
				
				aux = aux.next; //para donde tiene que empezar a buscar
				i++;
			}
			
			while(aux.next != cab && pos == 0) {
				
				if(aux.content.equals(elem)) {
					
					pos = i;
					
				}else {
					
					aux = aux.next;
					i++;
				}
			}
			
			if(aux.next == cab) { //estamos en el ultimo elemento
				
				if(aux.content.equals(elem)) {
					
					pos = i;
				}
			}
			
			if(pos == 0) { //se ha llegado al final de la lista
				
				throw new NoSuchElementException();
			}
		}	
		return pos;
	}

	@Override
	public T removeFirst(T elem) throws EmptyCollectionException {
		
		if(isEmpty() == true) {
			
			throw new EmptyCollectionException("The list is empty");
			
		}else {
			
			DoubleNode<T> aux = cab.next;
			int i = 1;
			T e = null;
			
			while(i < indexOf(elem)) {
				
				aux = aux.next;
				i++;
			}
			
			e = aux.content;
			aux.previous.next = aux.next;
			aux.next.previous = aux.previous;
			return e;

		}
		
	}

	@Override
	public T removeAll(T elem) throws EmptyCollectionException {
		
		T e = null;
		
		if(isEmpty() == true) {
			
			throw new EmptyCollectionException("The list is empty");
			
		}else {

			int i = 0;
			
			DoubleNode<T> aux = cab.next;
			
			while(aux.next != cab) {
				
				if(aux.content.equals(elem)) {
					
					i++;//contamos cuantas veces aparece el elemento
				}
				aux = aux.next;
			}
			
			if(aux.next == cab) { //estamos en el ultimo elemento
				
				if(aux.content.equals(elem)) {
					
					i++;
				}
			}
			
			while(i > 0 && indexOf(elem) > 0) {
				
				e = removeFirst(elem);
				i--; 
			}
			
		}
		return e;
	}
	
	@Override
	public T removeLast() throws EmptyCollectionException {
		
		T e = null;
		
		if(isEmpty() == true) {
			
			throw new EmptyCollectionException("The list is empty");
			
		}else {
			
			DoubleNode<T> aux = cab.next;
			
			while(aux.next != cab) {
				
				aux = aux.next;
			}
			e = aux.content;
			aux.previous.next = aux.next;
			aux.next.previous = aux.previous;
			
		}
		return e;
	}
	

	@Override
	public void reverse() {
		
		DoubleLinkedListImpl<T> other = new DoubleLinkedListImpl<>();
		Iterator<T> iter = reverseIterator();
		
		while(iter.hasNext()) {
			
			other.addLast(iter.next());
		}
		
		cab = other.cab;
	}

	@Override
	public int isSubList(DoubleLinkedList<T> part) {
		
		DoubleLinkedListImpl<T> other = new DoubleLinkedListImpl<>(part);
		DoubleNode<T> aux0 = cab.next; //recorre la lista
		DoubleNode<T> aux1 = other.cab.next; //recorre la sublista
		DoubleNode<T> aux2 = cab.next; //recorre para encontrar el primero de la sublista
		
		int nMovs = 1; //contamos las veces que se mueve aux1 para saber si coincide con el size de esa sublista
		int pos = 0;
		int found = 0; //inicializamos a 1 es decir a que no lo ha encontrado
		
		if(other.isEmpty()) { //si la sublista esta vacia devolvemos 1
			
			pos = 1;
			
		}else {
			
			while(aux0 != cab && found != 2) { //hasta que llegue al final de la lista
			
				while(aux0.content.equals(aux1.content) && found != 2) { //comparamos para encontrar el posible comienzo de una sublista
				
					found = 1; //hemos encontrado un posible comienzo de sublista
					if(nMovs == other.size()) { 
					
						while(aux0 != cab && nMovs > 1) {
						
							aux0 = aux0.previous;
							nMovs--;
						}
						//aux0 se ha situado donde empieza la sublista en nustra lista
						
						if(aux2 == aux0) { //el primer elemento es el comienzo de la sublista
							
							pos = 1;
							if(other.size() == 1) {
								
								found = 2; //hemos encontrado la sublista
							}
							
						}else {
							
							pos = 1; //empieza en 1 porque ya no puede ser la pos 1
							
							while(aux2 != cab && aux2 != aux0) { //movemos un aux hasta encontrar aux0 para contar en que pos esta
								
								aux2 = aux2.next;
								pos++;
							}
						}
						found = 2; //hemos encontrado la sublista
						
					}
					
					if (found == 1) { //si hemos encontrado un posible comienzo de sublista
						aux0 = aux0.next;
						aux1 = aux1.next;
						nMovs++;
					}
				}
				//llega a este punto si no se ha encontrado el comienzo de la sublista (found = 0) o si no era esa sublista (found = 1)
				aux0 = aux0.next; //avanzamos si no hemos encontrado un comienzo de sublista
				if(found == 1) { //volvemos a situar el auxiliar de la otra lista
					aux1 = other.cab.next; 
					found = 0;
					nMovs = 1;
				}
			}
			if(aux0 == cab && found != 2) { //si no se ha encontrado la sublista
				
				pos = -1;
			}
		}
		
		return pos;
	}

	@Override
	public void interlace(DoubleLinkedList<T> other) {
		
		int i = 2;
		Iterator<T> iter = other.iterator();
		
			while(iter.hasNext()) {
				
				addAtPos(iter.next(), i);
				i = i +2;
			}
	}	
	
	@Override
	public String toString() {
		
		if (cab != cab.next) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			DoubleNode<T> i = cab.next;
			while (i != cab) {
				rx.append(i.content);
				rx.append(", ");
				i = i.next;
			}
			rx.delete(rx.length() - 2, rx.length());
			rx.append("]");
			
			return rx.toString();
		} else {
			return "[]";
		}
	}

	

	///////////////////////////////////////////
	  // mÃ©todos que devuelve iteradores
	 ///////////////////////////////////////
	@Override
	public Iterator<T> oddAndEvenIterator() {
		return new OddAndEvenIterator();
	}

	 @Override
		public Iterator<T> iterator() {
	    	return new ForwardIterator();
			
		}

		@Override
		public Iterator<T> reverseIterator() {
			return new reverseIterator();
		}

		
	
}
