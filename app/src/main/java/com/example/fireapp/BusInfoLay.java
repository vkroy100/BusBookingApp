package com.example.fireapp;

public class BusInfoLay
{
   private String names,IdNo,SeatNo,status,email;

   BusInfoLay(String names,String IdNo,String SeatNo,String status,String email){}
   {
       this.names=names;
       this.IdNo=IdNo;
       this.SeatNo=SeatNo;
       //this.date=date;
       this.status=status;
       this.email=email;
   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    BusInfoLay(){

   }


    public String getIdNo() {
        return IdNo;
    }

    public void setIdNo(String idNo) {
        IdNo = idNo;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }





    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
