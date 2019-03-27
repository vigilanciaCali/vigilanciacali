package co.edu.usbcali.vas.security.control;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.jboss.aerogear.security.otp.api.Base32;

	public class Crypt {
		
		private final static String SHA = "SHA-256";
	  
		//PRUEBAS(salt estatico)
	    private final static String salt = "wviv3vqahdqwpfzcyx70twdqx7ebd7u7do5iu0d24clvsadve6ev7ij7mxl77kp8";

	    //private final static String salt = randomGeneration()+randomGeneration_sec();
	 
	    //Takes a string, and converts it to SHA hashed string.
	    public String securePassword(String password) {
	        String sha = "";
	        if(null == password) 
	            return null;
	         
	        password = password+salt;//adding a salt to the string before it gets hashed.
	        
	        try {
	            MessageDigest digest = MessageDigest.getInstance(SHA);//Create MessageDigest object for SHA-1
	            digest.update(password.getBytes(), 0, password.length());//Update input string in message digest
	            sha = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)
	  
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return sha;
	    }
	    
	    public String securePasswordSalt(String passwordToHash, String salt) {
	        String passwordHashed = "";
	        
	        if(passwordToHash == null){
	        	return null;
	        }

	        passwordHashed = passwordToHash + salt;//adding a salt to the string before it gets hashed.
	        
	        try {
	            MessageDigest digest = MessageDigest.getInstance(SHA);//Create MessageDigest object for SHA-1
	            digest.update(passwordHashed.getBytes(), 0, passwordHashed.length());//Update input string in message digest
	            passwordHashed = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)
	  
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return passwordHashed;
	    }
	    
	    public String saltRandomGeneration() {
			String randomStr = "";

			try {
				SecureRandom random = new SecureRandom();
				String numeroRandom = new BigInteger(128, random).toString(32);
				// Convertimos el secret key en byte
				byte[] hash = new byte[0];
				hash = numeroRandom.getBytes();
				// Convertimos el arreglo de byte en la clase Base32 del algoritmo
				randomStr = Base32.encode(hash);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return randomStr;
		}
	    
	    
	    public String secureString(String text) {
	        String sha = "";
	        if(null == text) 
	            return null;
	         
	        text = text+salt;//adding a salt to the string before it gets hashed.
	        
	        try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");//Create MessageDigest object for SHA
	            digest.update(text.getBytes(), 0, text.length());//Update input string in message digest
	            sha = new BigInteger(1, digest.digest()).toString(32);//Converts message digest value in base 16 (hex)
	  
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return sha;
	    }
	    
	    public static String randomGeneration() {
	        String randomStr = "";
	        
	        try {
	        	long milis = new java.util.GregorianCalendar().getTimeInMillis();
	 			Random r = new Random(milis);
	 			int i = 0;
	 			while (i < 64) {
	 				char c = (char) r.nextInt(255);
	 				if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
	 					randomStr += c;
	 					i++;
	 				}
	 			}
	  
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return randomStr.toLowerCase();
	    }
	    
	    public static String randomGeneration_sec() {
	        String randomStr = "";
	        
	        try {
	        	SecureRandom random = new SecureRandom();
	  			String numeroRandom = new BigInteger(128, random).toString(32);
	  			// Convertimos el secret key en byte
	  			byte[] hash = new byte[0];
	  			hash = numeroRandom.getBytes();
	  			// Convertimos el arreglo de byte en la clase Base32 del algoritmo
	  			randomStr = Base32.encode(hash);
	  
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return randomStr;
	    }
	

}
