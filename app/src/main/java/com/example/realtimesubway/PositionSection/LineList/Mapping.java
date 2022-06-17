package com.example.realtimesubway.PositionSection.LineList;

import com.example.realtimesubway.ArrivalSection.Data.Subway.RealtimePosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Mapping {
    public Map<String, String> sortedRealtimePositionMap;
    public Mapping(List<RealtimePosition> positionList){
        HashMap<String, String> dic = new HashMap<String, String>();
        for(int i=0; i<positionList.size(); i++){
            String key = positionList.get(i).getStatnId();
            String stationNmValue = positionList.get(i).getStatnNm();
            dic.put(key,stationNmValue);
        }

        // 맵 key 기준 정렬
        sortedRealtimePositionMap = new TreeMap<>(dic);
    }
}
