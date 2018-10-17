package app.boilerplate.utils.rx;

import io.reactivex.Scheduler;


public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
