package com.example.realtimesubway.PositionSection;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.R;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog {
    private Context context;
    public int beforePosition = 0; // 몇전역
    public String trainNo, destination; // 지하철 번호, 목적지
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String storeTrainNo, storeLineNum, storeDestination;
    int storeBeforePosition;


    public CustomDialog(Context context){
        this.context = context;
    }



    public void callFunction(ArrayList<AllStationData> allstationData, List<PositionData> positionList, int position){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.subway_customdialog);

        TextView text_trainNo = dialog.findViewById(R.id.trainNo);
        TextView text_statnTnm = dialog.findViewById(R.id.statnTnm);
        TextView text_statnNm = dialog.findViewById(R.id.statnNm);
        TextView text_directAt = dialog.findViewById(R.id.directAt);
        TextView text_result_checkBox = dialog.findViewById(R.id.checkbox_result);
        final Button button = dialog.findViewById(R.id.ok_btn);
        final Spinner sp = dialog.findViewById(R.id.spinner);
        final CheckBox before1 = dialog.findViewById(R.id.before1);
        final CheckBox before2 = dialog.findViewById(R.id.before2);
        final CheckBox before3 = dialog.findViewById(R.id.before3);
        final Button btn_checkBox = dialog.findViewById(R.id.btn_checkbox);

    // 지하철 세부정보 출력
        for(int i = 0; i<positionList.size(); i++){
            PositionData plGet = positionList.get(i);
            if(plGet.getStatnNm().equals(allstationData.get(position).getStationName())){
                dialog.show();
                text_trainNo.setText(plGet.getTrainNo());
                text_statnTnm.setText(plGet.getStatnTnm() + "역");
                text_statnNm.setText(plGet.getStatnNm() + "역");
                trainNo = plGet.getTrainNo();
                if(plGet.getDirectAt().equals("1")){
                    text_directAt.setText("급행");
                } else {
                    text_directAt.setText("완행");
                }
            }
        }

    // 스피너에 들어갈 문자열 설정
        String[] arr = new String[allstationData.size()];
        for(int i=0; i<allstationData.size(); i++){
            String statnNm = allstationData.get(i).getStationName();
            arr[i] = statnNm;
        }

    // 스피너로 목적지 설정하기
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destination = sp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    // 체크박스로 옵션 설정
        btn_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                if(before1.isChecked() == true) {
                    result = before1.getText().toString() + "에서 알려드리겠습니다.";
                    beforePosition = 1;
                } else if(before2.isChecked() == true) {
                    result = before2.getText().toString() + "에서 알려드리겠습니다.";
                    beforePosition = 2;
                } else if(before3.isChecked() == true) {
                    result = before3.getText().toString() + "에서 알려드리겠습니다.";
                    beforePosition = 3;
                } else {
                    result = "1개의 옵션을 선택하여 주십시오.";
                }
                text_result_checkBox.setText(result);

                /*SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                // 저장해둔 값 불러오기
                storeTrainNo = sharedPreferences.getString("storeTrainNo","");
                storeLineNum = sharedPreferences.getString("storeLineNum","");
                storeDestination = sharedPreferences.getString("storeDestination","");
                storeBeforePosition = sharedPreferences.getInt("storeBeforePosition",0);

                storeTrainNo = trainNo;
                //storeLineNum = lineNum;
                storeDestination = destination;
                storeBeforePosition = beforePosition;
                //editor.putString("linNum",storeLineNum);
                editor.putString("trainNo",storeTrainNo);
                editor.putString("destination",storeDestination);
                editor.putInt("beforePosition", storeBeforePosition);*/

                Log.d("test", "onClick: " + storeTrainNo);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
