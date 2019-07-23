package twenty.questions;

public class QuestionOrAnswerBank {

    public QuestionOrAnswer root;
    public int layers;

    QuestionOrAnswerBank(QuestionOrAnswer overallRoot, int layers) {
        this.root = overallRoot;
        this.layers = layers;
    }

    QuestionOrAnswerBank() {
        //fake method to "initialize it"
    }

    public void printBank(QuestionOrAnswer node) {
        if (root != null) {
            printBank(root.left);
            System.out.print(root.data + " ");
            printBank(root.right);
        }
    }

    public void add(String QorA, Boolean right) {
        root = add(new QuestionOrAnswer(QorA), right);
    }

    private QuestionOrAnswer add(QuestionOrAnswer node, Boolean right) {
        if (right) {
            root.right = node;
        } else {
            root.left = node;
        }
        return node;
    }

}
