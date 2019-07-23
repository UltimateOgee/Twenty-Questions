
package twenty.questions;

public class QuestionOrAnswer {
    
    public String data;
    public QuestionOrAnswer right, left;
    
    public QuestionOrAnswer(String data){
        this(data, null, null);
    }
    
    public QuestionOrAnswer(String data, QuestionOrAnswer left, QuestionOrAnswer right){
        this.data = data;
        this.right = right;
        this.left = left;
    }
    
}
