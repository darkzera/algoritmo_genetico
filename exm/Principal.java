public class Principal {
	static final int maxPts = 324;
	public static void main(String[] args) {
		int mat[][] = Coord.carrega("324.txt");
		int vet[] = new int[3];
		long maxLoop = (long)(Math.pow(maxPts,vet.length));
		long loop = 0;
		long prev;
		double nota, menorNota=999999999;
		boolean condFim = false;
		int cont=0;
		String saida;
		long tt=0,t2, t1 = System.currentTimeMillis();
		do{
			nota = avaliacao(vet,mat);
			if(menorNota>nota){
				menorNota = nota;
				System.out.println("Novo Melhor -----> "+exibeVet(vet)+" = "+nota);				
			}
			vet[0]++;
			for(int i=0;i<vet.length;i++){
				if(vet[i] == maxPts){	
					cont++;
					vet[i] = 0;	
					if(i<vet.length-1){
						vet[i+1]++;
					}
				}
			}
			if(cont==vet.length){
				condFim = true;
			}else{
				cont = 0;
			}		
			t2 = (System.currentTimeMillis()-t1)/1000;
			loop++;
			prev = (maxLoop*t2)/loop;
			if((tt+10)<t2){
				tt = t2;
				System.out.println("lendo... "+exibeVet(vet)+" em "+t2+" segundos de "+(prev/3600)+" horas.");
			}
		}while(!condFim);
		System.out.println("\nFim da busca em "+t2+" segundos");
	}
	static void exibeCord(int mat[][]){
		String saida="";
		for(int i=0;i<mat.length;i++){
			saida += "|"+mat[i][0]+"|"+mat[i][1]+"|\n";
		}
		System.out.println(saida);
	}
	static String exibeVet(int vet[]){
		String saida="";
		for(int i=0;i<vet.length;i++){
			if(vet[i]<10){
				saida += "|00"+vet[i];
			}else if(vet[i]<100){
				saida += "|0"+vet[i];
			}else{
				saida += "|"+vet[i];
			}
		}		
		saida += "|";
		return saida;
	}	
	static double avaliacao(int vet[],int mat[][]){
		double somatorio=0;
		double dist;
		double menorDist;
		for(int i=0;i<maxPts;i++){
			menorDist=99999999;
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
}
