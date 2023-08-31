package utfpr.aulathread;

public class CalculadoraThread {
    private int soma;
    
    public synchronized int somaArray(int[] array){
        soma = 0;
        for(int i=0; i<array.length; i++){
            soma += array[i];
            System.out.println("Executando a "+Thread.currentThread().getName()+
                    " somando o valor "+array[i]+" em um total de: "+soma);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){}
        }   
        return soma;
    }
}
