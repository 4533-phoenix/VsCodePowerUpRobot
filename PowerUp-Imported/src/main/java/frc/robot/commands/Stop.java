package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.SwingArmSystem;

/**
 * Stops the robot from moving.
 * @author 4533 Programming Team
 *
 */
public class Stop extends Command {

	public Stop() {
		
	}
	public void execute() {
		DriveSystem.getInstance().stop();
		IntakeSystem.getInstance().stop();
		SwingArmSystem.getInstance().stop();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
