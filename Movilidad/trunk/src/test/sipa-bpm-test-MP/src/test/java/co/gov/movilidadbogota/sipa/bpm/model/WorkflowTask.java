package co.gov.movilidadbogota.sipa.bpm.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"assignee",
"created",
"due",
"followUp",
"delegationState",
"description",
"executionId",
"owner",
"parentTaskId",
"priority",
"processDefinitionId",
"processInstanceId",
"taskDefinitionKey",
"caseExecutionId",
"caseInstanceId",
"caseDefinitionId",
"suspended",
"formKey",
"tenantId"
})
public class WorkflowTask {

@JsonProperty("id")
private String id;
@JsonProperty("name")
private String name;
@JsonProperty("assignee")
private Object assignee;
@JsonProperty("created")
private String created;
@JsonProperty("due")
private String due;
@JsonProperty("followUp")
private String followUp;
@JsonProperty("delegationState")
private String delegationState;
@JsonProperty("description")
private String description;
@JsonProperty("executionId")
private String executionId;
@JsonProperty("owner")
private String owner;
@JsonProperty("parentTaskId")
private String parentTaskId;
@JsonProperty("priority")
private Integer priority;
@JsonProperty("processDefinitionId")
private String processDefinitionId;
@JsonProperty("processInstanceId")
private String processInstanceId;
@JsonProperty("taskDefinitionKey")
private String taskDefinitionKey;
@JsonProperty("caseExecutionId")
private String caseExecutionId;
@JsonProperty("caseInstanceId")
private String caseInstanceId;
@JsonProperty("caseDefinitionId")
private String caseDefinitionId;
@JsonProperty("suspended")
private Boolean suspended;
@JsonProperty("formKey")
private String formKey;
@JsonProperty("tenantId")
private String tenantId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

public WorkflowTask withId(String id) {
this.id = id;
return this;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

public WorkflowTask withName(String name) {
this.name = name;
return this;
}

@JsonProperty("assignee")
public Object getAssignee() {
return assignee;
}

@JsonProperty("assignee")
public void setAssignee(Object assignee) {
this.assignee = assignee;
}

public WorkflowTask withAssignee(Object assignee) {
this.assignee = assignee;
return this;
}

@JsonProperty("created")
public String getCreated() {
return created;
}

@JsonProperty("created")
public void setCreated(String created) {
this.created = created;
}

public WorkflowTask withCreated(String created) {
this.created = created;
return this;
}

@JsonProperty("due")
public String getDue() {
return due;
}

@JsonProperty("due")
public void setDue(String due) {
this.due = due;
}

public WorkflowTask withDue(String due) {
this.due = due;
return this;
}

@JsonProperty("followUp")
public String getFollowUp() {
return followUp;
}

@JsonProperty("followUp")
public void setFollowUp(String followUp) {
this.followUp = followUp;
}

public WorkflowTask withFollowUp(String followUp) {
this.followUp = followUp;
return this;
}

@JsonProperty("delegationState")
public String getDelegationState() {
return delegationState;
}

@JsonProperty("delegationState")
public void setDelegationState(String delegationState) {
this.delegationState = delegationState;
}

public WorkflowTask withDelegationState(String delegationState) {
this.delegationState = delegationState;
return this;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

public WorkflowTask withDescription(String description) {
this.description = description;
return this;
}

@JsonProperty("executionId")
public String getExecutionId() {
return executionId;
}

@JsonProperty("executionId")
public void setExecutionId(String executionId) {
this.executionId = executionId;
}

public WorkflowTask withExecutionId(String executionId) {
this.executionId = executionId;
return this;
}

@JsonProperty("owner")
public String getOwner() {
return owner;
}

@JsonProperty("owner")
public void setOwner(String owner) {
this.owner = owner;
}

public WorkflowTask withOwner(String owner) {
this.owner = owner;
return this;
}

@JsonProperty("parentTaskId")
public String getParentTaskId() {
return parentTaskId;
}

@JsonProperty("parentTaskId")
public void setParentTaskId(String parentTaskId) {
this.parentTaskId = parentTaskId;
}

public WorkflowTask withParentTaskId(String parentTaskId) {
this.parentTaskId = parentTaskId;
return this;
}

@JsonProperty("priority")
public Integer getPriority() {
return priority;
}

@JsonProperty("priority")
public void setPriority(Integer priority) {
this.priority = priority;
}

public WorkflowTask withPriority(Integer priority) {
this.priority = priority;
return this;
}

@JsonProperty("processDefinitionId")
public String getProcessDefinitionId() {
return processDefinitionId;
}

@JsonProperty("processDefinitionId")
public void setProcessDefinitionId(String processDefinitionId) {
this.processDefinitionId = processDefinitionId;
}

public WorkflowTask withProcessDefinitionId(String processDefinitionId) {
this.processDefinitionId = processDefinitionId;
return this;
}

@JsonProperty("processInstanceId")
public String getProcessInstanceId() {
return processInstanceId;
}

@JsonProperty("processInstanceId")
public void setProcessInstanceId(String processInstanceId) {
this.processInstanceId = processInstanceId;
}

public WorkflowTask withProcessInstanceId(String processInstanceId) {
this.processInstanceId = processInstanceId;
return this;
}

@JsonProperty("taskDefinitionKey")
public String getTaskDefinitionKey() {
return taskDefinitionKey;
}

@JsonProperty("taskDefinitionKey")
public void setTaskDefinitionKey(String taskDefinitionKey) {
this.taskDefinitionKey = taskDefinitionKey;
}

public WorkflowTask withTaskDefinitionKey(String taskDefinitionKey) {
this.taskDefinitionKey = taskDefinitionKey;
return this;
}

@JsonProperty("caseExecutionId")
public String getCaseExecutionId() {
return caseExecutionId;
}

@JsonProperty("caseExecutionId")
public void setCaseExecutionId(String caseExecutionId) {
this.caseExecutionId = caseExecutionId;
}

public WorkflowTask withCaseExecutionId(String caseExecutionId) {
this.caseExecutionId = caseExecutionId;
return this;
}

@JsonProperty("caseInstanceId")
public String getCaseInstanceId() {
return caseInstanceId;
}

@JsonProperty("caseInstanceId")
public void setCaseInstanceId(String caseInstanceId) {
this.caseInstanceId = caseInstanceId;
}

public WorkflowTask withCaseInstanceId(String caseInstanceId) {
this.caseInstanceId = caseInstanceId;
return this;
}

@JsonProperty("caseDefinitionId")
public String getCaseDefinitionId() {
return caseDefinitionId;
}

@JsonProperty("caseDefinitionId")
public void setCaseDefinitionId(String caseDefinitionId) {
this.caseDefinitionId = caseDefinitionId;
}

public WorkflowTask withCaseDefinitionId(String caseDefinitionId) {
this.caseDefinitionId = caseDefinitionId;
return this;
}

@JsonProperty("suspended")
public Boolean getSuspended() {
return suspended;
}

@JsonProperty("suspended")
public void setSuspended(Boolean suspended) {
this.suspended = suspended;
}

public WorkflowTask withSuspended(Boolean suspended) {
this.suspended = suspended;
return this;
}

@JsonProperty("formKey")
public String getFormKey() {
return formKey;
}

@JsonProperty("formKey")
public void setFormKey(String formKey) {
this.formKey = formKey;
}

public WorkflowTask withFormKey(String formKey) {
this.formKey = formKey;
return this;
}

@JsonProperty("tenantId")
public String getTenantId() {
return tenantId;
}

@JsonProperty("tenantId")
public void setTenantId(String tenantId) {
this.tenantId = tenantId;
}

public WorkflowTask withTenantId(String tenantId) {
this.tenantId = tenantId;
return this;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public WorkflowTask withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}