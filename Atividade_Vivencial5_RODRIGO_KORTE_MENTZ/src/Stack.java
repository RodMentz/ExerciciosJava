/**
 * Interface que define o comportamento de uma Pilha.
 */
public interface Stack<E> {
	
	public boolean isEmpty();
	/**
	 * Informa se a pilha est� vazia.
	 * @return Verdadeiro se a pilha estiver vazia, falso caso contr�rio.
	 */
	
	
	public boolean isFull();
	/**
	 * Informa se a pilha est� cheia.
	 * @return Verdadeiro se a pilha estiver cheia, falso caso contr�rio.
	 */
	
	public int numElements();

	/**
	 * Informa a quantidade de elementos armazenados na pilha.
	 * @return A quantidade de elementos armazenados na pilha.
	 */
	public void push(E element) throws OverflowException;

	/**
	 * Adiciona um novo elemento � pilha.
	 * @param element O elemento a ser adicionado
	 */
	public E pop() throws UnderflowException;

	/**
	 * Retira um elemento da pilha.
	 * @return O elemento retirado
	 */
	public E top() throws UnderflowException;

	/**
	 * Informa qual o elemento no topo da pilha.
	 * @return O elemento atualmente no topo da pilha
	 */
}
