package com.example.realtimesubway.PositionSection;

import android.app.AlarmManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CustomDialog {
    private Context context;
    public CustomDialog(Context context){
        this.context = context;
    }


    public void callFunction(ArrayList<AllStationData> allstationData, List<PositionData> positionList, int position){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.subway_customdialog);

        TextView text_trainNo = (TextView)dialog.findViewById(R.id.trainNo);
        TextView text_statnTnm = (TextView)dialog.findViewById(R.id.statnTnm);
        TextView text_statnNm = (TextView)dialog.findViewById(R.id.statnNm);
        TextView text_directAt = (TextView)dialog.findViewById(R.id.directAt);
        TextView text_result_checkBox = (TextView)dialog.findViewById(R.id.checkbox_result);
        final Button button = (Button)dialog.findViewById(R.id.ok_btn);
        final Spinner sp = (Spinner)dialog.findViewById(R.id.spinner);
        final CheckBox before1 = (CheckBox)dialog.findViewById(R.id.before1);
        final CheckBox before2 = (CheckBox)dialog.findViewById(R.id.before2);
        final CheckBox before3 = (CheckBox)dialog.findViewById(R.id.before3);
        final Button btn_checkBox = (Button) dialog.findViewById(R.id.btn_checkbox);

    // 지하철 세부정보 출력
        for(int i = 0; i<positionList.size(); i++){
            if(positionList.get(i).getStatnNm().equals(allstationData.get(position).getStationName())){
                dialog.show();
                text_trainNo.setText(positionList.get(i).getTrainNo());
                text_statnTnm.setText(positionList.get(i).getStatnTnm() + "역");
                text_statnNm.setText(positionList.get(i).getStatnNm() + "역");
                if(positionList.get(i).getDirectAt().equals("1")){
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
                } else if(before2.isChecked() == true) {
                    result = before2.getText().toString() + "에서 알려드리겠습니다.";
                } else if(before3.isChecked() == true) {
                    result = before3.getText().toString() + "에서 알려드리겠습니다.";
                } else {
                    result = "1개의 옵션을 선택하여 주십시오.";
                }
                text_result_checkBox.setText(result);
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
