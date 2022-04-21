/* 1. PACKAGE CODE */
package TaskSharingChaincode;

/* 2. IMPORT DEPENDENCIES */
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.shim.ChaincodeStub;
import TaskSharingChaincode.ledgerapi.State;
import java.util.logging.Logger;

//3. DEFINE CONTRACT
@Contract(
		name="TaskSharingContract",
		info=@Info(
				title="Task Sharing Contract",
				description="Chaincode to be deployed on every private channel created in the network",
				version="0.0.1-SNAPSHOT"))

@Default
public class TaskSharingContract implements ContractInterface {
	private final static Logger LOG = Logger.getLogger(TaskSharingContract.class.getName());
	
	@Override
	public Context createContext(ChaincodeStub stub) {
		return new TaskContext(stub);
	}
	
	public TaskSharingContract() {
	}
	
	/**
	 * Transaction: instantiate
	 * Details: Instantiate the contract to perform any initial setup on the ledger (not used in this version).
	 * 
	 * @param ctx transaction context 
	 */
	@Transaction
	public void instantiate(TaskContext ctx) {
		LOG.info("No initial setup required for this use case");
	}
	
	/**
	 * Transaction: uploadTask 
	 * Details: The task owner upload a pending task on the private channel ledger.
	 * Prototype: submitTransaction(upoadTask,"taskID","uploadTime","taskCode");
	 * 
	 * @param ctx transaction context
	 * @param taskID
	 * @param uploadTime
	 * @param taskCode
	 */
	@Transaction()
	public Task uploadTask(TaskContext ctx, String taskID, String uploadTime, String taskCode) {
		System.out.println(ctx);
		Task task = Task.createInstance(taskID, uploadTime, "", "", "", taskCode, "");
		task.setUploaded();
		System.out.println(task);
		ctx.taskList.addTask(task);
		return task;
	}
	
	/**
	 * Transaction: beginExecution
	 * Details: The service provider start the processing of the task by querying it from the ledger
	 * Prototype: submitTransaction(beginExecution, "taskID", "execStartTime");
	 * 
	 * @param ctx transaction context
	 * @param taskID
	 * @param execStartTime
	 */
	@Transaction()
	public Task beginExecution(TaskContext ctx, String taskID, String execStartTime) {
		System.out.println(ctx);
		String taskKey = State.makeKey(new String[] {taskID});
		Task task = ctx.taskList.getTask(taskKey);
		if (task.isUploaded()) {
			task.setExecStarted();
		}
		task.setExecStartTime(execStartTime);
		System.out.println(task);
		ctx.taskList.updateTask(task);
		return task;
	}
	
	/**
	 * Transaction: finishExecution 
	 * Details: The service provider finish the processing of the task and add the results to the ledger
	 * Prototype: submitTransaction(finishExecution, "taskID", "execFinishTime", "taskResults")
	 * 
	 * @param ctx
	 * 
	 */
	@Transaction()
	public Task finishExecution(TaskContext ctx, String taskID, String execFinishTime, String taskResults) {
		System.out.println(ctx);
		String taskKey = State.makeKey(new String[] {taskID});
		Task task = ctx.taskList.getTask(taskKey);
		if (task.isExecStarted()) {
			task.setExecFinished();
		}
		task.setExecFinishTime(execFinishTime);
		task.setTaskResults(taskResults);
		System.out.println(task);
		ctx.taskList.updateTask(task);
		return task;
	}
	
	/**
	 * Transaction: downloadTask
	 * Details: 
	 * Prototype:
	 * 
	 *  @param ctx transaction context
	 */
	@Transaction()
	public Task downloadTask(TaskContext ctx, String taskID, String downloadTime) {
		System.out.println(ctx);
		String taskKey = State.makeKey(new String[] {taskID});
		Task task = ctx.taskList.getTask(taskKey);
		if (task.isExecFinished()) {
			task.setDownloaded();
		}
		task.setDownloadTime(downloadTime);
		System.out.println(task);
		ctx.taskList.updateTask(task);
		return task;
	}
}