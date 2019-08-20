package frc.robot.utilities;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.SwingArmSystem;

/**
 * A class used to put all of smart dashboard values to smart dashboard
 * @author 4533 Programming Team
 */
public class SmartDashboardValues {
    private SmartDashboard smartDashboard;
    private static DriverStation driverStation;
    public SmartDashboardValues() {
//        smartDashboard = new SmartDashboard();
        driverStation = DriverStation.getInstance();

    }

    /**
     * Updates the values on smart dashboard
     */
    public void updateValues() {
        SmartDashboard.putString("Our Switch Side", driverStation.getGameSpecificMessage().substring(0,1));
        SmartDashboard.putString("Our Scale Side", driverStation.getGameSpecificMessage().substring(1,2));
        SmartDashboard.putString("Thier Switch", driverStation.getGameSpecificMessage().substring(2,3));

        SmartDashboard.putNumber("Robot Angle", DriveSystem.getInstance().getAngle());

        SmartDashboard.putNumber("Left Position", DriveSystem.getInstance().getLeftPosition());
        SmartDashboard.putNumber("Right Position", DriveSystem.getInstance().getRightPosition());

        SmartDashboard.putNumber("Left Velocity", DriveSystem.getInstance().getLeftVelocity());
        SmartDashboard.putNumber("Right Velocity", DriveSystem.getInstance().getRightVelocity());
        SmartDashboard.putNumber("Target L", DriveSystem.getInstance().getLTargetVelocity());
        SmartDashboard.putNumber("Target R", DriveSystem.getInstance().getRTargetVelocity());

        SmartDashboard.putNumber("Potentiometer", SwingArmSystem.getInstance().position());
        SmartDashboard.putNumber("Potentiometer Angle", SwingArmSystem.getInstance().angle());
        SmartDashboard.putData("Pid Stuff", SwingArmSystem.getInstance().getPIDController());

        SmartDashboard.putString("Event", driverStation.getEventName());
        SmartDashboard.putNumber("Driver Station Location", driverStation.getLocation());
        SmartDashboard.putNumber("Match Number", driverStation.getMatchNumber());
        SmartDashboard.putNumber("Match Time", driverStation.getMatchTime());
        SmartDashboard.putNumber("Replay Number", driverStation.getReplayNumber());
    }

}
