package laba7;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import interfaces.task7.executor.Task;
import interfaces.task7.executor.TasksStorage;

/**
 * Хранилище заданий.
 * 
 * @author zinchenko
 *
 */
public class TasksStorageImpl implements TasksStorage {

    private Queue<Task> queue = new LinkedBlockingQueue<Task>();

    @Override
    public void add(final Task arg0) {
        queue.add(arg0);
    }

    @Override
    public int count() {
        return queue.size();
    }

    @Override
    public Task get() {
        return queue.poll();
    }

}
