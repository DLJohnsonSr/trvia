package com.d2j2.trvia.controllers;

import com.d2j2.trvia.apiEntities.Questions;
import com.d2j2.trvia.apiEntities.Results;
import com.d2j2.trvia.entities.MyQuestion;
import com.d2j2.trvia.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/admin/admindash")
    public String showAdminDash(){
        return "admin/admindash";
    }
    @GetMapping("/admin/addquestion")
    public String addQuestion() {
        return "admin/addquestion";
    }


     @PostMapping("/admin/savequestion")
     public String SaveQuestions(HttpServletRequest request){

//        RETRIEVE question search parameters
         String numberOfQuestion;
         String category="";
         String difficulty="";
         String type="";

         numberOfQuestion = "amount="+ request.getParameter("numberOfQuestion").toString();
         if(!request.getParameter("category").isEmpty()){
             category = "&category="+request.getParameter("category");
         }
         if(!request.getParameter("difficulty").isEmpty()){
             difficulty = "&difficulty="+request.getParameter("difficulty").toString();
         }
         if(!request.getParameter("type").isEmpty()){
             type = "&type="+request.getParameter("type").toString();
         }
         StringBuilder questionPara = new StringBuilder();
         questionPara.append("https://opentdb.com/api.php?"+numberOfQuestion+category+difficulty+type);
         String questionURL = questionPara.toString();

        RestTemplate restTemplate = new RestTemplate();
        Questions questions = restTemplate.getForObject(questionURL, Questions.class);

        for (Results eachResults:questions.getResults()){
            MyQuestion myQuestion = new MyQuestion();
            myQuestion.setCategory(eachResults.getCategory());
            myQuestion.setType(eachResults.getType());
            myQuestion.setDifficulty(eachResults.getDifficulty());
            myQuestion.setQuestion(eachResults.getQuestion());
            myQuestion.setCorrect_answer(eachResults.getCorrect_answer());
            myQuestion.setIncorrect_answers(eachResults.getIncorrect_answers());
            questionRepository.save(myQuestion);
        }
        System.out.println(questions.getResponse_code().toString());
        System.out.println(questions.getResults().toString());
        return "redirect:/admin/admindash";
    }
    @GetMapping("/admin/listquestion")
    public String listQuestions(Model model){
        model.addAttribute("adminQuestions",questionRepository.findAll());
        return "admin/listquestion";
    }

}
