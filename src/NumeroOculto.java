public class NumeroOculto extends Thread {
    //Creamos las variables estaticas para que se pueda ascceder desde cualquier hilo
    public static int numeroAzar;
    private static boolean adivinado=false;



    @Override
    public void run() {
        int numero=0;
        //Hasta que no se adivine, crearemos un numero aleatorio y llamaremos a propuesta numero
        while (numero==0){
            numero=propuestaNumero((int) (Math.random()*100));
            //LLamamos a propuesta numero con un numero aleatorio
            if (numero==1){
                //Si lo adivina escribimos el mensaje por consola
                System.out.println("El numero fue adivinado por el "+currentThread().getName());
                this.interrupt();
            }else if(numero==-1){
                this.interrupt();
            }

        }
    }

    /**
     * Este metodo devolvera -1 si el numero ya esta adivinado, 1, si el numero que le entra por parametros es el numero a adivinar y 0 si ninguja de las anteriores
     * En el caso de adivinarlo cambiara la variable adivinado a false
     * @param num
     * @return
     */
    private static synchronized int propuestaNumero(int num){
        int salida;
        if(adivinado){
            salida=-1;
        }else if(num==numeroAzar){
            adivinado=true;
            salida=1;
        }else{
            salida=0;
        }
        return salida;
    }

    public static void main(String[] args) {
        //Creamos el numero al azar
        int numeroAzar=(int) (Math.random()*100);
        NumeroOculto.numeroAzar=numeroAzar;

        //Lanzamos 10 hilos con un nombre diferente para cada uno
        for (int i = 0; i < 10; i++) {
            Thread h= new Thread(new NumeroOculto());
            h.setName("hilo "+(i+1));
            h.start();
        }
    }

}
