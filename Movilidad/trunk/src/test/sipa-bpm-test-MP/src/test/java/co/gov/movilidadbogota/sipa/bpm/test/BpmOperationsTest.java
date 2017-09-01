package co.gov.movilidadbogota.sipa.bpm.test;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.gov.movilidadbogota.sipa.bpm.config.ConfBeans;
import co.gov.movilidadbogota.sipa.bpm.model.HistoricTaskInstance;
import co.gov.movilidadbogota.sipa.bpm.model.ProcessVariableFormatter;
import co.gov.movilidadbogota.sipa.bpm.model.WorkflowProcess;
import co.gov.movilidadbogota.sipa.bpm.model.WorkflowTask;
import co.gov.movilidadbogota.sipa.bpm.service.ProcessesService;
import co.gov.movilidadbogota.sipa.bpm.service.TasksService;
import co.gov.movilidadbogota.sipa.common.ConfParameters;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * 
 * @author Hermes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfBeans.class })
public class BpmOperationsTest {
	
	@Autowired
	private ProcessesService processesService;
	
	@Autowired
	private TasksService tasksService;
	
	private static final String CONF_BPM_USER1 = "bpm.user1";
	private static final String CONF_BPM_USER2 = "bpm.user2";
	
	private static final String CONF_BPM_ROLE1 = "bpm.user1.role";
	
	private static String user1;
	private static String role1;
	private static String user2;
	
	@BeforeClass
	public static void setUp() {	
		user1 = ConfParameters.getParm(CONF_BPM_USER1);
		user2 = ConfParameters.getParm(CONF_BPM_USER2);
		role1 = ConfParameters.getParm(CONF_BPM_ROLE1);
	}
	
	@Test
	public void testProcess1() throws FileNotFoundException {
		String processInstance = processesService.startProcess(user1, "myProcess1",
				new HashMap<String, Object>());
		System.out.println("processInstance: " + processInstance);

		List<WorkflowTask> tasks = tasksService.getActiveTasks(user1,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(task.getAssignee() == null){
				tasksService.claimTask(task, user1);
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasks(user2,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(task.getAssignee() == null){
				tasksService.claimTask(task, user2);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user2+" task: " + task.getId());
		}

		//tasks = tasksService.getActiveTasks(user1,processInstance);
                tasks = tasksService.getActiveTasksAssignedToRole(role1, processInstance);

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(task.getAssignee() == null){
				tasksService.claimTask(task, user1);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}
	}
        
        @Test
	public void testProcess1_2() throws FileNotFoundException {
		String processInstance = processesService.startProcess(user1, "myProcess1",
				new HashMap<String, Object>());
		System.out.println("processInstance: " + processInstance);

		List<WorkflowTask> tasks = tasksService.getActiveTasks(user1,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(task.getAssignee() == null){
				tasksService.claimTask(task, user1);
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasks(user2,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(task.getAssignee() == null){
				tasksService.claimTask(task, user2);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user2+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasks(user1,processInstance);                

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(task.getAssignee() == null){
				tasksService.claimTask(task, user1);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}
	}

	@Test
	public void testProcess2ParallelGateway() throws FileNotFoundException {
		HashMap<String, Object> processVars = new HashMap<String, Object>();
		ProcessVariableFormatter value = new ProcessVariableFormatter();
                value.setType("String");
                value.setValue("value1");
                processVars.put("var1", value);

		String processInstance = processesService.startProcess(user1, "myProcess2", processVars);
		System.out.println("processInstance: " + processInstance);

		List<WorkflowTask> tasks = tasksService.getActiveTasks(user1,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		processVars = new HashMap<String, Object>();
                value = new ProcessVariableFormatter();
                value.setType("integer");
                value.setValue("2");
		processVars.put("input", value);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user1);				
			}
			tasksService.completeTask(task, processVars, false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasks(user2,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		processVars = new HashMap<String, Object>();
                value = new ProcessVariableFormatter();
                value.setType("integer");
                value.setValue("4");
		processVars.put("info", value);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user2);				
			}
			tasksService.completeTask(task, processVars, false);
			System.out.println("completed "+user2+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasks(user1,processInstance);

		Assert.assertTrue(tasks.size() == 2);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user1);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}
	}

	@Test
	public void testProcess2ExclusiveGateway() throws FileNotFoundException {
		HashMap<String, Object> processVars = new HashMap<String, Object>();
		ProcessVariableFormatter value = new ProcessVariableFormatter();
                value.setType("String");
                value.setValue("value1");

                processVars.put("var1", value);

		String processInstance = processesService.startProcess(user1, "myProcess2", processVars);
		System.out.println("processInstance: " + processInstance);

		List<WorkflowTask> tasks = tasksService.getActiveTasks(user1,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		processVars = new HashMap<String, Object>();
                value = new ProcessVariableFormatter();
                value.setType("integer");
                value.setValue("1");		
                processVars.put("input", value);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user1);				
			}
			tasksService.completeTask(task, processVars, false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasks(user2,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		processVars = new HashMap<String, Object>();
                value = new ProcessVariableFormatter();
                value.setType("integer");
                value.setValue("4");		
                processVars.put("info", value);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user2);				
			}
			tasksService.completeTask(task, processVars, false);
			System.out.println("completed "+user2+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasks(user1,processInstance);

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user1);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user1+" task: " + task.getId());
		}
	}

	@Test
	public void testProcess2DueDateToday() throws FileNotFoundException {
            HashMap<String, Object> vars = new HashMap<String, Object>();

            ProcessVariableFormatter value = new ProcessVariableFormatter();
            value.setType("String");
            value.setValue("value1");
            
            vars.put("var1", value);
            String processInstance = processesService.startProcess(user1, "myProcess2", vars);
            System.out.println("processInstance: " + processInstance);
            
            vars = new HashMap<String, Object>();
            vars.put("due", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));

            List<WorkflowTask> tasks = tasksService.getActiveTasks(user1,processInstance);
            for(WorkflowTask task : tasks) {
                tasksService.updateTask(task, vars);
            }
            //Carga las tareas que tienen fecha de vencimiento = hoy
            tasks = tasksService.getActiveTasksDuedateToday(user1, "myProcess2");
            Assert.assertTrue(tasks.size() == 1);
            HashMap<String, Object> processVars = new HashMap<String, Object>();    
            value = new ProcessVariableFormatter();
            value.setType("integer");
            value.setValue("1");		
            processVars.put("input", value);
            //Cierra la tarea
            for(WorkflowTask task : tasks) {
                tasksService.completeTask(task, processVars, false);
                System.out.println("completed "+user1+" task: " + task.getId());
            }

	}

