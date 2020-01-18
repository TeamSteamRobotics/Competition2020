/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.FeederConstants;

public class HopperSubsystem extends SubsystemBase {
  /**
   * Creates a new FeederSystem.
   */
  WPI_TalonSRX feederMotor = new WPI_TalonSRX(FeederConstants.feederID1);
  WPI_TalonSRX feederMotor2 = new WPI_TalonSRX(FeederConstants.feederID2);

  public void feed(){
    feederMotor.set(.5);
    feederMotor2.set(.5);
  }
  public void reverseFeed(){     
    feederMotor.set(-.5);
    feederMotor2.set(-.5);
  }
  public void stopFeeder(){
    feederMotor.set(0);
    feederMotor2.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
