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
import frc.robot.Constants.FeederConstants;

public class FeederSubsystem extends SubsystemBase {
  private final SparkMax m_feederMotor;
  /** Creates a new FeederSubsystem. */
  public FeederSubsystem() {
    m_feederMotor = new SparkMax(FeederConstants.kFeederMotorCanID, MotorType.kBrushless);

    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig feederMotorConfig = new SparkMaxConfig();

    globalConfig
      .smartCurrentLimit(50).idleMode(IdleMode.kCoast);

    feederMotorConfig
      .apply(globalConfig);

    m_feederMotor
      .configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void Feed() {
    m_feederMotor.set(FeederConstants.kFeederMotor);

  }

  public void ClearFeeder() {
    m_feederMotor.set(FeederConstants.kFeederMotorClear);

  }

  public void StopFeeder() {
    m_feederMotor.set(FeederConstants.kFeederMotorStop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Feeder Power", m_feederMotor.getAppliedOutput());
  }
}
