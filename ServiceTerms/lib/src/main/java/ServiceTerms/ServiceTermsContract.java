// Package code
package ServiceTerms;

// Import dependencies
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import com.owlike.genson.Genson;

// Define contract annotation
@Contract(
        name = "ServiceTermsContract",
        info = @Info(
                title = "ServiceTerms contract",
                description = "Chaincode to be deployed on the global channel",
                version = "0.0.1-SNAPSHOT"))

// Define contract class
@Default
public final class ServiceTermsContract implements ContractInterface {

	private final Genson genson = new Genson();
	private enum ServiceTermsErrors {
	        SERVICE_NOT_FOUND,
	        SERVICE_ALREADY_EXISTS
	    }
	
// Define contract transactions	
	/**
     * Add some initial properties to the ledger
     *
     * @param ctx the transaction context
     */
	
// Initialize the ledger with a pilot service 
    @Transaction()
    public void initLedger(final Context ctx) {
        ChaincodeStub stub= ctx.getStub();
        ServiceTerms serviceTerms = new ServiceTerms("1", "Angelo Vera-Rivera","Null","Null");        
        String serviceTermsState = genson.serialize(serviceTerms);
        stub.putStringState("1", serviceTermsState);
    }
    
    /**
     * Request service on the ledger.
     *
     * @param ctx the transaction context
     * @param serviceID the ID for the new service
     * @param serviceOwner the server's name of the new service request
     * @param availableServers the list of available servers in the network
     * @param serviceProviders the list of service providers
     * @return the created service
     */

// Request new service
    @Transaction()
    public ServiceTerms requestNewService(final Context ctx, final String serviceID, final String serviceOwner, final String availableServers,
            final String serviceProviders) {
    	ChaincodeStub stub = ctx.getStub();
        String serviceTermsState = stub.getStringState(serviceID);
        
        if (!serviceTermsState.isEmpty()) {
            String errorMessage = String.format("Service %s already exists", serviceID);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, ServiceTermsErrors.SERVICE_ALREADY_EXISTS.toString());
        }
        
        ServiceTerms serviceTerms = new ServiceTerms(serviceID, serviceOwner, "Null", "Null");
        serviceTermsState = genson.serialize(serviceTerms);
        stub.putStringState(serviceID, serviceTermsState); 
        return serviceTerms;
    }

    	/**
	     * Retrieves a home based upon home Id from the ledger.
	     *
	     * @param ctx the transaction context
	     * @param id the key
	     * @return the home found on the ledger if there was one
	     */
    
// Query service by ID
    	@Transaction()
	    public ServiceTerms queryServiceByID(final Context ctx, final String serviceID) {
	        ChaincodeStub stub = ctx.getStub();
	        String serviceTermsState = stub.getStringState(serviceID);

	        if (serviceTermsState.isEmpty()) {
	            String errorMessage = String.format("Service %s does not exist", serviceID);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, ServiceTermsErrors.SERVICE_NOT_FOUND.toString());
	        }
	        
	        ServiceTerms serviceTerms = genson.deserialize(serviceTermsState, ServiceTerms.class);
	        return serviceTerms;
	    }
    	
//        /**
//   	     * Changes the owner of a home on the ledger.
//   	     *
//   	     * @param ctx the transaction context
//   	     * @param id the key
//   	     * @param newOwner the new owner
//   	     * @return the updated home
//   	     */
//   	    @Transaction()
//   	    public Home changeHomeOwnership(final Context ctx, final String id, final String newHomeOwner) {
//  	        ChaincodeStub stub = ctx.getStub();

//   	        String homeState = stub.getStringState(id);

//   	        if (homeState.isEmpty()) {
//   	            String errorMessage = String.format("Home %s does not exist", id);
//   	            System.out.println(errorMessage);
//   	            throw new ChaincodeException(errorMessage, HomeTransferErrors.HOME_NOT_FOUND.toString());
//   	        }

//   	        Home home = genson.deserialize(homeState, Home.class);

//   	        Home newHome = new Home(home.getId(), home.getName(), home.getArea(), newHomeOwner, home.getValue());
   	        
//   	        String newHomeState = genson.serialize(newHome);
   	        
//   	        stub.putStringState(id, newHomeState);

//   	        return newHome;
   	    }

// Structure of the code:
// 1. Package code
// 2. Import dependencies
// 3. Define contract annotation
// 4. Define contract class
// 5. Define Genson instance and errors
// 6. Create contract transactions

// 1. Package code
//package ServiceTerms;

// 2. Import dependencies
//import org.hyperledger.fabric.contract.Context;
//import org.hyperledger.fabric.contract.ContractInterface;
//import org.hyperledger.fabric.contract.annotation.Contract;
//import org.hyperledger.fabric.contract.annotation.Default;
//import org.hyperledger.fabric.contract.annotation.Info;
//import org.hyperledger.fabric.contract.annotation.Transaction;
//import org.hyperledger.fabric.shim.ChaincodeException;
//import org.hyperledger.fabric.shim.ChaincodeStub;
//import com.owlike.genson.Genson;


// 3. Define contract annotation 
//@Contract(
//		name = " Service Terms Contract",
//		info = @Info(
//				title = "Service Terms Contract Implementation",
//				description = "Contract to be implemented in the global channel",
//				version = "1.0"
//				)
//		)

// 4. Define contract class
//@Default
//public final class ServiceTermsContract implements ContractInterface {
	
// 5. Define Genson instance and errors
//	private final Genson genson = new Genson();
//	private enum ServiceTermsErrors{
//		SERVICE_NOT_FOUND,
//		SERVICE_ALREADY_EXIST	
//	}

// 6. Create contract transactions
	
//	/**initLedger()
//	 * 
//	 * Add some initial properties to the ledger 
//	 * 
//	 * @param ctx the transaction context
//	 */
//	@Transaction()
//	public void initLedger(final Context ctx) {
//		ChaincodeStub stub = ctx.getStub();
//		ServiceTerms serviceTerms = new ServiceTerms("0000","Null","Null","Null");
//		String serviceState = genson.serialize(serviceTerms);
//		stub.putStringState("0000",serviceState);
//	}		
//}