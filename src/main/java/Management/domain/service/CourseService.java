package Management.domain.service;


import Management.entity.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omg.CosNaming.BindingHelper;

import java.math.BigInteger;
import java.util.List;

public interface CourseService {
		//List<Course> getCourseNameByUserId(BigInteger id);
		BigInteger putCourse(Course course);

		List<Course> getAllKlass(BigInteger userId,String userRole);

		List<Round> getRound(BigInteger id);

		Course getCourseById(BigInteger id);

        void deleteCourseById(BigInteger id);

		List<Team> getTeamByCourseId(BigInteger courseId);

		Team getMyTeam(BigInteger courseId,BigInteger studentId);

		List<Klass> getKalssById(BigInteger id);

		List<Student> getNoTeam(BigInteger id,BigInteger userId);

		BigInteger insertKlassById(Klass klass);

		List<ShareTeamApplication> getTeamShareByCourseId(BigInteger id);

		List<ShareSeminarApplivation> getSeminarShareByCourseId(BigInteger id);


        BigInteger teamShareRequest(ShareTeamApplication shareTeamApplication);

        BigInteger seminarShareRequest(ShareSeminarApplivation shareSeminarApplivation);

        List<Course> getAllCourse();

        List<ScoreExcel> getScore(BigInteger courseId);
}
