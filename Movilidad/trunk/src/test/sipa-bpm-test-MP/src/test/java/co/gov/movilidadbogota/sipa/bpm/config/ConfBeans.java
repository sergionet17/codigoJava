package co.gov.movilidadbogota.sipa.bpm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.gov.movilidadbogota.sipa.bpm.service.ProcessesService;
import co.gov.movilidadbogota.sipa.bpm.service.TasksService;

/**
 * Configure the beans used from the bpm test application.
 * 
 * @author Hermes
 *
 */
@Configuration
public class ConfBeans {
	
	@Bean
	public ProcessesService processesService()
	{
		return new ProcessesService();
	}
	
	@Bean
	public TasksService tasksService()
	{
		return new TasksService();
	}
	
	
}
