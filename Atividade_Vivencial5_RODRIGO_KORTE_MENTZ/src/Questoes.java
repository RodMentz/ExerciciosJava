import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Questoes {
	
	//EXERCICIO ARQUIVOS E RECURSAO
	public static void imprimeArvoreArquivos(String diretorio) {
		File file = new File(diretorio);
		try {
			if (!file.exists()) 
				throw new ErroDiretorio("Diretório não encontrado");
			
			if (!file.isDirectory()) 
				throw new ErroDiretorio("Isto não é um diretório");
				
		} catch (ErroDiretorio e){
			System.out.println("Erro: "+ e.getMessage());
			return;
		}
		
		System.out.println("Impressão de arquivos: \n");
		imprimeArvoreArquivos(file.getAbsolutePath(), file.listFiles(), 0, "");
	}

	private static void imprimeArvoreArquivos(String path, File[] arquivos, int i, String tabs) {
		if (arquivos == null)
			return;

		for (File arquivo : arquivos) {
			System.out.println(tabs + ("---") + arquivo.getName());

			if (arquivo.isDirectory()) {
				String novoPath = path + File.separator + arquivo.getName();
				imprimeArvoreArquivos(novoPath, arquivo.listFiles(), i + 1, tabs + "---");
			}
		}
	}
	
	//QUESTAO 1 DENTRO DE DOUBLYLINKEDLIST
	
	//QUESTAO 2
	public static boolean isPalindrome(char word[]) {
	    return isPalindrome(word, 0, word.length - 1);
	}

	private static boolean isPalindrome(char word[], int inicio, int fim) {
	    if (inicio >= fim)
	        return true;
	    
	    if (word[inicio] != word[fim])
	        return false;

	    return isPalindrome(word, inicio + 1, fim - 1);
	}
	
	//QUESTAO 3 A
	public static void list2File(StaticList<Double> list) {
		try {
			FileWriter fw = new FileWriter("listaInvertida.txt");

			for (int i = list.numElements() - 1; i >= 0; i--) {
				fw.write(list.get(i).toString());

				if (i > 0)
					fw.write("|");
			}

			fw.close();
			System.out.println("Arquivo listaInvertida.txt foi criado.");
		} catch (IOException e) {
			System.out.println("Erro ao criar o arquivo listaInvertida.txt: " + e.getMessage());
		}
	}
	
	//QUESTAO 3 B
	public static void sumStacks(StaticStack<Integer> s1, StaticStack<Integer> s2) throws OverflowException{
		if(s1.numElements() != s2.numElements()) throw new OverflowException();

		StaticStack<Integer> aux = new StaticStack<>(s1.numElements());
		StaticStack<Integer> aux2 = new StaticStack<>(s1.numElements());			
		while(s1.numElements() > 0)
			aux.push(s1.pop() + s2.pop());
		while(aux.numElements() > 0)
			aux2.push(aux.pop());

		System.out.println(aux2);
	}

	//QUESTAO 4
	public static StaticQueue<Integer> matrix2queue(int[][] m){
		int cont = 0;
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				cont++;
		StaticQueue<Integer> aux = new StaticQueue<>(cont);
		StaticQueue<Integer> aux2 = new StaticQueue<>(cont);

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				int element = m[i][j];
				if(element >= 0) {
					if(element % 2 == 0)
						aux2.enqueue(element);
					else 
						aux.enqueue(element);
				}	
			}
		}
		while(aux.numElements() > 0)
			aux2.enqueue(aux.dequeue());
		return aux2;
	}


	//MAIN PARA TESTES
	public static void main(String[] args) {
		
		DoublyLinkedList<Integer> lista = new DoublyLinkedList<>();

		lista.insertLast(1);
		lista.insertLast(2);
		lista.insertLast(3);
		lista.insertLast(2);
		lista.insertLast(4);
		lista.insertLast(5);
		lista.insertLast(2);
		
		System.out.println("Teste mover para o inicio: ");
		System.out.println("Lista: " + lista);

		int elemento = 2;
		boolean movido = lista.moveToFirst(elemento);

		if (movido)
			System.out.println("Elemento " + elemento + " movido.");
		 else 
			System.out.println("Elemento " + elemento + " não tem na lista.");
		
		System.out.println("Lista depois de mexer: " + lista);
		System.out.println();
		
		System.out.println("Teste palindromo: ");
		char[] word1 = {'a', 'n', 'a'};
		System.out.println(word1);
		System.out.println("É palindromo?  "+ isPalindrome(word1));

		char[] word2 = {'r', 'e', 'n', 'n', 'e', 'r'};
		System.out.println();
		System.out.println(word2);
		System.out.println("É palindromo?  "+ isPalindrome(word2));

		char[] word3 = {'c', 'a', 'r', 'r', 'o'};
		System.out.println();
		System.out.println(word3);
		System.out.println("É palindromo?  "+ isPalindrome(word3));

		System.out.println();
		StaticList<Double> list = new StaticList<>(5);
		list.insert(1.1, 0);
		list.insert(2.2, 1);
		list.insert(3.3, 2);
		list.insert(4.4, 3);
		list.insert(5.5, 4);

		System.out.println("Teste lista invertida: ");
		list2File(list);

		StaticStack<Integer> s1 = new StaticStack<>(5);
		StaticStack<Integer> s2 = new StaticStack<>(5);

		s1.push(1);
		s1.push(2);
		s1.push(3);
		s1.push(4);
		s1.push(5);

		s2.push(1);
		s2.push(2);
		s2.push(3);
		s2.push(4);
		s2.push(5);

		System.out.println("S1:\n"+s1);
		System.out.println("\nS2:\n"+s2);

		try {
			System.out.println("\nApós o método: ");
			sumStacks(s1, s2);
			System.out.println();

		} catch (OverflowException e) {
			System.out.println("As pilhas têm tamanhos diferentes.");
		}

		int[][] matriz = {
				{1, 2, 3},
				{4, 5, -6},
				{7, -8, 9}
		};
		
		System.out.println("Teste matriz positivo para fila: ");
		System.out.println(matrix2queue(matriz));
		System.out.println();
		
		System.out.println("Teste imprimeArvoreArquivos: \n");
		imprimeArvoreArquivos("C:\\\\Users\\\\Rodrigo\\\\eclipse-workspace\\\\Atividade_Vivencial5_RODRIGO_KORTE_MENTZ");
	}
}
