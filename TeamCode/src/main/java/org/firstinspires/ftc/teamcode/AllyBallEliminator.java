package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AllyBallEliminator {
    ColorSensor colorSensor;
    Telemetry telemetry;
    Ringtone ringtone1;

    public AllyBallEliminator(HardwareMap hardwareMap, Telemetry telemetry) {
        this.colorSensor = hardwareMap.colorSensor.get("sensor_color");;
        this.telemetry = telemetry;
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        ringtone1 = RingtoneManager.getRingtone(hardwareMap.appContext.getApplicationContext(), notification);

    }

    // returns the number of inches to move.
    // positive number means move forward, negative backwards.
    public double checkSensor()
    {

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // convert the RGB values to HSV values.
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        String message = "";
        double power;
        if (colorSensor.blue() >= 1) {
            message = "backwards full power";
            power = -1.0;
        } else if (colorSensor.red() >= 1) {
            message = "forward full power";
            power = 1.0;
            ringtone1.play();
        } else {
            message = "foward low power";
            power = .25;
        }

        telemetry.addData("Clear", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);

        telemetry.addData("AllyBallEliminator", message);
        return power;
    }
}
