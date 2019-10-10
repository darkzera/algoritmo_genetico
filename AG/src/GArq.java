import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GArq {
	public static void log(String texto,String caminho){
		FileWriter fw;
		BufferedWriter bw;
		try{
			fw = new FileWriter(caminho);
			bw = new BufferedWriter(fw);
			bw.write(texto);			
			bw.close();
			fw.close();			
		}catch(IOException e){
			System.out.println("Erro ao salvar arquivo!");
		}
	}
}
