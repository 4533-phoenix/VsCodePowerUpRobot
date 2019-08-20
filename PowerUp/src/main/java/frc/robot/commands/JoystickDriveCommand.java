package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveSystem;

public class JoystickDriveCommand extends Command
{
    Joystick controller;
    private DriveSystem driveSystem = DriveSystem.getInstance();
    public JoystickDriveCommand() {
        controller = new Joystick(RobotMap.JOYSTICK_PORT);
        requires(driveSystem);
    }
    public void execute() {
        double forward = controller.getY();
        double side = controller.getX();
        if(controller.getRawButton(RobotMap.RIGHT_BUMPER)|| controller.getRawButton(RobotMap.RIGHT_TRIGGER)) {
            driveSystem.setVelocity(500);
        }
        else {
            driveSystem.setVelocity(300);
        }
        if (Math.abs(forward) <= .05) {
            forward = 0;
        }
        if (RobotMap.PID_DRIVE_MODE) {
            driveSystem.driveVelocityOneJoystick(forward, side);
        } else {
            driveSystem.driveAction(forward, side);
        }
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
