/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

  WPI_TalonSRX left1 = new WPI_TalonSRX(0);
  WPI_VictorSPX right1 = new WPI_VictorSPX(1);
  WPI_TalonSRX left2 = new WPI_TalonSRX(2);
  WPI_VictorSPX right2 = new WPI_VictorSPX(3);

  SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);
  SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);

  DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public void drive(double speed, double rotation){
    diffDrive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    
  }
}
