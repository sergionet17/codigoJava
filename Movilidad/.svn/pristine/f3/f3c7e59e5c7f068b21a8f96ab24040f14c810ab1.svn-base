package co.gov.movilidadbogota.sipa.bpm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.gov.movilidadbogota.sipa.bpm.model.HistoricTaskInstance;
import co.gov.movilidadbogota.sipa.bpm.model.ProcessVariableFormatter;
import co.gov.movilidadbogota.sipa.bpm.model.WorkflowTask;
import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONObject;

@Component("tasksService")
@Scope("singleton")
public class TasksService {
	//// DATEFORMAT yyyy-MM-dd'T'HH:mm:ss 2017-08-03T13:00:00
	private HttpHeaders getHeaders() {
		String plainCredentials = "demo:demo";
		String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Credentials);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	public List<WorkflowTask> getActiveTasks(String user, String processDef) {
		// task?processInstanceId=3c134833-77d7-11e7-b295-f23c91e9e76e
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};
		String url = String.format("%s/task?processInstanceId=%s&assignee=%s", ConfParameters.getParm(Constants.CONF_BPM_URL),
				processDef,
                                user);
		

		HttpEntity<String> entity = new HttpEntity<String>("", getHeaders());
		
		String jsonTasks = new RestTemplate().getForObject(url, String.class, entity);
		List<WorkflowTask> tareas = new Gson().fromJson(jsonTasks, tareasType.getType());
                
                if(tareas == null || tareas.size() < 1){
                    String urlIdentity = String.format("%s/identity/groups?userId=%s", ConfParameters.getParm(Constants.CONF_BPM_IDENT),
                            user
                            );
                    
                    HttpEntity<String> entity1 = new HttpEntity<String>("", getHeaders());
                    
                    String jsonTasks1 = new RestTemplate().getForObject(urlIdentity, String.class, entity1);
                    JSONObject ident = new JSONObject(jsonTasks1);
                    JSONArray array = ident.getJSONArray("groups");
                    JSONObject grupo = array.getJSONObject(0);
                    tareas = getActiveTasksAssignedToRole(grupo.getString("id"),processDef);
                }
                
