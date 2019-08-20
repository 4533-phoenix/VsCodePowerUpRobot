package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A place for all of the static values used on the robot
 * @author 4533 Programming Team
 *
 */
public class RobotMap {
	// Controller Mappings
	
	/**
	 * This is the value for the joystick port that we use in driver station.
	 *
	 */
	public static int JOYSTICK_PORT = 0;
	/**
	 * This is the value for the button under the right analog stick.
	 * 
	 */
	public static int BUTTON_PORT = 1;
	public static int RIGHT_STICK_BUTTON = 12;
	/**
	 * This is the value for the button under the left analog stick.
	 * 
	 */
	public static int LEFT_STICK_BUTTON = 11;
	/**
	 * This is the value for the button labeled start.
	 * 
	 */
	public static int START_BUTTON = 10;
	/**
	 * This is the value for the button labeled back.
	 * 
	 */
	public static int BACK_BUTTON = 9;
	/**
	 * This is the value for the button labeled RT.
	 * 
	 */
	public static int RIGHT_TRIGGER = 8;
	/**
	 * This is the value for the button labeled LT.
	 * 
	 */
	public static int LEFT_TRIGGER = 7;
	/**
	 * This is the value for the button labeled RB.
	 * 
	 */
	public static int RIGHT_BUMPER = 6;
	/**
	 * This is the value for the button labeled LB.
	 * 
	 */
	public static int LEFT_BUMPER = 5;
	/**
	 * This is the value for the button labeled Y.
	 * 
	 */
	public static int Y_BUTTON = 4;
	/**
	 * This is the value for the button labeled B.
	 * 
	 */
	public static int B_BUTTON = 3;
	/**
	 * This is the value for the button labeled A.
	 * 
	 */
	public static int A_BUTTON = 2;
	/**
	 * This is the value for the button labeled X.
	 * 
	 */
	public static int X_BUTTON = 1;

	// Drive System Mappings
	
	/**
	 * This is the value for the left master motor controller used in drive system.
	 * 
	 */
	public static int MOTOR_LEFT_MASTER = 0;
	/**
	 * This is the value for the left slave motor controller used in drive system.
	 * 
	 */
	public static int MOTOR_LEFT_SLAVE = 1;
	/**
	 * This is the value for the right master motor controller used in drive system.
	 * 
	 */
	public static int MOTOR_RIGHT_MASTER = 2;
	/**
	 * This is the value for the right slave motor controller used in drive system.
	 * 
	 */
	public static int MOTOR_RIGHT_SLAVE = 3;

	// Intake System Mappings
	
	/**
	 * This is the value for the intake left motor controller used in the intake system.
	 * 
	 */
	public static int INTAKE_LEFT = 4;
	/**
	 * This is the value for the intake right motor controller used in the intake system.
	 * 
	 */
	public static int INTAKE_RIGHT = 6;

	// Swing Arm System Mappings
	
	/**
	 * This is the value for the swing arm motor controller used in the swing arm system.
	 * 
	 */
	public static int SWING_ARM_MOTOR = 5;
	/**
	 * This is the value for the swing arm potentiometer
	 * 
	 */
	public static int SWING_ARM_POTENTIOMETER = 1;
	
	/**
	 * This is the value for the intake box sensor
	 */
	public static int INTAKE_BOX_SENSOR = 2;
	//PID MODES
	
	/**
	 * This value determines if we are in PID in driving mode or not
	 */
	
	public static boolean PID_DRIVE_MODE = true;

	public static boolean IS_IN_X_MODE = false;

	public static String CONTROLLER_TYPE = DriverStation.getInstance().getJoystickName(0);
}
