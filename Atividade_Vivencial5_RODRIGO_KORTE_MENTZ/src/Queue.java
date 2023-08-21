/**
 * Interface que define o comportamento de uma Fila.
 */
public interface Queue<E> {
	
	public boolean isEmpty();
	/**
	 * Informa se a fila est� vazia.
	 * @return Verdadeiro se a fila estiver vazia, falso caso contr�rio.
	 */
	public boolean isFull();

	/**
	 * Informa se a fila est� cheia.
	 * @return Verdadeiro se a fila estiver cheia, falso caso contr�rio.
	 */
	
	public int numElements();
	/**
	 * Informa a quantidade de elementos armazenados na fila.
	 * @return A quantidade de elementos armazenados na fila.
	 */
	public void enqueue(E element) throws OverflowException;

	/**
	 * Insere um novo elemento na fila.
	 * @param element O elemento a ser inserido
	 */
	public E dequeue() throws UnderflowException;

	/**
	 * Retira um elemento da fila.
	 * @return O elemento retirado
	 */
	public E front() throws UnderflowException;
	
	/**
	* Informa qual o primeiro elemento da fila.
	* @return O primeiro elemento da fila
	*/
	public E back() throws UnderflowException;

	/**
	 * Informa qual o �ltimo elemento da fila.
	 * @return O �ltimo elemento da fila
	 */
}
