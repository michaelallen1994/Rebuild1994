// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DeployConstants;

public class DeploySubsystem extends SubsystemBase {
  private final SparkMax m_deployMotor;
  /** Creates a new DeploySubsystem. */
  public DeploySubsystem() {
    m_deployMotor = new SparkMax(DeployConstants.kDeployMotorCanID, MotorType.kBrushless);

    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig deployMotorConfig = new SparkMaxConfig();

    globalConfig
      .smartCurrentLimit(50).idleMode(IdleMode.kBrake);

    deployMotorConfig
      .apply(globalConfig);

    m_deployMotor
      .configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void Deploy() {
    m_deployMotor.set(DeployConstants.kDeployMotor);

  }

  public void Retract() {
    m_deployMotor.set(DeployConstants.kDeployMotorRetract);

  }

  public void StopDeploy() {
    m_deployMotor.set(DeployConstants.kDeployMotorStop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Deploy Power", m_deployMotor.getAppliedOutput());
  }
}