	@Test
	public void testProcess3DuedateDelayed() throws FileNotFoundException {
            HashMap<String, Object> vars = new HashMap<String, Object>();

            ProcessVariableFormatter value = new ProcessVariableFormatter();
            value.setType("String");
            value.setValue("value1");
            
            vars.put("var1", value);
            String processInstance = processesService.startProcess(user1, "myProcess2", vars);
            System.out.println("processInstance: " + processInstance);
            
            vars = new HashMap();
            vars.put("due", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date(new Date().getTime() - (24 * 60 * 60 * 1000))));

            List<WorkflowTask> tasks = tasksService.getActiveTasks(user1,processInstance);
            for(WorkflowTask task : tasks) {
                tasksService.updateTask(task, vars);
            }

            tasks = tasksService.getActiveTasksDelayed(user2, "myProcess2");

            Assert.assertTrue(tasks.size() == 1);
            HashMap<String, Object> processVars = new HashMap();    
            value = new ProcessVariableFormatter();
            value.setType("integer");
            value.setValue("1");		
            processVars.put("input", value);

            for (WorkflowTask task : tasks) {
                    if(tasksService.getActiveTaskAssignee(task) == null){
                            tasksService.claimTask(task, user2);				
                    }
                    tasksService.completeTask(task, processVars, false);
                    System.out.println("completed "+user2+" task: " + task.getId() + " vencimiento:"+ task.getDue());
            }

	}

	//@Test
	public void testProcess3Priority() throws FileNotFoundException {
		HashMap<String, Object> vars = new HashMap();
		vars.put("bpm_workflowDueDate", new Date(new Date().getTime() + (24 * 60 * 60 * 1000)));
		vars.put("bpm_workflowPriority", 5);

		String processInstance = processesService.startProcess(user2, "myProcess3", vars);
		System.out.println("processInstance: " + processInstance);

		List<WorkflowProcess> process = processesService.getActiveProcessesByPriority(user2,
				"myProcess3", 5);

		Assert.assertTrue(process.size() == 1);

		List<WorkflowTask> tasks = tasksService.getActiveTasksByPriority(user2, "myProcess3", 5);

		Assert.assertTrue(tasks.size() == 2);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user2);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user2+" task: " + task.getId());
		}

	}

	//@Test
	public void testProcess3Started() throws FileNotFoundException {
		HashMap<String, Object> vars = new HashMap<String, Object>();
		vars.put("bpm_workflowDueDate", new Date(new Date().getTime() + (6 * 24 * 60 * 60 * 1000)));
		vars.put("bpm_workflowPriority", 5);

		String processInstance = processesService.startProcess(user2, "myProcess3", vars);
		System.out.println("processInstance: " + processInstance);

		List<WorkflowProcess> process = processesService.getActiveProcessesStartedLastSevenDays(user2,
				"myProcess3");

		Assert.assertTrue(process.size() == 1);

		List<WorkflowTask> tasks = tasksService.getActiveTasksDuedateNextSevenDays(user2, "myProcess3");

		Assert.assertTrue(tasks.size() == 2);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user2);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user2+" task: " + task.getId());
		}

	}

	//@Test
	public void testProcess4AssignToUserAndGroup() throws FileNotFoundException {
		HashMap<String, Object> vars = new HashMap<String, Object>();

		String processInstance = processesService.startProcess(user2, "myProcess4", vars);
		System.out.println("processInstance: " + processInstance);

		List<WorkflowProcess> process = processesService.getActiveProcessesWithoutDuedate(user2,
				"myProcess4");

		Assert.assertTrue(process.size() == 1);

		List<WorkflowTask> tasks = tasksService.getActiveTasksAssignedToUser(user2, "myProcess4");

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user2);				
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user2+" task: " + task.getId());
		}

		tasks = tasksService.getActiveTasksAssignedToRole(role1, "myProcess4");

		Assert.assertTrue(tasks.size() == 1);

		for (WorkflowTask task : tasks) {
			if(tasksService.getActiveTaskAssignee(task) == null){
				tasksService.claimTask(task, user1);
			}
			tasksService.completeTask(task, new HashMap<String, Object>(), false);
			System.out.println("completed "+user1+" candidate task: " + task.getId());
		}

	}

	//@Test
	public void testCompletedTasksAndProcesses() throws FileNotFoundException {

		List<HistoricTaskInstance> tasks = tasksService.getCompletedTasks(user1, "myProcess1");

		for (HistoricTaskInstance task : tasks) {
			System.out.println("completed tasks user myProcess1 task: " + task.getId());
		}

		List<WorkflowProcess> processes = processesService.getCompletedProcesses(user2);
		
		for (WorkflowProcess process : processes) {
			System.out.println("completed processes user1 process: " + process.getId());
		}

	}

	@AfterClass
	public static void tearDown() {		
	}

}
