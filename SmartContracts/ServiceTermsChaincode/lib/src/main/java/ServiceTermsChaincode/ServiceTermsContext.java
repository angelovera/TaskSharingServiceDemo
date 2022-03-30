package ServiceTermsChaincode;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;

class ServiceTermsContext extends Context {
	public ServiceTermsContext(ChaincodeStub stub) {
        super(stub);
        this.serviceList = new ServiceList(this);
    }
    public ServiceList serviceList;
}