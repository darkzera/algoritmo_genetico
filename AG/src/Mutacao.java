public class Mutacao {
    public static boolean S(Cromossomo[] filhos,int porcentGene,int chanceMut, int variacao, int tipo, Analise A) {
        boolean cond = false;
        switch(tipo) {
            case 1:
                cond = mutClassica(filhos,porcentGene,chanceMut,variacao,A);
                break;
        }
        return cond;
    }
    public static boolean mutClassica(Cromossomo[] filhos,int porcentGene,int chanceMut, int variacao, Analise A) {
        if(chanceMut>(int)(Math.random()*100)) {
            int numGenes = (filhos[0].getNumGenes()*porcentGene)/100;
            if (numGenes == 0) {
                numGenes = 1;
            }
            int[] vetPos = new int[filhos[0].getNumGenes()];	
            for(int i=0;i<vetPos.length;i++) {
                vetPos[i] = i;
            }
            int aux,p1,p2;
            for(int i=0;i<vetPos.length;i++) {
                p1 =  (int)(Math.random()*vetPos.length);
                p2 =  (int)(Math.random()*vetPos.length);
                aux = vetPos[p1];
                vetPos[p1] = vetPos[p2];
                vetPos[p2] = aux;
            }
            for(int i=0;i<numGenes;i++) {
                int novoGene = (int)(Math.random()*variacao);
                filhos[0].setPosVetGene(novoGene,vetPos[i]);
                try {
                    filhos[1].setPosVetGene(novoGene,vetPos[i]);
                }catch (Exception e) {}
            }
            A.addMutacao();
            return true;
        }
        return false;
    }
}
