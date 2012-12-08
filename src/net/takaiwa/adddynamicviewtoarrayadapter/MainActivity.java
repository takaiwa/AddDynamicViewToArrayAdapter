package net.takaiwa.adddynamicviewtoarrayadapter;

import java.util.ArrayList;
import java.util.List;

import net.takaiwa.adddynamicviewtoarrayadapter.customviews.CustomView;
import net.takaiwa.adddynamicviewtoarrayadapter.customviews.CustomView2;
import net.takaiwa.adddynamicviewtoarrayadapter.customviews.CustomView3;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<SeasonsItem> list = null;
    private ListAdapter list_adapter = null;
    private int mListItemWidth, mListItemHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // リストへ格納する名前の一覧取得
        String[] item_names = getResources().getStringArray(R.array.season_names);

        // リスト初期化
        list = new ArrayList<SeasonsItem>(item_names.length);

        // 格納
        for(String name : item_names) {
            SeasonsItem season_item = new SeasonsItem();
            season_item.setName(name);

            list.add(season_item);
        }

        // アダプター経由でリストViewへ設定
        list_adapter = new ListAdapter(this, list);
        ListView list_view = (ListView)findViewById(R.id.listView1);
        list_view.setAdapter(list_adapter);

        getListItemSize(list_adapter);
    }

    /**
     * ListView項目のサイズを取得
     * @param list_adapter ArrayAdapterを継承しているクラス
     */
    private void getListItemSize(ListAdapter list_adapter) {

        // 0:どの項目もサイズが同じという前提で一番最初のリスト項目を取得
        View list_item_view  = list_adapter.getView(0, null, (ListView)findViewById(R.id.listView1));
        list_item_view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        Log.v(TAG, "list view item width:" + list_item_view.getMeasuredWidth()
                    + " height:" + list_item_view.getMeasuredHeight());

        // 画面サイズを取得
        WindowManager windowmanager = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display disp = windowmanager.getDefaultDisplay();
        Log.v(TAG, "display width:" + disp.getWidth() + " height:" + disp.getHeight());

        // ListViewの項目の幅は表示されてる部分？だけなので、画面サイズを採用する
        mListItemWidth = disp.getWidth();
        // TextViewを除いた幅が欲しい場合
//        mListItemWidth = disp.getWidth() - list_item_view.getMeasuredWidth();
        mListItemHeight = list_item_view.getMeasuredHeight();
    }

    /**
     * リストビューの項目の値を格納するクラス
     * @author takaiwa
     *
     */
    private class SeasonsItem {
        private String name = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * ListViewとListのアダプタ
     * @author takaiwa
     *
     */
    private class ListAdapter extends ArrayAdapter<SeasonsItem> {

        private LayoutInflater mInflater;

        public ListAdapter(Context context, List<SeasonsItem> objects) {
            super(context, 0, objects);
            this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, final ViewGroup parent) {

            final SeasonsItem season_item = this.getItem(position);
            if(null == convertView) {
                // 行のXMLレイアウトを取得
                convertView = this.mInflater.inflate(R.layout.list_row, null);
            }

            if(null != season_item) {
                // 季節名の設定
                TextView text_view = (TextView)convertView.findViewById(R.id.textView1);
                text_view.setText(season_item.getName());

                final FrameLayout lyt_list_row =
                    (FrameLayout)convertView.findViewById(R.id.FrameLayout1);

                // リストクリックでViewを追加
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         *  2.x系で表示されない
                         */
//                        CustomView custom_view = new CustomView(MainActivity.this);
//                        lyt_list_row.addView(custom_view);

                        /**
                         *  setLayoutParamsを実行できるようWidthとHeightを渡す
                         */
                        CustomView2 custom_view = new CustomView2(MainActivity.this, mListItemWidth, mListItemHeight);
                        lyt_list_row.addView(custom_view);

                        /**
                         *  せっかくなのでアニメーションするサンプルも
                         */
//                        CustomView3 custom_view = new CustomView3(MainActivity.this, mListItemWidth, mListItemHeight);
//                        lyt_list_row.addView(custom_view);
//                        custom_view.startMyAnim();
                    }
                });
            }
            return convertView;
        }
    }
}
