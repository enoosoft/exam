package com.enoo.quiz;

/**
 * 문제
 */
public class QuizQuestion {
	/**문제번호*/
	int questionNumber;
	/**문제*/
	String question;
	/**선택지*/
	String questionOptions[];
	/**정답*/
	int correctOptionIndex;
	/**사용자답안1~4(-1:선택안함)*/
	int userSelected=-1;
	
	
	public int getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(int userSelected) {
		this.userSelected = userSelected;
	}

	public String getQuestion()
	{ 
		return question;
	}
	
	public int getQuestionNumber()
	{
		return questionNumber;
	}
	
	public void setQuestionNumber(int i)
	{
		questionNumber=i;
	}
	
	public int getCorrectOptionIndex()
	{
		return correctOptionIndex;
	}
	
	public String[] getQuestionOptions()
	{
		return questionOptions;
	}
	
	public void setQuestion(String s)
	{
		question=s;
	}
	public void setCorrectOptionIndex(int i)
	{
		correctOptionIndex=i;
	}
	public void setQuestionOptions(String[]s)
	{
		questionOptions=s;
	}
	
	@Override
	public String toString(){
		String str= questionNumber+"."+getQuestion();
		for(String option:getQuestionOptions()){
			str=str+option+"  ";
		}
		str=str+"\n Correct Answer : "+getCorrectOptionIndex();
		return str;
	}

}
