package jtechlog.jmx;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter
        extends NotificationBroadcasterSupport
        implements CounterMBean {

    private AtomicInteger value = new AtomicInteger();

    @Override
    public int getValue() {
        return value.get();
    }

    @Override
    public void setValue(int i) {
        value.set(i);
    }

    @Override
    public void storno() {
        value.set(0);
    }

    synchronized public void incrementCounter() {
        int i = value.incrementAndGet();
        if (i % 10 == 0) {
            Notification n =
                    new AttributeChangeNotification(this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Counter value has changed",
                    "Counter value",
                    "long",
                    i - 1,
                    i);

            sendNotification(n);

        }
    }
    private long sequenceNumber = 1;

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
            AttributeChangeNotification.ATTRIBUTE_CHANGE
        };
        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }
}
