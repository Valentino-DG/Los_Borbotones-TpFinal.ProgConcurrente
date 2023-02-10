public class TerapiaAndSC implements Runnable{
    private Monitor monitor;

    public TerapiaAndSC(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            monitor.dispararTransicion(3);
            monitor.dispararTransicion(5);
        }
    }
}
