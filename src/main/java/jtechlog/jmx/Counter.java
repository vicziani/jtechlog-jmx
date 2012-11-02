package jtechlog.jmx;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Counter
        extends NotificationBroadcasterSupport
        implements CounterMBean {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void storno() {
        value = 0;
    }

    synchronized public void incrementCounter() {
        value++;
        if (value > 10) {
            Notification n =
                    new AttributeChangeNotification(this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Counter value has changed",
                    "Counter value",
                    "long",
                    value - 1,
                    value);

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
