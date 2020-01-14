package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.SwingArmSystem;
/**
 * The command that moves the swing arm up or down.
 * @author 4533 Programming Team
 *
 */
public class MoveSwingArm extends Command {

	double targetAngle;
	
	
	public MoveSwingArm(double angle){
		requires(SwingArmSystem.getInstance());
		targetAngle = angle;
	}

	protected void initialize() {
		SwingArmSystem.getInstance().enable();
	}
	
	public void execute() {
		SwingArmSystem.getInstance().setAngle(targetAngle);
	}

	public void end() {
		SwingArmSystem.getInstance().disable();
		SwingArmSystem.getInstance().stop();
	}
	
	public void interrupted() {
		end();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
