package com.zinjvi.rmi.service.impl;

import com.zinjvi.rmi.service.PerfectTimeI;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * User: zinchenko
 * Date: 9/3/13
 */
public class PerfectTime extends UnicastRemoteObject implements PerfectTimeI {

    protected PerfectTime() throws RemoteException {
    }

    @Override
    public Long getPerfectTime() throws RemoteException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) throws Exception {
        System.setSecurityManager(new RMISecurityManager());
        PerfectTime pt = new PerfectTime();
        Naming.rebind("//10.10.3.28/PerfectTime", pt);
        System.out.println("Ready to do time");
    }

}
