public class Cruzamento {	
    public static Cromossomo[] S(Cromossomo[] pais,int mat[][],int tipo) {
        Cromossomo[] filhos = new Cromossomo[2];
        switch(tipo) {
            case 1:
                filhos = mascara(pais);
                break;
            case 2:
                filhos[0] = PRAleatorio(pais,mat);
                filhos[1] = null;
                break;	
        }
        return filhos;
    }
    public static Cromossomo PRAleatorio(Cromossomo[] pais,int mat[][]) {
        Cromossomo guia = new Cromossomo(pais[0].getNumGenes());
        Cromossomo filho = new Cromossomo(pais[0].getNumGenes());
        int[] aux = new int[pais[0].getNumGenes()];
        for(int i=0;i<pais[0].getNumGenes();i++){
            guia.setPosVetGene(pais[0].getPosVetGene(i),i);
            aux[i] = i;
        }
        guia.avaliacao(mat);
        double fitMelhor = Float.MAX_VALUE;
        int cont = guia.getNumGenes();
        for(int i=0;i<guia.getNumGenes()-1;i++){
            int rand = (int)(Math.random()*cont);
            int pos = aux[rand];
            guia.setPosVetGene(pais[1].getPosVetGene(i),i);
            guia.avaliacao(mat);
            if(fitMelhor > guia.getFitness()){
                fitMelhor = guia.getFitness();
                for(int j=0;j<guia.getNumGenes();j++){
                    filho.setPosVetGene(guia.getPosVetGene(j),j);	            	 
                }
                filho.setFitness(guia.getFitness());
            }
            aux[rand] = aux[cont-1];
            cont--;
        }
        return filho;
    }
    public static Cromossomo[] mascara(Cromossomo[] pais) {
        Cromossomo[] filhos = new Cromossomo[2];
        filhos[0] = new Cromossomo(pais[0].getNumGenes());
        filhos[1] = new Cromossomo(pais[0].getNumGenes());
        int mask;
        for(int i=0;i<pais[0].getNumGenes();i++) {
            mask = (int)(Math.random()*2);
            if(mask == 0) {
                filhos[0].setPosVetGene(pais[0].getPosVetGene(i),i);
                filhos[1].setPosVetGene(pais[1].getPosVetGene(i),i);
            }else {
                filhos[0].setPosVetGene(pais[1].getPosVetGene(i),i);
                filhos[1].setPosVetGene(pais[0].getPosVetGene(i),i);
            }
        }
        return filhos;
    }
}
