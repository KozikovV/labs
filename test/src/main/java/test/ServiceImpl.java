package test;

/**
 * User: zinchenko
 * Date: 10/7/13
 */
public class ServiceImpl implements Service {

    private InternalService internalService;

    public InternalService getInternalService() {
        return internalService;
    }

    public void doSomethingInService() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doSomethingInServiceSecond() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setInternalService(InternalService internalService) {
        this.internalService = internalService;
    }
}
