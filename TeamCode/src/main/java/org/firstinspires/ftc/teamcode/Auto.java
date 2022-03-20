package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import javax.xml.parsers.FactoryConfigurationError;

@Autonomous(name="Auto", group="Pushbot")
public class Auto extends LinearOpMode {

    Hardware robot = new Hardware();
    //elapsed time poate fi folosit ptr miscari conditionate de timp
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    //ElapsedTime.reset() reseteaza timerul
    //ElapsedTime.time() intoarce timpul masurat de la ultima resetare

    @Override
    public void runOpMode() throws InterruptedException{
        //InterruptedException ar trb pusa in declaratia functiei ptr ca functia waitForStart()
        //poate chema Thread.currentThread().interrupt() care produce o inrerupere a threadului actual
        robot.init(hardwareMap);

        //miscare in cerc la 90 grade
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
//        parameters.loggingEnabled      = true;
//        parameters.loggingTag          = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//        robot.imu.initialize(parameters);

        waitForStart();
//        final float initAngle = robot.imu.getAngularOrientation().firstAngle;
        //imu.getAngularOrientation().firstAngle intoarce unghiul in plan (cu minus)
//        final float acceleration = -0.05f;
//
//        float angleDifference = 0f, speed = 0.6f;
//
//        robot.frontRightWheel.setPower(speed);
//        robot.frontLeftWheel.setPower(-speed);
//        robot.backRightWheel.setPower(speed);
//        robot.backLeftWheel.setPower(-speed);
//
        //miscarea robotului este una decelerata care scade in trepte, odata la 10 grade
//        while(opModeIsActive() && (Math.abs(robot.imu.getAngularOrientation().firstAngle - initAngle) < 90f)){
//            if((-angleDifference + Math.abs(robot.imu.getAngularOrientation().firstAngle - initAngle) >= 10f)){
//                speed = speed + acceleration;  //acceleratia este negativa
//                angleDifference = Math.abs(robot.imu.getAngularOrientation().firstAngle - initAngle);
                  //angle difference tine minte unghiul la care s-a efectuat ultima modificare a vitezei (in modul)
//                robot.frontRightWheel.setPower(speed);
//                robot.frontLeftWheel.setPower(-speed);
//                robot.backRightWheel.setPower(speed);
//                robot.backLeftWheel.setPower(-speed);
//            }
//        }
//
//        robot.frontRightWheel.setPower(0f);
//        robot.frontLeftWheel.setPower(0f);
//        robot.backRightWheel.setPower(0f);
//        robot.backLeftWheel.setPower(0f);

        //mentinerea robotului la aprox 30 cm de o suprafata plana care se poate misca inspre el si dinspre el
        //la peste 80 cm distanta de orice suprafata, robotul trb sa stea pe loc
        //sensorul este plasat pe partea dreapta a robotului, deci trb utilizata miscarea strafe
        double dist = 0f, speed = 0f;
        final double coefficient = -0.15f;

        while(opModeIsActive()){
            dist = robot.rangeSensor.getDistance(DistanceUnit.CM);
            if(dist >= 80f){ //conditia ca robotul sa fie pe loc
                continue;
            }
            speed = (30f - dist) * coefficient; //viteza depinde de distanta, la distante mari are viteza mare
            //la distante mici una mica
            robot.frontLeftWheel.setPower(-speed);
            robot.frontRightWheel.setPower(speed);
            robot.backRightWheel.setPower(-speed);
            robot.backLeftWheel.setPower(speed);
        }
        robot.frontLeftWheel.setPower(0f);
        robot.frontRightWheel.setPower(0f);
        robot.backRightWheel.setPower(0f);
        robot.backLeftWheel.setPower(0f);
    }
}
