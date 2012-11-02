package jtechlog.jmx;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitServletListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            CounterMBean counter = new Counter();
            mbs.registerMBean(counter, new ObjectName("jtechlog:type=Counter"));
            
            sce.getServletContext().setAttribute("counter", counter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
         MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
         mbs.unregisterMBean(new ObjectName("jtechlog:type=Counter"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}