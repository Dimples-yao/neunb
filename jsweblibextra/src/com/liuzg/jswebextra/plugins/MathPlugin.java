package com.liuzg.jswebextra.plugins;


import java.io.ObjectInputStream;

public class MathPlugin {
    public Integer toInt(Object obj){
        if(obj==null) return null;
        try{
            if(obj instanceof String) {
                return Integer.parseInt((String) obj);
            }else if(obj instanceof Double||obj instanceof Float||obj instanceof Long){
                return (int)obj;
            }else{
                throw new RuntimeException("UNKNOWN TYPE "+obj.getClass().getName());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Object abs(Object value){
        if(value instanceof Integer){
            return Math.abs((int)value);
        }else if(value instanceof Long){
            return Math.abs((long)value);
        }else if(value instanceof Double){
            return Math.abs((double)value);
        }else if(value instanceof Float){
            return Math.abs((float)value);
        }else{
            return null;
        }
    }

    public Object acos(double value){
        return Math.acos(value);
    }

    public Object asin(double value){
        return Math.asin(value);
    }

    public Object atan(double value){
        return Math.atan(value);
    }

    public Object atan2(double y,double x){
        return Math.atan2(y,x);
    }

    public Object ceil(double value){
        return Math.ceil(value);
    }

    public Object cos(double value){
        return Math.cos(value);
    }

    public Object exp(double value){
        return Math.exp(value);
    }

    public Object floor(double value){
        return Math.floor(value);
    }

    public Object log(double value){
        return Math.log(value);
    }

    public Object pow(double x,double y){
        return Math.pow(x,y);
    }

    public Object round(double value){
        return Math.round(value);
    }

    public Object sin(double value){
        return Math.sin(value);
    }

    public Object sqrt(double value){
        return Math.sqrt(value);
    }

    public Object tan(double value){
        return Math.tan(value);
    }













}
