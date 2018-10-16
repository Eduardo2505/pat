package mx.ine.sif.util;
 
import org.springframework.webflow.conversation.impl.SessionBindingConversationManager;
import org.springframework.webflow.execution.repository.impl.DefaultFlowExecutionRepository;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.executor.FlowExecutorImpl;
 
public class WebFlowHelper {
	private FlowExecutor flowExecutor;
 
	public WebFlowHelper(FlowExecutor flowExecutor) {
		this.flowExecutor = flowExecutor;
	}
 
	public FlowExecutor getFlowExecutor() {
		return flowExecutor;
	}
 
	public SessionBindingConversationManager getConversationManager() {
		return ((SessionBindingConversationManager) 
				((DefaultFlowExecutionRepository) 
				((FlowExecutorImpl) flowExecutor).getExecutionRepository()).getConversationManager());
	}
 
	public int getLockTimeoutSeconds() {
		return getConversationManager().getLockTimeoutSeconds();
	}
 
	public void setLockTimeoutSeconds(int lockTimeoutSeconds) {
		getConversationManager().setLockTimeoutSeconds(lockTimeoutSeconds);
	}
}