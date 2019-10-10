public class Principal {
    public static void main(String[] args) {
        //Calibragem Analise
        final int repeticao = 1; 		//vezes que o ag vai repetir
        final int alt = 1; 			//vezez que o ag vai alterar algo
        final String caminhoLog = "LOG.txt";

        //Calibragem Geral		
        final int ciclos = 200000; 		//criterio de parada (numero de loops)
        final int numGenes = 10; 		//numero de genes em um cromossomo
        final int tamPop = 100; 		//tamanho da populacao
        final String caminho = "324.txt";	

        //Calibragem para Selecao
        final int tipoSelecao = 1; 		//1-torneio
        final int numPart = 2; 			//quantidade de participantes em cada torneio

        //Calibragem para Cruzamento
        final int tipoCruzamento = 1; 	        //1-mascara			

        //Calibragem para Mutacao
        int chanceMut = 1;                      //Porcentagem de chance de sofrer mutacao
        final int tipoMutacao = 1; 		//1-mutacao classica
        final int porcentGene = 1; 		//Porcentagem dos genes que sofrem mutacao
        final int variacao = 324; 		//numero de genes possiveis para mutacao

        //Calibragem para Insercao
        final int tipoInsercao = 1; 	        //1-piorPai		



        System.out.println("Executando...");	
        int mat[][] = Util.carrega(caminho); //carrega o arquivos de coordenadas para uma matriz
        String saida = "";

        // esse for aqui repete X bateria de teste fazendo alguma alteracao em cada repeticao 
        // (neste caso a alteracao eh aumentar a taxa de mutacao)	
        for(int i=0;i<alt;i++) { 
            Analise[] vetAnalise = new Analise[repeticao];

            //esse for repete o AG Y vezes (estas repeticao sempre tem os mesmos parametros)
            for(int j=0;j<repeticao;j++) { 
                Populacao pop = new Populacao(tamPop,mat,numGenes);	
                int cont = 0;
                Cromossomo[] pais, filhos;				
                boolean condMut, condInsert;
                Analise A = new Analise();
                A.setTop1(pop.getTop1());
                long t = System.currentTimeMillis();
                do {
                    condMut = false;
                    condInsert = false;
                    pais = Selecao.S(pop,tipoSelecao,numPart);
                    filhos = Cruzamento.S(pais,mat,tipoCruzamento);	
                    condMut = Mutacao.S(filhos,porcentGene,chanceMut,variacao,tipoMutacao,A);
                    condInsert = Insercao.S(pop,pais,filhos,mat,tipoInsercao,t,A);	
                    if(condMut && condInsert) {
                        A.addContMutInsert();
                    }
                    cont++;
                }while(cont<ciclos);
                A.fechaTempo(t);
                vetAnalise[j] = A;
                System.out.println(A);
            } // end 2o for

            Analise F = new Analise();	
            saida += F.exibeMedia(repeticao,vetAnalise,chanceMut);
            GArq.log(saida, caminhoLog); //grava em um arquivo de LOG os resultados			
            System.out.println("Gravado... \tAlteracao: "+(i+1));			
            chanceMut++; //aumeta a taxa de mutacao
        } // end for 1o for

        System.out.println("Programa Finalizado!");
    }
}

