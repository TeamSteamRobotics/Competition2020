/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.TrackerConstants;

public class BallTrackingSubsystem extends SubsystemBase {
  /**
   * Creates a new BallTrackingSubsystem.
   */
  DigitalInput topTracker = new DigitalInput(TrackerConstants.topSensorPort);
  DigitalInput bottomTracker = new DigitalInput(TrackerConstants.bottomSensorPort);
  DigitalInput intakeTracker = new DigitalInput(TrackerConstants.intakeSensorPort);
    


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean isHopperFull() {
    return isBallAtTop() && isBallInIntake();  
  }

  public boolean isBallAtTop(){
    return topTracker.get();
  }

  public boolean isBallAtBottom(){
    return bottomTracker.get();
  }

  public boolean isBallInIntake(){
    return intakeTracker.get();
  }
}
