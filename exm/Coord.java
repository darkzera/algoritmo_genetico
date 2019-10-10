import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Coord {
	public static int[][] carrega(String caminho){
		FileReader arq;
		BufferedReader buff;
		String saida="", temp="";
		int pos=0,cont;
		try{
			arq = new FileReader(caminho);
			buff = new BufferedReader(arq);	
			int mat[][] = new int[Integer.parseInt(buff.readLine())][2];	
			while(buff.ready()){
				saida = buff.readLine();
				cont = 0;
				for(int i=0;i<saida.length();i++){
					if(saida.charAt(i)!=' '){
						temp += saida.charAt(i);
					}						
					if(saida.charAt(i)==' ' || i == saida.length()-1){
						mat[pos][cont] = Integer.parseInt(temp);
						temp="";
						cont++;						
					}
				}
				pos++;
			}
			buff.close();
			arq.close();
			return mat;
		}catch(IOException e){
			System.out.println("Erro na abertura do arquivo!");
			return null;
		} 		
	}	
}
