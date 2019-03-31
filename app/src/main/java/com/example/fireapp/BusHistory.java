package com.example.fireapp;

public class BusHistory
{
    private String busname;
    private String SeatNo;
    private String arrival_time,departure_time;
    private String day,date;
    private String name,IdNo;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return IdNo;
    }

    public void setIdNo(String idNo) {
        IdNo = idNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public BusHistory(){

    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }

    public BusHistory(String arrival_time, String busname, String departure_time, String SeatNo,
                      String day, String date, String name, String IdNo)
    {
        this.busname=busname;
        this.SeatNo=SeatNo;
        this.arrival_time=arrival_time;
        this.departure_time=departure_time;
        this.day=day;
        this.date=date;
        this.name=name;
        this.IdNo=IdNo;
    }
    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }


}
