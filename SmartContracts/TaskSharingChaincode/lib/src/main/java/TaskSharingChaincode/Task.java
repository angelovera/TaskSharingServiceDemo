/* 1. PACKAGE CODE */
package TaskSharingChaincode;

/* 2. IMPORT DEPENDENCIES */
import static java.nio.charset.StandardCharsets.UTF_8;
import TaskSharingChaincode.ledgerapi.State;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

@DataType()
public final class Task extends State {	
	
/* 3. DEFINE CHAINCODE STATES */
	public final static String UPLOADED = "UPLOADED";
	public final static String EXEC_STARTED = "EXEC_STARTED";
	public final static String EXEC_FINISHED = "EXEC_FINISHED";
	public final static String DOWNLOADED = "DOWNLOADED";

/* 4. DEFINE ATTRIBUTES RELATED TO STATES */
	@Property()
	private String state = "";
	
	public String getTaskState() {
		return state;
	}
	public Task setTaskState(String state) {
		this.state = state;
		return this;
	}
	
	@JSONPropertyIgnore()
	public boolean isUploaded() {
		return this.state.equals(Task.UPLOADED);
	}
	
	@JSONPropertyIgnore()
	public boolean isExecStarted() {
		return this.state.equals(Task.EXEC_STARTED);
	}
	
	@JSONPropertyIgnore()
	public boolean isExecFinished() {
		return this.state.equals(Task.EXEC_FINISHED);
	}
	
	@JSONPropertyIgnore()
	public boolean isDecisionDownloaded() {
		return this.state.equals(Task.DOWNLOADED);
	}
	
	public Task setUploaded() {
		this.state = Task.UPLOADED;
		return this;
	}
	
	public Task setExecStarted() {
		this.state = Task.EXEC_STARTED;
		return this;
	}
	
	public Task setExecFinished() {
		this.state = Task.EXEC_FINISHED;
		return this;
	}
	
	public Task setDownloaded() {
		this.state = Task.DOWNLOADED;
		return this;
	}
			
/* 5. DEFINE GENERAL ATTRIBUTES */
	@Property()
	private String taskID;
	@Property()
	private String uploadTime;
	@Property()
	private String execStartTime;
	@Property()
	private String execFinishTime;
	@Property()
	private String downloadTime;
	@Property()
	private String taskCode;
	@Property()
	private String taskResults;
	
/* 6. DEFINE METHODS */
	
	/* TASK ID */
	public String getTaskID() {
		return taskID;
	}
	
	public Task setTaskID(String taskID) {
		this.taskID = taskID;
		return this;
	}
	
	/* UPLOAD TIME */
	public final String getUploadTime() 
	{
		return uploadTime;
	}
	
	public Task setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
		return this;
	}
	
	/* EXECUTION STARTED TIME */ 
	public String getExecStartTime() {
		return execStartTime;
	}
	
	public Task setExecStartTime(String execStartedTime) {
		this.execStartTime = execStartedTime;
		return this;
	}
	
	/* EXECUTION FINISHED TIME*/
	public String getExecFinishTime() {
		return this.execFinishTime;
	}
	
	public Task setExecFinishTime(String execFinishedTime) {
		this.execFinishTime = execFinishedTime;
		return this;
	}
	
	/* DOWNLOAD TIME */
	public String getDownloadTime() {
		return this.downloadTime;
	}
	
	public Task setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
		return this;
	}
	
	/* TASK CODE */
	public String getTaskCode() {
		return this.taskCode;
	}
	
	public Task setTaskCode(String taskCode) {
		this.taskCode = taskCode;
		return this;
	}
	
	/* TASK RESULTS */
	public String getTaskResults() {
		return this.taskResults;
	}
	
	public Task setTaskResults(String taskResults) {
		this.taskResults = taskResults;
		return this;
	}
	
	/* STATE KEY */
	public Task() {
		super();
	}
	
	public Task setKey() {
		this.key = State.makeKey(new String[] {this.taskID});
		return this;
	}
	
/* 7. DEFINE CONSTRUCTOR OF TASK CLASS */
	
	@Override()
	public String toString() {
		return "Service::" + this.key + " " + this.getTaskID() + " " + this.getUploadTime() + " " + this.getExecStartTime() + " " + this.getExecFinishTime() + " " + this.getDownloadTime() + " " + this.getTaskCode();	
	}

	/**
	 * Deserialize state data to a ServiceTerms object
	 * 
	 * @param {Buffer} data to form back into the object
	 */
	public static Task deserialize(byte[] data) {
		JSONObject json = new JSONObject(new String(data,UTF_8));
		String taskID = json.getString("taskID");
		String uploadTime = json.getString("uploadTime");
		String execStartTime = json.getString("execStartTime");
		String execFinishTime = json.getString("execFinishTime");
		String downloadTime = json.getString("downloadTime");
		String taskCode = json.getString("taskCode");
		String taskResults = json.getString("taskResults");
		return createInstance(taskID, uploadTime, execStartTime, execFinishTime, downloadTime, taskCode, taskResults);			
	}
	
	/**
	 * CreateInstance is the constructor of the ServiceTerms object
	 */
	public static Task createInstance(String taskID, String uploadTime, String execStartTime, String execFinishTime, String downloadTime, String taskCode, String taskResults) {
		return new Task().setTaskID(taskID).setUploadTime(uploadTime).setExecStartTime(execStartTime).setExecFinishTime(execFinishTime).setDownloadTime(downloadTime).setTaskCode(taskCode).setTaskResults(taskResults);
	}
}
