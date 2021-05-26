package spring.boot.beans;


public class Answer {
    private boolean selected;
    private String text;
    private String answerText;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "selected=" + selected +
                ", text='" + text + '\'' +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
