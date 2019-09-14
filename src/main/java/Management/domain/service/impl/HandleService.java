package Management.domain.service.impl;

import Management.dao.HandleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/26 20:57
 * @Version 1.0
 */
@Service
public class HandleService {
    @Autowired
    HandleDao handleDao;

    public void cancelTeamShare(BigInteger id){
        handleDao.deleteTeamShare(id);
    }
    public void cancelSeminarShare(BigInteger id){
        handleDao.deleteSeminarShare(id);
    }

    public void handleTeamValid(BigInteger teamShareId,Integer status){
        handleDao.handleTeamValid(teamShareId,status);
        return;
    }
    public void handleSeminarShare(BigInteger id,Integer status){

        handleDao.handleSeminarShare(id,status);
    }
    public void handleTeamShare(BigInteger id,Integer status){
        handleDao.handleTeamShare(id,status);
    }
}
