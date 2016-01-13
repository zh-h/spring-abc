package xyz.springabc.error;

import java.util.HashMap;
import java.util.Map;

public class ValidateError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1500824352506037494L;
	private Map<String,String> error=new HashMap<String, String>();
	public Map<String, String> getError() {
		return error;
	}
	public void setError(Map<String, String> error) {
		this.error = error;
	}
	
	public void add(String key,String value){
		this.error.put(key, value);
	}
	
	public boolean noError(){
		return this.error.isEmpty();
	}
}
