package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Utilities.ControllerInput;

@TeleOp(name = "something")
public class Drive extends LinearOpMode {

    Hardware robot;
    ControllerInput controller;

    @Override
    public void runOpMode() throws InterruptedException {
        controller = new ControllerInput(gamepad1);

        robot = new Hardware();
        robot.init(hardwareMap);

        //asta e partea de calibrare si initializare a senzorului imu
        //efectiv dai copy paste si eventual schimbi grade in radiani
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
//        parameters.loggingEnabled      = true;
//        parameters.loggingTag          = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

//        robot.imu.initialize(parameters);

        waitForStart();
        double coefficient = 0.7f;

        while (opModeIsActive()) {
            controller.update();

            double leftStickY = -controller.left_stick_y; //leftStickY - fwd/back
            double leftStickX = controller.left_stick_x; //leftStickX - strafe
            //strafe e o miscare in stanga/dreapta produsa prin miscarea catre interior a rotilor
            //din directia in care vrem sa ne indreptam si miscarea catre exterior a celorlaltor 2

            double rightStickX = controller.right_stick_x; //rightStickY - turn

            boolean leftBumper = controller.leftBumperOnce(), rightBumper = controller.rightBumperOnce();
            //bumperele fac robotul sa se miste mai repede/incet

            if(leftBumper){
                coefficient -= 0.08f;
            }
            if(rightBumper){
                coefficient += 0.08f;
            }
            coefficient = Range.clip(coefficient, 0.3, 1);
            //Range.clip(double, double, double) taie primul numar a.i. sa nu depaseasca valorile min si max pasate
            //ca argumente in continuarea acestuia

            //telemetry afiseaza mesaje pe ecanul telefonului ce controleaza robotul
            //telemetry.addData(String, Object) nu e nevoie sa apelam noi functia toString a obiectului pe care
            //il pasam ca al 2lea argument
            telemetry.addData("distance in mm", robot.rangeSensor.getDistance(DistanceUnit.MM));
            telemetry.update(); //trebuie apelatat ptr a modifica mesajul

            //daca se folosesc strafe si turn in acelasi timp (din controller) se produce o miscare diagonala
            robot.frontLeftWheel.setPower(Range.clip(coefficient * (leftStickY + rightStickX + leftStickX), -1, 1));
            robot.frontRightWheel.setPower(Range.clip(coefficient * (leftStickY - rightStickX  - leftStickX), -1, 1));
            robot.backLeftWheel.setPower(Range.clip(coefficient * (leftStickY + rightStickX - leftStickX), -1, 1));
            robot.backRightWheel.setPower(Range.clip(coefficient * (leftStickY - rightStickX + leftStickX), -1, 1));
        }
    }
}