		return tareas;
	}

	public Object getActiveTaskAssignee(WorkflowTask task) {
		// TODO Auto-generated method stub
		return null;
	}

	public void claimTask(WorkflowTask task, String user) {
		// TODO Auto-generated method stub
		String url = String.format("%s/task/%s/assignee", ConfParameters.getParm(Constants.CONF_BPM_URL), task.getId());
		// url para claim de tarea
		
		String requestJson = new StringBuilder("{\"userId\":\"").append(user).append("\"}").toString();
		
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, getHeaders());
		new RestTemplate().postForObject(url, entity, String.class);
	}

	public void completeTask(WorkflowTask task, HashMap<String, Object> vars, boolean local) {
		StringBuilder sb = new StringBuilder();
		String url = String.format("%s/task/%s/complete", ConfParameters.getParm(Constants.CONF_BPM_URL), task.getId());
		// TODO: Llenar con variables de instancia
		if (vars != null) {
			List variablesFormateadas = new ArrayList();
                        int currVal = 0;
                        int max = variablesFormateadas.size();
			for (Map.Entry<String, Object> entry : vars.entrySet()) {
				String key = entry.getKey();
				ProcessVariableFormatter value = (ProcessVariableFormatter) entry.getValue();
				variablesFormateadas.add(value.generateJson(key, false));
				sb.append(value.generateJson(key, false));
                                currVal++;
                                if(currVal < max){
                                    sb.append(", ");
                                }
                        }

		}
		String requestJson = new ProcessVariableFormatter().generateWrapperJson(sb.toString());
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, getHeaders());
		System.out.println("url| "+url + " |");
                System.out.println("Json| "+requestJson + " |");
		new RestTemplate().postForObject(url, entity, String.class);
	}

	public List<WorkflowTask> getActiveTasksDuedateToday(String user, String processDef) {
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};
		String url = String.format("%s/task", ConfParameters.getParm(Constants.CONF_BPM_URL));
		// TODO: Llenar con variables de instancia
		String requestJson;
                requestJson = "?dueBefore=" + new SimpleDateFormat("yyyy-MM-dd'T'").format(new Date())+"23:59:59";
                requestJson += "&dueAfter=" + new SimpleDateFormat("yyyy-MM-dd'T'").format(new Date())+"00:00:01";
                
		url += requestJson;
                String jsonTareasxInstancia = new RestTemplate().getForObject(url, String.class);

		List<WorkflowTask> tareas = new Gson().fromJson(jsonTareasxInstancia, tareasType.getType());

		return tareas;
	}

	public List<WorkflowTask> getActiveTasksDelayed(String user, String processDef) {
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};
		String url = String.format("%s/task", ConfParameters.getParm(Constants.CONF_BPM_URL));
		// TODO: Llenar con variables de instancia
		String requestJson;
                requestJson = "?dueBefore=" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
		url += requestJson;
                String jsonTareasxInstancia = new RestTemplate().getForObject(url, String.class);

		List<WorkflowTask> tareas = new Gson().fromJson(jsonTareasxInstancia, tareasType.getType());

		return tareas;
	}

	public List<WorkflowTask> getActiveTasksByPriority(String user, String processDef, int priority) {
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};
		String url = String.format("%s/task", ConfParameters.getParm(Constants.CONF_BPM_URL));
		// TODO: Llenar con variables de instancia
		String requestJson = new StringBuilder("{\"priority\":\"").append(priority).append("\"}").toString();
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, getHeaders());
		new RestTemplate().postForObject(url, entity, String.class);

		String jsonTareasxInstancia = new RestTemplate().getForObject(url, String.class);

		List<WorkflowTask> tareas = new Gson().fromJson(jsonTareasxInstancia, tareasType.getType());

		return tareas;
	}

	public List<WorkflowTask> getActiveTasksDuedateNextSevenDays(String user, String processDef) {
		Date date = new Date();
		Date date7Days = new Date(); // TODO: sumar 7 días
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};
		String url = String.format("%s/task", ConfParameters.getParm(Constants.CONF_BPM_URL));
		// TODO: Llenar con variables de instancia
		String requestJson = new StringBuilder("{\"dueBefore\":\"").append(date.toString())
				.append("\", \"dueAfter\":\"").append(date7Days.toString()).append("\"}").toString();
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, getHeaders());
		new RestTemplate().postForObject(url, entity, String.class);

		String jsonTareasxInstancia = new RestTemplate().getForObject(url, String.class);

		List<WorkflowTask> tareas = new Gson().fromJson(jsonTareasxInstancia, tareasType.getType());

		return tareas;
	}

	public List<WorkflowTask> getActiveTasksAssignedToUser(String user, String processDef) {
		return getActiveTasks(user, processDef);
	}

	public List<WorkflowTask> getActiveTasksAssignedToRole(String role, String processDef) {
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};
		String url = String.format("%s/task?processInstanceId=%s&candidateGroup=%s",
				ConfParameters.getParm(Constants.CONF_BPM_URL), processDef, role);

		HttpEntity<String> entity = new HttpEntity<String>("", getHeaders());
		
		String jsonTasks = new RestTemplate().getForObject(url, String.class, entity);
		List<WorkflowTask> tareas = new Gson().fromJson(jsonTasks, tareasType.getType());
		return tareas;
	}
        
        public List<WorkflowTask> getActiveTasksAssignedToRole(String roles) {
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};
		String url = String.format("%s/task?candidateGroup=%s",
				ConfParameters.getParm(Constants.CONF_BPM_URL), roles);

		HttpEntity<String> entity = new HttpEntity<String>("", getHeaders());
		
               
		String jsonTasks = new RestTemplate().getForObject(url, String.class, entity);
		List<WorkflowTask> tareas = new Gson().fromJson(jsonTasks, tareasType.getType());
		return tareas;
	}

	public List<WorkflowTask> getActiveTaskAssignedToInstance(String idInstance) {
		return getActiveTasks("", idInstance);
	}

	public List<HistoricTaskInstance> getCompletedTasks(String user, String processDef) {
		// TODO Auto-generated method stub
		return null;
	}
        
        public void updateTask(WorkflowTask task, HashMap<String, Object> variables){
            String url = String.format("%s/task/%s/", ConfParameters.getParm(Constants.CONF_BPM_URL), task.getId());
            // url para asignar variables a la tarea
            System.out.println(url);
            String requestJson;
            requestJson = ProcessVariableFormatter.variablesCorchete(variables);
		
            HttpEntity<String> entity = new HttpEntity<String>(requestJson, getHeaders());
            new RestTemplate().put(url, entity);
            
        }

}
