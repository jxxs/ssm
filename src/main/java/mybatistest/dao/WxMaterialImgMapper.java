package mybatistest.dao;

import mybatistest.model.WxMaterialImg;

public interface WxMaterialImgMapper {
    int deleteByPrimaryKey(String mediaId);

    int insert(WxMaterialImg record);

    int insertSelective(WxMaterialImg record);

    WxMaterialImg selectByPrimaryKey(String mediaId);

    int updateByPrimaryKeySelective(WxMaterialImg record);

    int updateByPrimaryKey(WxMaterialImg record);
}