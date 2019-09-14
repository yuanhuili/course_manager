package Management.mapper;
import Management.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@Mapper
@Component
public interface AttendanceMapper  {

    List<Attendance> selectByKlassSeminarId(@Param("klassSeminarId") BigInteger klassSeminarId);

    void startPresentation(@Param("attendanceId")BigInteger attendanceId);

    void startTwoPresentation(@Param("attendanceId")BigInteger attendanceId);

    void endPresentation(@Param("attendanceId")BigInteger attendenceId);

    void deleteById(@Param("attendanceId")BigInteger attendanceId);

    void addAttendance(@Param("attendance")Attendance attendance);

    void updatePPT(@Param("attendanceId")BigInteger attendanceId, @Param("name")String name,@Param("url") String url);

    void updateReport(@Param("attendanceId")BigInteger attendanceId, @Param("name")String name,@Param("url")String url);

    Attendance selectById(@Param("attendanceId")BigInteger attendenceId);

    void endAllPresentation(@Param("klassSeminarId")BigInteger klassSeminarId);
}
