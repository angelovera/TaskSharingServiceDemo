/* 1. PACKAGE CODE */
package ServiceTermsChaincode;

/* 2. IMPORT DEPENDENCIES */
import static java.nio.charset.StandardCharsets.UTF_8;
import ServiceTermsChaincode.ledgerapi.State;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

/* 3. DEFINE SERVICE TERMS CLASS */
@DataType()
public final class ServiceTerms extends State{

/* 4. DEFINE CONTRACT STATES */
	public final static String REQUESTED = "REQUESTED";
	public final static String REPLIED = "REPLIED";
	public final static String DECISION_STARTED = "DECISION_STARTED";
	public final static String DECISION_FINISHED = "DECISION_FINISHED";
	
/* 5. DEFINE ATTRIBUTES AND METHODS RELATED TO THE STATES */
	@Property()
	private String state = "";
	
	public String getState() {
		return state;
	}
	
	public ServiceTerms setState(String state) {
		this.state = state;
		return this;
	}
	
	@JSONPropertyIgnore()
	public boolean isRequested() {
		return this.state.equals(ServiceTerms.REQUESTED);
	}
	
	@JSONPropertyIgnore()
	public boolean isReplied() {
		return this.state.equals(ServiceTerms.REPLIED);
	}
	
	@JSONPropertyIgnore()
	public boolean isDecisionStarted() {
		return this.state.equals(ServiceTerms.DECISION_STARTED);
	}
	
	@JSONPropertyIgnore()
	public boolean isDecisionFinished() {
		return this.state.equals(ServiceTerms.DECISION_FINISHED);
	}
	
	
	public ServiceTerms setRequested() {
		this.state = ServiceTerms.REQUESTED;
		return this;
	}
	
	public ServiceTerms setReplied() {
		this.state = ServiceTerms.REPLIED;
		return this;
	}
	
	public ServiceTerms setDecisionStarted() {
		this.state = ServiceTerms.DECISION_STARTED;
		return this;
	}
	
	public ServiceTerms setDecisionFinished() {
		this.state = ServiceTerms.DECISION_FINISHED;
		return this;
	}
	
/* 6. DEFINE GENERAL ATTRIBUTES */
	@Property()
	private String serviceID;
	@Property()
	private String serviceOwner;
	@Property()
	private String requestedResources;
	@Property()
	private String requestTime;
	@Property()
	private String availableServers;
	@Property
	private String replyTime;
	@Property()
	private String serviceProviders;
	@Property
	private String decisionTime;
	
/* 7. DEFINE METHODS (GETTERS & SETTERS) */
	/* SERVICE ID */
	public String getServiceID() {
		return serviceID;	
	}
	
	public ServiceTerms setServiceID(String serviceID) {
		this.serviceID = serviceID;
		return this;
	}
	
	/* SERVICE OWNER */
	public String getServiceOwner() {
		return serviceOwner;
	}
	
	public ServiceTerms setServiceOwner(String serviceOwner) {
		this.serviceOwner = serviceOwner;
		return this;
	}
	
	/* REQUESTED RESOURCES */
	public String getRequestedResources() {
		return requestedResources;
	}
	
	public ServiceTerms setRequestedResources(String requestedResources) {
		this.requestedResources = requestedResources;
		return this;
	}
	
	/* REQUEST TIME */
	public String getRequestTime() {
		return requestTime;
	}
	
	public ServiceTerms setRequestTime(String requestTime) {
		this.requestTime = requestTime;
		return this;
	}
	
	/* AVAILABLE SERVERS */
	public String getAvailableServers() {
		return availableServers;
	}
	public ServiceTerms setAvailableServers(String availableServers) {
		this.availableServers = availableServers;
		return this;
	}
	
	/* REPLY TIME */
	public String getReplyTime() {
		return replyTime;
	}
	
	public ServiceTerms setReplyTime(String replyTime) {
		this.replyTime = replyTime;
		return this;
	}
	
	/* SERVICE PROVIDERS */
	public String getServiceProviders() {
		return serviceProviders;
	}
	
	public ServiceTerms setServiceProviders(String serviceProviders) {
		this.serviceProviders = serviceProviders;
		return this;
	}
	
	/* DECISION TIME */
	public String getDecisionTime() {
		return decisionTime;
	}
	
	public ServiceTerms setDecisionTime(String decisionTime) {
		this.decisionTime = decisionTime;
		return this;
	}
	
	/* STATE KEY */
	public ServiceTerms() {
		super();
	}
	
	public ServiceTerms setKey() {
		this.key = State.makeKey(new String[] {this.serviceID});
		return this;
	}
	
/* 8. DEFINE CONSTRUCTOR OF SERVICE TERMS CLASS */
	@Override()
	public String toString() {
		return "Service::" + this.key + " " + this.getServiceID() + " " + this.getServiceOwner() + " " + this.getRequestedResources() + " " + this.getRequestTime() + " " + this.getAvailableServers() + " " + this.getReplyTime() + " " + this.getServiceProviders() + " " + this.getDecisionTime();	
	}
	
	/**
	 * Deserialize state data to a ServiceTerms object
	 * 
	 * @param {Buffer} data to form back into the object
	 */
	public static ServiceTerms deserialize(byte[] data) {
		JSONObject json = new JSONObject(new String(data,UTF_8));
		String serviceID = json.getString("serviceID");
		String serviceOwner = json.getString("serviceOwner");
		String requestedResources = json.getString("requestedResources");
		String requestTime = json.getString("requestTime");
		String availableServers = json.getString("availableServers");
		String replyTime = json.getString("replyTime");
		String serviceProviders = json.getString("serviceProviders");
		String decisionTime = json.getString("decisionTime");
		return createInstance(serviceID, serviceOwner, requestedResources, requestTime, availableServers, replyTime, serviceProviders, decisionTime);			
	}
	
	/**
	 * CreateInstance is the constructor of the ServiceTerms object
	 */
	public static ServiceTerms createInstance(String serviceID, String serviceOwner, String requestedResources, String requestTime, String availableServers, String replyTime, String serviceProviders, String decisionTime) {
		return new ServiceTerms().setServiceID(serviceID).setServiceOwner(serviceOwner).setRequestedResources(requestedResources).setRequestTime(requestTime).setAvailableServers(availableServers).setReplyTime(replyTime).setServiceProviders(serviceProviders).setDecisionTime(decisionTime);
	}
}
