package com.easy.study.rsa;

import org.apache.commons.codec.binary.Base64;

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
    //"MIIEpAIBAAKCAQEAzr35DToe0yAnOTOHdJT/J/SgImkHd54qzHT7r/GBc0pOO4hDkHA+PwNRY30RzPRQCAzhsMb99+utu/zULLgRCluadK/uI6BFGD8Q8R/jTqJ07/EYNLSUPslq+hioeNwC3SVs0PL6FwsMlpG2bWWf90dKyLL9WMc/UAKwtun4qNrIiT9LjiTFR3bV6hFJQ/BLkCtwImHcZ2rvKugwBZCPZilPxVA60OK/FS+PZ8IkeakekplDUf+xod6hKPkV8JRmdFbRmkPEtWE6fYwDzr9/vjKkFDYhI0JmbYlxyIEBUCVKiF1PLgAxp4aw60q8nUTQmkrzW3vdX2TCQla66Jqa+QIDAQABAoIBAB2BPS7nEYkd+JsQQI+hx/xizAu9I4StvPvq4hSNCeVzQFD8tG/DpQ6HIbFqIwU2BMnxVLBO5eXg6619eqZbKoto4VUv2nOZJuM79OYEoAMT6k5oAQVclB+VzT+eD0UYWQxIkAwN8SUivYwrhXZ9X2QB96lG6iMmZGG90Ix6PbOTLAl8KHkitIaKaY6rxhuk40sCDAB5CkDZr94MlP0+So+vrhcMFUrlAiUrAxzm0JBw0RTlXdZOB2w66Tilyyee4Csrx+MCW6RrmqQKQgaTESCSqPmRbdZu9Sr1N+Djvg/I8MwlLoSRG7Y9CqHMmV0YJiRCufVbSAcWCRuseOe2H8UCgYEA6VESy1XhjX77XNOmJa8TkEpIPi+AJHmX+0KtOHLh8CzZ6EUtojPygLLWBTbP0yZQt5upCIlokQb86ZctMvnxVzU1nkknk/AoheytxPbIsOANiHqA3EfkOMObr05Sma6yKUznWfLc1qYCI5cO82E8Wh5SfM8QhEUFIZEIa3CeD0MCgYEA4td+llrTvtoOXrMinLaxWbLXeufEmnQbd0twAsW/n+ikO5rmLeT/bvZzCrMt5DDY+fRFakfzZvDtOlUWXkIK/hxzv99EknaUA1JbFRSIifUrcV/jwtJQT+84pqNbg2zQv+0nOJkAzkhcRAG7OHNq79XqOWZP/nfJ51FF3UW0kxMCgYEAwGz4JjFvdAaNg1Kfjibl6LQK/xaoBe0u9rBYMt01EKO9GH6tk0BqudBFCUnaf5fzLGs6Lus9DZeI0ZK4WXmgnT8qOKC2/qEsmr/H1VF/1bkEEFKQopy8UkCpzxy2rT9SexONHzdZAcMqsnWsg/fnEaA+gec5BQ3znGqEWjAofAcCgYBUgrRGj9ATammwHkzm1al9IYPl5jL42AjjiNdSMRoTGTkWe78FmisPzAFQGzEdspUjij3SOrwTve6jrM+IjlJKGY+GDEgfyM2b7zK/x8aWnyBwPKk+C9yf6x/P9UxCymd7GX9jNp12PL8jFwIJJfbRI4D/oX5r73TpGP5OUYnUwQKBgQCLTJSo5j7CaUKNRaoHbsYTgiSIhY3DBnMu71DzDtRl0DZrhaQLziRdgp594ti3293yx+Mbrj1P2yxlEHJ8MPyn9eb1oqXwhw/VV8P2QyWwT5E71OYX3DsQYqxzbXPf2P0J4NmK4zDtNllbZjB0lz9xDBNCL++BaM9Hk+okmklZqA==";

    public static void main(String[] args) throws Exception {

        RsaEncrypt rsa = new RsaEncrypt();
        rsa.loadPublicKey(PUBLICKEY);
        rsa.loadPrivateKey(PRIVATEKEY);
        byte[] bytes = rsa.encrypt(rsa.getPublicKey(), new String("test").getBytes());
        bytes = Base64.encodeBase64(bytes);
        System.err.println(new String(Base64.encodeBase64(bytes)));
        byte[] res = rsa.decrypt(rsa.getPrivateKey(), Base64.decodeBase64(bytes));
        System.err.println(new String(res));

    }

//    private static JSONObject encryptData(Payment payment) throws Exception {
//        String pub = PUBLICKEY;
//        String merPrivateKey = PRIVATEKEY;
//        JSONObject reqJSONObject = (JSONObject) JSONObject.toJSON(payment);
//        String sign = PayUtil.buildMysign(reqJSONObject, merPrivateKey);
//        payment.setSign(sign);
//        reqJSONObject.put("sign", sign);
//        // 生成DES密钥
//        String desPriKey = new String(Base64.encodeBase64(DES.generatorDESKey().getBytes(CHARSET)), CHARSET);
//        String d = reqJSONObject.toJSONString();
//        String ed = SecurityUtil.encryptBySymmetry(d, desPriKey);
//        RSAEncrypt encrypt = new RSAEncrypt();
//        encrypt.loadPublicKey(pub);
//        // 公钥加密
//        String ek = new String(Base64.encodeBase64(RSAEncrypt.encrypt(encrypt.getPublicKey(), desPriKey.getBytes(CHARSET))), CHARSET);
//        JSONObject postData = new JSONObject();
//        postData.put("data", ed);
//        postData.put("key", ek);
//        return postData;
//    }
}
