package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.JoystickDriveCommand;

/**
 * The system that allows the robot move in a direction.
 * @author 4533 Programming Team
 *
 */
public class DriveSystem extends Subsystem {
	/**
	 * An instance of DriveSystem
	 */
	private static DriveSystem INSTANCE;
	/**
	 * The left master motor controller
	 */
	WPI_TalonSRX leftMaster;
	/**
	 * The left slave motor controller
	 */
	WPI_TalonSRX leftSlave;
	/**
	 * The right master motor controller
	 */
	WPI_TalonSRX rightMaster;
	/**
	 * The right slave motor controller
	 */
	WPI_TalonSRX rightSlave;
	/**
	 * The maximum velocity we want our robot to go (RPM)
	 */
	private static int MAX_VELOCITY = 500;
	/**
	 * How many units there are on the magnetic encoder
	 */
	public static final int UNITS_PER_REVOLUTION = 4096;
	/**
	 * 
	 */
	private static final int TIMEOUT = 10;
	/**
	 * The PID port in which the motor controller is set to
	 */
	private static final int DEFAULT_PID_INDEX = 0;
	/**
	 * A variable used when driving with velocity controls
	 */
	private double targetL = 0;
	/**
	 * A variable used when driving with velocity controls
	 */
	private double targetR = 0;
	/**
	 * A counter used to determine when to print to riolog
	 */
	private int printCounter = 0;
	
	private Port navXPort;
	
//	private AHRS navX;


	/**
	 * Sets up motor controllers and the encoders on them
	 */
	public DriveSystem() {
		rightMaster = new WPI_TalonSRX(RobotMap.MOTOR_RIGHT_MASTER);
		leftMaster = new WPI_TalonSRX(RobotMap.MOTOR_LEFT_MASTER);
		rightSlave = new WPI_TalonSRX(RobotMap.MOTOR_RIGHT_SLAVE);
		leftSlave = new WPI_TalonSRX(RobotMap.MOTOR_LEFT_SLAVE);

		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, DEFAULT_PID_INDEX, TIMEOUT);
		leftMaster.setSensorPhase(true);

		leftMaster.configNominalOutputForward(0, TIMEOUT);
		leftMaster.configNominalOutputReverse(0, TIMEOUT);
		leftMaster.configPeakOutputForward(1, TIMEOUT);
		leftMaster.configPeakOutputReverse(-1, TIMEOUT);

		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, DEFAULT_PID_INDEX, TIMEOUT);
		rightMaster.setSensorPhase(true);

		rightMaster.configNominalOutputForward(0, TIMEOUT);
		rightMaster.configNominalOutputReverse(0, TIMEOUT);
		rightMaster.configPeakOutputForward(1, TIMEOUT);
		rightMaster.configPeakOutputReverse(-1, TIMEOUT);

		leftMaster.configAllowableClosedloopError(DEFAULT_PID_INDEX, 50, TIMEOUT);
		rightMaster.configAllowableClosedloopError(DEFAULT_PID_INDEX, 50, TIMEOUT);

		leftMaster.setInverted(true);
		leftSlave.setInverted(true);
		navXPort = SPI.Port.kMXP;
