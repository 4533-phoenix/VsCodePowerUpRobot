package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.IntakeSystem;
/**
 * Intakes the cube.
 * @author 4533 Programming Team
 *
 */
public class TakeIn extends Command {


	public TakeIn(){
		requires(IntakeSystem.getInstance());
	}

	public void execute(){
		IntakeSystem.getInstance().in();
	}

	public void end() {
		IntakeSystem.getInstance().stop();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
