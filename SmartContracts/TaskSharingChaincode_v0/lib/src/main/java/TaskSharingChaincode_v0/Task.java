/* Package chaincode */
package TaskSharingChaincode_v0;

/* Import libraries and dependencies */
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;

/* Define class */
@DataType()
public final class Task {
	
	/* Define attributes */
	@Property()
	private final String taskID;
	@Property()
	private final String uploadTime;
	@Property()
	private final String taskCode;
	@Property()
	private final String execStartTime;
	@Property()
	private final String execFinishTime;
	@Property()
	private final String downloadTime;
	@Property()
	private final String taskResults;
	
	
	/* Getters */
	public String getTaskID() {
		return taskID;
	}

	public String getUploadTime() {
		return uploadTime;
	}
	
	public String getTaskCode() {
		return taskCode;
	}
	
	public String getExecStartTime() {
		return execStartTime;
	}

	public String getExecFinishTime() {
		return execFinishTime;
	}

	public String getTaskResults() {
		return taskResults;
	}
	
	public String getDownloadTime() {
		return downloadTime;
	}
	
	/* Constructor */
	public Task(@JsonProperty("taskID") final String taskID, @JsonProperty("uploadTime") final String uploadTime, 
			@JsonProperty("taskCode") final String taskCode, @JsonProperty("execStartTime") final String execStartTime, 
			@JsonProperty("execFinishTime") final String execFinishTime, @JsonProperty("taskResults") final String taskResults,
			@JsonProperty("downloadTime") final String downloadTime) {
		
		this.taskID = taskID;
		this.uploadTime = uploadTime;
		this.taskCode = taskCode;
		this.execStartTime = execStartTime;
		this.execFinishTime = execFinishTime;
		this.taskResults = taskResults;
		this.downloadTime = downloadTime;
	}

	/* Serialization methods */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Task other = (Task) obj;

		return Objects.deepEquals(new String[] { getTaskID(), getUploadTime(), getTaskCode(), getExecStartTime(), getExecFinishTime(), getDownloadTime(), getTaskResults()},
				new String[] { other.getTaskID(), other.getUploadTime(), other.getTaskCode(), other.getExecStartTime(), other.getExecFinishTime(), other.getDownloadTime(), other.getTaskResults()});
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getTaskID(), getUploadTime(), getTaskCode(), getExecStartTime(), getExecFinishTime(), getTaskResults(), getDownloadTime());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [TaskID=" + taskID + ", UploadTime=" + uploadTime + ", TaskCode=" + taskCode 
				+ ", ExecStartTime=" + execStartTime + ", ExecFinishTime=" + execFinishTime + ", TaskResults=" + taskResults + ", DownloadTime=" + downloadTime + "]";
	}
}
