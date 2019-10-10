public class Populacao {
    private Cromossomo[] vetC;
    private Cromossomo top1;

    Populacao(int tamPop,int[][] mat, int numGenes){
        this.vetC = new Cromossomo[tamPop]; 
        String label;
        boolean primeiro = true;

        for(int i=0;i<vetC.length;i++) {
            System.out.print("Criando a População: "+(i*100)/tamPop+"%\n");
            if(i<10) {
                label = "00"+i;
            }else if(i<100) {
                label = "0"+i;
            }else {
                label = i+"";
            }			
            this.vetC[i] = new Cromossomo(mat,numGenes,label,1,i);
            if(primeiro) {
                this.top1 = this.vetC[i];
                primeiro = false;
            }else if(this.top1.getFitness()>this.vetC[i].getFitness()){
                this.top1 = this.vetC[i];
            }
        }
    }

    public Cromossomo getVetC(int pos) {
        return vetC[pos];
    }
    public int tam() {
        return vetC.length;
    }
    public void setPosVetC(int pos,Cromossomo novo) {
        this.vetC[pos] = novo;
    }
    public Cromossomo getTop1() {
        return top1;
    }
    public void setTop1(Cromossomo top1) {
        this.top1 = top1;
    }

    String exibe() {
        String saida = "";
        for(int i=0;i<vetC.length;i++){
            saida += this.vetC[i].exibe()+"\n";
        }
        //saida += "Melhor:\n"+this.top1.exibe()+"\n";
        return saida;
    }

}
