package mybatistest.service;

import mybatistest.model.WxMaterialImg;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * Created by sunj on 2016/5/31.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestWxMaterialImgService {
    private static Logger logger = Logger.getLogger(TestWxMaterialImgService.class);

    private IWxMaterialImgService wxMaterialImgService;

    @Autowired
    public void IWxMaterialImgService(IWxMaterialImgService wxMaterialImgService) {
        this.wxMaterialImgService = wxMaterialImgService;
    }

    @Test
    public void testInsert() throws Exception {
        WxMaterialImg img = new WxMaterialImg();
        img.setGuid(new BigDecimal(2));
        img.setMediaId("abcasdasdasdasdasda");
        img.setName("测试图片1");
        img.setPath("测试路径");
        img.setUpdateTime(14000000);
        img.setUrl("");
        wxMaterialImgService.insert(img);
    }
}
