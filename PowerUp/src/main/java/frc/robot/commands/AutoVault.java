package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoVault extends CommandGroup{
    public AutoVault(String position) {
        if(position.equals("L")) {
            this.addSequential(new AngleTurn(90));
        }
        else {
            this.addSequential(new AngleTurn(-90));
        }
        this.addSequential(new DriveDistance(100));
        this.addSequential(new MoveSwingArm(5));
        this.addSequential(new PushOut(.15),1);
        if(position.equals("L")) {
            this.addSequential(new AngleTurn(-135));
        }
        else {
            this.addSequential(new AngleTurn(135));
        }
        this.addSequential(new DriveDistance(150));
    }
}
