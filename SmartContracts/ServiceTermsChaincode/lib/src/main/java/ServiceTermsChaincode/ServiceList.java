package ServiceTermsChaincode;

import ServiceTermsChaincode.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

public class ServiceList {

    private StateList stateList;

    public ServiceList(Context ctx) {
        this.stateList = StateList.getStateList(ctx, ServiceList.class.getSimpleName(), ServiceTerms::deserialize);
    }

    public ServiceList addService(ServiceTerms service) {
        stateList.addState(service);
        return this;
    }

    public ServiceTerms getService(String serviceKey) {
        return (ServiceTerms) this.stateList.getState(serviceKey);
    }

    public ServiceList updateService(ServiceTerms service) {
        this.stateList.updateState(service);
        return this;
    }
}
