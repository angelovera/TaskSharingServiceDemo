/* Package chaincode */
package ServiceTermsChaincode_v0;

/* Import libraries and dependencies */
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;

/* Define class */
@DataType()
public final class ServiceTerms {
	
	/* Define attributes*/
	@Property()
	private final String serviceID;
	@Property()
	private final String serviceOwner;
	@Property()
	private final String requestedResources;
	@Property()
	private final String requestTime;
	@Property()
	private final String availableServers;
	@Property()
	private final String replyTime;
	@Property()
	private final String decisionStartTime;
	@Property()
	private final String serviceProviders;
	@Property()
	private final String decisionFinishTime;
	
	/* Getters */
	public String getServiceID() {
		return serviceID;
	}

	public String getServiceOwner() {
		return serviceOwner;
	}

	public String getRequestedResources() {
		return requestedResources;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public String getAvailableServers() {
		return availableServers;
	}

	public String getReplyTime() {
		return replyTime;
	}
	
	public String getDecisionStartTime() {
		return decisionStartTime;
	}

	public String getServiceProviders() {
		return serviceProviders;
	}

	public String getDecisionFinishTime() {
		return decisionFinishTime;
	}
	
	/* Constructor */
	public ServiceTerms(@JsonProperty("serviceID") final String serviceID, @JsonProperty("serviceOwner") final String serviceOwner,
			@JsonProperty("requestedResources") final String requestedResources, @JsonProperty("requestTime") final String requestTime,
			@JsonProperty("availableServers") final String availableServers, @JsonProperty("replyTime") final String replyTime, 
			@JsonProperty("decisionStartTime") final String decisionStartTime, @JsonProperty("serviceProviders") final String serviceProviders, @JsonProperty("decisionFinishTime") final String decisionFinishTime) {
		this.serviceID = serviceID;
		this.serviceOwner = serviceOwner;
		this.requestedResources = requestedResources;
		this.requestTime = requestTime;
		this.availableServers = availableServers;
		this.replyTime = replyTime;
		this.decisionStartTime = decisionStartTime;
		this.serviceProviders = serviceProviders;
		this.decisionFinishTime = decisionFinishTime;
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

		return Objects.deepEquals(new String[] { getServiceID(), getServiceOwner(), getRequestedResources(), getRequestTime(), getAvailableServers(), getReplyTime(), getDecisionStartTime(), getServiceProviders(), getDecisionFinishTime()},
				new String[] { other.getServiceID(), other.getServiceOwner(), other.getRequestedResources(), other.getRequestTime(), other.getAvailableServers(), other.getReplyTime(), other.getDecisionStartTime(), other.getServiceProviders(), other.getDecisionFinishTime()});
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getServiceID(), getServiceOwner(), getRequestedResources(), getRequestTime(), getAvailableServers(), getReplyTime(), getDecisionStartTime(), getServiceProviders(), getDecisionFinishTime());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [ServiceID=" + serviceID + ", ServiceOwner=" + serviceOwner
				+ ", RequestedResources=" + requestedResources + ", RequestTime=" + requestTime + ", AvailableServers=" + availableServers + ", ReplyTime=" + replyTime + ", DecisionStartTime=" + decisionStartTime + ", ServiceProviders" + serviceProviders + ", DecisionFinishTime" + decisionFinishTime + "]";
	}
}