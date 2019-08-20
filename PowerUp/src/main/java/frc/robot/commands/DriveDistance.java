package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
/**
 * The command that makes the robot drive to a certain distance
 * @author 4533 Programming Team
 *
 */
public class DriveDistance extends Command {
	
	private DriveSystem driveSystem = DriveSystem.getInstance();
	private double distance;
	private static final double CIRCUMFERENCE = 6*Math.PI;
	private static final double UNITS_PER_INCH = DriveSystem.UNITS_PER_REVOLUTION/CIRCUMFERENCE;
	
	public DriveDistance(double distance) {
		this.requires(driveSystem);
		this.distance = distance * UNITS_PER_INCH;
		DriveSystem.getInstance().setPeakOutput(.5);
	}
	
	public void execute() {
		DriveSystem.getInstance().setPIDFValues(0.1, 0.0001, 0, 0);
		driveSystem.drivePosition((int)(-distance));
	}
	
	@Override
	protected boolean isFinished() {
		return (Math.abs(driveSystem.getLeftPosition()) >= distance) && (Math.abs(driveSystem.getRightPosition()) >= distance);
	}
	
	public void end() {
		driveSystem.setPosition(0);
		driveSystem.stop();
	}

}
