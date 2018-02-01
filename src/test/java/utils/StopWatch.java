package utils;

public class StopWatch {

    ThreadLocal<Long> initTime = new ThreadLocal<>();

    public void start(){
        initTime.set(System.currentTimeMillis());
    }

    public long stop(){
        return System.currentTimeMillis() - initTime.get();
    }
}
