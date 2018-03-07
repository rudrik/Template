package template.r3tech.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import javax.crypto.Cipher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import template.r3tech.com.model.ItemModel;
import template.r3tech.com.utils.Constant;
import template.r3tech.com.utils.DBHelper;

public class ContentDetailsActivity extends AppCompatActivity {


    @BindView(R.id.activity_content_details_tvContent)
    TextView tvContent;
    private List<ItemModel> lstList;
    private DBHelper dbHelper;
    int currentPosition;


    @OnClick(R.id.activity_content_details_btnNext)
    void nextContent() {
        tvContent.setText(lstList.get(++currentPosition).getItemContent().toString());

    }

    @OnClick(R.id.activity_content_details_btnPrevious)
    void previousContent() {
        tvContent.setText(lstList.get(--currentPosition).getItemContent().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        lstList = dbHelper.getContent();
        setContentView(R.layout.activity_content_details);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(Constant.getInstance().CURRENT_POSITION)) {
            currentPosition = getIntent().getIntExtra(Constant.getInstance().CURRENT_POSITION, 0);
        }
        tvContent.setText(lstList.get(currentPosition).getItemContent().toString());
    }
}