//		navX = new AHRS(navXPort);


	}

	/**
	 * Creates a new instance of DriveSystem
	 */
	public static void initialize() {
		if (INSTANCE == null) {
			INSTANCE = new DriveSystem();
		}
	}

	/**
	 * Gets an instance of the DriveSystem
	 * 
	 * @return INSTANCE which is a DriveSystem object
	 */
	public static DriveSystem getInstance() {
		return INSTANCE;
	}

	/**
	 * Sets the motor controllers to follow specific PIDF loop
	 * 
	 * @param p
	 *            Proportional Gain
	 * @param i
	 *            Integral Gain
	 * @param d
	 *            Derivative Gain
	 * @param f
	 *            Feed-forward Gain
	 */
	public void setPIDFValues(double p, double i, double d, double f) {
		leftMaster.config_kF(DEFAULT_PID_INDEX, f, TIMEOUT);
		leftMaster.config_kP(DEFAULT_PID_INDEX, p, TIMEOUT);
		leftMaster.config_kI(DEFAULT_PID_INDEX, i, TIMEOUT);
		leftMaster.config_kD(DEFAULT_PID_INDEX, d, TIMEOUT);
		rightMaster.config_kF(DEFAULT_PID_INDEX, f, TIMEOUT);
		rightMaster.config_kP(DEFAULT_PID_INDEX, p, TIMEOUT);
		rightMaster.config_kI(DEFAULT_PID_INDEX, i, TIMEOUT);
		rightMaster.config_kD(DEFAULT_PID_INDEX, d, TIMEOUT);
	}

	/**
	 * Sets the motor controllers to run using the percent output mode
	 * 
	 * @param left
	 *            A value between -1 and 1. Typically a value from the
	 *            controller's left joystick is passed here
	 * @param right
	 *            A value between -1 and 1. Typically a value from the
	 *            controller's right joystick is passed here
	 */
	public void driveAction(double left, double right) {
		// This method used to actually drive the robot
		this.leftMaster.set(left);
		this.rightMaster.set(right);
		this.leftSlave.set(ControlMode.Follower, RobotMap.MOTOR_LEFT_MASTER);
		this.rightSlave.set(ControlMode.Follower, RobotMap.MOTOR_RIGHT_MASTER);
	}

	/**
	 * Sets the motor controllers to run using the velocity output mode. Left
	 * and Right parameters are used as scaling factors.
	 * 
	 * @param left
	 *            A value between -1 and 1. Typically a value from the
	 *            controller's left joystick is passed here
	 * @param right
	 *            A value between -1 and 1. Typically a value from the
	 *            controller's right joystick is passed here
	 */
	public void driveVelocity(double left, double right) {
		targetL = left * MAX_VELOCITY * UNITS_PER_REVOLUTION / 600;
		targetR = right * MAX_VELOCITY * UNITS_PER_REVOLUTION / 600;
		leftMaster.set(ControlMode.Velocity, targetL);
		leftSlave.set(ControlMode.Follower, RobotMap.MOTOR_LEFT_MASTER);
		rightMaster.set(ControlMode.Velocity, targetR);
		rightSlave.set(ControlMode.Follower, RobotMap.MOTOR_RIGHT_MASTER);
		if (printCounter == 50) {
			System.out.println("Out: " + leftMaster.getMotorOutputVoltage() / leftMaster.getBusVoltage() + "\tSpd: "
					+ leftMaster.getSelectedSensorVelocity(DEFAULT_PID_INDEX) + "\tErr: "
					+ leftMaster.getClosedLoopError(DEFAULT_PID_INDEX) + "\tTrg: " + targetL + "\tOut: "
					+ rightMaster.getMotorOutputVoltage() / rightMaster.getBusVoltage() + "\tSpd: "
					+ rightMaster.getSelectedSensorVelocity(DEFAULT_PID_INDEX) + "\tErr: "
					+ rightMaster.getClosedLoopError(DEFAULT_PID_INDEX) + "\tTrg: " + targetR);
			printCounter = 0;
		}
		printCounter++;
	}
	public void driveVelocityOneJoystick(double percentF, double percentS) {
		targetL = percentF * MAX_VELOCITY * UNITS_PER_REVOLUTION / 600;
		targetR = percentF * MAX_VELOCITY * UNITS_PER_REVOLUTION / 600;
		if(percentS > .5) {
			targetL *= -percentS;
		}
		if(percentS < .5) {
			targetR *= percentS;
		}
		leftMaster.set(ControlMode.Velocity, -targetL);
		rightMaster.set(ControlMode.Velocity, -targetR);
		leftSlave.set(ControlMode.Velocity, -targetL);
		rightSlave.set(ControlMode.Velocity, -targetR);

	}
	/**
	 * Sets the motor controllers to run using position mode
	 * 
	 * @param position
	 *            The position value that the encoder will be set to go to
	 */
	public void drivePosition(int position) {
		leftMaster.set(ControlMode.Position, position);
		rightMaster.set(ControlMode.Position, position);
		leftSlave.set(ControlMode.Follower, RobotMap.MOTOR_LEFT_MASTER);
		rightSlave.set(ControlMode.Follower, RobotMap.MOTOR_RIGHT_MASTER);
	}

	/**
	 * Sets the default command for the method
	 */
	public void initDefaultCommand() {
		if(RobotMap.CONTROLLER_TYPE.equals("Logitech Attack 3")) {
			this.setDefaultCommand(new JoystickDriveCommand());
		}
		else {
			this.setDefaultCommand(new DriveCommand());
		}

	}

	// Just some methods we can use in autonomous
	/**
	 * Moves the robot forward.
	 * 
	 * @param v
	 *            A value between -1 and 1
	 */
	public void forward(double v) {
		this.driveAction(v, v);
	}

	/**
	 * Moves the robot backward.
	 * 
	 * @param v
	 *            A value between -1 and 1
	 */
	public void backward(double v) {
		this.driveAction(-v, -v);
	}

	/**
	 * Stops the robot.
	 */
	public void stop() {
		this.driveAction(0.0, 0.0);
	}

	/**
	 * Turns the robot.
	 * 
	 * @param v
	 *            A value between -1 and 1 sent to the left motor controller
	 * @param w
	 *            A value between -1 and 1 sent to the right motor controller
	 */
	public void turn(double v, double w) {
		this.driveAction(v, w);
	}

	/**
	 * Sets the position on the encoder without moving the wheels.
	 * 
	 * @param position
	 *            The position the encoder will be set to.
	 */
	public void setPosition(int position) {
		leftMaster.setSelectedSensorPosition(position, DEFAULT_PID_INDEX, TIMEOUT);
		rightMaster.setSelectedSensorPosition(position, DEFAULT_PID_INDEX, TIMEOUT);
	}

	/**
	 * Returns the left wheels position based on the left encoder.
	 * 
	 * @return The left wheels position
	 */
	public int getLeftPosition() {
		return leftMaster.getSelectedSensorPosition(DEFAULT_PID_INDEX);
	}

	/**
	 * Returns the right wheels position based on the right encoder.
	 * 
	 * @return The right wheels position
	 */
	public int getRightPosition() {
		return rightMaster.getSelectedSensorPosition(DEFAULT_PID_INDEX);
	}

	/**
	 * Returns the current velocity of the left wheels
	 * 
	 * @return The left wheels velocity
	 */
	public double getLeftVelocity() {
		return leftMaster.getSelectedSensorVelocity(DEFAULT_PID_INDEX);
	}

	/**
	 * Returns the current velocity of the right wheels
	 * 
	 * @return The right wheels velocity
	 */
	public double getRightVelocity() {
		return rightMaster.getSelectedSensorVelocity(DEFAULT_PID_INDEX);
	}

	/**
	 * Returns the left target velocity
	 * 
	 * @return The left target velocity
	 */
	public double getLTargetVelocity() {
		return targetL;
	}

	/**
	 * Returns the right target velocity
	 * 
	 * @return The right target velocity
	 */
	public double getRTargetVelocity() {
		return targetR;
	}

	/**
	 * Sets the RPM the robot will move at
	 * 
	 * @param RPM
	 *            The rotations per minute the robot will move at
	 */
	public void setVelocity(int RPM) {
		MAX_VELOCITY = RPM;
	}
	public double getAngle() {
//		return navX.getAngle();
		return 0;
	}
	public void resetAngle() {
//		navX.reset();
	}
	public void setPeakOutput(double output) {
		rightMaster.configPeakOutputForward(output, TIMEOUT);
		leftMaster.configPeakOutputForward(output, TIMEOUT);
		leftMaster.configPeakOutputReverse(-output, TIMEOUT);
		rightMaster.configPeakOutputReverse(-output, TIMEOUT);
	}

	public void setRampRate(double secondsToMax) {
		leftMaster.configClosedloopRamp(secondsToMax, TIMEOUT);
		rightMaster.configClosedloopRamp(secondsToMax, TIMEOUT);
	}
}
