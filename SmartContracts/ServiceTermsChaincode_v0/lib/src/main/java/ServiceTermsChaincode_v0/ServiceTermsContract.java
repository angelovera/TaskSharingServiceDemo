/* Package chaincode */
package ServiceTermsChaincode_v0;

/* Import libraries and dependencies */
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import com.owlike.genson.Genson;

/* Define contract */
@Contract(
        name = "ServiceTerms",
        info = @Info(
                title = "ServiceTerms contract",
                description = "ServiceTerms chaincode version 0",
                version = "0.0.1-SNAPSHOT"))

@Default
public final class ServiceTermsContract implements ContractInterface {

	private final Genson genson = new Genson();
	private enum ServiceTermsErrors {
	        SERVICE_NOT_FOUND,
	        SERVICE_ALREADY_EXISTS
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
	 * 
	 * return service
	 */
    @Transaction()
    public ServiceTerms serviceRequest(final Context ctx, final String serviceID, final String serviceOwner, final String requestedResources, final String requestTime) {  
    	ChaincodeStub stub = ctx.getStub();
        String serviceState = stub.getStringState(serviceID);
        if (!serviceState.isEmpty()) {
            String errorMessage = String.format("Service %s already exists", serviceID);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, ServiceTermsErrors.SERVICE_ALREADY_EXISTS.toString());
        }
        ServiceTerms service = new ServiceTerms(serviceID, serviceOwner, requestedResources, requestTime, null, null, null, null, null);
        serviceState = genson.serialize(service);
        stub.putStringState(serviceID, serviceState); 
        return service;
    }
    
    /**
	 * Transaction: serviceReply
	 * Details: Following a serviceRequest, the cloud node writes the list of available servers on the global channel ledger.
	 * Prototype: submitTransaction(serviceReply,"serviceID","availableServers");
	 * 
	 * @param ctx transaction context
	 * @param serviceID
	 * 
	 * return service
	 */
   	 @Transaction()
   	 public ServiceTerms serviceReply(final Context ctx, final String serviceID, final String availableServers, final String replyTime) {
   		 ChaincodeStub stub = ctx.getStub();
   		 String serviceState = stub.getStringState(serviceID);
   		 if (serviceState.isEmpty()) {
   			 String errorMessage = String.format("Service %s does not exist", serviceID);
   	         System.out.println(errorMessage);
   	         throw new ChaincodeException(errorMessage, ServiceTermsErrors.SERVICE_NOT_FOUND.toString());
   	     }
   	     ServiceTerms service = genson.deserialize(serviceState, ServiceTerms.class);
   	     ServiceTerms newService = new ServiceTerms(service.getServiceID(), service.getServiceOwner(), service.getRequestedResources(), service.getRequestTime(), availableServers, replyTime, null, null, null);
   	     String newServiceState = genson.serialize(newService);
   	     stub.putStringState(serviceID, newServiceState);
   	     return newService;
   	 }
   	 
   	 /**
   	 * Transaction: serviceDecisionStarted
	 * Details: After the service has been replied, the edge node reads the list of available servers and solves a task allocation problem.
	 * Prototype: submitTransaction(serviceDecisionStarted,"serviceID");
	 * 
	 * @param ctx transaction context
	 * @param serviceID
	 * 
	 * return service
	 */
   	@Transaction()
  	 public ServiceTerms decisionStarted(final Context ctx, final String serviceID, final String decisionStartTime) {
  		 ChaincodeStub stub = ctx.getStub();
  		 String serviceState = stub.getStringState(serviceID);
  		 if (serviceState.isEmpty()) {
  			 String errorMessage = String.format("Service %s does not exist", serviceID);
  	         System.out.println(errorMessage);
  	         throw new ChaincodeException(errorMessage, ServiceTermsErrors.SERVICE_NOT_FOUND.toString());
  	     }
  		 ServiceTerms service = genson.deserialize(serviceState, ServiceTerms.class);
  	     ServiceTerms newService = new ServiceTerms(service.getServiceID(), service.getServiceOwner(), service.getRequestedResources(), service.getRequestTime(), service.getAvailableServers(), service.getReplyTime(), decisionStartTime, null, null);
  	     String newServiceState = genson.serialize(newService);
  	     stub.putStringState(serviceID, newServiceState);
  	     return newService;
  	 }
   	 
   	/**
	 * Transaction: serviceDecisionFinished
	 * Details: After the edge node solves the task allocation problem, it writes the results on to the ledger.
	 * Prototype: submitTransaction(serviceDecisionFinished,"serviceID","serviceProviders");
	 * 
	 * @param ctx transaction context
	 * @param serviceID
	 * @param serviceProviders
	 * 
	 * return service
	 */
   	 @Transaction()
   	 public ServiceTerms decisionFinished(final Context ctx, final String serviceID, final String serviceProviders, final String decisionFinishTime) {
   		 ChaincodeStub stub = ctx.getStub();
   		 String serviceState = stub.getStringState(serviceID);
   		 if (serviceState.isEmpty()) {
   			 String errorMessage = String.format("Service %s does not exist", serviceID);
   	         System.out.println(errorMessage);
   	         throw new ChaincodeException(errorMessage, ServiceTermsErrors.SERVICE_NOT_FOUND.toString());
   	     }
   	     ServiceTerms service = genson.deserialize(serviceState, ServiceTerms.class);
   	     ServiceTerms newService = new ServiceTerms(service.getServiceID(), service.getServiceOwner(), service.getRequestedResources(), service.getRequestTime(), service.getAvailableServers(), service.getReplyTime(), service.getDecisionStartTime(),serviceProviders, decisionFinishTime);
   	     String newServiceState = genson.serialize(newService);
   	     stub.putStringState(serviceID, newServiceState);
   	     return newService;
   	 }
   	 
     /**
      * Transaction: queryServiceByID 
      * Details: The monitoring tool queries a service by ID.
      * Prototype: submitTransaction(queryServiceByID,"serviceID");
      * 
      * @param ctx transaction context
      * @param serviceID
      * 
      * return service
      */
      @Transaction()
  	public ServiceTerms queryServiceByID(final Context ctx, final String serviceID) {
      	ChaincodeStub stub = ctx.getStub();
  	    String serviceState = stub.getStringState(serviceID);
  	    if (serviceState.isEmpty()) {
  	    	String errorMessage = String.format("Service %s does not exist", serviceID);
  	        System.out.println(errorMessage);
  	        throw new ChaincodeException(errorMessage, ServiceTermsErrors.SERVICE_NOT_FOUND.toString());
  	    }	        
  	    ServiceTerms service = genson.deserialize(serviceState, ServiceTerms.class);
  	    return service;
      }  	 
}
