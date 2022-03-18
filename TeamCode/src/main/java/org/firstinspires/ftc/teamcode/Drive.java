package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utilities.ControllerInput;

@TeleOp
public class Drive extends LinearOpMode {

    Hardware robot;
    ControllerInput controller;

    @Override
    public void runOpMode() throws InterruptedException {
        controller = new ControllerInput(gamepad1);

        robot = new Hardware();
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            controller.update();

            double leftStickY = controller.left_stick_y;
            double leftStickX = controller.left_stick_x;
            double rightStickX = controller.right_stick_x;

            //TODO Miscare aici


        }
    }
}
