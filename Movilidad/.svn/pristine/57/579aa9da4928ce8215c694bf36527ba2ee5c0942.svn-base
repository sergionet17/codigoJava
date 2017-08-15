package co.gov.movilidadbogota.sipa.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Class used to get the configuration parameters.
 * 
 * @author Hermes
 */
public class ConfParameters
{
   private static HashMap<String, String> parms = new HashMap<String, String>();

   /**
    * Obtain a value parameter based on it´s name
    * @param parmName 
    */
   public  static String getParm(final String parmName)
   {
      if ( parmName == null || parmName.equals(""))
           throw new IllegalArgumentException( "Nombre del parametro a solicitar no puede ser nulo ni vacio");

      if ( parms.size() == 0 )
         loadParms();

      String sParm= parms.get( parmName.toLowerCase());
      if ( sParm == null )
         throw new IllegalArgumentException("Parametro["+ parmName+ "] no ha sido registrado");

      return sParm;
   }// getParm

   /*
    * Create the system configuration parameters
    */
   private static void loadParms()
   {
      ResourceBundle         resource = ResourceBundle.getBundle("sipa");
      Enumeration<String>    parmKeys = resource.getKeys();
      
      while (parmKeys.hasMoreElements())
      {
         String parm  = (String)parmKeys.nextElement();
         String key   = parm.trim().toLowerCase();
         String value = resource.getString(parm).trim();

         if ( parms.containsKey( key) )
            System.out.println("Parametro["+ parm+ "] duplicado. Ha sido ignorado");
         else
         {
            parms.put( key, value);
            System.out.println("Parametro ["+ key+ "]= {"+ value+ "}");
         }
      }     
   }// loadParms

}// Configuration
