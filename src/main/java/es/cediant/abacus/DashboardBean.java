/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.db.RunProcs;
import es.cediant.db.RunProcsHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
public class DashboardBean implements Serializable {
    private static final long serialVersionUID = 648864815261749745L;
    private boolean pollEnabled = false;
    private String strOne;
    private String strTwo;

    /**
     * Creates a new instance of DashboardBean
     */
    public DashboardBean() {
        //////////////////
        updateData(5); ///
        //////////////////
    }

    public String getStrOne() {
        System.out.println("strOne="+strOne);
        return strOne;
    }

    public void setStrOne(String strOne) {
        this.strOne = strOne;
    }

    public String getStrTwo() {
        return strTwo;
    }

    public void setStrTwo(String strTwo) {
        this.strTwo = strTwo;
    }
        
    public boolean getPollEnabled() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userBean");
        if(userBean == null){
            System.out.println("userBean not found");
        }
        System.out.println("getPollEnabled()-->"+userBean.getActiveTab());
        setPollEnabled(userBean.getActiveTab().equalsIgnoreCase("Dashboard"));
        System.out.println("PollEnabled="+this.pollEnabled);
        return pollEnabled;
    }
 
    public void setPollEnabled(boolean pollEnabled) {
        this.pollEnabled = pollEnabled;
    }
    
    public void updateData(int secs){
        
        Date end = new Date();
        
        /////////////////
        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 7-1, 1, 10, 0, 9);
        end = calendar.getTime();
        /////////////////
        
        calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.SECOND, -secs);
        Date start = new Date();
        start = calendar.getTime();
        StringBuilder dataOne = new StringBuilder();
        StringBuilder dataTwo = new StringBuilder();
        RunProcsHelper rph = new RunProcsHelper();
        ArrayList<RunProcs> result = rph.getRunProcs(start, end);
        
        // Count total number of proccesses in every second 
        // Count total number of every proccess type in the last 'secs' seconds
        Date startTime = new Date();
        Date endTime = new Date();
        startTime = start;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=1; i<secs; i++){
            int count = 0;
            calendar = Calendar.getInstance();
            calendar.setTime(start);
            calendar.add(Calendar.SECOND, 1);
            endTime = calendar.getTime();
            for (RunProcs runProcs: result){                
                if((runProcs.getDate().after(startTime) && runProcs.getDate().before(endTime))
                        || (runProcs.getDate().equals(startTime)) || (runProcs.getDate().equals(endTime))){
                    count+=runProcs.getNum();
                }
                if(i==1){
                    String procType = runProcs.getType();
                    if(map.containsKey(procType)){
                        int number = map.get(procType);
                        map.put(procType, number+runProcs.getNum());
                    } else {
                        map.put(procType, runProcs.getNum());
                    }
                }
            }
            dataOne.append(count+",");
            startTime = endTime;
        }
        if((dataOne.lastIndexOf(",")!=-1) && (dataOne.lastIndexOf(",")==(dataOne.length()-1))){
            dataOne=dataOne.deleteCharAt(dataOne.length()-1);
        }
        for (Object key: map.keySet()){
            dataTwo.append(map.get(key)+",");
        }
        if((dataTwo.lastIndexOf(",")!=-1) && (dataTwo.lastIndexOf(",")==(dataTwo.length()-1))){
            dataTwo=dataTwo.deleteCharAt(dataTwo.length()-1);
        } 
        
        //setStrOne("["+dataOne.toString()+"]");
        setStrOne(dataOne.toString());
        //setStrOne("3,7,9,1,4,6,8,2,5");
        //setStrTwo("["+dataTwo.toString()+"]");
        setStrTwo(dataTwo.toString());
        
        /////////////////
        StringBuilder str = new StringBuilder();
        for(int i=0; i<4; i++){
            if(i>0){
                str.append(",");
            }
            int randInt = (int) (Math.random()*100);
            str.append(randInt);
        }
        setStrOne(str.toString());
        /////////////////
        
        //System.out.println(strOne);
        //System.out.println(strTwo);
        
    }
    
}
