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
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

  AHRS gyro = new AHRS();

  WPI_TalonSRX left1 = new WPI_TalonSRX(0);
  WPI_TalonSRX right1 = new WPI_TalonSRX(1);
  WPI_VictorSPX left2 = new WPI_VictorSPX(0);
  WPI_VictorSPX right2 = new WPI_VictorSPX(1);

  SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);
  SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);

  DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public DriveSubsystem(){
    left1.setSensorPhase(true);
    right1.setSensorPhase(false);
  }
  @Override
  public void periodic() {
    System.out.println(getAngle());
  }

  public void drive(double speed, double rotation, boolean squareInputs) {
    diffDrive.arcadeDrive(speed, rotation, squareInputs);
  }

  public double getAngle(){
    return gyro.getAngle();
  }

  public void resetGyro() {
    gyro.zeroYaw();
  }


  public double getDistance(){
    return (left1.getSelectedSensorPosition() + right1.getSelectedSensorPosition()) / 2.0;
  }

  public void resetEncoders(){
    left1.setSelectedSensorPosition(0);
    right1.setSelectedSensorPosition(0);
  }

  public void autoDrive(double forward, double turn){
    forward += Math.copySign(.15, forward);
    turn += Math.copySign(.15, turn);
    drive(forward, turn, false);
  }

  public void keepGoing(){
    left.set(left.get());
    right.set(right.get());
  }
}
