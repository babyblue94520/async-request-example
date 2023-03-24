package com.example.asyncrequestexample.infrastructure.support.concurrent;

import com.example.asyncrequestexample.infrastructure.exception.StringRejectedExecutionException;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

@Service
public class CustomAsyncTaskExecutor extends SimpleAsyncTaskExecutor {

    private volatile boolean exception = false;
    private volatile boolean full = false;
    private volatile boolean string = false;

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public void setString(boolean string) {
        this.string = string;
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        if(full){
            if(string){
                throw new StringRejectedExecutionException("Queue full");
            }else{
                throw new RejectedExecutionException("Queue full");
            }
        }else{
            if(exception){
                throw new RuntimeException("Exception");
            }else{
                super.execute(task, startTimeout);
            }
        }
    }
}
