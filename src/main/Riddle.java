package main;

public class Riddle {
    private String question;
    private String answer;
    private Item rewardItem;
    private boolean solved;

    public Riddle(String question, String answer, Item rewardItem) {
        this.question = question;
        this.answer = answer;
        this.rewardItem = rewardItem;
        this.solved = false;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Item getRewardItem() {
        return rewardItem;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean checkSolution(String playerAttempt) {
        return this.answer.equalsIgnoreCase(playerAttempt.trim());
    }
}
