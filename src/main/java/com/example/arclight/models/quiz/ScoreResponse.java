package com.example.arclight.models.quiz;

import com.example.arclight.entities.Score;

public class ScoreResponse
{
    private  Long id;
   private double mark;
   private  double total;

   public ScoreResponse(Score score){
       this.id= score.getId();
       this.mark=score.getMark();
       this.total=score.getTotal();
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
