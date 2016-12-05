package com.easy.study.rsa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class RsaTest {

    public static final String CHARSET = "UTF-8";// UTF-8

    public static final String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv56h/Ax9bX7rUzXYLjGBg1y1g" +
            "ADhsz92MRRAjV68YIbERBEGr7LEWPwLkGffr0e4N9BVYnHp6U1nxZxS9ZFONOcmq" +
            "Pg2wn3PGmWsVu0kOJnB4UsNuUHjcgIXW53VNgfPb+9oeeEgWxZTtfQ/ap6Rbe04h" +
            "BEOE1T3jPtiK2VRrHwIDAQAB";
    //"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzr35DToe0yAnOTOHdJT/J/SgImkHd54qzHT7r/GBc0pOO4hDkHA+PwNRY30RzPRQCAzhsMb99+utu/zULLgRCluadK/uI6BFGD8Q8R/jTqJ07/EYNLSUPslq+hioeNwC3SVs0PL6FwsMlpG2bWWf90dKyLL9WMc/UAKwtun4qNrIiT9LjiTFR3bV6hFJQ/BLkCtwImHcZ2rvKugwBZCPZilPxVA60OK/FS+PZ8IkeakekplDUf+xod6hKPkV8JRmdFbRmkPEtWE6fYwDzr9/vjKkFDYhI0JmbYlxyIEBUCVKiF1PLgAxp4aw60q8nUTQmkrzW3vdX2TCQla66Jqa+QIDAQAB";


    public static final String PRIVATEKEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAO/nqH8DH1tfutTN" +
            "dguMYGDXLWAAOGzP3YxFECNXrxghsREEQavssRY/AuQZ9+vR7g30FVicenpTWfFn" +
            "FL1kU405yao+DbCfc8aZaxW7SQ4mcHhSw25QeNyAhdbndU2B89v72h54SBbFlO19" +
            "D9qnpFt7TiEEQ4TVPeM+2IrZVGsfAgMBAAECgYEAuQOhMILJ9f/36BGkDffuhACu" +
            "X7EL0u7j3z+L2JXQ1P12vrbXpq1dTNi/dAaK6nT18BDjPXlAA4KwhLmIhx8ORwdv" +
            "s61sEb4+qTLuaCctqpenTw3HHgUUM4ig4gDH7KNUBac9vGziqNwlCdbSfPqw7cF4" +
            "bOk6tqdr2inEX+WZ+RECQQD/1P0l9XwxzUWvIJXicr4m5mRyLyt2C7ZSpP/lHIV6" +
            "PDSxTpPihsENZe6vULVf12Vd4dV6dkxgdvi2Y6HD/LUbAkEA8A/92z5o86aG0rrt" +
            "ysRp4mADdfpyBLVqMGihwafhfbh6q+OgAda9wJ9enaBpYTe9zy/JF/XiOjEKx4Ou" +
            "l372TQJBAN6BuMtS/3mxN3JhvPr/ITNkHujQ7dP1y9WFfKsga1ctFgdAeI8E9NNa" +
            "XIZOg/GfvaIx0SJ50WP1FcxMS13SZRECQG8KVpfU9GLPQkmzIU7LL1cAplkp8Rdw" +
            "ep/NphSXfNvUOxnK/jYbiEXS8cbjbhsD145AQjoNCAl2qc/DfzygA3kCQQCZ5HK1" +
            "PbFjwo7FzsgQcwjs8/V7053NK8oFi7IecuB1zIROVosOaFxLkRL7RAgp5AKDyXHq" +
            "FkoUlcpNKYJdorNv";

    public static void main(String[] args) throws Exception {

        RsaEncrypt rsa = new RsaEncrypt();
        //加载公钥
        rsa.loadPublicKey(PUBLICKEY);
        //加载私钥
        rsa.loadPrivateKey(PRIVATEKEY);
        //创建test对象
        Test t = new Test();
        t.setName("testaaa");
        t.setAge(123);
        t.setSex("testString");

        //生成代签名内容字符串
        String content = createLinkString(JSON.parseObject(JSON.toJSONString(t)), true);
        //生成签名
        String rsaSign = new String(Base64.encodeBase64(rsa.rsaSign(content, rsa.getPrivateKey())));
        System.err.println("build sign:" + rsaSign);
        //验证签名
        System.err.println(rsa.doCheck(content, Base64.decodeBase64(rsaSign), rsa.getPublicKey()));
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param ifSort 是否需要排序
     * @return 拼接后字符串
     */
    public static String createLinkString(final JSONObject obj, final boolean ifSort) {
        //取出键集后排序
        List<String> keys = null;
        keys = new ArrayList<String>(obj.keySet());
        if (ifSort) {
            Collections.sort(keys);
        }
        String prestr = "";
        //依次取出
        for (String key : keys) {
            //签名字段及空值不参与签名
            if (key.equals("sign"))
                continue;
            String value = obj.getString(key);
            if (StringUtils.isBlank(value))
                continue;
            prestr = prestr + key + "=" + value + "&";
        }
        prestr = prestr.substring(0, prestr.length() - 1);
        return prestr;
    }
}

class Test implements Serializable {
    private String name;
    private String sex;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
