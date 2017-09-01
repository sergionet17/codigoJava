package co.gov.movilidadbogota.sipa.bpm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowProcess {
        
	private String id;
	private String definitionId;
	private String businessKey;
	private String caseInstanceId;
	private boolean ended;
	private boolean suspended;
	private String tenantId;
	
	public WorkflowProcess(String id, String definitionId, String businessKey, String caseInstanceId, boolean ended,
			boolean suspended, String tenantId) {
		super();
		this.id = id;
		this.definitionId = definitionId;
		this.businessKey = businessKey;
		this.caseInstanceId = caseInstanceId;
		this.ended = ended;
		this.suspended = suspended;
		this.tenantId = tenantId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getCaseInstanceId() {
		return caseInstanceId;
	}
	public void setCaseInstanceId(String caseInstanceId) {
		this.caseInstanceId = caseInstanceId;
	}
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	public boolean isSuspended() {
		return suspended;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}
