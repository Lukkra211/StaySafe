package com.example.pc.staysafe.objects;

import com.example.pc.staysafe.model.entity.Answer;
import com.example.pc.staysafe.model.entity.Question;

import java.util.ArrayList;

public class Result {
    public String question;
    public String userAnswer;
    public int type;
    public ArrayList<Answer> answers;

   public Result(String question, ArrayList<Answer> answers, String userAnswer, int type){
       this.question = question;
       this.userAnswer = userAnswer;
       this.answers = answers;
       this.type = type;
   }
}
