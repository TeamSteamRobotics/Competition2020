/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int leftID1 = 0;
        public static final int leftID2 = 1;
        public static final int rightID1 = 2;
        public static final int rightID2 = 3;

        public static final class GyroTurnPID {
            public static final double kP = .5 / 90;
            public static final double kI = 0;
            public static final double kD = 0;
        }

        public static final class VisionTurnPID {
            public static final double kP = .5;
            public static final double kI = 0;
            public static final double kD = 0;
        }

        public static final class EncDrivePID {
            public static final double kP = 0;
            public static final double kI = 0;
            public static final double kD = 0;
        }
    }

    public static final class ShooterConstants {
        public static final int shooterID1 = 4;
        public static final int shooterID2 = 5;
    }

    public static final class FeederConstants {
        public static final int feederID1 = 6;
        public static final int feederID2 = 7;
    }

    public static final class IntakeConstants {
        public static final int lowerIntakeID = 8;
        public static final int upperIntakeID = 9;
        public static final int deployMotorID = 10;
    }

    public static final class ClimbConstants {
        public static final int leftClimberID = 11;
        public static final int rightClimberID = 12;
    }

    public static final class TrackerConstants {
        public static final int topSensorPort = 1;
        public static final int bottomSensorPort = 2;
        public static final int intakeSensorPort = 3;
    }
}
