package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.SwingArmSystem;
/**
 * The place where buttons are mapped to commands
 * @author 4533 Programming Team
 *
 */
public class OI {
	private Joystick j = new Joystick(RobotMap.JOYSTICK_PORT);
	private Joystick buttons = new Joystick(RobotMap.BUTTON_PORT);
	private static OI INSTANCE;


	private JoystickButton intakeIn = new JoystickButton(j, RobotMap.LEFT_BUMPER);
	private JoystickButton intakeOut = new JoystickButton(j, RobotMap.LEFT_TRIGGER);
	private JoystickButton arm62 = new JoystickButton(j, RobotMap.Y_BUTTON);
	private JoystickButton armFast62 = new JoystickButton(j, RobotMap.RIGHT_BUMPER);
	private JoystickButton arm15 = new JoystickButton(j, RobotMap.X_BUTTON);
	private JoystickButton arm20 = new JoystickButton(j, RobotMap.B_BUTTON);
	private JoystickButton arm5 = new JoystickButton(j, RobotMap.A_BUTTON);
	private JoystickButton redButton = new JoystickButton(buttons, 12);
	private JoystickButton startBtn = new JoystickButton(buttons, 11);
	private JoystickButton switchBtn = new JoystickButton(buttons, 10);
	private JoystickButton vaultBtn = new JoystickButton(buttons, 9);
	private JoystickButton floorBtn = new JoystickButton(buttons, 8);
	
	/**
	 * Creates the controls for the robots
	 */
	public OI(){
		IntakeSystem.initialize();
		SwingArmSystem.initialize();
		if(DriverStation.getInstance().getJoystickName(0).equals("2In1 USB Joystick")) {
			arm62 = new JoystickButton(j, 1);
			arm15 = new JoystickButton(j, 4);
			arm20 = new JoystickButton(j, 2);
			arm5 = new JoystickButton(j, 3);

		}
		intakeIn.whileHeld(new TakeIn());
		intakeOut.whileHeld(new PushOut(.75));
		arm62.whenPressed(new MoveSwingArm(62));
		//armFast62.whenPressed(new MoveSwingArm(62));
		//When intake detected move to 10
		arm5.whenPressed(new MoveSwingArm(5));
		arm15.whenPressed(new MoveSwingArm(15));
		arm20.whenPressed(new MoveSwingArm(20));
		startBtn.whenPressed(new MoveSwingArm(62));
		switchBtn.whenPressed(new MoveSwingArm(20));
		vaultBtn.whenPressed(new MoveSwingArm(15));
		floorBtn.whenPressed(new MoveSwingArm(5));
		redButton.whileHeld(new ExtraButtonCommand());
	}
	/**
	 * @return Instance: An instance of OI
	 */
	public static OI getInstance() {
		return INSTANCE;
	}
	/**
	 * Initializes OI
	 */
	public static void initialize() {
		if (INSTANCE == null) {
			INSTANCE = new OI();
		}
	}
}
