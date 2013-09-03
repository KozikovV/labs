package com.zinjvi.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User: zinchenko
 * Date: 9/3/13
 */
public interface PerfectTimeI extends Remote{
    public Long getPerfectTime() throws RemoteException;
}
