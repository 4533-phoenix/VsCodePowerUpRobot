package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JamesAutonomous extends CommandGroup {

    public JamesAutonomous(String gameData) {

        //
        //    R    f10r23.97f85r15o0.35
        //    L    f10l23.97f85l15o0.35
        //

        String commandCode=gameData;
        String[] commandList={"f","r","l","u","d","o","i"};
        boolean notDone=true;
        while(notDone){
            int maxDist=1000000;
            int maxComm=-1;
            for(int i=0;i<commandList.length;i++){
                if(commandCode.indexOf(commandList[i])!=-1){
                    if(commandCode.indexOf(commandList[i])<maxDist){
                        maxDist=commandCode.indexOf(commandList[i]);
                        maxComm=i;
                    }
                }
            }
            if (maxComm == -1) {
                notDone = false;
            }
            int nexDist = 1000000;
            int nexComm = -1;
            for (int i = 0; i < commandList.length; i++) {
                if (commandCode.substring(1).indexOf(commandList[i]) != -1) {
                    if (commandCode.substring(1).indexOf(commandList[i]) < nexDist) {
                        nexDist = 1+commandCode.substring(1).indexOf(commandList[i]);
                        nexComm = i;
                    }
                }
            }
            if(nexComm==-1){
                if(maxComm==0){
                    this.addSequential(new DriveDistance(Double.parseDouble(commandCode.substring(1))));
                }
                if(maxComm==1){
                    this.addSequential(new AngleTurn(Double.parseDouble(commandCode.substring(1))));
                }
                if(maxComm==2){
                    this.addSequential(new AngleTurn(-Double.parseDouble(commandCode.substring(1))));
                }
                if(maxComm==3){
                    this.addSequential(new MoveSwingArm(Double.parseDouble(commandCode.substring(1))));
                }
                if(maxComm==4){
                    this.addSequential(new MoveSwingArm(-Double.parseDouble(commandCode.substring(1))));
                }
                if(maxComm==5){
                    this.addSequential(new PushOut(Double.parseDouble(commandCode.substring(1))),0.5);
                }
                if(maxComm==6){
                    this.addSequential(new PushOut(-Double.parseDouble(commandCode.substring(1))),0.5);
                }
                notDone = false;
            }else{
                if(maxComm==0){
                    this.addSequential(new DriveDistance(Double.parseDouble(commandCode.substring(1,nexDist))));
                }
                if(maxComm==1){
                    this.addSequential(new AngleTurn(Double.parseDouble(commandCode.substring(1,nexDist))));
                }
                if(maxComm==2){
                    this.addSequential(new AngleTurn(-Double.parseDouble(commandCode.substring(1,nexDist))));
                }
                if(maxComm==3){
                    this.addSequential(new MoveSwingArm(Double.parseDouble(commandCode.substring(1,nexDist))));
                }
                if(maxComm==4){
                    this.addSequential(new MoveSwingArm(-Double.parseDouble(commandCode.substring(1,nexDist))));
                }
                if(maxComm==5){
                    this.addSequential(new PushOut(Double.parseDouble(commandCode.substring(1,nexDist))),0.5);
                }
                if(maxComm==6){
                    this.addSequential(new PushOut(-Double.parseDouble(commandCode.substring(1,nexDist))),0.5);
                }
                commandCode=commandCode.substring(nexDist);
            }
        }
    }
}
