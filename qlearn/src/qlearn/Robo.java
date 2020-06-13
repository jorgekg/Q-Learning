package qlearn;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;

public class Robo implements ButtonListener {

	private int[][] actions = { { 0, 1, 3, 0 },
			{ 0, 1, 2, 1 },
			{ 3, 2, 2, 1 },
			{ 3, 2, 3, 0 } };

	private int[][] IA = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
			{ 0, 0, 0, 0 } };

	private static final double ALPHA = 0.8;
	private static final double GAMMA = 0.5;
	private static final int SPEED = 150;

	private boolean stop = false;

	public void start() throws Exception {
		int state = 0;
		int action = 0;

		Motor.A.setSpeed(SPEED);
		Motor.C.setSpeed(SPEED);

		System.out.println("Pressione ENTER");
		Button.ENTER.waitForPressAndRelease();

		Button.ENTER.addButtonListener(this);

		while (!stop) {
			action = Utils.getRandonAction();
			if (actions[state][action] != state) {
				int stateLine = executaAcao(state, action);
				Thread.sleep(500);
				int reward = Utils.reward();
				int actionLine = biggestAction(stateLine);
				IA[state][action] = (int) (IA[state][action] + ALPHA
						* (reward + GAMMA * IA[stateLine][actionLine] - IA[state][action]));
				state = stateLine;
			}
		}
		System.out.println("finalizando...");
		Thread.sleep(2000);
		while (true) {
			action = biggestAction(state);
			state = executaAcao(state, action);
		}
	}

	public int executaAcao(int state, int action) {
		switch (action) {
		case 0:
			Move.firstMotorUp();
			break;
		case 1:
			Move.firstMotorDown();
			break;
		case 2:
			Move.lasMotorUp();
			break;
		case 3:
			Move.lastMotorDown();
			break;
		}
		return actions[state][action];
	}

	public int biggestAction(int state) {
		int reward = 0;
		int index = 0;
		for (int i = 0; i < 4; i++) {
			if (IA[state][i] > reward) {
				reward = IA[state][i];
				index = i;
			}
		}
		return index;
	}

	public void buttonPressed(Button b) {
		stop = true;
	}

	public void buttonReleased(Button b) {
		stop = false;
	}
}
