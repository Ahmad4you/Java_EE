package com.home.hibernate.OneToOne;

/**
 * One To One - Bidirectional
 * 
 * @author Ahmad Alrefai
 */

public class Main {

	public static void main(String[] args) {
		
		OneToOneBiDB db = new OneToOneBiDB();
		db.createSF();
		
		db.createTeacher();
		
		db.cancelSF();

	}

}
