
package cn.com.gsoft.maximo.common;

import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;


public class Test {
	
	public static void main(String[] args){

		try{
			System.out.println("1234567890");
			Session session = NotesFactory.createSession("127.0.0.1", "admin", "password");
			System.out.println(session);
		}catch(NotesException e){
			
		}
	}

}
