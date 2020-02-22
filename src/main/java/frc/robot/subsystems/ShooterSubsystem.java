/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ShooterConstants;

import java.util.TreeMap;
import java.util.Map.Entry;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
  WPI_TalonSRX shooterMaster = new WPI_TalonSRX(ShooterConstants.masterID);
  WPI_TalonSRX shooterFollower = new WPI_TalonSRX(ShooterConstants.followerID);

  double targetSpeed = 0;

  TreeMap<Double, Double> shooterSpeeds = new TreeMap<>(); //distance(meters), speed(STUsPerDecisecond)
  
  public ShooterSubsystem() {
    shooterFollower.setInverted(InvertType.OpposeMaster);
    shooterFollower.set(ControlMode.Follower, ShooterConstants.masterID);
    shooterMaster.setNeutralMode(NeutralMode.Coast);
    shooterFollower.setNeutralMode(NeutralMode.Coast);

    shooterMaster.setSensorPhase(true);

    shooterMaster.selectProfileSlot(0, 0);

    shooterMaster.config_kP(0, .03);
    shooterMaster.config_kI(0, 0);
    shooterMaster.config_kD(0, 0);
    shooterMaster.config_kF(0, 0.01760006);

    Shuffleboard.getTab("driverInfo").addBoolean("shooter at speed", this::isAtSpeed);

    populateDistanceTable();
  }

  public void populateDistanceTable(){
    shooterSpeeds.put(7.5, -36000d);
    shooterSpeeds.put(5.18, -32500d);
    shooterSpeeds.put(3.86, -31100d);
    shooterSpeeds.put(9.08, -39800d);
    shooterSpeeds.put(8.07, -38000d);
    shooterSpeeds.put(7.37, -36500d);

  }
  public void shoot(){
    shooterMaster.set(-.75);
  }
  public void stopShooter(){
    shooterMaster.set(0);
    targetSpeed = 0;
  }

  public void move(double speed){
    shooterMaster.set(speed);
  }

  public void movePID(double STUsPerDecisecond){
    targetSpeed = STUsPerDecisecond;
    shooterMaster.set(ControlMode.Velocity, STUsPerDecisecond);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(isAtSpeed());
  }

  public boolean isAtSpeed(){
    return Math.abs(shooterMaster.getClosedLoopError()) < ShooterConstants.tolerance;
  }

  public double getTargetSpeed(double distance){
    //get the shooter speeds for the two distances closest to the one provided.
    Entry<Double, Double> p1 = shooterSpeeds.floorEntry(distance);
    Entry<Double, Double> p2 = shooterSpeeds.ceilingEntry(distance);

    //linearly interpolate the two points to get an approximate speed for the shooter.
    return (p2.getValue() - p1.getValue())/(p2.getKey() - p1.getKey())
                * (distance - p1.getKey()) + p1.getValue();
    //(a,b), (c,d)
    //y = (d-b)/(c-a) * (x-a) + b
  }
}
