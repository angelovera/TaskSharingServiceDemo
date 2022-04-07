package TaskSharingChaincode;

import TaskSharingChaincode.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

public class TaskList {
	
	private StateList stateList;
	
	public TaskList(Context ctx) {
		this.stateList = StateList.getStateList(ctx, TaskList.class.getSimpleName(), Task::deserialize);
	}
	
	public TaskList addTask(Task task) {
		stateList.addState(task);
		return this;
	}
	
	public Task getTask(String taskKey) {
		return (Task) this.stateList.getState(taskKey);
	}
	
	public TaskList updateTask(Task task) {
		this.stateList.updateState(task);
		return this;
	}
}
