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
import static frc.robot.Constants.DriveConstants.VisionDrivePID;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class VisionDrive extends PIDCommand {
  /**
   * Creates a new VisionDrive.
   */
  public VisionDrive(DriveSubsystem drive, VisionSubsystem vision) {
    super(
        // The controller that the command will use
        new PIDController(VisionDrivePID.kP, VisionDrivePID.kI, VisionDrivePID.kD),
        // This should return the measurment
        vision::getTargetDistance,
        // This should return the setpoint (can also be a constant)
        VisionDrivePID.shootDistance,
        // This uses the output
        output -> {
          drive.drive(output, 0);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(VisionDrivePID.posTolerance, VisionDrivePID.velTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
