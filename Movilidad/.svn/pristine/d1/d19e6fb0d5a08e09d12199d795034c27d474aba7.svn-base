/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.movilidadbogota.sipa.bpm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author crist
 */
public class ProcessVariableFormatter {
    
    private String value;
    private String type;

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public String generateJson(String variable, boolean coma){
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(variable);
        sb.append("\":{\"value\":\"");
        sb.append(this.value);
        sb.append("\",\"type\":\"");
        sb.append(this.type);
        sb.append("\"}");
        if(coma)
            sb.append(",");
        return sb.toString();
    }   
    
    public static String generateWrapperJson(String content){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"variables\": {");
        sb.append(content);
        sb.append("}}");
        return sb.toString();
    }
    
    public static String variablesCorchete(HashMap<String, Object> variables){
        String salida = new String();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (variables != null && variables.size() > 0) {
            List variablesFormateadas = new ArrayList();
            int currVal = 0;
            int max = variables.entrySet().size();
            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                String key = entry.getKey();
                sb.append("\"");
                sb.append(key);
                sb.append("\":\"");
                sb.append(entry.getValue());
                sb.append("\"");
                currVal++;
                if(currVal < max){
                    sb.append(", ");
                }
            }
	}
        sb.append("}");
        salida = sb.toString();
    System.out.println(salida);
        return salida;
    }
}
