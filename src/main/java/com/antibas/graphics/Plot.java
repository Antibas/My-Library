package com.antibas.graphics;

import com.antibas.util.Methods;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class Plot extends JPanel {
	
	List<Double> cord;
	int marg;
	
	public Plot(int marg, List<Double> cord) {
		super();
		this.marg = marg;
		this.cord = cord;
	}
	
	@Override
	protected void paintComponent(Graphics grf){  
        //create instance of the Graphics to use its methods  
        super.paintComponent(grf);  
        Graphics2D graph = (Graphics2D)grf;  
          
        //Sets the value of a single preference for the rendering algorithms.  
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
          
        // get width and height  
        int width = getWidth();  
        int height = getHeight();  
          
        // draw graph  
        graph.draw(new Line2D.Double(marg, marg, marg, height-marg));  
        graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));  
          
        //find value of x and scale to plot points  
        double x = (double)(width-2*marg)/(cord.size()-1);  
        double scale = (double)(height-2*marg)/Methods.max(cord);  
          
        //set color for points  
        graph.setPaint(Color.RED);  
          
        // set points to the graph  
        for(int i=0; i<cord.size(); i++){  
            double x1 = marg+i*x;  
            double y1 = height-marg-scale*cord.get(i);  
            graph.fill(new Ellipse2D.Double(x1-2, y1-2, 4, 4));  
        }  
    }  
         
	public void plot() {
		JFrame frame = new JFrame();  
        // set size, layout and location for frame.  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.add(this);  
        frame.setSize(400, 400);  
        frame.setLocation(200, 200);  
        frame.setVisible(true);  
	}
	
}
