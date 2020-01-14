package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
/**
 * A command that turns the robot to a certain angle.
 * @author 4533 Programming Team
 *
 */
public class AngleTurn extends Command {
	
	double angle;
	
	public AngleTurn(double angle) {
		this.requires(DriveSystem.getInstance());
		this.angle = angle;
		DriveSystem.getInstance().resetAngle();
	}
	//P was at .15
	public void execute() {
		DriveSystem.getInstance().setPIDFValues(.25, 0, 2.5, 0.243);
		if(angle > 0) {
			DriveSystem.getInstance().driveVelocity(-.35, .35);
		}
		else if(angle < 0) {
			DriveSystem.getInstance().driveVelocity(.35, -.35);
		}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Math.abs(DriveSystem.getInstance().getAngle()) >= Math.abs(angle);
	}
	public void end() {
		DriveSystem.getInstance().resetAngle();
		DriveSystem.getInstance().setPosition(0);
		DriveSystem.getInstance().stop();
	}
}
