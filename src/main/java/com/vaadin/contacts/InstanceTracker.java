package com.vaadin.contacts;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.InvocationContext;

/**
 *
 * @author adam-bien.com
 */
public class InstanceTracker {

    private static final Logger LOG = Logger.getLogger(InstanceTracker.class.getName());
    private static ConcurrentHashMap<String, AtomicInteger> managedInstances = new ConcurrentHashMap<>();

    @PostConstruct
    public void onCreate(InvocationContext context) throws Exception {
        Object target = context.getTarget();
        final String createdClass = target.getClass().getName();
        LOG.log(Level.INFO, "Created: {0}", createdClass);
        increment(createdClass);
        context.proceed();
    }

    /**
     * Is only invoked in case the intercepted component also implements a
     *
     * @PreDestroy callback
     */
    @PreDestroy
    public void onDestroy(InvocationContext context) throws Exception {
        Object target = context.getTarget();
        final String createdClass = target.getClass().getName();
        LOG.log(Level.INFO, "Destroyed: {0}", createdClass);
        decrement(null);
        context.proceed();
    }

    void increment(String createdClass) {
        managedInstances.putIfAbsent(createdClass, new AtomicInteger(0));
        AtomicInteger counter = managedInstances.get(createdClass);
        int instances = counter.incrementAndGet();
        LOG.log(Level.INFO, "{0} of class: {1}", new Object[]{instances, createdClass});
    }

    void decrement(String createdClass) {
        managedInstances.putIfAbsent(createdClass, new AtomicInteger(0));
        AtomicInteger counter = managedInstances.get(createdClass);
        int instances = counter.decrementAndGet();
        LOG.log(Level.INFO, "{0} of class: {1}", new Object[]{instances, createdClass});
    }

    public static String report() {
        LOG.log(Level.INFO, "Instances: {0}", managedInstances);
        return managedInstances.toString();
    }
}
