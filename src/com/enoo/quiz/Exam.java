package com.enoo.quiz;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * 시험
 */
public class Exam {
	//디비에서 가져온 원본 문제 목록
	ArrayList<QuizQuestion> lst = new ArrayList<QuizQuestion>();
	
	//현재 문제번호
	public int currentQuestion=0;
	//총 문제수
	public int totalNumberOfQuestions=0;
	//문제풀이 시간제한(분)
	public int quizDuration=0;
	
	public List<Integer> quizSelectionsList=new ArrayList<Integer>();
	
	
	public List<Integer> getQuizSelectionsList() {
		return quizSelectionsList;
	}
	//제출 답안 목록
	public Map<Integer,Integer> selections= new LinkedHashMap<Integer,Integer>();
	
	//푼 문제 목록
	public ArrayList<QuizQuestion> questionList;
	
	/**
	 * 챕터명과 문제 개수를 입력하여 문제지를 만든다
	 * @param test 챕터명
	 * @param totalNumberOfQuestions
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public Exam(String test,int totalNumberOfQuestions) throws SAXException,ParserConfigurationException,IOException, URISyntaxException{
		System.out.println("Exam.Exam test="+test);
		//문제지 db 가져오기
		lst = CreateQuestions.getQuestionList(test);
		
		//답안지 초기화(-1 : 선택 안함)
		for(int i=0;i<totalNumberOfQuestions;i++){
			selections.put(i,-1);
		}
		//푼 문제 목록 초기화
		questionList = new ArrayList<QuizQuestion>(totalNumberOfQuestions);
		
	}
	
	public ArrayList<QuizQuestion> getLst(){
		return lst;
	}
	
	//푼 문제 목록에 담기	
	public void setQuestion(int i)
	{
		questionList.add(i,lst.get(i));
		
	}
	
	
	public QuizQuestion getQuestion(int i)
	{
		return questionList.get(i);
	}
	
	public ArrayList<QuizQuestion> getQuestionList(){
		return this.questionList;
	}
	
	public int getCurrentQuestion(){return currentQuestion;}
	
	public Map<Integer,Integer> getSelections(){
		return this.selections;
	}
	
	/**
	 * 채점
	 */
	public int calculateResult(Exam exam,int taken){
		int totalCorrect=0;
		Map<Integer,Integer> userSelectionsMap=exam.selections;	
		
		List<Integer> userSelectionsList=new ArrayList<Integer>();
		
		for (Map.Entry<Integer, Integer> entry :userSelectionsMap.entrySet()) {
			userSelectionsList.add(entry.getValue());
		}
		
		quizSelectionsList=userSelectionsList;
		
		List<QuizQuestion> questionList=exam.questionList;
		
		List<Integer> correctAnswersList=new ArrayList<Integer>();
		for(QuizQuestion question: questionList){
			correctAnswersList.add(question.getCorrectOptionIndex());
		}
		
		//푼 문제들
		for(int i=0;i<taken-1;i++){
			System.out.println("calculateResult.userSelectionsList.get("+i+")="+userSelectionsList.get(i));
			System.out.println("calculateResult.correctAnswersList.get("+i+")="+correctAnswersList.get(i));
			//사용자가 제출한 답안과 정답과 비교하여 채점
			if(userSelectionsList.get(i) == correctAnswersList.get(i)){
				totalCorrect++;
			}
		}
		//정답 수
		System.out.println("totalCorrect="+totalCorrect);
		
		return totalCorrect;
	}
	
	/**
	 * 문제번호 별 제출 답안 가져옴
	 */
	public int getUserSelectionForQuestion(int i){
		Map<Integer,Integer> map=getSelections();
		
		int rst = -1;
		//null pointer exception  나서 체크함
		if(null != map.get(i)) {
			rst = (Integer) map.get(i);
		}
		
		return rst;
	}	
	
	
	public int getTotalNumberOfQuestions(){
		return totalNumberOfQuestions;
	}
   
	public void setTotalNumberOfQuestions(int i){
		this.totalNumberOfQuestions=i;
	}
	
	@Override
	public String toString() {
		String str = "Exam.questionList.size="+questionList.size()+"\r\n";
		str = str + "Exam.lst.size="+lst.size();
		return str;
	}
	
}
