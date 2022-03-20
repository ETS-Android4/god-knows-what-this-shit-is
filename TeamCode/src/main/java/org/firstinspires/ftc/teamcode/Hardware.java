package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;

public class Hardware {
    public DcMotorEx frontLeftWheel = null;
    public DcMotorEx frontRightWheel = null;
    public DcMotorEx backLeftWheel = null;
    public DcMotorEx backRightWheel = null;
//    public BNO055IMU imu = null; // sensor care ajuta la masurarea unghiului si acceleratiei
    // de obicei folosit doar ptr unghi
    public Rev2mDistanceSensor rangeSensor = null; //sensor de distanta
    // nu e foarte precis, doar pe suprafete plane puse paralel cu robotul are precizie buna

    public void init(HardwareMap hwMap) {
//        imu = hwMap.get(BNO055IMU.class, "imu");
        rangeSensor = hwMap.get(Rev2mDistanceSensor.class, "distance");

        //-----------------------------Wheels------------------------------------------

        frontLeftWheel = hwMap.get(DcMotorEx.class, "FL");
        frontRightWheel = hwMap.get(DcMotorEx.class, "FR");
        backLeftWheel = hwMap.get(DcMotorEx.class, "BL");
        backRightWheel = hwMap.get(DcMotorEx.class, "BR");

        frontLeftWheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontRightWheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backLeftWheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backRightWheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftWheel.setDirection(DcMotorEx.Direction.FORWARD);
        frontRightWheel.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftWheel.setDirection(DcMotorEx.Direction.FORWARD);
        backRightWheel.setDirection(DcMotorEx.Direction.REVERSE);

        frontLeftWheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRightWheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeftWheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRightWheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        frontLeftWheel.setPower(0);
        frontRightWheel.setPower(0);
        backLeftWheel.setPower(0);
        backRightWheel.setPower(0);
    }


}
