package co.gov.movilidadbogota.sipa.bpm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.gov.movilidadbogota.sipa.bpm.config.ConfBeans;
import co.gov.movilidadbogota.sipa.bpm.model.WorkflowTask;
import co.gov.movilidadbogota.sipa.bpm.service.ProcessesService;
import co.gov.movilidadbogota.sipa.bpm.service.TasksService;
import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

/**
 * Test BPM concurrency 
 * 
 * @author Hermes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfBeans.class })
public class ConcurrencyBPMTestClient {
   
   @Autowired
   private ProcessesService processesService;
   
   @Autowired
   private TasksService tasksService;
      
   private Client[] clients;
   
   // Concurrency test params
   private static final String CONF_BPM_CLIENT_COUNT = "bpm.client.count";    
   private static final String CONF_BPM_USER1 = "bpm.user1";
   private static final String CONF_BPM_USER2 = "bpm.user2";  
   private static final String CONF_BPM_ROLE1 = "bpm.user1.role";
   
   private String user1;
   private String role1;
   private String user2;
   
   
   @Before
   public void setUp() throws Exception {
      //Concurrency test params
      int testClientCount = Integer.parseInt(ConfParameters.getParm(CONF_BPM_CLIENT_COUNT)); 
      
      user1 = ConfParameters.getParm(CONF_BPM_USER1);
      user2 = ConfParameters.getParm(CONF_BPM_USER2);
      role1 = ConfParameters.getParm(CONF_BPM_ROLE1);
      
      System.out.println("Concurrency test initializing, with " + testClientCount+"...");
      //Create the clients for concurrency testing
      clients = new Client[testClientCount];
      for (int clientIndex = 0; clientIndex < testClientCount; clientIndex++) {
         clients[clientIndex] = new Client(clientIndex + 1);
      }        
   }
      
   @Test
   public void cmisConcurrencyTest() throws Exception {
      System.err.println("Concurrency test starting...");
      
      //Create the future tasks for the repo clients
      ArrayList<FutureTask<Boolean>> clientTasks = new ArrayList<FutureTask<Boolean>>(clients.length);
      for (int clientIndex = 0; clientIndex < clients.length; clientIndex++) {
         clientTasks.add(clientIndex, new FutureTask<Boolean>(clients[clientIndex]));
      }
      //Start the threads 
      ExecutorService executorService = Executors.newCachedThreadPool();
      long testStartTime = System.currentTimeMillis();
      for (int taskIndex = 0; taskIndex < clientTasks.size(); taskIndex++) {
         executorService.execute(clientTasks.get(taskIndex));
      }

      boolean done = false;
      while (!done) { // Loop until all tasks are done.

         boolean foundRunningTask = false;
         for (int taskIndex = 0; taskIndex < clientTasks.size(); taskIndex++) {

            if (!clientTasks.get(taskIndex).isDone()) {
               foundRunningTask = true;
               continue;
            }
            clientTasks.get(taskIndex).get();        
         }
         if (!foundRunningTask) {
            done = true;
         } else {
            Thread.sleep(250); 
         }

      } // while (!done)

      long testEndTime = System.currentTimeMillis();

      executorService.shutdown();

      System.out.println("Concurrency test completed, with " + clients.length + " concurrent clients"
            + " on " + ConfParameters.getParm(Constants.CONF_BPM_URL) + " server "
            + " running "
            + " in a total of "
            + Math.round((testEndTime - testStartTime) / 1000d) + " seconds ");
      
   }

   /**
    * The bpm client.
    * 
    * @author Hermes
    *
    */
   public class Client implements Callable<Boolean> {
      protected int clientNumber;
      protected String clientUUID = UUID.randomUUID().toString();
      
      public Client(int clientNumber) throws Exception {
         this.clientNumber = clientNumber;     
      }

      /* (non-Javadoc)
       * @see java.util.concurrent.Callable#call()
       */
      @Override
      public Boolean call() throws Exception {         
         long docStartTime = System.currentTimeMillis();
         
         //Process myProcess1 execution start
         String processInstance = processesService.startProcess(user1, "myProcess1",
               new HashMap<String, Object>());       
         List<WorkflowTask> tasks = tasksService.getActiveTasks(user1,processInstance);
         Assert.assertTrue(tasks.size() == 1);
         for (WorkflowTask task : tasks) {
            tasksService.completeTask(task, new HashMap<String, Object>(), false);
            System.out.println("completed "+user1+" task: " + task.getId());
         }
         tasks = tasksService.getActiveTasks(user2,processInstance);
         Assert.assertTrue(tasks.size() == 1);
         for (WorkflowTask task : tasks) {
            tasksService.completeTask(task, new HashMap<String, Object>(), false);
            System.out.println("completed "+user2+" task: " + task.getId());
         }
         tasks = tasksService.getActiveTasksAssignedToRole(role1, processInstance);
         Assert.assertTrue(tasks.size() == 1);
         for (WorkflowTask task : tasks) {
            if(task.getAssignee() == null){
               tasksService.claimTask(task, user1);            
            }
            tasksService.completeTask(task, new HashMap<String, Object>(), false);
            System.out.println("completed "+user1+" task: " + task.getId());
         }
         //Process myProcess1 execution finish
         
         long docEndTime = System.currentTimeMillis();
         System.err.println("Client " + String.format("%03d", Integer.valueOf(clientNumber)) + " completed during "
               + String.format("%.1f", Double.valueOf((docEndTime - docStartTime) / 1000d)) + " seconds.");
         System.err.flush();
         return Boolean.TRUE;
         
      }

   } // Client

}
