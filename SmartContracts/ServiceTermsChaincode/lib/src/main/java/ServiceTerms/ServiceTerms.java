// Package code
package ServiceTerms;

// Import Dependencies
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;

// Define service terms class
@DataType()
public final class ServiceTerms {

//  Define attributes
	@Property()
	private final String serviceID;
	@Property()
	private final String serviceOwner;
	@Property()
	private final String availableServers;
	@Property()
	private final String serviceProviders;
	
//Define methods (getters)	
	public String getServiceID() {
		return serviceID;
	}

	public String getServiceOwner() {
		return serviceOwner;
	}

	public String getAvailableServers() {
		return availableServers;
	}

	public String getServiceProviders() {
		return serviceProviders;
	}

// Define constructor
	public ServiceTerms(@JsonProperty("serviceID") final String serviceID, @JsonProperty("serviceOwner") final String serviceOwner,
			@JsonProperty("availableServers") final String availableServers, @JsonProperty("serviceProviders") final String serviceProviders) {
		this.serviceID = serviceID;
		this.serviceOwner = serviceOwner;
		this.availableServers = availableServers;
		this.serviceProviders = serviceProviders;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		ServiceTerms other = (ServiceTerms) obj;

		return Objects.deepEquals(new String[] { getServiceID(), getServiceOwner(), getAvailableServers(), getServiceProviders() },
				new String[] { other.getServiceID(), other.getServiceOwner(), other.getAvailableServers(), other.getServiceProviders() });
	}

	@Override
	public int hashCode() {
		return Objects.hash(getServiceID(), getServiceOwner(), getAvailableServers(), getServiceProviders());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [ID=" + serviceID + ", owner=" + serviceOwner
				+ ", availability=" + availableServers + ", serviced by=" + serviceProviders + "]";
	}

}

// Structure of the code:
// 1. Package code
// 2. Import dependencies
// 3. Define service terms class
// 4. Define service terms state values
// 5. Define service terms properties
// 6. Define service terms methods
// 7. Define constructor

// 1. Package code
//package ServiceTerms;

// 2. Import dependencies
//import static java.nio.charset.StandardCharsets.UTF_8;
//import com.owlike.genson.annotation.JsonProperty;
//import org.hyperledger.fabric.contract.annotation.DataType;
//import org.hyperledger.fabric.contract.annotation.Property;
//import java.util.Objects;
//import ServiceTerms.ledgerapi.State;
//import org.json.JSONObject;
//import org.json.JSONPropertyIgnore;

// 3. Define service terms class
//@DataType()
//public class ServiceTerms extends State {

// 4. Define service terms state values
    //public final static String REQUESTED = "REQUESTED";
    //public final static String REPLIED = "REPLIED";
    //public final static String DECISION_STARTED = "DECISION_STARTED";
    //public final static String DECISION_FINISHED = "DECISION_FINISHED";

// 5. Define service terms properties
//    @Property()
//    private String state="";
//    @Property()
//   	private final String serviceID;
//   	@Property()
//   	private final String serviceOwner;
//   	@Property()
//   	private final String availableServers;
//   	@Property()
//   	private final String serviceProviders;
    
 // 6. Define service terms methods
    
//    public String getState() {
//        return state;
//    }

//    public ServiceTerms setState(String state) {
//        this.state = state;
//        return this;
//    }

//    @JSONPropertyIgnore()
//    public boolean isRequested() {
//        return this.state.equals(ServiceTerms.REQUESTED);
//    }

//    @JSONPropertyIgnore()
//    public boolean isReplied() {
//        return this.state.equals(ServiceTerms.REPLIED);
//    }

//    @JSONPropertyIgnore()
//    public boolean isDecisionStarted() {
//        return this.state.equals(ServiceTerms.DECISION_STARTED);
//    }

//    @JSONPropertyIgnore()
//    public boolean isDecisionFinished() {
//        return this.state.equals(ServiceTerms.DECISION_FINISHED);
//    }
    
//    public ServiceTerms setRequested() {
//        this.state = ServiceTerms.REQUESTED;
//        return this;
//    }

//    public ServiceTerms setReplied() {
//        this.state = ServiceTerms.REPLIED;
//        return this;
//    }

//    public ServiceTerms setDecisionStarted() {
//        this.state = ServiceTerms.DECISION_STARTED;
//        return this;
//    }

//    public ServiceTerms setDecisionFinished() {
//        this.state = ServiceTerms.DECISION_FINISHED;
//        return this;
//    }
    
//    public ServiceTerms() {
//        super();
//   }
    
//    public String getServiceID() {
//        return serviceID;
//    }

//    public ServiceTerms setServiceID(String serviceID) {
//        this.serviceID = serviceID;
//        return this;
//    }
    
//    public String getServiceOwner() {
//    	return serviceOwner;
//    }
    
//    public ServiceTerms setServiceOwner(String serviceOwner) {
//        this.serviceOwner = serviceOwner;
//        return this;
//    }
    
//    public String getAvailableServers() {
//       return availableServers;
//    }

//    public ServiceTerms setAvailableServers(String availableServers) {
//        this.availableServers = availableServers;
//        return this;
//    }

//    public String getServiceProviders() {
//   	return serviceProviders;
//     }

//    public ServiceTerms setServiceProviders(String serviceProviders) {
//        this.serviceProviders = serviceProviders;
//        return this;
//    }

//    @Override
//    public String toString() {
//       return "Paper::" + this.key + "   " + this.getServiceID() + " " + getServiceOwner() + " " + getAvailableServers() + " " + getServiceProviders();
//    }

//    /**
//    * Deserialize a state data to service terms
//     *
//     * @param {Buffer} data to form back into the object
//     */
//    public static ServiceTerms deserialize(byte[] data) {
//       JSONObject json = new JSONObject(new String(data, UTF_8));

//        String serviceID = json.getString("serviceID");
//        String serviceOwner = json.getString("serviceOwner");
//        String availableServers = json.getString("availableServers");
//        String serviceProviders = json.getString("serviceProviders");
//        String state = json.getString("state");        
//        return createInstance(serviceID, serviceOwner, availableServers, serviceProviders,state);
//    }

//    public static byte[] serialize(ServiceTerms paper) {
//       return State.serialize(paper);
//    }

//    /**
//     * Factory method to create a service terms object
//     */
    
 // 7. Define constructor 
//    public static ServiceTerms createInstance(String serviceID, String serviceOwner, String availableServers,
//            String serviceProviders, String state) {
//       return new ServiceTerms().setServiceID(serviceID).setServiceOwner(serviceOwner).setAvailableServers(availableServers)
//               .setServiceProviders(serviceProviders).setState(state);
//    }
//}