package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.AutoLift;

/**
 * The system that allows the robot to move the intake up and down.
 * @author 4533 Programming Team
 *
 */
public class SwingArmSystem extends PIDSubsystem {
	
	private VictorSPX motor;
	private AnalogInput potentiometer;
	public static SwingArmSystem INSTANCE;
	
	/**
	 * Sets the motor up and adds a potentiometer
	 */
	public SwingArmSystem() {
		super(0.08, 0.0, 0.0);
		motor = new VictorSPX(RobotMap.SWING_ARM_MOTOR);
		potentiometer = new AnalogInput(RobotMap.SWING_ARM_POTENTIOMETER);
		getPIDController().setContinuous(false);
	}
	/**
	 * Creates a new instance of SwingArmSystem
	 */
	public static void initialize() {
		if(INSTANCE == null) {
			INSTANCE = new SwingArmSystem();
		}
	}
	/**
	 * @return Instance: An instance of SwingArmSystem
	 */
	public static SwingArmSystem getInstance() {
		return INSTANCE;
	}
	/**
	 * Moves the swing arm up
	 */
	public void up() {
		motor.set(ControlMode.PercentOutput, .5);
	}
	/**
	 * Moves the swing arm down
	 */
	public void down() {
		motor.set(ControlMode.PercentOutput, -.5);
	}
	/**
	 * Stops moving the swing arm
	 */
	public void stop() {
		motor.set(ControlMode.PercentOutput, 0);
	}
	/**
	 * Gets the position of the swing arm
	 * @return The value of the potentiometer on the swing arm
	 */
	public double position() {
		return potentiometer.getValue();
	}
	/**
	 * Gets the angle in human terms of the swing arm
	 * @return The angle of the swing arm
	 */
	public double angle() {
		return .04 * (this.position())-8.10;
	}
	@Override
	protected void initDefaultCommand() {

		this.setDefaultCommand(new AutoLift());
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.angle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		motor.set(ControlMode.PercentOutput, output);
	}
	/**
	 * Sets the swing arm to an angle
	 * @param angle The angle that the swing arm will be set to
	 */
	public void setAngle(double angle) {
		this.setSetpoint(angle);
	}
}
