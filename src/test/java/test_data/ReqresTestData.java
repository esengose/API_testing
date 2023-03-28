package test_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresTestData {


    public Map<String, Object> expectedDataMethod(String name,String job,String id,String createdAt) {
        Map<String,Object>expectedData = new HashMap<>();
        expectedData.put("name",name);
        expectedData.put("job",job);
        if (id != null){
        expectedData.put("id",id);}
        if(createdAt!=null){
        expectedData.put("createdAt",createdAt);}
        return expectedData;
    }
}
