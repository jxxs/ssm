package mybatistest.service;

import mybatistest.model.WxMaterialImg;

/**
 * Created by sunj on 2016/5/31.
 */
public interface IWxMaterialImgService {
    int deleteByPrimaryKey(String mediaId);

    int insert(WxMaterialImg record);

    int insertSelective(WxMaterialImg record);

    WxMaterialImg selectByPrimaryKey(String mediaId);

    int updateByPrimaryKeySelective(WxMaterialImg record);

    int updateByPrimaryKey(WxMaterialImg record);
}
