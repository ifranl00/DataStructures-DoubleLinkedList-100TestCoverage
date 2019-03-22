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
				at = at.next;
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
		 private DoubleNode<T> oddIter;
		 private DoubleNode<T> evenIter;
		 private DoubleNode<T> iter;
		
		
		public OddAndEvenIterator(){
			
			iter = cab.next;
			oddIter = cab.next;
			evenIter = cab.next.next;
		}
		
		
		@Override
		public boolean hasNext() {
			
			boolean hasNext = false;
			
			if(iter != cab) {
				
				hasNext = true;
			}
		
			return hasNext;
		}
		
		
		@Override
		public T next() {
			
			T element = null;
			int nRound = 0;
			
			if(hasNext() == false) {
				
				throw new NoSuchElementException();
			}else {
				
				
				element = iter.content;
				iter = iter.next;
				
				
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void addFirst(T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAtPos(T element, int p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNTimes(T element, int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getElem(int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElem(T elem, int p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int indexOf(T elem, int p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T removeFirst(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeAll(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

	@Override
	public void reverse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isSubList(DoubleLinkedList<T> part) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void interlace(DoubleLinkedList<T> other) {
		// TODO Auto-generated method stub
		
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
		return null;
		// TODO Auto-generated method stub
		
		
	}

	 @Override
		public Iterator<T> iterator() {
	    	return new ForwardIterator();
			
		}

		@Override
		public Iterator<T> reverseIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		
	
}
