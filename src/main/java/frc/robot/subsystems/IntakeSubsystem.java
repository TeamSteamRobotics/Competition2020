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
import static frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  WPI_TalonSRX lowerIntakeMotor = new WPI_TalonSRX(IntakeConstants.lowerIntakeID);
  WPI_TalonSRX upperIntakeMotor = new WPI_TalonSRX(IntakeConstants.upperIntakeID);
  //WPI_TalonSRX intakeDeployer = new WPI_TalonSRX(IntakeConstants.deployMotorID);

  SpeedControllerGroup intakeMotors = new SpeedControllerGroup(lowerIntakeMotor, upperIntakeMotor);

  public void intake(){
   intakeMotors.set(.7);
  }

  public void deployIntake(){
    //intakeDeployer.set(.5);
  }

  public void stopIntake(){
    intakeMotors.set(0);
  }
  public void setSpeed(double speed){
    intakeMotors.set(speed);
  }

  public void holdDeployment() {
    //intakeDeployer.set(.3);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
