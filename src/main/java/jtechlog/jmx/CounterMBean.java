package jtechlog.jmx;

public interface CounterMBean {

    public long getValue();

    public void setValue(long counter);

    public void storno();
}
