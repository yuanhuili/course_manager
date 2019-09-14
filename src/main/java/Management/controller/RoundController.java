package Management.controller;

import Management.controller.vo.AllRoundGradeVO;
import Management.controller.vo.RoundScoreVO;
import Management.controller.vo.RoundVO;
import Management.controller.vo.SeminarVO;
import Management.domain.service.CourseService;
import Management.domain.service.RoundService;
import Management.domain.service.TeamService;
import Management.domain.service.impl.SeminarService;
import Management.entity.*;
import Management.mapper.SeminarMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
@CrossOrigin
@RestController()
@RequestMapping("/round")
public class RoundController {
    @Autowired
    CourseService courseService;
    @Autowired
    RoundService roundService;
    @Autowired
    TeamService teamService;
    @Autowired
    SeminarService seminarService;

    @GetMapping(value = "/{roundId}/seminar")
    public List<SeminarVO> querrySeminarList(@PathVariable("roundId")BigInteger roundId){
        List<Seminar> seminars=roundService.querrySeminar(roundId);
        List<SeminarVO> seminarVOS=new ArrayList<SeminarVO>();
        seminars.forEach(seminar -> {
            SeminarVO seminarVO=new SeminarVO();
            BeanUtils.copyProperties(seminar,seminarVO);
            seminarVOS.add(seminarVO);
        });
        return seminarVOS;
    }

    @GetMapping(value = "/{roundId}")
    public Round getRoundById(@PathVariable("roundId")BigInteger roundId){
        Round round =roundService.getRoundById(roundId);
        return round;
    }

    @PostMapping()
    public Integer putRound(@RequestBody Round round,
                            @RequestAttribute("user")User user){
        BigInteger roundId=roundService.putRound(round);
        return roundId.intValue();
    }

    @PutMapping(value = "/{roundId}")
    public void updateRound(@PathVariable("roundId")BigInteger roundId,@RequestBody Round round){
        round.setId(roundId);
        roundService.updateRound(round);
        return;
    }

    @GetMapping(value = "/{roundId}/roundScore")
    public List<RoundScoreVO> queryRoundScoer(@PathVariable("roundId")BigInteger roundId){
        List<RoundScore> roundScores=roundService.queryRoundScore(roundId);
        List<RoundScoreVO> roundScoreVOS=new ArrayList<RoundScoreVO>();
        roundScores.forEach(roundScore -> {
            RoundScoreVO roundScoreVO=new RoundScoreVO(roundScore);
            roundScoreVO.setTeamName(teamService.querryTeamById(roundScore.getTeamId()).getTeamName());
            roundScoreVOS.add(roundScoreVO);
        });
        return roundScoreVOS;
    }

    @GetMapping(value = "/allGrade/{courseId}")
    public List<AllRoundGradeVO> getAllGrade(@PathVariable("courseId")BigInteger courseId) {
        List<Round> rounds= courseService.getRound(courseId);
        List<AllRoundGradeVO> allRoundGradeVOS=new ArrayList<AllRoundGradeVO>();
        for (Round round : rounds) {
            AllRoundGradeVO allRoundGradeVO=new AllRoundGradeVO();
            List<RoundScore> roundScores=roundService.queryRoundScore(round.getId());
            List<RoundScoreVO> roundScoreVOS=new ArrayList<RoundScoreVO>();
            roundScores.forEach(roundScore -> {
                RoundScoreVO roundScoreVO=new RoundScoreVO(roundScore);
                roundScoreVO.setTeamName(teamService.querryTeamById(roundScore.getTeamId()).getTeamName());
                roundScoreVOS.add(roundScoreVO);
            });
            allRoundGradeVO.setRoundScoreVOS(roundScoreVOS);
            allRoundGradeVO.setRoundId(round.getId());
            allRoundGradeVOS.add(allRoundGradeVO);
        }
        return allRoundGradeVOS;
    }

    @GetMapping(value = "/teamGrade/{roundId}")
    public List<RoundScoreVO> getTeamGrade(@PathVariable("roundId")BigInteger roundId){
        List<RoundScore> roundScores=roundService.queryRoundScore(roundId);
        List<RoundScoreVO> roundScoreVOS=new ArrayList<RoundScoreVO>();
        roundScores.forEach(roundScore -> {
            List<SeminarScore> seminarScores=new ArrayList<SeminarScore>();
            seminarScores=seminarService.getRoundSeminarScore(roundId,roundScore.getTeamId());
            roundScore.setSeminarScores(seminarScores);
            RoundScoreVO roundScoreVO=new RoundScoreVO(roundScore);
            roundScoreVO.setTeamName(teamService.querryTeamById(roundScore.getTeamId()).getTeamName());
            roundScoreVOS.add(roundScoreVO);
        });
        return roundScoreVOS;

    }


    @GetMapping(value = "/{roundId}/team/{teamId}/roundScore")
    public RoundScoreVO querryTeamRoundScore(@PathVariable("roundId")BigInteger roundId,
                                           @PathVariable("teamId")BigInteger teamId){
        RoundScore roundScore=roundService.querryTeamRoundScore(roundId,teamId);
        List<SeminarScore> seminarScores=new ArrayList<SeminarScore>();
        seminarScores=seminarService.getRoundSeminarScore(roundId,roundScore.getTeamId());
        roundScore.setSeminarScores(seminarScores);

        RoundScoreVO roundScoreVO=new RoundScoreVO(roundScore);
        roundScoreVO.setTeamName(teamService.querryTeamById(roundScore.getTeamId()).getTeamName());
        roundScoreVO.setRoundSerial(roundService.getRoundById(roundId).getRoundSerial());
        return roundScoreVO;
    }

    @PutMapping(value = "/{roundId}/team/{teamId}/roundScore")
    public void putTeamRoundScore(@PathVariable("roundId")BigInteger roundId,
                                  @PathVariable("teamId")BigInteger teamId,
                                  @RequestBody RoundScore roundScore){
        roundScore.setRoundId(roundId);
        roundScore.setTeamId(teamId);
        BigDecimal totalScore=new BigDecimal(0);
        totalScore=totalScore.add(roundScore.getPresentationScore());
        totalScore=totalScore.add(roundScore.getQuestionScore());
        totalScore=totalScore.add(roundScore.getReportScore());
        roundScore.setTotalScore(totalScore);
        roundService.putRoundScore(roundScore);
    }









}
