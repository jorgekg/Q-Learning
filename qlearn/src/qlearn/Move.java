package qlearn;

import lejos.nxt.Motor;

public class Move {

	public static void firstMotorDown() {
		Motor.C.rotate(100);
		Motor.C.stop();
	}
	
	public static void firstMotorUp() {
		Motor.C.rotate(-100);
		Motor.C.stop();
	}
	
	public static void lastMotorDown() {
		Motor.A.rotate(65);
		Motor.A.stop();
	}
	
	public static void lasMotorUp() {
		Motor.A.rotate(-65);
		Motor.A.stop();
	}

	
}
