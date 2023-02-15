public class TerminarTurno implements Runnable{
    private Monitor monitor;

    public TerminarTurno(Monitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            monitor.dispararTransicion(6);
        }
    }

}
