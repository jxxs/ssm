package mybatistest.service;

import mybatistest.dao.WxMaterialImgMapper;
import mybatistest.model.WxMaterialImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * Created by sunj on 2016/5/31.
 */
@Service
public class WxMaterialImgServiceImpl implements IWxMaterialImgService {

    public WxMaterialImgServiceImpl() {
    }

    private WxMaterialImgMapper wxMaterialImgMapper;
    @Autowired
    public void setWxMaterialImgMapper(WxMaterialImgMapper wxMaterialImgMapper) {
        this.wxMaterialImgMapper = wxMaterialImgMapper;
    }

    @Override
    public int deleteByPrimaryKey(String mediaId) {
        return wxMaterialImgMapper.deleteByPrimaryKey(mediaId);
    }

    @Override
    public int insert(WxMaterialImg record) {
        return wxMaterialImgMapper.insert(record);
    }

    @Override
    public int insertSelective(WxMaterialImg record) {
        return wxMaterialImgMapper.insertSelective(record);
    }

    @Override
    public WxMaterialImg selectByPrimaryKey(String mediaId) {
        return wxMaterialImgMapper.selectByPrimaryKey(mediaId);
    }

    @Override
    public int updateByPrimaryKeySelective(WxMaterialImg record) {
        return wxMaterialImgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WxMaterialImg record) {
        return wxMaterialImgMapper.updateByPrimaryKey(record);
    }
}
