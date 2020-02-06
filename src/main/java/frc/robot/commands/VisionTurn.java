/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

import static frc.robot.Constants.DriveConstants.VisionTurnPID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class VisionTurn extends PIDCommand {
  /**
   * Creates a new GyroTurn2.
   */
  private boolean runExecute = true;

  public VisionTurn(DriveSubsystem drive, VisionSubsystem vision) {
    super(
        // The controller that the command will use
        new PIDController(VisionTurnPID.kP, VisionTurnPID.kI, VisionTurnPID.kD, .04),
        // This should return the measurement
        () -> -vision.getTargetX(),
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> {
          drive.autoDrive(0, output);
        });

    addRequirements(drive, vision);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(VisionTurnPID.posTolerance, VisionTurnPID.velTolerance);
  }


  @Override
  public void execute() {
    // TODO Auto-generated method stub
    if(runExecute){
      super.execute();
    }

    runExecute = !runExecute;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
