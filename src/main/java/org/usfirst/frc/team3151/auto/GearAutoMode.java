package org.usfirst.frc.team3151.auto;

import org.usfirst.frc.team3151.RobotConstants;
import org.usfirst.frc.team3151.auto.action.*;
import org.usfirst.frc.team3151.subsystem.DriveTrain;
import org.usfirst.frc.team3151.subsystem.GearFlipper;
import org.usfirst.frc.team3151.subsystem.Ultrasonic;

public final class GearAutoMode extends ActionBasedAutoMode {

    private final DriveTrain driveTrain;
    private final GearFlipper gearFlipper;
    private final Ultrasonic ultrasonic;
    private final int driverStation;

    public GearAutoMode(DriveTrain driveTrain, GearFlipper gearFlipper, Ultrasonic ultrasonic, int driverStation) {
        this.driveTrain = driveTrain;
        this.gearFlipper = gearFlipper;
        this.ultrasonic = ultrasonic;
        this.driverStation = driverStation;
    }

    @Override
    public void init() {
        resetAutoActions();
        driveTrain.disableAutoTurn();
        int angle = driverStation == 2 ? 0 : (driverStation == 1 ? RobotConstants.GEAR_LEFT_ANGLE : RobotConstants.GEAR_RIGHT_ANGLE);

        registerAutoAction(new DriveToDistanceAutoAction(driveTrain, ultrasonic, 0.25, 0, driverStation == 2 ? 1.55 : 1.8));

        if (angle != 0) {
            registerAutoAction(new RotateToAngleAutoAction(driveTrain, angle));
        }

        registerAutoAction(new DriveToVisionTargetAutoAction(driveTrain));
        registerAutoAction(new FlipGearAutoAction(gearFlipper));
    }

}