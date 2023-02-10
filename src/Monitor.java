import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private Rdp red;
    private ReentrantLock lock;
    private ArrayList<Condition> colaDeHilos;
    private ArrayList<Integer>encolados = new ArrayList<>();
    Random rand = new Random();
    //private Integer [] encolados = new Integer[10];
    //Agregar politica

    public Monitor(){
        this.red = new Rdp();
        this.lock = new ReentrantLock();
        this.colaDeHilos = new ArrayList<Condition>();

        for (int i = 0; i < red.getCantTransiciones(); i++) {
        //Creamos una cola para cada transicion para que almacene los hilos que se encuentren esperando a poder dispararla.
            colaDeHilos.add(lock.newCondition());
            }
    }

    public void dispararTransicion(int transicion){
        lock.lock();
        while(!red.disparar(transicion)){
            try{
                encolados.add(transicion);
                colaDeHilos.get(transicion).await();
                encolados.remove(encolados.indexOf(transicion));
            }
            catch (Exception e){
                e.printStackTrace();
                return;
            }
        }
        System.out.println("Se disparo la transicion: " + transicion);
        if(encolados.size() != 0){
            int random = rand.nextInt(encolados.size());
            colaDeHilos.get(encolados.get(random)).signal();
        }
        lock.unlock();

    }
}
