package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.SwingArmSystem;

import static frc.robot.subsystems.SwingArmSystem.*;

public class AutoLift extends Command{
    public AutoLift() {
        requires(SwingArmSystem.getInstance());
    }
    public void execute() {
        if(IntakeSystem.getInstance().hasCube()) {
            getInstance().setAngle(15);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
