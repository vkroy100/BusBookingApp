package com.example.fireapp;

public class Bus
{
   private String busname;
   private String remaining_seats;
   private String arrival_time,departure_time;
   private String day,date,totalSeat;

    public String getDay() {
        return day;
    }

    public String getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(String totalSeat) {
        this.totalSeat = totalSeat;
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

    public Bus(){

    }

    public Bus(String arrival_time,String busname,String departure_time,String remaining_seats,
               String day,String date,String totalSeat)
    {
        this.busname=busname;
        this.remaining_seats=remaining_seats;
        this.arrival_time=arrival_time;
        this.departure_time=departure_time;
        this.day=day;
        this.date=date;
        this.totalSeat=totalSeat;
    }
    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public String getRemaining_seats() {
        return remaining_seats;
    }



    public void setRemaining_seats(String remaining_seats) {
        this.remaining_seats = remaining_seats;
    }
}
