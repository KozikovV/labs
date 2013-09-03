package laba7;

import interfaces.task7.executor.Executor;
import interfaces.task7.executor.Task;
import interfaces.task7.executor.TasksStorage;

/**
 * Executor.
 * 
 * @author zinchenko
 * 
 */
public class ExecutorImpl implements Executor {

    private static final int MAX_TRY = 5;
    private static final int TIME_SLEEP = 100;

    private TasksStorage tasks;

    private boolean started = false;
    private boolean stoped = false;

    @Override
    public TasksStorage getStorage() {
        return tasks;
    }

    @Override
    public void setStorage(final TasksStorage arg0) {

        if (arg0 == null) {
            throw new NullPointerException();
        }

        tasks = arg0;

    }

    @Override
    public void start() {
        if (started) {
            throw new IllegalStateException();
        }
        started = true;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!stoped) {

                    tryExecute();

                    try {
                        System.out.println("go to sleep "
                                + Thread.currentThread().getName());
                        Thread.sleep(TIME_SLEEP);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();
    }

    /**
     * Попытка выполнить задачу.
     */
    private void tryExecute() {
        Task task = tasks.get();
        if (task != null) {
            task.incTryCount();
            try {
                if (!task.execute()) {
                    putBackTask(task);
                }
                System.out.println("executed "
                        + Thread.currentThread().getName());
            } catch (Exception e) {
                putBackTask(task);
            }
        }
    }
    
    private void putBackTask(Task task){
        int count = task.getTryCount();
        if (count <= MAX_TRY) {
            tasks.add(task);
            System.out.println("put back "
                    + Thread.currentThread().getName());
        }
    }

    @Override
    public void stop() {
        if (!started) {
            throw new IllegalStateException();
        }
        stoped = true;
        started = false;
    }

}
