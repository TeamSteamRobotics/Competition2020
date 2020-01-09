/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
  WPI_TalonSRX rightShooter = new WPI_TalonSRX(5);
  WPI_TalonSRX leftShooter = new WPI_TalonSRX(4);
  public ShooterSubsystem() {

  }
  public void shoot(){
    rightShooter.set(.5);
    leftShooter.set(-.5);
  }
  public void stopShooter(){
    rightShooter.set(0);
    leftShooter.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
