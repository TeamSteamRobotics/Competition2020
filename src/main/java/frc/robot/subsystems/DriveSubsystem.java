/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

  AHRS gyro = new AHRS();

  WPI_TalonFX left1 = new WPI_TalonFX(DriveConstants.leftID1);
  WPI_TalonFX right1 = new WPI_TalonFX(DriveConstants.rightID1);
  WPI_TalonFX left2 = new WPI_TalonFX(DriveConstants.leftID2);
  WPI_TalonFX right2 = new WPI_TalonFX(DriveConstants.rightID2);

  SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);
  SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);

  DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  @Override
  public void periodic() {
    
  }

  public void drive(double speed, double rotation) {
    diffDrive.arcadeDrive(speed, rotation);
  }

  public double getAngle(){
    return gyro.getAngle();
  }

  public double getDistance(){
    return (left1.getSelectedSensorPosition() + right1.getSelectedSensorPosition()) / 2.0;
  }

  public void resetEncoders(){
    left1.setSelectedSensorPosition(0);
    right1.setSelectedSensorPosition(0);
  }
}
