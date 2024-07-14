package com.home.hibernate.relationships;

public class OneToOneBidirectional {

	/**
	 * One To One - Bidirectional
	 * 
	 * Selber Primary Key der Tabellen
	 * 
	 * Navigieren in beide Richtungen moeglich
	 * -->
	 * Teacher Eigenschaft in TeacherAddresse 
	 * oder auch so geht dass, TeacherAddresse Eigenschaft in Teacher.
	 * 
	 * Verwendung mappedBy: der fuer das aktualisieren der DB zust�ndig ist
	 * 
	 * -----> Darauf achten: TeacherAddresse nutzt selben PK von gespeicherten Teacher
	 */
}
