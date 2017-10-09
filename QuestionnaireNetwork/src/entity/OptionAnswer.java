package entity;

public class OptionAnswer {
	private int optionAnswerId;
	private int optionId;
	private int questionId;
	private int answerId;

	public int getOptionAnswerId() {
		return optionAnswerId;
	}
	public void setOptionAnswerId(int optionAnswerId) {
		this.optionAnswerId = optionAnswerId;
	}
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
}
