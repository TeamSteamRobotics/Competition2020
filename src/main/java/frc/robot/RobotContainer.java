/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Feeder;
import frc.robot.commands.Succ;
import frc.robot.commands.ReverseFeeder;
import frc.robot.commands.Shooter;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.DeployIntaker;
import frc.robot.commands.Drive;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
  private final FeederSubsystem m_feederSubsystem = new FeederSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();



  Joystick stick = new Joystick(0);
  JoystickButton shootButton = new JoystickButton(stick, 1);
  JoystickButton intakeButton = new JoystickButton(stick, 2);
  JoystickButton feedButton = new JoystickButton(stick, 5);
  JoystickButton reverseFeedButton = new JoystickButton(stick, 3);
  JoystickButton climbUpButton = new JoystickButton(stick, 6);
  JoystickButton climbDownButton = new JoystickButton(stick, 4);
  JoystickButton deployIntakerButton = new JoystickButton(stick, 7);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(new Drive(m_driveSubsystem, stick::getY, stick::getX));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    shootButton.whileHeld(new Shooter(m_shooterSubsystem));
    intakeButton.whileActiveOnce(new Succ(m_intakeSubsystem));
    feedButton.whileHeld(new Feeder(m_feederSubsystem));
    reverseFeedButton.whileHeld(new ReverseFeeder(m_feederSubsystem));
    climbUpButton.whileHeld(new ClimbUp(m_climbSubsystem));
    climbDownButton.whileHeld(new ClimbDown(m_climbSubsystem));
    deployIntakerButton.whileHeld(new DeployIntaker(m_intakeSubsystem));
      
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
    return null;
  }
}
