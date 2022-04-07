package TaskSharingChaincode;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;

class TaskContext extends Context{
	public TaskContext(ChaincodeStub stub) {
		super(stub);
		this.taskList = new TaskList(this);
	}
	public TaskList taskList;
}