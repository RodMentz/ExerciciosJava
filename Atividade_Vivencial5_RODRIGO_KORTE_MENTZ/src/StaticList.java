/*
* Implementação de uma lista linear com armazenamento estático,
* baseado em array.
*/
public class StaticList<E> implements List<E> {
	private E[] elements;
	private int numElements;
	/**
	 * Constrói uma lista com um tamanho máximo.
	 * @param maxSize O tamanho máximo da lista
	 */
	public StaticList(int maxSize) {
		elements = (E[])new Object[maxSize];
		numElements = 0;
	}
	public int numElements() {
		return numElements;
	}
	public boolean isEmpty() {
		return numElements == 0;
	}
	public boolean isFull() {
		return numElements == elements.length;
	}
	public void insert(E element, int pos) throws OverflowException,
	IndexOutOfBoundsException{
		// verifica se há espaço na lista
		if (isFull())
			throw new OverflowException();
		// verifica se a posição é válida
		if (pos < 0 || pos > numElements)
			throw new IndexOutOfBoundsException();
		// desloca para a direita os elementos necessários,
		// abrindo espaço para o novo
		for (int i = numElements-1; i >= pos; i--)
			elements[i+1] = elements[i];
		// armazena o novo elemento e ajusta o total
		elements[pos] = element;
		numElements++;
	}
	public E remove(int pos) throws UnderflowException,
	IndexOutOfBoundsException{
		if(isEmpty())
			throw new UnderflowException();
		// verifica se a posição é válida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();
		// guarda uma referencia temporária ao elemento removido�
		E element = elements[pos];
		// desloca para a esquerda os elementos necessários,
		// sobrescrevendo a posição do que está sendo removido
		for (int i = pos; i < numElements-1; i++)
			elements[i] = elements[i+1];
		// define para null a posição antes ocupada pelo último,
		// para que a coleta de lixo possa atuar, e ajusta o total
		elements[numElements-1] = null;
		numElements--;
		return element;
	}
	public E get(int pos) throws IndexOutOfBoundsException{
		// verifica se a posição é válida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();
		return elements[pos];
	}
	public int search(E element) {
		for (int i = 0; i < numElements; i++)
			if (element.equals(elements[i]))
				return i;
		// se chegar até aqui, é porque não encontrou
		return -1;
	}
	/**
	 * Retorna uma representação String da lista.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < numElements; i++)
			s += elements[i] + " ";
		return s;
	}
	
	public boolean remove(E element) throws UnderflowException{
		if(this.isEmpty()) throw new UnderflowException();
		int pos = this.search(element);
		if(pos != -1){
			remove(pos);
			return true;
		}
		return false;
	}
	
	public void insertAfter(E obj1, E obj2) {
		if(this.isEmpty()) throw new UnderflowException();
		int pos = this.search(obj1);
		if(pos != -1)
			insert(obj2, pos+1);
	}
	
	public void swap(int pos1, int pos2) {
		if(this.isEmpty()) throw new UnderflowException();
		if(pos1 < 0 || pos1 > numElements || pos2 < 0 || pos2 >= numElements)
			throw new IndexOutOfBoundsException();
		E aux = elements[pos1];
		elements[pos1] = elements[pos2];
		elements[pos2] = aux;
	}
	
	public void flip() {
	    for(int i = 0; i < numElements/2; i++) {
	        E aux = elements[i];
	        int j = numElements - i - 1;
	        elements[i] = elements[j];
	        elements[j] = aux;
	    }
	}
	
	
	public void insert(List<E> novaLista, int pos) throws OverflowException, IndexOutOfBoundsException{
		if(pos < 0 || pos > numElements)
			throw new IndexOutOfBoundsException();
		
		for(int i=0; i < novaLista.numElements(); i++)
			insert(novaLista.get(i), pos++);
	}
	
	public void dedup() {
		for(int i=0; i<numElements-1; i++)
			for(int j=i+1; j<numElements; j++)
				if(elements[i].equals(elements[j]))
					remove(j--);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null || !(obj instanceof List)) {
	        return false;
	    }
	    List<?> other = (List<?>) obj;
	    if (this.numElements() != other.numElements()) {
	        return false;
	    }
	    for (int i = 0; i < this.numElements(); i++) {
	        if (!this.get(i).equals(other.get(i))) {
	            return false;
	        }
	    }
	    return true;
	}
	
	@SuppressWarnings("unchecked")
	public StaticList<E> clone() {
	    try {
	        StaticList<E> newList = (StaticList<E>) super.clone();
	        newList.elements = (E[]) new Object[numElements];
	        for (int i = 0; i < numElements; i++) {
	            newList.elements[i] = elements[i];
	        }
	        return newList;
	    } catch (CloneNotSupportedException e) {
	        throw new AssertionError();
	    }
	}
	
	public int remove(int ini, int fim) {
	    if (ini < 0 || ini > numElements || fim < 0 || fim > numElements) {
	        throw new IndexOutOfBoundsException();
	    }
	    if (ini > fim) {
	        throw new IllegalArgumentException("ini must be less than or equal to fim");
	    }
	    int removed = fim - ini + 1;
	    for (int i = ini; i <= fim; i++) {
	        elements[i] = null;
	    }
	    for (int i = ini; i < numElements - removed; i++) {
	        elements[i] = elements[i + removed];
	    }
	    numElements -= removed;
	    return removed;
	}
	
	public List<E> split(int pos) {
	    if (pos < 0 || pos > numElements) {
	        throw new IndexOutOfBoundsException();
	    }
	    List<E> newList = new StaticList<E>(100);
	    for (int i = pos; i < numElements; i++) {
	        newList.insert(elements[i], i);
	        elements[i] = null;
	    }
	    numElements = pos;
	    return newList;
	}
}