package Management.domain.service.impl;

import Management.entity.Attendance;
import Management.entity.KlassSeminar;
import Management.entity.Question;
import Management.entity.Seminar;
import Management.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/24
 */
@Service
public class AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    ClassSeminarMapper classSeminarMapper;
    @Autowired
    SeminarMapper seminarMapper;

    public void deleteById(BigInteger attendanceId) {
        attendanceMapper.deleteById(attendanceId);
    }

    public BigInteger newAttendance(Attendance attendance, BigInteger seminarId, BigInteger classId) {
        BigInteger id=classSeminarMapper.selectId(classId,seminarId);
        attendance.setKlassSeminarId(id);
        attendanceMapper.addAttendance(attendance);
        BigInteger id1=attendance.getId();
        return id1;

    }

    public void updatePPT(BigInteger attendanceId, String name, String url) {
        attendanceMapper.updatePPT(attendanceId,name,url);
    }

    public void updateReport(BigInteger attendanceId, String name, String url) {
        attendanceMapper.updateReport(attendanceId,name,url);
    }

    public List<Attendance> selectAllAttendance(BigInteger attendenceId) {
        List<Attendance> mainList=new ArrayList<Attendance>();
        Attendance attendance1=new Attendance();
        Attendance attendance=attendanceMapper.selectById(attendenceId);
        KlassSeminar klassSeminar=classSeminarMapper.selectById(attendance.getKlassSeminarId());
        Seminar seminar=seminarMapper.selectById(klassSeminar.getSeminarId());
        int maxTeam=seminar.getMaxTeam();
        List<Attendance> attendances=attendanceMapper.selectByKlassSeminarId(attendance.getKlassSeminarId());
        for(int i=1;i<maxTeam;i++){
            mainList.add(attendance1);
        }
        attendances.forEach(attendance2 -> {
            mainList.add(attendance2.getTeamOrder()-1,attendance2);
        });
        if(mainList.size()>maxTeam)
        {
            for (int i=mainList.size()-maxTeam;i>1;i--)
                mainList.remove(maxTeam+1);
            mainList.remove(maxTeam);
        }
        return mainList;

    }
}
