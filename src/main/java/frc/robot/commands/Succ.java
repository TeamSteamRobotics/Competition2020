/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class Succ extends CommandBase {
  /**
   * Creates a new IntakeCommand.
   */
  IntakeSubsystem intaker;
  public Succ(IntakeSubsystem intaker) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intaker = intaker;
    addRequirements(intaker);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intaker.intake(); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intaker.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}