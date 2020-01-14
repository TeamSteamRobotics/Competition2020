/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallTrackingSubsystem extends SubsystemBase {
  /**
   * Creates a new BallTrackingSubsystem.
   */
    DigitalInput topTracker = new DigitalInput(1);
    DigitalInput bottomTracker = new DigitalInput(2);
    DigitalInput intakeTracker = new DigitalInput(3);
    


  public BallTrackingSubsystem(){

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
