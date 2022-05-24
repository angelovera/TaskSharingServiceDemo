package TaskSharingChaincode_v0;

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
        name = "TaskSharingContract",
        info = @Info(
                title = "TaskSharingContract contract",
                description = "TaskSharingContract chaincode version 0",
                version = "0.0.1-SNAPSHOT"))

@Default
public final class TaskSharingContract implements ContractInterface {

	private final Genson genson = new Genson();
	private enum TaskErrors {
	        TASK_NOT_FOUND,
	        TASK_ALREADY_EXISTS
	    }

	/**
	 * Transaction: uploadTask 
	 * Details: The task owner upload a pending task on the private channel ledger
	 * Prototype: submitTransaction(upoadTask,"taskID","uploadTime","taskCode");
	 * 
	 * @param ctx transaction context
	 * @param taskID
	 * @param uploadTime
	 * @param taskCode
	 * 
	 * return Task
	 */
    @Transaction()
    public Task uploadTask(final Context ctx, final String taskID, final String uploadTime, final String taskCode) {  
    	ChaincodeStub stub = ctx.getStub();
        String taskState = stub.getStringState(taskID);
        if (!taskState.isEmpty()) {
            String errorMessage = String.format("Task %s already exists", taskID);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, TaskErrors.TASK_ALREADY_EXISTS.toString());
        }
        Task task = new Task(taskID, uploadTime, taskCode, null, null, null, null);
        taskState = genson.serialize(task);
        stub.putStringState(taskID, taskState); 
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
	 * 
	 * return Task
	 */
   	 @Transaction()
   	 public Task beginExecution(final Context ctx, final String taskID, final String execStartTime) {
   		 ChaincodeStub stub = ctx.getStub();
   		 String taskState = stub.getStringState(taskID);
   		 if (taskState.isEmpty()) {
   			 String errorMessage = String.format("Task %s does not exist", taskID);
   	         System.out.println(errorMessage);
   	         throw new ChaincodeException(errorMessage, TaskErrors.TASK_NOT_FOUND.toString());
   	     }
   	     Task task = genson.deserialize(taskState, Task.class);
   	     Task newTask = new Task(task.getTaskID(), task.getUploadTime(), task.getTaskCode(), task.getExecStartTime(), null, null, null);
   	     String newTaskState = genson.serialize(newTask);
   	     stub.putStringState(taskID, newTaskState);
   	     return newTask;
   	 }
   	 
   	/**
 	 * Transaction: finishExecution 
 	 * Details: The service provider finish the processing of the task and add the results to the ledger
 	 * Prototype: submitTransaction(finishExecution, "taskID", "execFinishTime", "taskResults")
 	 * 
 	 * @param ctx
 	 * @param taskID
 	 * @param execFinishTime
 	 * @param taskResults
 	 * 
 	 * return Task
 	 */
   	@Transaction()
  	 public Task finishExecution(final Context ctx, final String taskID, final String execFinishTime, final String taskResults) {
  		 ChaincodeStub stub = ctx.getStub();
  		 String taskState = stub.getStringState(taskID);
  		 if (taskState.isEmpty()) {
  			 String errorMessage = String.format("Task %s does not exist", taskID);
  	         System.out.println(errorMessage);
  	         throw new ChaincodeException(errorMessage, TaskErrors.TASK_NOT_FOUND.toString());
  	     }
  		 Task task = genson.deserialize(taskState, Task.class);
  	     Task newTask = new Task(task.getTaskID(), task.getUploadTime(), task.getTaskCode(), task.getExecStartTime(), execFinishTime, taskResults, null);
  	     String newTaskState = genson.serialize(newTask);
  	     stub.putStringState(taskID, newTaskState);
  	     return newTask;
  	 }
   	 
   	/**
	 * Transaction: downloadTask
	 * Details: The task owner download the results of the task from the private channel ledger.
	 * Prototype: submitTransaction(downloadTask, "taskID", "downloadTime")
	 * 
	 *  @param ctx transaction context
	 *  @param downloadTime
	 *  
	 *  return Task
	 */
   	 @Transaction()
   	 public Task downloadTask(final Context ctx, final String taskID, final String downloadTime) {
   		 ChaincodeStub stub = ctx.getStub();
   		 String taskState = stub.getStringState(taskID);
   		 if (taskState.isEmpty()) {
   			 String errorMessage = String.format("Task %s does not exist", taskID);
   	         System.out.println(errorMessage);
   	         throw new ChaincodeException(errorMessage, TaskErrors.TASK_NOT_FOUND.toString());
   	     }
   	     Task task = genson.deserialize(taskState, Task.class);
   	     Task newTask = new Task(task.getTaskID(), task.getUploadTime(), task.getTaskCode(), task.getExecStartTime(), task.getExecFinishTime(), task.getTaskResults(), downloadTime);
   	     String newTaskState = genson.serialize(newTask);
   	     stub.putStringState(taskID, newTaskState);
   	     return newTask;
   	 }
   	 
     /**
      * Transaction: queryTaskByID 
      * Details: The monitoring tool queries a service by ID.
      * Prototype: submitTransaction(queryServiceByID,"serviceID");
      * 
      * @param ctx transaction context
      * @param serviceID
      * 
      * return service
      */
      @Transaction()
  	public Task queryTaskByID(final Context ctx, final String taskID) {
      	ChaincodeStub stub = ctx.getStub();
  	    String taskState = stub.getStringState(taskID);
  	    if (taskState.isEmpty()) {
  	    	String errorMessage = String.format("Task %s does not exist", taskID);
  	        System.out.println(errorMessage);
  	        throw new ChaincodeException(errorMessage, TaskErrors.TASK_NOT_FOUND.toString());
  	    }	        
  	    Task task = genson.deserialize(taskState, Task.class);
  	    return task;
      }  	 
}
