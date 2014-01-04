package br.com.tribalingua.verbtest.repository;

import java.util.List;

import br.com.tribalingua.verbtest.model.StudentScore;

public interface IStudentScoreLogRepository {
	public static String KEY = "StudentLog";
	
	void addLog(List<StudentScore> scores, int groupId);
	List<StudentLog> loadAllStudentLogs(int groupClassId);
	
	public static class StudentLog{
		private int testCount;
		private int score;
		private String name;
		private String thumbUrl;
		
		public StudentLog(int testCount, int score, String name, String thumbUrl) {
			this.testCount = testCount;
			this.score = score;
			this.name = name;
			this.thumbUrl = thumbUrl;
		}
		
		public void add(StudentScore score){
			this.testCount++;
			this.score += score.getScore();
			this.name = score.getName();
		}
		
		public boolean isSame(StudentScore score){
			return this.name.equals(score.getName());
		}
		
		public int getTestCount() {
			return testCount;
		}
		public void setTestCount(int testCount) {
			this.testCount = testCount;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public String getThumbUrl() {
			return thumbUrl;
		}

	}

}
