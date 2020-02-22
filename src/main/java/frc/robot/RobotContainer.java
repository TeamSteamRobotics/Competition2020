/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.Intake;
import frc.robot.commands.ManualShoot;
import frc.robot.commands.MoveToIntake;
import frc.robot.commands.MoveToShooter;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinIntake;
import frc.robot.commands.VisionDrive;
import frc.robot.commands.VisionLineUp;
import frc.robot.commands.VisionTurn;
import frc.robot.commands.VisionTurn2;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.EpicAutonomous;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.HoldPosition;
import frc.robot.subsystems.BallTrackingSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static edu.wpi.first.wpilibj2.command.CommandGroupBase.sequence;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  
	//private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final HopperSubsystem m_feederSubsystem = new HopperSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  //private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final BallTrackingSubsystem m_ballTrackingSubsystem = new BallTrackingSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  


  Joystick stick = new Joystick(0);

  JoystickButton aimButton = new JoystickButton(stick, 1);
  JoystickButton testShoot = new JoystickButton(stick, 2);

  XboxController xboxController = new XboxController(1);

  TriggerButton shootButton = new TriggerButton(xboxController, Hand.kRight);
  TriggerButton intakeButton = new TriggerButton(xboxController, Hand.kLeft);
  JoystickButton spinUpButton = new JoystickButton(xboxController, 6);
  //JoystickButton climbDownButton = new JoystickButton(stick, 3 );
  //JoystickButton climbUpButton = new JoystickButton(xboxController, 5);

  //manual overrides
  POVButton manualIntakeButton = new POVButton(xboxController, 270);
  POVButton moveToShooterButton = new POVButton(xboxController, 0);
  POVButton moveToIntakeButton = new POVButton(xboxController, 180);
  POVButton manualShootButton = new POVButton(xboxController, 90);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //NetworkTableEntry speed = Shuffleboard.getTab("Tab").add("Shooter Speed", 0).getEntry();
    // Configure the button bindings
    m_driveSubsystem.loadMusic("RickRoll.chrp");
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(new Drive(m_driveSubsystem, stick::getY, stick::getX));
    //m_shooterSubsystem.setDefaultCommand(new RunCommand(() -> m_shooterSubsystem.movePID(speed.getDouble(0)), m_shooterSubsystem));
    //m_feederSubsystem.setDefaultCommand(new RunCommand(() -> m_feederSubsystem.move(stick.getY()), m_feederSubsystem));
    //m_intakeSubsystem.setDefaultCommand(new RunCommand(() -> m_intakeSubsystem.setSpeed(-stick.getY()), m_intakeSubsystem));
    //m_climbSubsystem.setDefaultCommand(new RunCommand(()->m_climbSubsystem.setSpeed(xboxController.getY(Hand.kLeft)), m_climbSubsystem));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    testShoot.whileHeld(new MoveToShooter(m_feederSubsystem));//.alongWith(new MoveToShooter(m_feederSubsystem)));
    aimButton.whileHeld(new VisionTurn(m_driveSubsystem, m_visionSubsystem));//new VisionTurn(m_driveSubsystem, m_visionSubsystem));
    //aimButton.whileHeld(new StartEndCommand(() -> m_driveSubsystem.playMusic(), () -> {}, m_driveSubsystem));
    //aimButton.whileHeld(new StartEndCommand(() -> m_driveSubsystem.playTone(880 + 440 * stick.getThrottle()), () -> m_driveSubsystem.stop(), m_driveSubsystem));
    shootButton.whileHeld(new Shoot(m_shooterSubsystem, m_feederSubsystem, m_ballTrackingSubsystem, m_intakeSubsystem));
    intakeButton.whileHeld(new ConditionalCommand(
      new WaitCommand(0), 
      new Intake(m_intakeSubsystem, m_feederSubsystem, m_ballTrackingSubsystem)
        .raceWith(new StartEndCommand(
          () -> xboxController.setRumble(RumbleType.kRightRumble, 1), 
          () -> xboxController.setRumble(RumbleType.kRightRumble, 0)
        )),
      m_ballTrackingSubsystem::isHopperFull
    ));
    spinUpButton.toggleWhenActive(new ManualShoot(m_shooterSubsystem));
    spinUpButton.toggleWhenActive(new StartEndCommand(
      () -> xboxController.setRumble(RumbleType.kLeftRumble, 1), 
      () -> xboxController.setRumble(RumbleType.kLeftRumble, 0)
    ));

    //manual overrides
    //moveToIntakeButton.whileHeld(new MoveToIntake(m_feederSubsystem));
    //moveToShooterButton.whileHeld(new MoveToShooter(m_feederSubsystem));
    //manualIntakeButton.whileHeld(new SpinIntake(m_intakeSubsystem));
    //manualShootButton.whileHeld(new ManualShoot(m_shooterSubsystem));

    //aimButton.whileHeld(new VisionLineUp(m_visionSubsystem, m_driveSubsystem));
    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return new EpicAutonomous(m_driveSubsystem, null, null, null, null, null);
    return new EpicAutonomous(m_driveSubsystem, null, null, null, null, null);
    //return null;
  }
}
