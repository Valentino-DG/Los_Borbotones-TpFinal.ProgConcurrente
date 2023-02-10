public class Shockroom implements Runnable{
    private Monitor monitor;

    public Shockroom(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            monitor.dispararTransicion(1);
        }
    }
}
