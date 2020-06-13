package qlearn;

import java.util.Random;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Utils {
	
	private static Random rand = new Random();
	private static UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S2);
	private static int initialDistance = sonic.getDistance();

	public static int getRandonAction() {
		return rand.nextInt(400) / 100;
	}
	
	public static int reward() {
		int reconpensa = initialDistance - sonic.getDistance();
		System.out.println(reconpensa);
		return reconpensa * 10;
	}
	
}
