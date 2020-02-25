/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  /**
   * Creates a new VisionSubsystem.
   */
  NetworkTableInstance table = NetworkTableInstance.getDefault();
  NetworkTable visionTable = table.getTable("chameleon-vision").getSubTable("Microsoft LifeCam HD-3000");
  NetworkTableEntry poseEntry = visionTable.getEntry("targetPose");
  NetworkTableEntry yawEntry = visionTable.getEntry("targetYaw");
  int counter = 0;

  public VisionSubsystem(){
    Shuffleboard.getTab("driverInfo").addBoolean("aimedAtTarget", () -> Math.abs(getTargetX()) < .05);
  }

  public double getTargetDistance(){
    double[] defaultPose = {0, 0, 0};
    double[] pose = poseEntry.getDoubleArray(defaultPose);
    return Math.hypot(pose[0], pose[1]);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(getTargetX());
    counter = (counter + 1) % 10;

    double[] defaultPose = {0, 0, 0};
    if(counter == 2){
      double[] pose = poseEntry.getDoubleArray(defaultPose);
      //System.out.println(pose[0]+", "+pose[1]+", "+pose[2]);
    }
    //System.out.println(table.getTable("chameleon-vision").getSubTables());
  }

  /**
   * returns the scaled x position of the target in the interval [-1,1].
   */
  public double getTargetX(){
    return yawEntry.getDouble(0) / 30.0;
  }

  public double getTargetAngle(){
    return yawEntry.getDouble(0);
  }
  public double[] getCoordinates(){
    double[] pose = poseEntry.getDoubleArray(new double[3]);
    return Arrays.copyOfRange(pose, 0, 2);

  }
}
