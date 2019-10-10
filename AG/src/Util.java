import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
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
    static double avaliacao(int vet[],int mat[][]){
        int maxPts = mat.length;
        double somatorio=0;
        double dist;
        double menorDist;
        for(int i=0;i<maxPts;i++){
            menorDist = Double.MAX_VALUE;
            for(int j=0;j<vet.length;j++){
                dist = distancia(mat,i,vet[j]);
                if(menorDist>dist){
                    menorDist = dist;
                }
            }
            somatorio+=menorDist;
        }
        return somatorio;
    }
    static double distancia(int mat[][],int c1, int c2){
        double distancia;
        int c = mat[c1][0]-mat[c2][0];
        int b = mat[c1][1]-mat[c2][1];
        distancia = Math.sqrt(Math.pow(b,2)+Math.pow(c,2));		
        return distancia;
    }
    static Scanner entrada = new Scanner(System.in);
    static void pressQT(){
        System.out.print("\n\nPressione qualquer tecla para voltar ao Menu Principal...");
        entrada.nextLine();
        //limpaTela();
    }
}
