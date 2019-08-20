package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * The system that allows the robot to intake cubes and shoot them out
 * @author 4533 Programming Team
 *
 */
public class IntakeSystem extends Subsystem {
	
	private VictorSPX leftMotor;
	private VictorSPX rightMotor;
	private static IntakeSystem INSTANCE;
	private AnalogInput boxSensor;
	
	/**
	 * Sets up the motors for intake
	 */
	public IntakeSystem() {
		leftMotor = new VictorSPX(RobotMap.INTAKE_LEFT);
		rightMotor = new VictorSPX(RobotMap.INTAKE_RIGHT);
		rightMotor.setInverted(true);
		boxSensor = new AnalogInput(RobotMap.INTAKE_BOX_SENSOR);
	}
	
	/**
	 * Instance is instantiated as a new IntakeSystem
	 */
	public static void initialize() {
		if(INSTANCE == null) {
			INSTANCE = new IntakeSystem();
		}
	}
	
	/**
	 * @return Instance: An instance of IntakeSystem
	 */
	public static IntakeSystem getInstance() {
		return INSTANCE;
	}
	/**
	 * Sets the motors to bring the cube in
	 */
	public void in() {
		leftMotor.set(ControlMode.PercentOutput, -.5);
		rightMotor.set(ControlMode.PercentOutput, -.5);
	}
	/**
	 * Sets the motors to push the cube out
	 */
	public void out(double percent) {
		leftMotor.set(ControlMode.PercentOutput, percent);
		rightMotor.set(ControlMode.PercentOutput, percent);
	}
	/**
	 * Stops the motors
	 */
	public void stop() {
		leftMotor.set(ControlMode.PercentOutput, 0);
		rightMotor.set(ControlMode.PercentOutput, 0);
	}
	/**
	 * Detects if there is a cube in the Intake
	 * @return False because we have no sensor on the intake yet
	 */
	public boolean hasCube() {
		if(boxSensor.getValue() >= 12 && boxSensor.getValue() <= 30) {
			return true;
		}
		return false;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
