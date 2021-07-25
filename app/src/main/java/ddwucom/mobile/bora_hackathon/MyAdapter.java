package ddwucom.mobile.bora_hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;    //inflater 객체 생성 시 필요
    private int layout;     //adaterView 항목에 대한 layout
    private ArrayList<Board> myDataArrayList;  //원본 데이터 리스트
    private LayoutInflater layoutInflater;  //inflater 객체

    public MyAdapter(Context context, int layout, ArrayList<Board> myDataArrayList) {
        this.context = context;
        this.layout = layout;
        this.myDataArrayList = myDataArrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return myDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myDataArrayList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //리스트뷰의 position 위치를 저장해 onClick 내부에서 사용하기 위해 상수 선언
        final int pos = position;
        ViewHolder holder;

        if(convertView == null) {//전달받은 뷰가 미생성일 경우 inflater 사용해서 layout에 해당하는 view객체 만들기
            convertView = layoutInflater.inflate(layout, parent, false);
//          viewHolder 객체 생성해 각 멤버에 view 객체 준비
//          화면 요소 준비
            holder = new ViewHolder();
            holder.text = (TextView)convertView.findViewById(R.id.et_context);

//            생성한 viewHolder 객체 저장
            convertView.setTag(holder);
        } else {
//            convertView가 이미 존재하는 경우 getTag()사용해서 viewHolder객체 로딩
            holder = (ViewHolder)convertView.getTag();
        }
//      viewHolder 객체가 저장하고 있는 view 객체의 요소에 데이터 저장
//      화면과 데이터 결합
        holder.text.setText(myDataArrayList.get(pos).getText());

//        생성한 view 반환
        return convertView;
    }

    //    뷰 레이아웃의 내부 view를 멤버로 갖는 정적 클래스를 생성하여 반복사용 할 수 있도록 구성
    static class ViewHolder {
        TextView text;
    }
}
