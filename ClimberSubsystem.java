// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
  private final SparkMax m_climberLeader;
  private final SparkMax m_climberFollower;
  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    m_climberLeader = new SparkMax(ClimberConstants.kClimberLeaderCanID, MotorType.kBrushless);
    m_climberFollower = new SparkMax(ClimberConstants.kClimberFollowerCanID, MotorType.kBrushless);

    SparkMaxConfig globalConfig = new SparkMaxConfig();

    SparkMaxConfig m_climberLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig m_climberFollowerConfig = new SparkMaxConfig();

    globalConfig
      .smartCurrentLimit(50)
      .idleMode(IdleMode.kBrake);

    m_climberLeaderConfig
      .apply(globalConfig);

    m_climberFollowerConfig
      .apply(globalConfig)
      .follow(m_climberLeader)
      .inverted(true);

    m_climberLeaderConfig.encoder.positionConversionFactor(1000);

    m_climberLeaderConfig.encoder.velocityConversionFactor(1000);

    m_climberLeaderConfig.signals.primaryEncoderPositionPeriodMs(5); 
    
    m_climberLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_climberFollower.configure(m_climberFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void Climb() {
    m_climberLeader.set(ClimberConstants.kClimberFollower);

  }

  public void Drop() {
    m_climberLeader.set(ClimberConstants.kClimberLeaderDown);

  }

  public void StopClimber() {
    m_climberLeader.set(ClimberConstants.kClimberLeaderStop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Climber Leader Power", m_climberLeader.getAppliedOutput());
    SmartDashboard.putNumber("Climber Follower Power", m_climberFollower.getAppliedOutput());
  }
}
