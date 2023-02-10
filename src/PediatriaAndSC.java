public class PediatriaAndSC implements Runnable{
    private Monitor monitor;

    public PediatriaAndSC(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            monitor.dispararTransicion(2);
            monitor.dispararTransicion(4);
        }
    }
}
