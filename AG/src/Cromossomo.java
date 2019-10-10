import java.text.DecimalFormat;

public class Cromossomo {
    private String label;
    private int[] vetGene;
    private double fitness;
    private int geracao;
    private int posVet;

    // Cada individuo cromossomo representa um candidato potencial do problema (solucao???) 

    Cromossomo(int numGenes){
        this.vetGene = new int[numGenes];
    }
    Cromossomo(int[][] mat,int numGenes,String label,int geracao,int posVet){
        this.geracao = geracao;
        this.label = label;
        this.posVet = posVet;
        this.vetGene = new int[numGenes];
        for(int i=0;i<numGenes;i++){
            vetGene[i] = (int)(Math.random()*mat.length);
        }
        this.fitness = Util.avaliacao(vetGene,mat);		
        int backupPos;
        double newFitness;		
        for(int i=0;i<numGenes;i++){
            backupPos = this.vetGene[i];
            for(int j=0;j<mat.length;j++){
                this.vetGene[i] = j;
                newFitness = Util.avaliacao(this.vetGene,mat);
                if(newFitness<this.fitness){					
                    this.fitness = newFitness;
                    backupPos = j;					
                }
            }
            this.vetGene[i] = backupPos;
        }	
    }
    void avaliacao(int mat[][]){
        int maxPts = mat.length;
        double somatorio=0;
        double dist;
        double menorDist;
        for(int i=0;i<maxPts;i++){
            menorDist = Double.MAX_VALUE;
            for(int j=0;j<this.vetGene.length;j++){
                dist = Util.distancia(mat,i,vetGene[j]);
                if(menorDist>dist){
                    menorDist = dist;
                }
            }
            somatorio+=menorDist;
        }
        this.fitness = somatorio;
    }
    String exibe(){		
        DecimalFormat df = new DecimalFormat("0.000");
        String fitness = df.format(this.fitness);		
        String saida = this.label+": G";
        if(this.geracao<10) {
            saida += this.geracao+"  ";
        }else if(this.geracao<100) {
            saida += this.geracao+" ";
        }else {
            saida += this.geracao;
        }
        saida += "\tFitness: "+fitness+"\t";
        for(int i=0;i<this.vetGene.length;i++){
            if(vetGene[i]<10) {
                saida += "|00"+vetGene[i];
            }else if(vetGene[i]<100) {
                saida += "|0"+vetGene[i];
            }else {
                saida += "|"+vetGene[i];
            }			
        }
        saida += "|";
        return saida;
    }
    String exibeA(){		
        DecimalFormat df = new DecimalFormat("0.000");
        String fitness = df.format(this.fitness);		
        String saida = "Fitness do Top1: \t"+fitness;
        saida += "\nMedianas do Top 1: \t";
        for(int i=0;i<this.vetGene.length;i++){
            if(vetGene[i]<10) {
                saida += "|00"+vetGene[i];
            }else if(vetGene[i]<100) {
                saida += "|0"+vetGene[i];
            }else {
                saida += "|"+vetGene[i];
            }			
        }
        saida += "|";
        return saida;
    }
    void heranca(Cromossomo[] pais, Cromossomo piorPai) {
        if(pais[0].getGeracao()>pais[1].getGeracao()) {
            this.geracao = pais[0].getGeracao()+1;
        }else {
            this.geracao = pais[1].getGeracao()+1;
        }
        this.posVet = piorPai.getPosVet();
        this.label = piorPai.getLabel();
    }	
    public String getLabel() {
        return label;
    }
    public double getFitness() {
        return fitness;
    }	
    public int getNumGenes() {
        return this.vetGene.length;
    }
    public void setPosVetGene(int gene,int pos) {
        this.vetGene[pos] = gene;
    }
    public int getPosVetGene(int pos) {
        return this.vetGene[pos];
    }
    public int getPosVet() {
        return posVet;
    }
    public int getGeracao() {
        return geracao;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }	
}

