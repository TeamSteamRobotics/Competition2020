/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
  /**
   * Creates a new ClimbSubsygtem.
   */
  WPI_TalonSRX rightLifter = new WPI_TalonSRX(0);
  WPI_TalonSRX leftLifter = new WPI_TalonSRX(1);
  SpeedControllerGroup lifters = new SpeedControllerGroup(leftLifter, rightLifter);

  public void goUp(){
    lifters.set(.5); 
  } 

  public void goDown(){
    lifters.set(-.5);
  }

  public void stopClimb(){
    lifters.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
