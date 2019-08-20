package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoVault;
import frc.robot.commands.Autonomous;
import frc.robot.commands.JamesAutonomous;
import frc.robot.commands.MiddleAuto;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.IntakeSystem;
import frc.robot.subsystems.SwingArmSystem;
import frc.robot.utilities.SmartDashboardValues;
import edu.wpi.first.wpilibj.CameraServer;
/**
 * The class that the robot runs through. It is the closest thing to a main method the program has.
 * @author 4533 Programming Team
 *
 */
public class Robot extends IterativeRobot {

	//public static Autonomous autonomousCommand;
	public static MiddleAuto MAuto;
	public static JamesAutonomous testAuto;
	public static Autonomous LRAuto;
	public static AutoVault VAuto;
	public static String gameData;
	public SmartDashboardValues smartDashboardValues;
	public SendableChooser<String> autoPositionChooser;
	public SendableChooser<String> autoObjectiveChooser;
	private CameraServer cameraServer;
	/**
	 * Method is called when the robot is first turned on
	 * Initializes all of the subsystems and OI.
	*/
	public void robotInit() {
		DriveSystem.initialize();
		IntakeSystem.initialize();
		SwingArmSystem.initialize();
		OI.initialize();
		DriveSystem.getInstance().resetAngle();
		smartDashboardValues = new SmartDashboardValues();
		autoPositionChooser = new SendableChooser<String>();
		autoObjectiveChooser = new SendableChooser<String>();
		autoPositionChooser.addDefault("Middle Position", "M");
		autoPositionChooser.addObject("Left Position", "L");
		autoPositionChooser.addObject("Right Position", "R");
		autoObjectiveChooser.addObject("Switch", "S");
		autoObjectiveChooser.addObject("Vault", "V");
		autoObjectiveChooser.addObject("Test","T");
		SmartDashboard.putData(autoPositionChooser);
		cameraServer = CameraServer.getInstance();
		cameraServer.startAutomaticCapture();
	}
	/**
	 * What is called when the robot first recognizes it is disabled
	 * 
	 */
	
	public void disabledInit() {

	}

	/**
	 * Called constantly while the robot is disabled
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * What is called when autonomous first starts
	 * 
	 */
	
	public void autonomousInit() {
        DriveSystem.getInstance().setPeakOutput(.5);
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        DriveSystem.getInstance().resetAngle();
        //autonomousCommand = new Autonomous(gameData, autoPositionChooser.getSelected());
        MAuto = new MiddleAuto(gameData);//"f10r23.97f85l15o0.35"
        testAuto = new JamesAutonomous(gameData);
        LRAuto = new Autonomous(gameData, autoPositionChooser.getSelected());
        VAuto = new AutoVault(autoPositionChooser.getSelected());
        DriveSystem.getInstance().setPIDFValues(0.1, 0.0001, 0, 0);
        DriveSystem.getInstance().setPosition(0);
        DriveSystem.getInstance().setRampRate(0);
        //autonomousCommand.start();
        //if (autoObjectiveChooser.getSelected().equals("S")) {
            if (autoPositionChooser.getSelected().equals("M")) {
                MAuto.start();
            }
            else if(autoPositionChooser.getSelected().equals("L") || autoPositionChooser.getSelected().equals("R")) {
                LRAuto.start();
            }
        //}
        //else if(autoObjectiveChooser.getSelected().equals("T")) {
            //testAuto.start();
        //}
        //else if(autoObjectiveChooser.getSelected().equals("V")) {
        	//VAuto.start();
		//}
    }

	/**
	 * Called constantly during the Autonomous Period
	 */
	
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		smartDashboardValues.updateValues();
	}

	/**
	 * What is called at the beginning of the Teleop Period
	 * 
	 *  F-Gain = ( 100% * 1023 ) / MagneticEncoderVelocity
		MagneticEncoderVelocity determinted from TalonSRX self-test
	 	f = 1023 / 3168 = 0.323
	 */
	public void teleopInit() {
		if(MAuto != null) {
			MAuto.cancel();
		}
		// F-Gain = ( 100% * 1023 ) / MagneticEncoderVelocity
		//   MagneticEncoderVelocity determinted from TalonSRX self-test
		// f = 1023 / 3168 = 0.323
		if(RobotMap.PID_DRIVE_MODE) {
			//Was at P=.25 before i changed it
			DriveSystem.getInstance().setPIDFValues(.15, 0, 2.5, 0.243);
		}
		DriveSystem.getInstance().setPeakOutput(1);
		DriveSystem.getInstance().setRampRate(.25);
		
	}
	/**
	 * 
	 * Called constantly during Teleop
	 */
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		smartDashboardValues.updateValues();
	}
	
	
	/**
	 * 
	 * Called constantly when in Test mode
	 */
	public void testPeriodic() {

	}
}
