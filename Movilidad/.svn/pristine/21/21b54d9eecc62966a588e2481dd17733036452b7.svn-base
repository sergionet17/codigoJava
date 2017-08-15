package co.gov.movilidadbogota.sipa.bpm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.gov.movilidadbogota.sipa.bpm.model.ProcessVariableFormatter;
import co.gov.movilidadbogota.sipa.bpm.model.WorkflowProcess;
import co.gov.movilidadbogota.sipa.bpm.model.WorkflowProcessDefinition;
import co.gov.movilidadbogota.sipa.bpm.model.WorkflowTask;
import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

@Component("processesService")
@Scope("singleton")
public class ProcessesService {

	private HttpHeaders getHeaders() {
		String plainCredentials = "demo:demo";
		String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Credentials);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	/**
	 * Start a new process and return the instance id.
	 * 
	 * @param user
	 * @param processDef
	 * @param variables
	 * @return
	 */
	public String startProcess(String user, String processDef, HashMap<String, Object> variables) {
		// url para instanciar
		int i;
		TypeToken<List<WorkflowTask>> tareasType = new TypeToken<List<WorkflowTask>>() {
		};

		// Discutible el uso del hashmap para recorrido, pero...
		// se recorre, se genera el json de las variables para formato final
		StringBuilder sb = new StringBuilder();
		if (variables != null && variables.size() > 0) {
			List variablesFormateadas = new ArrayList();
                        int currVal = 0;
                        int max = variables.entrySet().size();
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				String key = entry.getKey();
				ProcessVariableFormatter value = (ProcessVariableFormatter) entry.getValue();
				variablesFormateadas.add(value.generateJson(key, false));
				sb.append(value.generateJson(key, false));
                                currVal++;
                                if(currVal < max){
                                    sb.append(", ");
                                }
                        }
System.out.println(sb.toString());
		}
		// instancia un proceso
		String urlInstancia = String.format("%s/process-definition/key/%s/start",
				ConfParameters.getParm(Constants.CONF_BPM_URL), processDef);
System.out.println(urlInstancia);
		String requestJson = "{}";
		System.out.print("json para enviar:");
		System.out.println(requestJson);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, getHeaders());
                System.out.println(urlInstancia);
		String respuestaInstancia = new RestTemplate().postForObject(urlInstancia, entity, String.class);

		JSONObject jsonObj = new JSONObject(respuestaInstancia);

		System.out.println("url de instancia:" + urlInstancia);
		System.out.println("id instancia proceso:" + jsonObj.getString("id"));

		// trae las tareas de la instancia recien creada para asignarles el
		// usuario
		String urlGetTareas = String.format("%s/task?processInstanceId=%s",
				ConfParameters.getParm(Constants.CONF_BPM_URL), jsonObj.getString("id"));

		String jsonTareasxInstancia = new RestTemplate().getForObject(urlGetTareas, String.class);

		List<WorkflowTask> tareas = new Gson().fromJson(jsonTareasxInstancia, tareasType.getType());

		for (WorkflowTask tarea : tareas) {
			System.out.println("id de tarea:" + tarea.getId());
			new TasksService().claimTask(tarea, user);
		}

		// retorna solo id instancia creada
		return jsonObj.getString("id");
	}

	public List<WorkflowProcess> getActiveProcessesDuedateToday(String user, String processDef) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WorkflowProcess> getActiveProcessesDelayed(String user, String processDef) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WorkflowProcess> getActiveProcessesByPriority(String user, String processDef, int priority) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WorkflowProcess> getActiveProcessesStartedLastSevenDays(String user, String processDef) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WorkflowProcess> getActiveProcessesWithoutDuedate(String user, String processDef) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WorkflowProcess> getCompletedProcesses(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	public WorkflowProcessDefinition getProcessDefinition(String idInstance) {
		/// process-definition/byProcessId?key=Process_2
		TypeToken<WorkflowProcessDefinition> processDefinition = new TypeToken<WorkflowProcessDefinition>() {
		};
		String url = String.format("%s/process-definition/byProcessId?key=",
				ConfParameters.getParm(Constants.CONF_BPM_URL), idInstance);

		HttpEntity<String> entity = new HttpEntity<String>("", getHeaders());
		// new RestTemplate().getForObject(url, entity);
		String jsonProcessDefinition = new RestTemplate().getForObject(url, String.class, entity);
		return new Gson().fromJson(jsonProcessDefinition, processDefinition.getType());

	}

}
