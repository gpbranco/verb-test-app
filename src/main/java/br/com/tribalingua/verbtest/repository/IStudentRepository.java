package br.com.tribalingua.verbtest.repository;

import java.util.List;

import br.com.tribalingua.verbtest.model.StudentScore;

public interface IStudentRepository {

	public static String KEY = "Student";
	List<StudentScore> loadAllStudentsScore(int classId);
	
}
