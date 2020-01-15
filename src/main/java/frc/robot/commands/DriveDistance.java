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

import static frc.robot.Constants.drivePID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveDistance extends PIDCommand {
  /**
   * Creates a new DriveDistance.
   */
  double startAngle;

  public DriveDistance(DriveSubsystem drivetrain, double distance) {
    super(
        // The controller that the command will use
        new PIDController(drivePID.kP, drivePID.kI, drivePID.kD),
        // This should return the measurement
        drivetrain::getDistance,
        // This should return the setpoint (can also be a constant)
        drivetrain.getDistance() + distance,
        // This uses the output
        output -> {
          drivetrain.drive(output, 0);
        });
    
    //drivetrain.resetEncoders();
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}