/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

  AHRS gyro = new AHRS();

  WPI_TalonFX left1 = new WPI_TalonFX(0);
  WPI_TalonFX right1 = new WPI_TalonFX(1);
  WPI_TalonFX left2 = new WPI_TalonFX(2);
  WPI_TalonFX right2 = new WPI_TalonFX(3);

  SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);
  SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);

  DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public void drive(double speed, double rotation){
    diffDrive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    
  }

  public double getAngle(){
    return gyro.getAngle();
  }
}
