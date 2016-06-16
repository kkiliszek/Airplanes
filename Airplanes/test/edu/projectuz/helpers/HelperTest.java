package edu.projectuz.helpers;

import edu.projectuz.model.Vector2;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bartosz on 2016-05-12.
 */
public class HelperTest {

    @Test
    public void getSectionLength() throws Exception {
        //Arrange
        Vector2 a = new Vector2(2,3);
        Vector2 b = new Vector2(3,2);

        //Act
        double result = Helper.getSectionLength(a,b);
        //Assert
        assertEquals(1.4142, result, 0.001);
    }
}