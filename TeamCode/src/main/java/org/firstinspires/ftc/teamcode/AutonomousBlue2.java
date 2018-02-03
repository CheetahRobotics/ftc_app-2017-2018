package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name = "AutonomousBlue2", group = "Linear Opmode")
public class AutonomousBlue2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Servo sensor_arm;
    double sensor_armPower;

    @Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        sensor_arm = hardwareMap.get(Servo.class, "arm_1");

        Driver driver = new Driver(hardwareMap, telemetry);
        AllyBallEliminator allyBallEliminator = new AllyBallEliminator(hardwareMap, telemetry, driver.leftDrive,true);
        double s = runtime.seconds();
        int state = 0;

        waitForStart();
        runtime.reset();
        double power = 0;

        sensor_armPower = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            s = runtime.seconds();

            if (s < 2) {
                state = 0;
                sensor_armPower = .3;//drop arm
            } else if (s < 4) {
                state = 1;
                driver.driveStraight(power/driver.scaleFactor);
                power = allyBallEliminator.checkSensor();
            } else if (s < 4.5) {
                state = 2;
                sensor_armPower = 1;//lift arm
                driver.driveStraight(1.0);
            } else if (s < 5.5) {
                state = 3;
                driver.turnRight(1.0);
            } else if (s < 6.25) {
                state = 4;
                driver.driveStraight(1.0);
            }
            else {
                driver.stop();
            }

            sensor_arm.setPosition(sensor_armPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("state", state);
            telemetry.addData("power", power);

            telemetry.update();

        }
    }
}


