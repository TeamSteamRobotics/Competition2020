package frc.robot.util;


public class PIDGains {
    public double kP;
    public double kI;
    public double kD;

    public PIDGains(double p, double i, double d){
        kP = p;
        kI = i;
        kD = d;
    }
}