/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.structures;

import javax.vecmath.Point3d;

/**
 *
 * @author miguel
 */
public class Focus {
    
    private Point3d startPoint;
    private Point3d endPoint;
    private double intensity;

    public Focus(){      
        startPoint = new Point3d();
        endPoint = new Point3d();
    }
    
    public Focus(Point3d startPoint, Point3d endPoint){
        this();
        this.setStartPoint(startPoint);
        this.setEndPoint(endPoint);
        this.setIntensity(startPoint.distance(endPoint));
    }        
    
    public Point3d getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point3d startPoint) {
        this.startPoint = startPoint;
        this.setIntensity(startPoint.distance(endPoint));
    }

    public Point3d getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point3d endPoint) {
        this.endPoint = endPoint;
        this.setIntensity(startPoint.distance(endPoint));
    }

    public double getIntensity() {
        return intensity;
    }

    private void setIntensity(double intensity) {
        this.intensity = intensity;
    }        
    
    public String printStart(){
        return "Start(x="+startPoint.x+", y="+startPoint.y+", z="+startPoint.z+")";
    }
    
    public String printEnd(){
        return "End(x="+endPoint.x+", y="+endPoint.y+", z="+endPoint.z+")";
    }
    
    public String printIntensity(){
        return "Intensity("+intensity+")";
    }            
    
    public String printFocus(){
        return printStart()+"\n"+printEnd()+"\n"+printIntensity();
    }
}
