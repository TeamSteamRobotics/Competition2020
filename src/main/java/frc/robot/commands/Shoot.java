/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends CommandBase {
  /**
   * Creates a new 
   */

  ShooterSubsystem shooter;
  HopperSubsystem hopper;
  public Shoot(ShooterSubsystem shooter, HopperSubsystem hopper) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.hopper = hopper;
    this.shooter = shooter;
    addRequirements(shooter, hopper);
    
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.shoot();
    hopper.moveToShooter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    shooter.stopShooter();
    hopper.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
