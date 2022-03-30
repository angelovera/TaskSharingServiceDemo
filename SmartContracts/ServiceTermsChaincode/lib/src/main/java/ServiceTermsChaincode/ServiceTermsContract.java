// 1, PACKAGE CODE
package ServiceTermsChaincode;


// 2. IMPORT DEPENDENCIES
import java.util.logging.Logger;
import ServiceTermsChaincode.ledgerapi.State;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeStub;


// 3. DEFINE CONTRACT
@Contract(
		name="ServiceTermsContract",
		info=@Info(
				title="Service Terms Contract",
				description="Chaincode to be deployed on the global channel",
				version="0.0.1-SNAPSHOT"))


@Default
public class ServiceTermsContract implements ContractInterface{
	private final static Logger LOG = Logger.getLogger(ServiceTermsContract.class.getName());
	
	@Override
	public Context createContext(ChaincodeStub stub) {
		return new ServiceTermsContext(stub);
	}
	
	public ServiceTermsContract() {
	}
	
	/**
	 * Define a custom context for service terms.	
	 */
		
	/**
	 * Instantiate the contract to perform any initial setup on the ledger.
	 * 
	 * @param ctx is the transaction context 
	 */
	@Transaction
	public void instantiate(ServiceTermsContext ctx) {
		LOG.info("No initial setup required for this use case");
	}
	
	/**
	 * Transaction: serviceRequest 
	 * Details: An edge server writes a service request on the global channel ledger.
	 * Prototype: submitTransaction(serviceRequest,"serviceID","serviceOwner","requestedResources");
	 * 
	 * @param ctx transaction context
	 * @param serviceID
	 * @param serviceOwner
	 * @param requestedResources
	 */
	@Transaction()
	public ServiceTerms serviceRequest(ServiceTermsContext ctx, String serviceID, String serviceOwner, String requestedResources) {
		System.out.println(ctx);
		ServiceTerms service = ServiceTerms.createInstance(serviceID, serviceOwner, requestedResources,"","");
		service.setRequested();
		service.setServiceOwner(serviceOwner);
		System.out.println(service);
		ctx.serviceList.addService(service);
		return service;
	}
	
	/**
	 * Transaction: serviceReply
	 * Details: Following a serviceRequest, the cloud node writes the list of available servers on the global channel ledger.
	 * Prototype: submitTransaction(serviceReply,"serviceID","availableServers");
	 * 
	 * @param ctx transaction context
	 * @param serviceID
	 */
	public ServiceTerms serviceReply(ServiceTermsContext ctx, String serviceID, String availableServers) {
		String paperKey = State.makeKey(new String[] {serviceID});
		ServiceTerms service = ctx.serviceList.getService(paperKey);
		if (service.isRequested()) {
			service.setReplied();
		}
		service.setAvailableServers(availableServers);
		ctx.serviceList.updateService(service);
		return service;
	}
	
	/**
	 * Transaction: serviceDecisionStarted
	 * Details: After the service has been replied, the edge node reads the list of available servers and solves a task allocation problem.
	 * Prototype: submitTransaction(serviceDecisionStarted,"serviceID");
	 * 
	 * @param ctx transaction context
	 * @param serviceID
	 */
	public ServiceTerms serviceDecisionStarted(ServiceTermsContext ctx, String serviceID) {
		String paperKey = State.makeKey(new String[] {serviceID});
		ServiceTerms service = ctx.serviceList.getService(paperKey);
		if(service.isReplied())
		{
			service.setDecisionStarted();	
		}
		return service;
	}
	
	/**
	 * Transaction: serviceDecisionFinished
	 * Details: After the edge node solves the task allocation problem, it writes the results on to the ledger.
	 * Prototype: submitTransaction(serviceDecisionFinished,"serviceID","serviceProviders");
	 * 
	 * @param ctx transaction context
	 * @param serviceID
	 * @param serviceProviders
	 */
	public ServiceTerms serviceDecisionFinished(ServiceTermsContext ctx, String serviceID, String serviceProviders) {
		String paperKey = State.makeKey(new String[] {serviceID});
		ServiceTerms service = ctx.serviceList.getService(paperKey);
		if(service.isDecisionStarted()) {
			service.setDecisionFinished();
		}
		service.setServiceProviders(serviceProviders);
		ctx.serviceList.updateService(service);
		return service;
	}
}