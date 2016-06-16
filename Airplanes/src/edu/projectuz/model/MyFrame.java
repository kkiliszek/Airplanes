package edu.projectuz.model;

import edu.projectuz.enums.SegmentType;

import javax.swing.*;
k
public class MyFrame extends JFrame {


    public MyFrame(Map map, Company company) {
        super("Airplanes");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setLayout(null);
        JLayeredPane myPane = new JLayeredPane();
        JLabel background = new JLabel(new ImageIcon("map.png"));
        background.setBounds(0, 0, 750, 750);
        setContentPane(myPane);
        myPane.add(background, 2);
        //super.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //super.setVisible(true);


        new Thread() {
            public void run() {
                Segment[] airportSegments = map.getSegment(SegmentType.AIRPORT_PLANE);
                Segment[] planeSegments = map.getSegment(SegmentType.AIRPORT_PLANE);

                JLabel[] airports = new JLabel[airportSegments.length];
                JLabel[] plane = new JLabel[airportSegments.length];

                for (int i=0; i<airportSegments.length; i++)
                {
                    airports[i] = new JLabel(new ImageIcon("busy_airport.png"));
                    airports[i].setBounds((int)airportSegments[i].getPosition().getX(), (int)airportSegments[i].getPosition().getY(), 7, 7);
                    myPane.add(airports[i], 0);
                }

                for (int i=0; i<airportSegments.length; i++)
                {
                    plane[i] = new JLabel(new ImageIcon("plane.png"));
                    plane[i].setBounds((int)planeSegments[i].getPosition().getX(), (int)planeSegments[i].getPosition().getY(), 10, 10);
                    myPane.add(plane[i], 0);
                }


                while(true)
                {
                    for (int i=0; i<airports.length; i++) {
                        plane[i].setBounds((int)company.getPlane(i).getPosition().getX(), (int)company.getPlane(i).getPosition().getY(), 10, 10);
                    }
                }
            }
        }.start();
    }
}


