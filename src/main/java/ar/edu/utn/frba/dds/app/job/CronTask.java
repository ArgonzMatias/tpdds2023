package ar.edu.utn.frba.dds.app.job;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CronTask {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> cronHandle;

    public void iniciarCronTask(Job job, long intervaloEnHoras) {
        cronHandle = scheduler.scheduleAtFixedRate(job::ejecutar, 0, intervaloEnHoras, TimeUnit.HOURS);
    }

    public void detenerCronTask() {
        cronHandle.cancel(false);
    }
}