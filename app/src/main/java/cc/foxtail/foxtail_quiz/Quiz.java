package cc.foxtail.foxtail_quiz;

/**
 * Created by andong-won on 2017. 9. 30..
 */

public class Quiz {
    private int resourceId;
    private boolean answer;

    public Quiz(int resourceId, boolean answer) {
        this.resourceId = resourceId;
        this.answer = answer;
    }

    public int getResourceId() {
        return resourceId;
    }

    public boolean isAnswer() {
        return answer;
    }
}

