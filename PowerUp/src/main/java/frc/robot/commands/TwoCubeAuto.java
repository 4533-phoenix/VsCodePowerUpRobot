package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoCubeAuto extends CommandGroup {

        public TwoCubeAuto(String gameData) {
            //Drive forward 10 in
            this.addSequential(new DriveDistance(10));
            //Turn based on switch side
            if(gameData.substring(0,1).equals("L")) {
                this.addSequential(new AngleTurn(-(90-66.03)));
            }
            else {
                this.addSequential(new AngleTurn(90-66.03));
            }
            //Drive to switch
            this.addSequential(new DriveDistance(85));
            if(gameData.substring(0,1).equals("L")) {
                this.addSequential(new AngleTurn((90-75)));
            }
            else {
                this.addSequential(new AngleTurn(-(90-75)));
            }
            //Release cube
            this.addSequential(new DriveDistance(10), .5);
            this.addSequential(new PushOut(.35),.75);

            this.addSequential(new DriveDistance(-10),.5);
            //Turn to cube stack
            if(gameData.substring(0,1).equals("L")) {
                this.addSequential(new AngleTurn((45)));
            }
            else {
                this.addSequential(new AngleTurn(-(45)));
            }
            //Intake cube
            this.addSequential(new TakeIn(), .5);

            //Turn back to switch
            if(gameData.substring(0,1).equals("L")) {
                this.addSequential(new AngleTurn(-(45)));
            }
            else {
                this.addSequential(new AngleTurn((45)));
            }
            this.addSequential(new DriveDistance(10), .5);
            //Shoot cube
            this.addSequential(new PushOut(.35),.75);
            //Turn back to 0
//        if(gameData.substring(0,1).equals("L")) {
//            this.addSequential(new AngleTurn((-90)));
//        }
//        else {
//            this.addSequential(new AngleTurn(90));
//        }
//        //Drive Forward
//        this.addSequential(new DriveDistance(50));
//        //Turn 90
//        if(gameData.substring(0,1).equals("L")) {
//            this.addSequential(new AngleTurn(90));
//        }
//        else {
//            this.addSequential(new AngleTurn(-90));
//        }
//        //Drive Forward
//        this.addSequential(new DriveDistance(86));
//        //Turn 90
//        if(gameData.substring(0,1).equals("L")) {
//            this.addSequential(new AngleTurn(90));
//        }
//        else {
//            this.addSequential(new AngleTurn(-90));
//        }
//        this.addSequential(new MoveSwingArm(5));
        }

}
