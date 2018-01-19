package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AllyBallEliminator {
    ColorSensor colorSensor;
    Telemetry telemetry;
    Ringtone ringtone1;
    DcMotor leftMotor;
    int startPosition;

    public AllyBallEliminator(HardwareMap hardwareMap, Telemetry telemetry, DcMotor leftMotor) {
        this.colorSensor = hardwareMap.colorSensor.get("sensor_color");;
        this.telemetry = telemetry;
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        ringtone1 = RingtoneManager.getRingtone(hardwareMap.appContext.getApplicationContext(), notification);
        this.leftMotor = leftMotor;
        this.startPosition = leftMotor.getCurrentPosition();
    }

    boolean nowImSure = false;
    double lastValueOfPower = 0;
    // returns the number of inches to move.
    // positive number means move forward, negative backwards.
    public double checkSensor()
    {
        int changeInPosition = 0;
        String message = "";
        double power;
        if (nowImSure) {
            changeInPosition =  Math.abs(leftMotor.getCurrentPosition() - startPosition);
            if (changeInPosition > 1250) {
                message = "zero power";
                power = 0;
            }
            else
                power = lastValueOfPower;
            telemetry.addData("ChangeInPosition", changeInPosition);
            telemetry.addData("AllyBallEliminator", message);
            return power;
        }
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // convert the RGB values to HSV values.
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        if (colorSensor.blue() >= 1) {
            nowImSure = true;
            message = "backwards full power";
            power = -1;
        } else if (colorSensor.red() >= 1) {
            nowImSure = true;
            message = "forward full power";
            power = 1;
            ringtone1.play();
        } else {
            message = "foward low power";
            power = .25;
        }
        lastValueOfPower = power;

       telemetry.addData("Clear", colorSensor.alpha());
       telemetry.addData("Red  ", colorSensor.red());
       telemetry.addData("Blue ", colorSensor.blue());
       telemetry.addData("Hue", hsvValues[0]);

        telemetry.addData("ChangeInPosition", changeInPosition);
        telemetry.addData("AllyBallEliminator", message);
        return power;
    }
}
