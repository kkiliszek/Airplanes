package edu.projectuz.helpers;

import edu.projectuz.model.Vector2;

import java.math.*;

import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Helper {
    public static double getSectionLength(Vector2 a, Vector2 b){
        return abs(sqrt(pow(b.getX() - a.getX(), 2) + pow(b.getY() - a.getY(),2)));
    }
}
