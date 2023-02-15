public class SalaComunAndTT implements Runnable{
    private Monitor monitor;

    public SalaComunAndTT(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            monitor.dispararTransicion(7);
            monitor.dispararTransicion(8);
            monitor.dispararTransicion(9);
            monitor.dispararTransicion(10);
        }
    }
}
