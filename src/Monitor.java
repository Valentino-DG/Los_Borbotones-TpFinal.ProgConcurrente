import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private Log log;
    private Rdp red;
    private Politica politica;
    private ReentrantLock lock;
   // private ArrayList<Condition> colaDeHilos;
    private ArrayList<Integer> encolados = new ArrayList<Integer>();
    private ArrayList<Semaphore> colaDeHilos;
    private Semaphore mutex ;

    public Monitor(Log log){

        this.log = log;
        red = new Rdp();
        politica=new Politica();
        lock = new ReentrantLock();
       // this.colaDeHilos = new ArrayList<Condition>();
        colaDeHilos = new ArrayList<Semaphore>();
        mutex = new Semaphore(1);

       // for (int i = 0; i < red.getCantTransiciones(); i++) {
           //Creamos una cola para cada transicion para que almacene los hilos que se encuentren esperando a poder dispararla.
         // colaDeHilos.add(lock.newCondition());
        //}

        for(int i = 0; i < red.getCantTransiciones(); i++){
            colaDeHilos.add(new Semaphore(0));
        }

    }

    public void dispararTransicion(Integer transicion){

    try {
        mutex.acquire();
    } catch(Exception e){
    }

    while(!red.disparar(transicion)){
            try{
                encolados.add(transicion);
                mutex.release();
                colaDeHilos.get(transicion-1).acquire();
                encolados.remove(transicion);
            }
            catch (Exception e){
                e.printStackTrace();
                return;
            }
        }

        if(transicion == 6 || transicion == 10 || transicion == 4 || transicion == 5) {
            log.logear(transicion);
        }

        if(encolados.size() > 0) {

            encolados = politica.reordenar(encolados);

            for (Integer it : encolados) {
                if (red.estaSensibilizada(it)) {
                    colaDeHilos.get(it - 1).release();
                    return;

                }
            }
        }
            mutex.release();

    }



    private ArrayList<Integer> sensibilizada( ArrayList<Integer> encolados ){

      ArrayList<Integer> encSens = new ArrayList<Integer>();

      for(Integer it: encolados){

          if(red.estaSensibilizada(it)){
              encSens.add(it);
          }
      }

        imprimir(encSens);
        imprimir(encolados);

      if(encSens.isEmpty()){

          return encolados;
      }else{
          return encSens;
      }

    }

    private void imprimir(ArrayList<Integer> lista){

        for(Integer it: lista){

            System.out.print(it);
        }
        System.out.println("--------------------------");
    }


}
