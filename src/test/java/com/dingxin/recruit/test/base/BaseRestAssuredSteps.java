package com.dingxin.recruit.test.base;

/*import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;*/
import com.dingxin.recruit.test.config.OAuthConfig;
import com.dingxin.recruit.test.config.TestConfig;
import com.dingxin.recruit.test.repository.CustomerRepository;
import com.google.common.base.Strings;
import gherkin.deps.com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public abstract class BaseRestAssuredSteps {

    static final Logger logger = LoggerFactory.getLogger(BaseRestAssuredSteps.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public void prepareRecords()  throws JSONException{
        ;
    }

    protected Set<String> removeIds = new HashSet<>();

    public String url;
    public String apiPath;

    public HashMap<String, Map> records = new HashMap<>();
    public HashMap<String, Map> updateIgnoreFields = new HashMap<>();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestConfig testConfig;

    /*protected MongoDatabase getDatabase() {
        return null;
    }

    protected String getCurrentCollectionName() {
        return null;
    }*/

    static
    {
        OAuth.loginOAtuh();
    }
    protected final static String oathToken = OAuth.getHeaderToken();

    private String testerToken = null;

    public String getTesterToken(){
        if(StringUtils.isEmpty(this.testerToken)){
            logger.warn(">>>>>>>>>: there is no testerToken");
            return oathToken;
        }
        return this.testerToken;
    }

    public void initAuthCodeToRedis(String key, String value){
        ValueOperations<String, String> operations=redisTemplate.opsForValue();
        operations.set("auth_code_token:" + key, value);
    }

    /*protected void deleteCreatedRecords() {
        if(removeIds != null) {

            for(String id: removeIds) {
                DeleteResult result = delete(id);

                if(result == null) {
                    continue;
                }
            }
            removeIds.clear();
        }
    }*/

    public String suffix () {
        return "_API_TEST_" + UUID.randomUUID().toString();
    }

    public String suffix (String prefix) {
        return prefix + suffix ();
    }


    public void init(){
        try{
            prepareRecords();
            add(getNewOne());
        }catch (Exception err){

        }
    }

    public JSONObject getNewOne(){
        return new JSONObject();
    }

    public void delete(String table, Map<String, String> condition){
        customerRepository.delete(table, condition);
    }

    /*public void testAll(){
        Assert.assertTrue(all().size()>0);
    }

    public void testModify(){
        JSONObject one = getNewOne();

        Map old = add(one);

        //Assert.assertNotNull( old.get("createdDate") );
        //Assert.assertNotNull( old.get("createdBy") );

        for(Object key: old.keySet()){
            if("id".equals(key)) continue;
            if(key.toString().toLowerCase().indexOf("date")>=0) continue;
            if(key.toString().toLowerCase().indexOf("deleteflag")>=0) continue;
            if(key.toString().toLowerCase().indexOf("lastmodifiedby")>=0) continue;
            if(key.toString().toLowerCase().indexOf("createdby")>=0) continue;
            if(key.toString().toLowerCase().indexOf("version")>=0) continue;
            if(key.toString().toLowerCase().indexOf("events")>=0) continue;

            if( updateIgnoreFields.containsKey(key) ) continue;

            Object value = old.get(key);
            String valueType = null;
            if(value != null) {
                valueType = value.getClass().getTypeName().toLowerCase();
            }

            if( null != value && valueType.indexOf("boolean") >=0 ){
                old.put(key, !(boolean)value);
            }else if(  null != value && valueType.indexOf("string") >=0 ){
                old.put(key, key.toString() +"_UPDATED"+suffix());
            }else if(  null != value
                    && (valueType.indexOf("integer") >=0
                    || valueType.indexOf("long") >=0
                    || valueType.indexOf("double") >=0
                    || valueType.indexOf("float") >=0 ) ){
                old.put(key, Math.round(  Math.random() * 1000 ));
            }else{
                old.put(key, key.toString() +"_UPDATED"+suffix());
            }
        }

        old = beforeUpdate(old);

        Map updated = update(old);
        Assert.assertNotNull(updated);

        updated = one((String) old.get("id"));
        Assert.assertNotNull(updated);

        for(Object key: old.keySet()){

            if("id".equals(key)) continue;
            if(key.toString().toLowerCase().indexOf("date")>=0) continue;
            if(key.toString().toLowerCase().indexOf("deleteflag")>=0) continue;
            if(key.toString().toLowerCase().indexOf("createdby")>=0) continue;
            if(key.toString().toLowerCase().indexOf("lastmodifiedby")>=0) continue;
            if(key.toString().toLowerCase().indexOf("version")>=0) continue;
            if(key.toString().toLowerCase().indexOf("events")>=0) continue;
            if(old.get(key)==null) continue;

            logger.debug("BaseRestAssuredSteps::testModify::checking: " + key);
            if (updated.get(key) == null) {
                logger.error("field of {} is null", key);
                continue;
            }

            String valueType = updated.get(key).getClass().getTypeName().toLowerCase();
            if( valueType.indexOf("double")>=0
                    ||  valueType.indexOf("integer")>=0
                    ||  valueType.indexOf("float")>=0
                    ||  valueType.indexOf("long")>=0  ){
                Assert.assertEquals(
                        Math.round(Float.valueOf(updated.get(key).toString())),
                        Math.round(Float.valueOf(old.get(key).toString())
                        ));
            }else{
                Assert.assertEquals( updated.get(key), old.get(key) );
            }
        }

        //Assert.assertNotNull( updated.get("lastModifiedDate") );
        //Assert.assertNotNull( updated.get("lastModifiedBy") );
    }

    protected Map beforeUpdate(Map old) {
        return old;
    }

    public void testDeactivate(){
        JSONObject one = getNewOne();
        Map ret = add(one);
        Assert.assertNotNull(ret);
        String id = (String) ret.get("id");
        ret = deactivate(id);
        Assert.assertNotNull(ret);
        Assert.assertEquals(false, ret.get("active"));

        ret = one(id);
        Assert.assertNotNull(ret);
        Assert.assertEquals(false, ret.get("active"));

    }

    public void testAddDuplicate(String fieldNames) throws JSONException {
        JSONObject one = getNewOne();
        Map old = add(one);
        Assert.assertNotNull(old);

        one = getNewOne();

        for(String fieldName: fieldNames.split(",")) {
            one.put( fieldName, old.get(fieldName) );
        }

        Map ret = add(one);
        Assert.assertNull(ret);
    }

    public void testUpdateDuplicate(String fieldNames) throws JSONException {
        JSONObject one = getNewOne();
        Map old = add(one);
        Assert.assertNotNull(old);

        one = getNewOne();
        Map ret = add(one);
        Assert.assertNotNull(ret);

        for( String fieldName: fieldNames.split(",") ) {
            ret.put(fieldName, old.get(fieldName));
        }

        ret = update(ret);
        Assert.assertNull(ret);
    }*/

    /*protected DeleteResult delete(String id) {


        String colName = getCurrentCollectionName();

        if(colName == null) {
            logger.warn("getCurrentCollectionName() is not implemented so that cannot delete data.");
            return null;
        }

        return delete(id, colName);
    }

    protected Bson getBson(String id) {
        return eq("_id", id);
    }

    protected DeleteResult delete(String id, String collectionName) {
        if(id == null) {
            return null;
        }

        MongoDatabase database = getDatabase();

        if(database == null) {
            logger.warn("getDatabase() is not implemented so that cannot delete data.");
            return null;
        }

        MongoCollection<Document> collection = database.getCollection(collectionName);

        logger.info("delete one record from collection {}, id is {}", collection, id);
        DeleteResult result = null;

        try{
            result = collection.deleteOne(getBson(id));
            if(result.getDeletedCount()==0){
                result = collection.deleteOne(new Document("_id", new ObjectId(id)));
            }
        }catch (Exception err){
            logger.error(err.getLocalizedMessage());
            err.printStackTrace();
        }

        return result;
    }*/

    public Map add(JSONObject one){
        Map result = (Map)doPost(apiPath, one.toString());


        if(result == null) {
            return result;
        }

        Object entityId = result.get("id");

        if(entityId != null && entityId instanceof String) {
            removeIds.add((String)entityId);
        }
        return result;
    }

    public List<Map> all(){
        return (List<Map>)doGet(apiPath);
    }

    public Map one(String id){
        return (Map)doGet(apiPath + "/" + id);
    }

    public Map deactivate(String id){
        return (Map)doDelete(apiPath + "/" + id);
    }

    public Map update(Object one){
        return (Map)doPut(apiPath , new JSONObject((Map)one).toString());
    }

    public Object doGet(String path){
        return doGet(path, "data");
    }

    public Object doGet(String path, String jsonPath){
        logger.debug(String.format("BaseRestAssuredSteps::doGet:\n%s",
                path));
        Response rsp = given().header("Authorization", this.oathToken)
                .contentType("application/json").when().get(path)
                .then().assertThat().statusCode(200)
                .extract().response();
        Object result = rsp.path(jsonPath);
        if( null == result ){
            logger.error( rsp.asString() );
        }
        return result;
    }

    public Object doPost(String path, String body){
        return doPost(path, body, "data");
    }

    public Object doPost(String path, String body, String jsonPath){

        logger.debug(String.format("BaseRestAssuredSteps::doPost:\n%s\n%s",
                path, body));

        Response rsp =  given().header("Authorization", this.oathToken)
                .contentType("application/json").body(body).when().post(path)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.debug("retrieved response: {}", rsp.asString());

        Object result = rsp.path(jsonPath);

        return result;
    }

    public Object doPost(String path, String body, String jsonPath, String token){

        logger.debug(String.format("BaseRestAssuredSteps::doPost:\n%s\n%s",
                path, body));

        Response rsp =  given().header("Authorization", token)
                .contentType("application/json").body(body).when().post(path)
                .then().assertThat().statusCode(200)
                .extract().response();

        logger.debug("retrieved response: {}", rsp.asString());

        Object result = rsp.path(jsonPath);

        return result;
    }

    public String initAndLogin(JSONObject userObject){

        int code = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", userObject.toString(), "code");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", OAuthConfig.getSingleton().getoAuthBasicAuth());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        try {
            map.add("username", (String) userObject.get("username"));
            map.add("password", (String) userObject.get("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map.add("recType", OAuthConfig.getSingleton().getRecType());
        map.add("authCode", OAuthConfig.getSingleton().getAuthCode());
        map.add("codeCipher", OAuthConfig.getSingleton().getCodeCipher());
        HttpEntity<MultiValueMap<String, String>> httpEnity = new HttpEntity<>(map, headers);
        ResponseEntity<HashMap> result = null;
        try {
            result = restTemplate.exchange(OAuthConfig.getSingleton().getLoginURL(), HttpMethod.POST, httpEnity, HashMap.class);
            logger.info(">>>>>>>>>>" + result.getBody().get("access_token").toString());
        }catch (org.springframework.web.client.HttpClientErrorException ex){
            logger.warn(">>>>>>>>>>: 400 return");
            //Assert.assertTrue(ex.getRawStatusCode() == 400);
        }

        if (null != result && result.hasBody()) {
            HashMap<String, Object> response = result.getBody();
            this.testerToken = response.get("access_token").toString();
            return this.testerToken;
        }
        return "";
    }

    public JSONObject getNewOne(String username, String password) {
        try {
            JSONObject applicant = new JSONObject();
            applicant.put("userIdCard", "IDCARD_API_TEST_ACCOUNT");
            applicant.put("userMbphone", "MOBILE_API_TEST_ACCOUNT");
            applicant.put("email", "EMAIL_API_TEST_ACCOUNT@qq.com");
            applicant.put("username", username);
            applicant.put("recType", "1");
            applicant.put("password", password);
            applicant.put("oidValidation", suffix("VALIDATION"));
            applicant.put("answer", suffix("ANSWER"));
            applicant.put("authCode", "BPIF");
            applicant.put("codeCipher", "c65bf5608aeea9640c4a906c90638b50");
            return applicant;
        }catch (JSONException jsonEx){
            return null;
        }
    }

    public Object doPut(String path, String body){
        return doPut(path, body, "data");
    }

    public Object doPut(String path, String body, String jsonPath){

        logger.debug(String.format("BaseRestAssuredSteps::doPut:\n%s\n%s",
                path, body));

        Response rsp = given().header("Authorization", this.oathToken)
                .contentType("application/json").body(body).when().put(path)
                .then().assertThat().statusCode(200)
                .extract().response();

        Object result = rsp.path(jsonPath);
        if( null == result ){
            logger.error( rsp.asString() );
        }
        return result;
    }

    public Object doDelete(String path){
        return doDelete(path, "data");
    }


    public Object doDelete(String path, String jsonPath){

        logger.debug(String.format("BaseRestAssuredSteps::doDelete:\n%s", path));

        Response rsp =   given().header("Authorization", this.oathToken)
                .delete(path)
                .then().assertThat().statusCode(200)
                .extract().response();

        Object result = rsp.path(jsonPath);
        if( null == result ){
            logger.error( rsp.asString() );
        }
        return result;
    }


    public Object doDelete(String path, String body, String jsonPath){

        logger.debug(String.format("BaseRestAssuredSteps::doDelete:\n%s\n%s",
                path, body));

        Response rsp =   given().header("Authorization", this.oathToken)
                .contentType("application/json").body(body).when().delete(path)
                .then().assertThat().statusCode(200)
                .extract().response();

        Object result = rsp.path(jsonPath);
        if( null == result ){
            logger.error( rsp.asString() );
        }
        return result;
    }
}
